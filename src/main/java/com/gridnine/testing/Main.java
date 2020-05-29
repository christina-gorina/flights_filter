package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = new LinkedList<>(FlightBuilder.createFlights());

        new Filter(flights,
                new DepartureBefore(LocalDateTime.now()),
                new ArrivalBeforeDeparture(),
                new GroundTimeMoreThan(Duration.ofHours(2)));
    }
}