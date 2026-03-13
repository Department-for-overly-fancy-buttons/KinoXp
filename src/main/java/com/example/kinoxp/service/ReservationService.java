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

        for (Ticket ticket : reservation.getTickets()) {
            total += ticket.getTicketType().getPrice();
            System.out.println("Ticket - " + ticket.getSeatLabel() + " - __________ " + ticket.getTicketType().getPrice() + "kr.");
            if (reservation.getShowing().getMovie().getDuration() > 170) {
                total += feeRepository.findById("hel_aften_film").get().getPrice();
                System.out.println("HelAftens film gebyr: __________ 30kr. \nSubtotal __________ " + total + "kr.");
            }
            if (reservation.getShowing().isThreeDimensional()) {
                total += feeRepository.findById("3d").get().getPrice();
                System.out.println("3D gebyr: __________ " + feeRepository.findById("3d").get().getPrice() + "kr. \nSubtotal __________ " + total + "kr.");
            }
        }
        System.out.println("Subtotal __________ " + total + "kr.");

        double fee = feeHandler(reservation);
        double discount = groupDiscount(reservation, total);

//        System.out.println("Reservation gebyr:  __________ " + fee + "kr. \nSubtotal __________ " + (total + fee) + "kr.");
        System.out.println("Reservation gebyr eller Rabat:  __________" + discount + "kr. \nSubtotal __________ " + (total + discount) + "kr.");
        System.out.println("Total __________ " + (total + discount) + "kr.");
        return total + discount;

    }
    private double feeHandler(Reservation reservation) {
        double reservationfee = 0;
        if (reservation.getTickets().size() < 5) {
            return feeRepository.findById("reservationsgebyr").get().getPrice();
        }
        return 0;

    }
   private double groupDiscount(Reservation reservation, double subtotal) {
       if (reservation.getTickets() == null) {
           return 0;
       }
       int numberOfTickets = reservation.getTickets().size();
       if (numberOfTickets > 10) {
//           double totalTicketPrice = 0;
//
//           for (Ticket ticket : reservation.getTickets()) {
//               totalTicketPrice += ticket.getTicketType().getPrice();
//           }
           return -(subtotal * 0.07);
       }else if(reservation.getTickets().size() < 5){
           return feeRepository.findById("reservationsgebyr").get().getPrice();
       }
       return  0;
   }
 }
