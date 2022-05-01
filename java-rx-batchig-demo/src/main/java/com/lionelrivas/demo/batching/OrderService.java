package com.lionelrivas.demo.batching;

import java.time.Duration;
import reactor.core.publisher.Flux;

public class OrderService {

    public Flux<PurchaseOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> new PurchaseOrder());
    }

}
