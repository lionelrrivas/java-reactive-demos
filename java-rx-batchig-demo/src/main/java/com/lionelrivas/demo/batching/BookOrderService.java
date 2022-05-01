package com.lionelrivas.demo.batching;

import java.time.Duration;
import reactor.core.publisher.Flux;

public class BookOrderService {
    
    public Flux<BookOrder> bookOrderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> new BookOrder());
    }

}
