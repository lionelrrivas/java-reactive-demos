package com.lionelrivas.demo.subscribers;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class GenericSubscriber implements Subscriber<Object>{

    private String name = "";
    
    public GenericSubscriber(String name) {
        this.name = name + " ";
    }

    public GenericSubscriber() {}
    
    
    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println(name + "onSubscribe(): received subscription");
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object object) {
        System.out.println(name + "onNext(): " + object);
    }
    
    @Override
    public void onError(Throwable throwable) {
        System.out.println(name + "onError(): " + throwable);
    }

    @Override
    public void onComplete() {
        System.out.println(name + "onComplete(): completed.");
    }

}
