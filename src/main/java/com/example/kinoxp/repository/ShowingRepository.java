package com.example.kinoxp.repository;

import com.example.kinoxp.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Long> {
    List<Showing> findByTheater_IdOrderByStartTimeAsc(long theaterId);

    List<Showing> findByMovie_Id(long movieId);

    List<Showing> findAllByStartTimeGreaterThanEqualOrderByStartTime(LocalDateTime time);
}
