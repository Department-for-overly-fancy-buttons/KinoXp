package com.example.kinoxp.repository;

import com.example.kinoxp.model.Category;
import com.example.kinoxp.model.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketTypeRepository extends JpaRepository<TicketType, String> {


}
