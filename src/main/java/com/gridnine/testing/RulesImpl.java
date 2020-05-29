package com.gridnine.testing;

import java.util.List;

public abstract class RulesImpl implements Rules {
    public List<Flight> apply(List<Flight> flights) {
        for (int i = 0; i < flights.size(); ) {
            if(!test(flights.get(i))){
                flights.remove(i);
            } else {
                ++i;
            }
        }

        printFlights(flights);

        return flights;
    }

    private void printFlights(List<Flight> flights) {
        System.out.println("--------------------------------");
        for(Flight flight:flights) {
            System.out.println(flight);
        }
    }
}