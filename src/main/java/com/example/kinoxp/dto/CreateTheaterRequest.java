package com.example.kinoxp.dto;

import com.example.kinoxp.model.TheaterRow;

import java.util.List;

public record CreateTheaterRequest(String theaterName,
                                   String location,
                                   int numberOfRows,
                                   int seatsPerRow,
                                   List<TheaterRow> rows
                                   ) {
}
