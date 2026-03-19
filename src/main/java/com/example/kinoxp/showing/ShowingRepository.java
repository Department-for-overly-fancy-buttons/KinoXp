package com.example.kinoxp.showing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Long> {
    List<Showing> findByTheater_IdAndStartTimeAfterOrderByStartTime(long theaterId, LocalDateTime time);

    List<Showing> findByMovie_Id(long movieId);

    List<Showing> findAllByStartTimeAfterOrderByStartTime(LocalDateTime time);
}
