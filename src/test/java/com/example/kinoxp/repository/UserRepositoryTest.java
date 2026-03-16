package com.example.kinoxp.repository;

import com.example.kinoxp.KinoXpApplication;
import com.example.kinoxp.model.Role;
import com.example.kinoxp.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
        },
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = UserRepository.class),
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {ShowingRepository.class, CategoryRepository.class, ReservationRepository.class, TheaterRepository.class, TicketRepository.class, MovieRepository.class}))
@ContextConfiguration(classes = KinoXpApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setup(){
        testUser = new User("John Johnson", "Jonny123", Role.EMPLOYEE);
        userRepository.save(testUser);
    }

    @AfterEach
    public void tearDown(){
        userRepository.delete(testUser);
    }

    @Test
    public void givenUser_whenSaved_thenCanBeFoundById(){

        User foundUser = userRepository.findById(testUser.getId()).orElse(null);

        assertNotNull(foundUser);
        assertEquals(testUser.getUsername(), foundUser.getUsername());
        assertEquals(testUser.getPassword(), foundUser.getPassword());
        assertEquals(testUser.getRole(), foundUser.getRole());
    }

    @Test
    public void givenUser_whenUpdated_thenCanBeFoundWithUpdatedData(){
        testUser.setUsername("Joel Johnson");
        User foundUser = userRepository.findById(testUser.getId()).orElse(null);

        assertNotNull(foundUser);
        assertEquals(testUser.getUsername(), foundUser.getUsername());
    }

    @Test
    public void givenUserId_whenDeleted_thenCannotBeFoundById(){
        userRepository.deleteById(testUser.getId());
        User foundUser = userRepository.findById(testUser.getId()).orElse(null);
        assertNull(foundUser);
    }

}