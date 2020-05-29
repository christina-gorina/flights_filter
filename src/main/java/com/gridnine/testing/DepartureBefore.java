package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public class DepartureBefore extends RulesImpl {
    LocalDateTime mDate;

    public DepartureBefore(LocalDateTime date) {
        mDate = date;
    }

    @Override
    public boolean test(Flight flight){
        List<Segment> segments = flight.getSegments();
        if (segments.isEmpty()) {
            throw new IllegalArgumentException("Flight without segments");
        }

        LocalDateTime departureDate = segments.get(0).getDepartureDate();
        return !departureDate.isBefore(mDate);
    }
}
