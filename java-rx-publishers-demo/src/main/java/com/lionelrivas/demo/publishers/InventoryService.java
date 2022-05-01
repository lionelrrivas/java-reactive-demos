package com.lionelrivas.demo.publishers;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import reactor.core.publisher.Flux;

public class InventoryService {

    private static final Map<String, Integer> CATEGORY_QUANTITY_MAP = new HashMap<>();
    
    private final Flux<String> inventoryStream;

    public InventoryService() {
        CATEGORY_QUANTITY_MAP.put("Kids", 100);
        CATEGORY_QUANTITY_MAP.put("Automotive", 100);
        this.inventoryStream = inventoryStream();
        
    }

    public Consumer<PurchaseOrder> purchaseOrderConsumer() {
        return purchaseOrder -> {
            CATEGORY_QUANTITY_MAP.computeIfPresent(purchaseOrder.getCategory(), (key, value) -> value - purchaseOrder.getQuantity());
        };
    }

    private static Flux<String> inventoryStream() {
        return Flux
                .interval(Duration.ofSeconds(2))
                .map(i -> CATEGORY_QUANTITY_MAP.toString());
    }
    
    public void subscribe(GenericSubscriber subscriber) {
        this.inventoryStream.subscribe(subscriber);
    }

}
