package com.lionelrivas.demo.flux;

import com.lionelrivas.demo.consumers.NextConsumer;
import java.util.List;
import reactor.core.publisher.Flux;

public class MultipleSubscriberDemo {
    
    public static void main(String[] args) {
        
        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4, 5, 6);
        Flux<Integer> evenNumbersFlux = integerFlux.filter(i -> i % 2 == 0);
        Flux<Integer> oddNumbersFlux = integerFlux.filter(i -> i % 2 > 0);
        integerFlux.subscribe(i -> System.out.println("integer subscriber " + i));
        evenNumbersFlux.subscribe(i -> System.out.println("even subscriber " + i));
        oddNumbersFlux.subscribe(i -> System.out.println("odd subscriber " + i));
        
                
        // must use supplier to avoid stream closed exception when there are multiple subscribers
        Flux<Integer> streamFlux = Flux.fromStream(() -> List.of(1,2,3,4,5).stream());
        
        // multiple subscribers.
        streamFlux.subscribe(new NextConsumer());
        streamFlux.subscribe(new NextConsumer());
    }
}
