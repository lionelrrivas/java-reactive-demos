package com.lionelrivas.demo.publishers;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import com.lionelrivas.demo.util.ThreadUtil;
import java.time.Duration;
import reactor.core.publisher.Flux;

public class CombineLatestDemo {
    
    public static void main(String[] args) {
        
        Flux.combineLatest(getAlpha(), getNumeric(), (alpha, numeric) -> alpha + numeric)
                .subscribe(new GenericSubscriber());
        
        ThreadUtil.sleepSeconds(20);
                
    }
    
    private static Flux<String> getAlpha() {
        return Flux.just("A", "B", "C", "D")
                .delayElements(Duration.ofSeconds(1));
    }
    
    private static Flux<Integer> getNumeric() {
        return Flux.just(1, 2)
                .delayElements(Duration.ofSeconds(3));
    }

}
