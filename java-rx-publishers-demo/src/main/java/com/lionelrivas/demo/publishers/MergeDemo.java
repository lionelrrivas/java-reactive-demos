package com.lionelrivas.demo.publishers;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import com.lionelrivas.demo.util.ThreadUtil;
import reactor.core.publisher.Flux;

public class MergeDemo {

    public static void main(String[] args) {
        AmericanAirlines americanAirlines = new AmericanAirlines();
        QatarAirlines qatarAirlines = new QatarAirlines();
        EmiratesAirlines emiratesAirlines = new EmiratesAirlines();
        
        Flux<String> searchResults = Flux.merge(
                americanAirlines.getFlights(),
                qatarAirlines.getFlights(),
                emiratesAirlines.getFlights()
        );
        
        searchResults.subscribe(new GenericSubscriber());
        
        ThreadUtil.sleepSeconds(10);
    }
}
