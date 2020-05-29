package com.gridnine.testing;

import java.util.List;

public interface Rules {
    List<Flight> apply(List<Flight> flights);
    boolean test(Flight flight);
}

