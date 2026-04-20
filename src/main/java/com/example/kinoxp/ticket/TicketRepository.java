package com.example.kinoxp.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface TicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> findByReservationId(long reservationId);

}
