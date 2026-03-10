package com.example.kinoxp.service;


import com.example.kinoxp.exceptions.NotFoundException;
import com.example.kinoxp.model.Reservation;
import com.example.kinoxp.model.Ticket;
import com.example.kinoxp.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
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

    private double priceCalulationTotal(Ticket ticket) {
        double price = 0.0;
        return 0.0;

    }
    private double feeHandler() {
        double reservationfee = 50;
        return 0.0;
    }

}
