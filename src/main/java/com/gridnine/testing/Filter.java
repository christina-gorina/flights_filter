package com.gridnine.testing;

import java.util.List;

public class Filter {

    public Filter(List<Flight> flights, Rules... rules) {
        for(Rules rule : rules){
            rule.apply(flights);
        }
    }
}