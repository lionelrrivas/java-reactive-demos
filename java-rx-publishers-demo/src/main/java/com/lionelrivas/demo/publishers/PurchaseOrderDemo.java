package com.lionelrivas.demo.publishers;

import com.lionelrivas.demo.subscribers.GenericSubscriber;

public class PurchaseOrderDemo {
    
    public static void main(String[] args) throws InterruptedException {
        OrderService orderService = new OrderService();
        InventoryService inventoryService = new InventoryService();
        RevenueService revenueService = new RevenueService();
        
        orderService.subscribe(revenueService.purchaseOrderConsumer());
        orderService.subscribe(inventoryService.purchaseOrderConsumer());
        
        revenueService
                .subscribe(new GenericSubscriber("revenue subscriber"));
        
        inventoryService
                .subscribe(new GenericSubscriber("inventory subscriber"));
        
        Thread.sleep(30000);
        
    }

}
