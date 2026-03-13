package com.example.kinoxp.service;


import com.example.kinoxp.exceptions.NotFoundException;
import com.example.kinoxp.model.Reservation;
import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.repository.FeeTypeRepository;
import com.example.kinoxp.repository.ReservationRepository;
import com.example.kinoxp.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final FeeTypeRepository feeRepository;
    private Map<String, Double> listOfCalculations = new HashMap<>();

    public ReservationService(ReservationRepository reservationRepository, TicketTypeRepository ticketTypeRepository, FeeTypeRepository feeTypeRepository) {
        this.reservationRepository = reservationRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.feeRepository = feeTypeRepository;
    }

    public Reservation createReservation(Reservation reservation) {

        reservation.setTotalPrice(priceCalculationTotal(reservation));
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found with id: " + id));
    }

    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Ticket> getAllTicketsForShowing(Long showingId) {
        List<Reservation> reservations = reservationRepository.findAllByShowing_Id(showingId);
        List<Ticket> tickets = new ArrayList<>();
        reservations.forEach(reservation -> tickets.addAll(reservation.getTickets()));
        return tickets;
    }


    public double priceCalculationTotal(Reservation reservation) {
        double total = 0;
        int numberOfTickets = reservation.getTickets().size();

        for (Ticket ticket : reservation.getTickets()) {
            total += ticket.getTicketType().getPrice();
        }

        if (reservation.getShowing().getMovie().getDuration() > 170) {
            total += (feeRepository.findById("hel_aften_film").get().getPrice() * numberOfTickets);
        }
        if (reservation.getShowing().isThreeDimensional()) {
            total += (feeRepository.findById("3d").get().getPrice() * numberOfTickets);
        }

        if (numberOfTickets < 5) {
            total += feeRepository.findById("reservationsgebyr").get().getPrice();
        } else if (numberOfTickets > 10) {
            double discount = total * 0.07;
            total -= discount;
        }
        return total;

    }
}
