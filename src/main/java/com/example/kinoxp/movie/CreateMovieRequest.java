package com.example.kinoxp.movie;

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
