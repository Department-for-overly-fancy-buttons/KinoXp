package com.example.kinoxp.reservation;


import com.example.kinoxp.exceptions.NotFoundException;
import com.example.kinoxp.showing.Showing;
import com.example.kinoxp.showing.ShowingService;
import com.example.kinoxp.ticket.Ticket;
import com.example.kinoxp.ticket.FeeTypeServiceInterface;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final FeeTypeServiceInterface feeService;
    private final ShowingService showingService;

    public ReservationService(ReservationRepository reservationRepository, FeeTypeServiceInterface feeTypeService, ShowingService showingService) {
        this.reservationRepository = reservationRepository;
        this.feeService = feeTypeService;
        this.showingService = showingService;
    }

    public Reservation createReservation(CreateReservationRequest reservationRequest) {
        Showing foundShowing = showingService.getShowingById(reservationRequest.showingId());
        Reservation reservation = ReservationMapper.toReservation(reservationRequest, foundShowing);
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
            total += (feeService.getFeeById("hel_aften_film").getPrice() * numberOfTickets);
        }
        if (reservation.getShowing().isThreeDimensional()) {
            total += (feeService.getFeeById("3d").getPrice() * numberOfTickets);
        }

        if (numberOfTickets < 5) {
            total += feeService.getFeeById("reservationsgebyr").getPrice();
        } else if (numberOfTickets > 10) {
            double discount = total * 0.07;
            total -= discount;
        }
        return total;

    }
}
