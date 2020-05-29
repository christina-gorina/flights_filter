package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class GroundTimeMoreThan extends RulesImpl {
    Duration mDuration;
    public GroundTimeMoreThan(Duration duration) {
        mDuration = duration;
    }

    @Override
    public boolean test(Flight flight) {
        List<Segment> segments = flight.getSegments();

        if (segments.isEmpty()) {
            throw new IllegalArgumentException("Flight without segments");
        }

        if(segments.size() < 2){
            return true;
        }

        Duration duration = Duration.ZERO;
        for (int i = 1; i < segments.size(); i++) {
            LocalDateTime arrival = segments.get(i - 1).getArrivalDate();
            LocalDateTime departure = segments.get(i).getDepartureDate();
            duration = duration.plus(Duration.between(arrival, departure));
            if (duration.compareTo(mDuration) > 0) {
                return false;
            }
        }
        return true;
    }
}
