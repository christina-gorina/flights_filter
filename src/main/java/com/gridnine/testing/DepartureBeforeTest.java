package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DepartureBeforeTest {
    private  DepartureBefore departureBefore;
    private LocalDateTime threeDaysFromNow;
    @BeforeEach
    public void setUp() {
        departureBefore = new DepartureBefore(LocalDateTime.now());
        threeDaysFromNow = LocalDateTime.now().plusDays(3);
    }

    @Test
    public void departureAfterDate() {
        Segment segment = new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
        Flight flight = new Flight(segments);

        boolean isAfter = departureBefore.test(flight);
        assertTrue(isAfter);

    }

    @Test
    public void departureBeforeDate() {
        Segment segment = new Segment(threeDaysFromNow.minusDays(6), threeDaysFromNow);
        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
        Flight flight = new Flight(segments);

        boolean isBefore = departureBefore.test(flight);
        assertFalse(isBefore);
    }

    @Test
    public void flightWithoutSegments() {
        Flight flight = new Flight(new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> departureBefore.test(flight));
    }
}
