//package com.example.kinoxp.repository;
//
//import com.example.kinoxp.KinoXpApplication;
//import com.example.kinoxp.model.Category;
//import com.example.kinoxp.model.Movie;
//import com.example.kinoxp.model.Showing;
//import com.example.kinoxp.model.Theater;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest(properties = {
//        "spring.datasource.url=jdbc:h2:mem:testdb",
//        "spring.jpa.hibernate.ddl-auto=create-drop"
//        },
//        includeFilters = @ComponentScan.Filter(
//                type = FilterType.ASSIGNABLE_TYPE,
//                classes = ShowingRepository.class),
//        excludeFilters = @ComponentScan.Filter(
//                type = FilterType.ASSIGNABLE_TYPE,
//                classes = {MovieRepository.class, CategoryRepository.class, ReservationRepository.class, TheaterRepository.class, TicketRepository.class, UserRepository.class}))
//@ContextConfiguration(classes = KinoXpApplication.class)
//public class ShowingRepositoryTest {
//
//    @Autowired
//    private ShowingRepository showingRepository;
//
//    private Movie testMovie;
//    private Theater testTheater;
//    private Showing testShowing;
//
//    @BeforeEach
//    public void setup(){
//        testMovie = new Movie("Predator: Killer of Killers", "Three of the fiercest warriors in human history become prey to the ultimate killer of killers.", 17, 85.0, List.of(new Category("Sci-fi"), new Category("Action")));
//        testTheater = new Theater("Sal 1", 10, 20);
//        testShowing = new Showing(testMovie, testTheater, LocalDateTime.of(2026, 3, 8, 18, 15), false);
//        showingRepository.save(testShowing);
//    }
//
//    @AfterEach
//    public void tearDown(){
//        showingRepository.delete(testShowing);
//    }
//
//    @Test
//    public void givenShowing_whenSaved_thenCanBeFoundById(){
//
//        Showing foundShowing = showingRepository.findById(testShowing.getId()).orElse(null);
//
//        assertNotNull(foundShowing);
//
//        Movie foundMovie = foundShowing.getMovie();
//        Theater foundTheater = foundShowing.getTheater();
//
//        assertNotNull(foundMovie);
//        assertTrue(foundMovie.getTitle().equalsIgnoreCase(testMovie.getTitle()));
//        assertTrue(foundMovie.getDescription().equalsIgnoreCase(testMovie.getDescription()));
//        assertEquals(testMovie.getPgRating(), foundMovie.getPgRating());
//        assertEquals(testMovie.getDuration(), foundMovie.getDuration());
//        assertTrue(foundMovie.getCategories().containsAll(testMovie.getCategories()));
//
//        assertNotNull(foundTheater);
//        assertTrue(foundTheater.getTheaterName().equalsIgnoreCase(testTheater.getTheaterName()));
//        assertEquals(testTheater.getNumberOfRows(), foundTheater.getNumberOfRows());
//        assertEquals(testTheater.getSeatsPerRow(), foundTheater.getSeatsPerRow());
//
//        assertTrue(foundShowing.getStartTime().isEqual(LocalDateTime.of(2026, 3, 8, 18, 15)));
//        assertEquals(testShowing.isThreeDimensional(), foundShowing.isThreeDimensional());
//    }
//
//    @Test
//    public void givenShowing_whenUpdated_thenCanBeFoundWithUpdatedData(){
//        testShowing.setStartTime(LocalDateTime.of(2025, 10, 17, 21, 10));
//        Showing foundShowing = showingRepository.findById(testShowing.getId()).orElse(null);
//
//        assertNotNull(foundShowing);
//        assertTrue(foundShowing.getStartTime().isEqual(LocalDateTime.of(2025, 10, 17, 21, 10)));
//    }
//
//    @Test
//    public void givenShowingId_whenDeleted_thenCannotBeFoundById(){
//        showingRepository.deleteById(testShowing.getId());
//        Showing foundShowing = showingRepository.findById(testShowing.getId()).orElse(null);
//        assertNull(foundShowing);
//    }
//
//}