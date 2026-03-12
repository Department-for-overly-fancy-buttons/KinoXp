package com.example.kinoxp.repository;

import com.example.kinoxp.KinoXpApplication;
import com.example.kinoxp.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
}
        )
//@ContextConfiguration(classes = KinoXpApplication.class)
public class TicketRepositoryTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    private TicketRepository ticketRepository;

    private Movie testMovie;
    private Theater testTheater;
    private Showing testShowing;
    private Reservation testReservation;
    private TicketType testTicketType;
    private Ticket testTicket;

    @Test
    public void testBeans(){
        var beans = context.getBeanDefinitionNames();
        for(String bean : beans){
            System.out.println(bean);
        }
    }

    @BeforeEach
    public void setup(){
        testMovie = new Movie("Predator: Killer of Killers", "Three of the fiercest warriors in human history become prey to the ultimate killer of killers.", 17, 85.0, List.of(new Category("Sci-fi"), new Category("Action")));
        testTheater = new Theater("Sal 1", 10, 20, "Kjønehavn");
        testShowing = new Showing(testMovie, testTheater, LocalDateTime.of(2026, 3, 8, 18, 15), false);
        testReservation = new Reservation(testShowing, "Emilie.K.Meyer@gmail.com", "+45 90348172", "Emilie Kalmer", "Meyer");
        testTicketType = new TicketType("Royal", 400);
        testTicket = new Ticket(testReservation, 3, 2, testTicketType);
        ticketRepository.save(testTicket);
    }

    @AfterEach
    public void tearDown(){
        ticketRepository.delete(testTicket);
    }

    @Test
    public void givenTicket_whenSaved_thenCanBeFoundById(){

        Ticket foundTicket = ticketRepository.findById(testTicket.getId()).orElse(null);

        assertNotNull(foundTicket);

        TicketType foundTicketType = foundTicket.getTicketType();
        Reservation foundReservation = foundTicket.getReservation();
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

        assertNotNull(foundReservation);
        assertTrue(foundReservation.getEmail().equalsIgnoreCase(testReservation.getEmail()));
        assertEquals(testReservation.getPhoneNumber(), foundReservation.getPhoneNumber());
        assertTrue(foundReservation.getFirstName().equalsIgnoreCase(testReservation.getFirstName()));
        assertTrue(foundReservation.getLastName().equalsIgnoreCase(testReservation.getLastName()));

        assertEquals(testTicket.getRowNumber(), foundTicket.getRowNumber());
        assertEquals(testTicket.getSeatNumber(), foundTicket.getSeatNumber());

        assertNotNull(foundTicketType);
        assertEquals(testTicket.getTicketType(), foundTicketType);
        assertEquals(testTicketType.getTicketType(), foundTicketType.getTicketType());
        assertEquals(testTicketType.getPrice(), foundTicketType.getPrice());
    }

    @Test
    public void givenTicket_whenUpdated_thenCanBeFoundWithUpdatedData(){
        testTicket.setRowNumber(1);
        Ticket foundTicket = ticketRepository.findById(testTicket.getId()).orElse(null);

        assertNotNull(foundTicket);
        assertEquals(testTicket.getRowNumber(), foundTicket.getRowNumber());
    }

    @Test
    public void givenTicketId_whenDeleted_thenCannotBeFoundById(){
        ticketRepository.deleteById(testTicket.getId());
        Ticket foundTicket = ticketRepository.findById(testTicket.getId()).orElse(null);
        assertNull(foundTicket);
    }



}