package com.example.kinoxp.service;


import com.example.kinoxp.exceptions.NotFoundException;
import com.example.kinoxp.model.Reservation;
import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.model.TicketType;
import com.example.kinoxp.repository.ReservationRepository;
import com.example.kinoxp.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TicketTypeRepository ticketTypeRepository;

    public ReservationService(ReservationRepository reservationRepository, TicketTypeRepository ticketTypeRepository) {
        this.reservationRepository = reservationRepository;
        this.ticketTypeRepository = ticketTypeRepository;
    }

    public Reservation createReservation(Reservation reservation) {
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

    public List<Reservation> getAllReservationsForShowing(Long showingId) {
        return reservationRepository.findByShowing_Id(showingId);
    }


    private double priceCalulationTotal(TicketType ticketType, Ticket ticket, Reservation reservation) {
        double total = 0;
        Optional<TicketType> ticketType1 = ticketTypeRepository.findById(ticketType.getTicketType());
        for (Ticket ticket1: reservation.getTickets()) {
            total += ticketType1.get().getPrice();
        }

        total += feeHandler(reservation);
        return total;

    }
    private double feeHandler(Reservation reservation) {
        double reservationfee = 0;
        if (reservation.getTickets().size() < 5) {
            return 50;
        }
        return 0;


    }
   private double groupDiscount(Reservation reservation, TicketType ticketType) {
       if (reservation.getTickets() == null) {
           return 0;
       }
       int numberOfTickets = reservation.getTickets().size();
       if (numberOfTickets > 10) {
           double totalTicketPrice = 0;

           for (Ticket ticket : reservation.getTickets()) {
               totalTicketPrice += ticketType.getPrice();
           }
           return totalTicketPrice * 0.07;
       }
       return  0;
   }
 }
