package com.example.kinoxp.theater;

import java.util.ArrayList;
import java.util.List;

public class TheaterMapper {

    public static Theater toTheater(CreateTheaterRequest request) {
        Theater newTheater = new Theater(request.theaterName(), request.numberOfRows(), request.seatsPerRow(), request.location());
        List<TheaterRow> rows = new ArrayList<>();
        for (TheaterRow row : request.rows()) {
            row.setTheater(newTheater);
            rows.add(row);
        }
        newTheater.setTheaterRows(rows);
        return newTheater;
    }

}
