package com.lionelrivas.demo.flux;

import java.util.concurrent.CountDownLatch;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class StockPriceSubscriber {

    private static StockPricePublisher stockPricePublisher;
    
    public static void main(String[] args) throws InterruptedException {
        
        CountDownLatch countDownLatch = new CountDownLatch(1);
        
        stockPricePublisher = new StockPricePublisher();
        stockPricePublisher.publishPrice().subscribeWith(new Subscriber<Integer>() {
            
            private Subscription subscription; 
            
            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                System.out.println("received subscription");
                subscription.request(Integer.MAX_VALUE);
            }

            @Override
            public void onNext(Integer price) {
                System.out.println("Price: $" + price);
                if (price > 110 || price < 90) {
                    this.subscription.cancel();
                    countDownLatch.countDown();
                }
            }

            @Override
            public void onError(Throwable error) {
                System.out.println(error.getMessage());
                countDownLatch.countDown();
            }

            @Override
            public void onComplete() {
                System.out.println("complete.");
                countDownLatch.countDown();
            }
            
        });
        
        countDownLatch.await();
    }
    
}
