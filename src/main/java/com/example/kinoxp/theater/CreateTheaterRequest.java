package com.example.kinoxp.theater;

import java.util.List;

public record CreateTheaterRequest(String theaterName,
                                   String location,
                                   int numberOfRows,
                                   int seatsPerRow,
                                   List<TheaterRow> rows
                                   ) {
}
