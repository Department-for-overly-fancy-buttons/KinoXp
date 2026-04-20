package com.example.kinoxp.theater;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TheaterRepository extends JpaRepository<Theater,Long> {

}
