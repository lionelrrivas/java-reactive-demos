package com.lionelrivas.demo.operators;

import com.lionelrivas.demo.subscribers.GenericSubscriber;

public class FlatMapDemo {
    
    public static void main(String[] args) throws InterruptedException {
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        
        userService.getUsers()
                .flatMap(user -> orderService.getOrders(user.getUserId()))
                .subscribe(new GenericSubscriber());
        
        Thread.sleep(10000);
    }

}
