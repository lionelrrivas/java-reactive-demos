package com.lionelrivas.demo.sinks.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelMessage {

    private String sender;
    private String receiver;
    private String message;

    @Override
    public String toString() {
        return "[%s -> %s] : %s".formatted(sender, receiver, message);
    }
    
}
