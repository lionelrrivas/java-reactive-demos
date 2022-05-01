package com.lionelrivas.demo.sinks.model;

import java.util.function.Consumer;

public class ChannelMember {

    private final String name;
    private Consumer<String> messageConsumer;
 
    public ChannelMember(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void receiveMessage(String message) {
        System.out.println(message);
    }
    
    public void sendMessage(String message) {
        this.messageConsumer.accept(message);
    }
    
    void setMessageConsumer(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }
    
}
