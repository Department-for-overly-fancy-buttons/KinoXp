package com.example.kinoxp.repository;

import com.example.kinoxp.KinoXpApplication;
import com.example.kinoxp.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
        },
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = ReservationRepository.class),
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {ShowingRepository.class, CategoryRepository.class, MovieRepository.class, TheaterRepository.class, TicketRepository.class, UserRepository.class}))
@ContextConfiguration(classes = KinoXpApplication.class)
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    private Movie testMovie;
    private Theater testTheater;
    private Showing testShowing;
    private Reservation testReservation;

    @BeforeEach
    public void setup(){
        testMovie = new Movie("Predator: Killer of Killers", "Three of the fiercest warriors in human history become prey to the ultimate killer of killers.", 17, 85.0, List.of(new Category("Sci-fi"), new Category("Action")));
        testTheater = new Theater("Sal 1", 10, 20);
        testShowing = new Showing(testMovie, testTheater, LocalDateTime.of(2026, 3, 8, 18, 15), false);
        testReservation = new Reservation(testShowing, "Emilie.K.Meyer@gmail.com", "+45 90348172", "Emilie Kalmer", "Meyer");
        reservationRepository.save(testReservation);
    }

    @AfterEach
    public void tearDown(){
        reservationRepository.delete(testReservation);
    }

    @Test
    public void givenReservation_whenSaved_thenCanBeFoundById(){

        Reservation foundReservation = reservationRepository.findById(testReservation.getId()).orElse(null);

        assertNotNull(foundReservation);

        Showing foundShowing = foundReservation.getShowing();
        Movie foundMovie = foundShowing.getMovie();
        Theater foundTheater = foundShowing.getTheater();

        assertNotNull(foundMovie);
        assertTrue(foundMovie.getTitle().equalsIgnoreCase(testMovie.getTitle()));
        assertTrue(foundMovie.getDescription().equalsIgnoreCase(testMovie.getDescription()));
        assertEquals(testMovie.getPgRating(), foundMovie.getPgRating());
        assertEquals(testMovie.getDuration(), foundMovie.getDuration());
        assertTrue(foundMovie.getCategories().containsAll(testMovie.getCategories()));

        assertNotNull(foundTheater);
        assertTrue(foundTheater.getTheaterName().equalsIgnoreCase(testTheater.getTheaterName()));
        assertEquals(testTheater.getNumberOfRows(), foundTheater.getNumberOfRows());
        assertEquals(testTheater.getSeatsPerRow(), foundTheater.getSeatsPerRow());

        assertNotNull(foundShowing);
        assertTrue(foundShowing.getStartTime().isEqual(LocalDateTime.of(2026, 3, 8, 18, 15)));
        assertEquals(testShowing.isThreeDimensional(), foundShowing.isThreeDimensional());

        assertTrue(foundReservation.getEmail().equalsIgnoreCase(testReservation.getEmail()));
        assertEquals(testReservation.getPhoneNumber(), foundReservation.getPhoneNumber());
        assertTrue(foundReservation.getFirstName().equalsIgnoreCase(testReservation.getFirstName()));
        assertTrue(foundReservation.getLastName().equalsIgnoreCase(testReservation.getLastName()));
    }

    @Test
    public void givenReservation_whenUpdated_thenCanBeFoundWithUpdatedData(){
        testReservation.setEmail("EmilieKSJ902@gmail.com");
        Reservation foundReservation = reservationRepository.findById(testReservation.getId()).orElse(null);

        assertNotNull(foundReservation);
        assertEquals(testReservation.getEmail(),foundReservation.getEmail());
    }

    @Test
    public void givenReservationId_whenDeleted_thenCannotBeFoundById(){
        reservationRepository.deleteById(testReservation.getId());
        Reservation foundReservation = reservationRepository.findById(testReservation.getId()).orElse(null);
        assertNull(foundReservation);
    }

    //findByShowing_Id(long showingId);
//    @Test
//    public void GivenShowingID_WhenSaved_ThenCanFindRelevantReservations(){
//
//        //Saving additional data
//        Reservation additionalTestReservationOne = new Reservation(testShowing, "KajHajMedDig@icloud.com", "+45 44817504", "Kaj", "Sjøisted");
//        Reservation additionalTestReservationTwo = new Reservation(testShowing, "Coffe-addict-2009189@gmail.com", "+45 74872314", "Julie", "Sandre");
//        reservationRepository.save(additionalTestReservationOne);
//        reservationRepository.save(additionalTestReservationTwo);
//
//        List<Reservation> foundReservations = reservationRepository.findByShowing_Id(testShowing.getId());
//
//        assertNotNull(foundReservations);
//
//        for (Reservation foundReservation : foundReservations) {
//            assertNotNull(foundReservation);
//
//            Showing foundShowing = foundReservation.getShowing();
//            Movie foundMovie = foundShowing.getMovie();
//            Theater foundTheater = foundShowing.getTheater();
//
//            assertNotNull(foundMovie);
//            assertTrue(foundMovie.getTitle().equalsIgnoreCase(testMovie.getTitle()));
//            assertTrue(foundMovie.getDescription().equalsIgnoreCase(testMovie.getDescription()));
//            assertEquals(testMovie.getPgRating(), foundMovie.getPgRating());
//            assertEquals(testMovie.getDuration(), foundMovie.getDuration());
//            assertTrue(foundMovie.getCategories().containsAll(testMovie.getCategories()));
//
//            assertNotNull(foundTheater);
//            assertTrue(foundTheater.getTheaterName().equalsIgnoreCase(testTheater.getTheaterName()));
//            assertEquals(testTheater.getNumberOfRows(), foundTheater.getNumberOfRows());
//            assertEquals(testTheater.getSeatsPerRow(), foundTheater.getSeatsPerRow());
//
//            assertNotNull(foundShowing);
//            assertTrue(foundShowing.getStartTime().isEqual(LocalDateTime.of(2026, 3, 8, 18, 15)));
//            assertEquals(testShowing.isThreeDimensional(), foundShowing.isThreeDimensional());
//
//            assertTrue(foundReservation.getEmail().equalsIgnoreCase(testReservation.getEmail()));
//            assertEquals(testReservation.getPhoneNumber(), foundReservation.getPhoneNumber());
//            assertTrue(foundReservation.getFirstName().equalsIgnoreCase(testReservation.getFirstName()));
//            assertTrue(foundReservation.getLastName().equalsIgnoreCase(testReservation.getLastName()));
//        }
//        }

}