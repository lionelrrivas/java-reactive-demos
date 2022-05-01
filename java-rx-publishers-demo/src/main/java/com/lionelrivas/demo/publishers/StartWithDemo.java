package com.lionelrivas.demo.publishers;

import com.lionelrivas.demo.generators.NameGenerator;
import com.lionelrivas.demo.subscribers.GenericSubscriber;

public class StartWithDemo {

    public static void main(String[] args) {
        NameGenerator nameGenerator = new NameGenerator();

        nameGenerator.generateNames()
                .take(2)
                .subscribe(new GenericSubscriber("customer 1"));

        nameGenerator.generateNames()
                .take(2)
                .subscribe(new GenericSubscriber("customer 2"));
        
        
        nameGenerator.generateNames()
                .take(4)
                .subscribe(new GenericSubscriber("customer 3"));
        
        nameGenerator.generateNames()
                .filter(name -> name.equals("Lionel"))
                .take(1)
                .subscribe(new GenericSubscriber("customer 4"));

    }
 
}
