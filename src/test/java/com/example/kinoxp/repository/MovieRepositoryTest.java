package com.example.kinoxp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieRepositoryTest {

    private MovieRepository repository;

    @BeforeEach
    void setup(){
        this.repository = new MovieRepository();
    }

    @Test
    void testGithubActionCI_imput_one() {
        assertTrue(repository.testGithubActionCI(1));
    }

    @Test
    void testGithubActionCI_imput_zero() {
        assertTrue(repository.testGithubActionCI(0));
    }
}