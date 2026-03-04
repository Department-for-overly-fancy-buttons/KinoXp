package com.example.kinoxp.repository;

import com.example.kinoxp.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowingRepository extends JpaRepository<Showing,Long> {
}
