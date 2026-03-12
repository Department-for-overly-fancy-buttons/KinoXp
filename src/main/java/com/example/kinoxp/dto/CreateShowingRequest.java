package com.example.kinoxp.dto;

import java.time.LocalDateTime;

public record CreateShowingRequest(
        Long movieId,
        Long theaterId,
        LocalDateTime startTime,
        boolean is3d
) {
}
