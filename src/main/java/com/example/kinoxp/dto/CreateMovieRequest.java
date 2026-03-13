package com.example.kinoxp.dto;

import com.example.kinoxp.model.Category;
import jakarta.persistence.Column;

import java.util.Base64;
import java.util.List;

public record CreateMovieRequest(
        String title,
        String description,
        int pgRating,
        double duration,
        List<Category> categories,
        String posterData
        ) {
}
