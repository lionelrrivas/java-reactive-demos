package com.lionelrivas.demo.batching;

import java.util.function.Function;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OrderProcessor {

    public Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> automotiveProcessor() {
        return flux -> flux.doOnNext(purchaseOrder -> {
            purchaseOrder.setPrice(1.1 * purchaseOrder.getPrice());
            purchaseOrder.setItem("{{ " + purchaseOrder.getItem() + "}}");
        });
    }

    public Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> kidsProcessor() {
        return flux -> flux
                .doOnNext(purchaseOrder -> purchaseOrder.setPrice(0.5 * purchaseOrder.getPrice()))
                .flatMap(purchaseOrder -> Flux.concat(Mono.just(purchaseOrder), freePurchaseOrder()));
    }
    
    private static Mono<PurchaseOrder> freePurchaseOrder() {
        return Mono.fromSupplier(() -> {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setItem("FREE - " + purchaseOrder.getItem());
            purchaseOrder.setPrice(0);
            purchaseOrder.setCategory("Kids");
            purchaseOrder.setQuantity(1);
            return purchaseOrder;
        });
    }

}
