package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GroundTimeMoreThanTest {
    GroundTimeMoreThan groundTimeMoreThan;
    private LocalDateTime threeDaysFromNow;

    @BeforeEach
    public void setUp() {
        groundTimeMoreThan = new GroundTimeMoreThan(Duration.ofHours(2));
        threeDaysFromNow = LocalDateTime.now().plusDays(3);
    }

    @Test
    public void groundTimeMore(){
        Segment segment1 = new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2));
        Segment segment2 = new Segment(threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);
        Flight flight = new Flight(segments);
        boolean isMore = groundTimeMoreThan.test(flight);
        assertFalse(isMore);
    }

    @Test
    public void groundTimeLess(){
        Segment segment1 = new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2));
        Segment segment2 = new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5));
        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);
        Flight flight = new Flight(segments);
        boolean isLess = groundTimeMoreThan.test(flight);
        assertTrue(isLess);
    }

    @Test
    public void flightWithoutSegments(){
        Flight flight = new Flight(new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> groundTimeMoreThan.test(flight));
    }
}
