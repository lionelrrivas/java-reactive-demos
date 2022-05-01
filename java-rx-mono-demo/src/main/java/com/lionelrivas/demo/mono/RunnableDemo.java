package com.lionelrivas.demo.mono;

import com.lionelrivas.demo.consumers.ErrorConsumer;
import com.lionelrivas.demo.consumers.NextConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import reactor.core.publisher.Mono;

public class RunnableDemo {

    public static void main(String[] args) {
        Mono.fromRunnable(timeConsumingOperation())
                .subscribe(new NextConsumer(),
                        new ErrorConsumer(),
                        () -> System.out.println("Process is complete."));
    }

    private static Runnable timeConsumingOperation() {
        return () -> {
            sleep();
            System.out.println("Time consuming operation complete.");
        };
    }

    private static void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RunnableDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
