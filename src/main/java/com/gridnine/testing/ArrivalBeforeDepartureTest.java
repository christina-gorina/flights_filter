package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ArrivalBeforeDepartureTest {
    ArrivalBeforeDeparture arrivalBeforeDeparture;
    private LocalDateTime threeDaysFromNow;

    @BeforeEach
    public void setUp() {
        arrivalBeforeDeparture = new ArrivalBeforeDeparture();
        threeDaysFromNow = LocalDateTime.now().plusDays(3);
    }

    @Test
    public void ArrivalBeforeDeparture() {
        Segment segment1 = new Segment(threeDaysFromNow, threeDaysFromNow.minusHours(6));
        Segment segment2 = new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);
        Flight flight = new Flight(segments);
        boolean isBefore = arrivalBeforeDeparture.test(flight);
        assertFalse(isBefore);
    }

    @Test
    public void ArrivalAfterDeparture() {
        Segment segment1 = new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2));
        Segment segment2 = new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);
        Flight flight = new Flight(segments);
        boolean isAfter = arrivalBeforeDeparture.test(flight);
        assertTrue(isAfter);
    }

    @Test
    public void flightWithoutSegments() {
        Flight flight = new Flight(new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> arrivalBeforeDeparture.test(flight));
    }
}