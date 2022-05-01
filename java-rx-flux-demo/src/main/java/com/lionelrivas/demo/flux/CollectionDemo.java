package com.lionelrivas.demo.flux;

import com.lionelrivas.demo.consumers.NextConsumer;
import java.util.List;
import reactor.core.publisher.Flux;

public class CollectionDemo {
    public static void main(String[] args) {
        
        Flux.fromArray(new Integer[]{1,2,3,4,5})
                .subscribe(new NextConsumer());
        
        Flux.fromIterable(List.of(1,2,3,4,5))
                .subscribe(new NextConsumer());

                
    }
}
