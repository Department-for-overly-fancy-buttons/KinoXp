//package com.example.kinoxp.repository;
//
//import com.example.kinoxp.KinoXpApplication;
//import com.example.kinoxp.theater.Theater;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.test.context.ContextConfiguration;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest(properties = {
//        "spring.datasource.url=jdbc:h2:mem:testdb",
//        "spring.jpa.hibernate.ddl-auto=create-drop"
//        },
//        includeFilters = @ComponentScan.Filter(
//                type = FilterType.ASSIGNABLE_TYPE,
//                classes = TheaterRepository.class),
//        excludeFilters = @ComponentScan.Filter(
//                type = FilterType.ASSIGNABLE_TYPE,
//                classes = {ShowingRepository.class, CategoryRepository.class, ReservationRepository.class, MovieRepository.class, TicketRepository.class, UserRepository.class}))
//@ContextConfiguration(classes = KinoXpApplication.class)
//public class TheaterRepositoryTest {
//
//    @Autowired
//    private TheaterRepository theaterRepository;
//
//    private Theater testTheater;
//
//    @BeforeEach
//    public void setup(){
//        testTheater = new Theater("Sal 24", 10, 8);
//        theaterRepository.save(testTheater);
//    }
//
//    @AfterEach
//    public void tearDown(){
//        theaterRepository.delete(testTheater);
//    }
//
//    @Test
//    public void givenTheater_whenSaved_thenCanBeFoundById(){
//
//        Theater foundTheater = theaterRepository.findById(testTheater.getId()).orElse(null);
//
//        assertNotNull(foundTheater);
//        assertTrue(foundTheater.getTheaterName().equalsIgnoreCase(testTheater.getTheaterName()));
//        assertEquals(testTheater.getNumberOfRows(), foundTheater.getNumberOfRows());
//        assertEquals(testTheater.getSeatsPerRow(), foundTheater.getSeatsPerRow());
//    }
//
//    @Test
//    public void givenTheater_whenUpdated_thenCanBeFoundWithUpdatedData(){
//        testTheater.setTheaterName("Sal B4");
//        Theater foundTheater = theaterRepository.findById(testTheater.getId()).orElse(null);
//
//        assertNotNull(foundTheater);
//        assertTrue(foundTheater.getTheaterName().equalsIgnoreCase(testTheater.getTheaterName()));
//    }
//
//    @Test
//    public void givenTheaterId_whenDeleted_thenCannotBeFoundById(){
//        theaterRepository.deleteById(testTheater.getId());
//        Theater foundTheater = theaterRepository.findById(testTheater.getId()).orElse(null);
//        assertNull(foundTheater);
//    }
//
//}