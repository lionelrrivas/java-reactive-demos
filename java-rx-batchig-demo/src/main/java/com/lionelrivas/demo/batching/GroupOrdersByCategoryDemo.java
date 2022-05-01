package com.lionelrivas.demo.batching;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import com.lionelrivas.demo.util.ThreadUtil;
import java.util.Map;
import java.util.function.Function;
import reactor.core.publisher.Flux;

public class GroupOrdersByCategoryDemo {

    
    public static void main(String[] args) {
        OrderProcessor orderProcessor = new OrderProcessor();
        
        Map<String, Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> functions = Map.of(
                "Kids", orderProcessor.kidsProcessor(),
                "Automotive", orderProcessor.automotiveProcessor()
        );
        
        OrderService orderService = new OrderService();
        orderService.orderStream()
                .filter(purchaseOrder -> functions.containsKey(purchaseOrder.getCategory()))
                .groupBy(PurchaseOrder::getCategory)
                .flatMap(groupedFlux -> functions.get(groupedFlux.key()).apply(groupedFlux))
                .subscribe(new GenericSubscriber());
        
        ThreadUtil.sleepSeconds(60);
                
    }
}
