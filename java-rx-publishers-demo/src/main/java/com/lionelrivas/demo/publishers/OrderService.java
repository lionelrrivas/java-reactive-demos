package com.lionelrivas.demo.publishers;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Consumer;
import reactor.core.publisher.Flux;

public class OrderService {

    private Flux<PurchaseOrder> orderStream;

    public OrderService() {
        this.orderStream = buildOrderStream();
    }
    
    public void subscribe(Consumer<PurchaseOrder> purchaseOrderConsumer) {
        orderStream.subscribe(purchaseOrderConsumer);
    }

    private Flux<PurchaseOrder> buildOrderStream() {
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> {
                    return new PurchaseOrder();
                })
                .publish()
                .refCount(2);
    }
    
}
