package com.lionelrivas.demo.flux;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class CustomSubscriptionDemo {
    
    
    public static void main(String[] args) {
        
        AtomicReference<Subscription> atomicReference = new AtomicReference<>();
        
        Flux.range(0, 20)
//                .log()
                .subscribeWith(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("onSubscribe(): recieved subscripton");
                atomicReference.set(subscription);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext(): received " + integer);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError(): received " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete(): completed.");
            }
            
        });
        
        sleep(3);
        atomicReference.get().request(3);
        
        sleep(5);
        atomicReference.get().request(5);
        
        sleep(8); 
        atomicReference.get().request(6);
        
//        sleep(5);
//        System.out.println("cancelling subscription...");
//        atomicReference.get().cancel();
//        sleep(5);
        
        // request after cancelling subscription has no effect, the publisher will not 
        // emit any more items once the subscription has been cancelled.
        System.out.println("changed my mind, requesting again...");
        atomicReference.get().request(6);
        
    }
    
    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CustomSubscriptionDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
