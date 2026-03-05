package com.example.kinoxp.controller;

import com.example.kinoxp.model.Movie;
import com.example.kinoxp.model.Reservation;
import com.example.kinoxp.service.MovieService;
import com.example.kinoxp.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")

public class ReservationController implements ReservationControllerInterface{

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService){

        this.reservationService = reservationService;

    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations(){

        return ResponseEntity.ok(reservationService.getAllReservations());
    }

}
