package com.lionelrivas.demo.flux;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.core.publisher.Flux;

public class OperatorsDemo {
    
    public static void main(String[] args) {
        Flux.range(1, 10)
                .log()
                .take(3)
                .log()
                .subscribe(new GenericSubscriber());
    }

}
