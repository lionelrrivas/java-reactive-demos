package com.lionelrivas.demo.batching;

import com.lionelrivas.demo.util.ThreadUtil;
import java.time.Duration;
import reactor.core.publisher.Flux;

public class GroupByDemo {

    public static void main(String[] args) {
        Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(i -> i % 2)
                .subscribe(groupedFlux -> groupedFluxProcessor(groupedFlux, groupedFlux.key()));
        ThreadUtil.sleepSeconds(30); 
    }
    
    private static void groupedFluxProcessor(Flux<Integer> flux, int key) {
        System.out.println("groupedFluxProcessor(): called");
        flux.subscribe(i -> System.out.println("key = " + key + ", item = " + i));
    }
}
