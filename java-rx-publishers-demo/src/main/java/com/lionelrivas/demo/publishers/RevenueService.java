package com.lionelrivas.demo.publishers;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import reactor.core.publisher.Flux;

public class RevenueService {

    private static final Map<String, Double> CATEGORY_PRICE_MAP = new HashMap<>();
    private final Flux<String> revenueStream;

    public RevenueService() {
        CATEGORY_PRICE_MAP.put("Kids", 0.0);
        CATEGORY_PRICE_MAP.put("Automotive", 0.0);
        this.revenueStream = revenueStream();
    }

    public Consumer<PurchaseOrder> purchaseOrderConsumer() {
        return purchaseOrder -> {
            CATEGORY_PRICE_MAP.computeIfPresent(purchaseOrder.getCategory(), (key, value) -> value + purchaseOrder.getPrice());
        };
    }

    private static Flux<String> revenueStream() {
        return Flux
                .interval(Duration.ofSeconds(2))
                .map(i -> CATEGORY_PRICE_MAP.toString());
    }

    public void subscribe(GenericSubscriber subscriber) {
        this.revenueStream.subscribe(subscriber);
    }

}
