package com.example.kinoxp.movie;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {
    @Id
    private String genre;

    public Category(String genre) {
        this.genre = genre;
    }

    public Category() {

    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
