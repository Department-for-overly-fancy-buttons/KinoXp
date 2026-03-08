package com.example.kinoxp.repository;

import com.example.kinoxp.KinoXpApplication;
import com.example.kinoxp.model.Category;
import com.example.kinoxp.model.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
        },
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = MovieRepository.class),
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {ShowingRepository.class, CategoryRepository.class, ReservationRepository.class, TheaterRepository.class, TicketRepository.class, UserRepository.class}))
@ContextConfiguration(classes = KinoXpApplication.class)
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    private Movie testMovie;

    @BeforeEach
    public void setup(){
        testMovie = new Movie("Predator: Killer of Killers", "Three of the fiercest warriors in human history become prey to the ultimate killer of killers.", 17, 85.0, List.of(new Category("Sci-fi"), new Category("Action")));
        movieRepository.save(testMovie);
    }

    @AfterEach
    public void tearDown(){
        movieRepository.delete(testMovie);
    }

    @Test
    public void givenMovie_whenSaved_thenCanBeFoundById(){

        Movie foundMovie = movieRepository.findById(testMovie.getId()).orElse(null);

        assertNotNull(foundMovie);
        assertTrue(foundMovie.getTitle().equalsIgnoreCase(testMovie.getTitle()));
        assertTrue(foundMovie.getDescription().equalsIgnoreCase(testMovie.getDescription()));
        assertEquals(testMovie.getPgRating(), foundMovie.getPgRating());
        assertEquals(testMovie.getDuration(), foundMovie.getDuration());
        assertTrue(foundMovie.getCategories().containsAll(testMovie.getCategories()));
    }

    @Test
    public void givenMovie_whenUpdated_thenCanBeFoundWithUpdatedData(){
        testMovie.setTitle("Predator: The Killer of Killers");
        Movie foundMovie = movieRepository.findById(testMovie.getId()).orElse(null);

        assertNotNull(foundMovie);
        assertTrue(foundMovie.getTitle().equalsIgnoreCase(testMovie.getTitle()));
    }

    @Test
    public void givenMovieId_whenDeleted_thenCannotBeFoundById(){
        movieRepository.deleteById(testMovie.getId());
        Movie foundMovie = movieRepository.findById(testMovie.getId()).orElse(null);
        assertNull(foundMovie);
    }

}