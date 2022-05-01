package com.lionelrivas.demo.mono;

import com.lionelrivas.demo.consumers.NextConsumer;
import java.util.concurrent.CompletableFuture;
import reactor.core.publisher.Mono;

public class CompletableFutureDemo {
    
    private static final UserRepository userRepository = new UserRepository();
    
    public static void main(String[] args) throws Exception {
        
        Mono.fromFuture(userFuture()).subscribe(new NextConsumer());
        Thread.sleep(4000);
    }
    
    private static CompletableFuture<User> userFuture() {
        
        return CompletableFuture.supplyAsync(() -> userRepository.randomUser());
        
    }
}
