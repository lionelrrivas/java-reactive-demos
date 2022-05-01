package com.lionelrivas.demo.operators;

import com.lionelrivas.demo.model.PurchaseOrder;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class OrderService {
    
    private static final Map<Integer, List<PurchaseOrder>> ORDER_MAP;
    
    static {
        ORDER_MAP = Map.of(1, List.of(new PurchaseOrder(1), new PurchaseOrder(1), new PurchaseOrder(1)), 
                2, List.of(new PurchaseOrder(2), new PurchaseOrder(2), new PurchaseOrder(2)));
    }

    public Flux<PurchaseOrder> getOrders(int userId) {
        return Flux.create((FluxSink<PurchaseOrder> ordersFluxSink) -> {
            ORDER_MAP.get(userId).forEach(ordersFluxSink::next);
            ordersFluxSink.complete();
        }).delayElements(Duration.ofSeconds(1));
    }
}
