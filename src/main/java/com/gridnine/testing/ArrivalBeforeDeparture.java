package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public class ArrivalBeforeDeparture extends RulesImpl {

    @Override
    public boolean test(Flight flight) {
        List<Segment> segments = flight.getSegments();
        if (segments.isEmpty()) {
            throw new IllegalArgumentException("Flight without segments");
        }

         boolean match = segments.stream().anyMatch((s) -> {
            LocalDateTime departureDate = s.getDepartureDate();
            LocalDateTime arrivalDate = s.getArrivalDate();
            return arrivalDate.isBefore(departureDate);
        });

        return !match;
    }
}
