package com.lionelrivas.demo.sinks.model;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SlackChannel {

    private final String name;
    private final Sinks.Many<ChannelMessage> channelSink;
    private final Flux<ChannelMessage> channelFlux;

    public SlackChannel(String name) {
        this.name = name;
        this.channelSink = Sinks.many().replay().all();
        this.channelFlux = channelSink.asFlux();
    }

    public void joinChannel(ChannelMember channelMember) {
        System.out.println("%s joined %s".formatted(channelMember.getName(), this.name));
        this.subscribe(channelMember);
        channelMember.setMessageConsumer(message -> this.postMessage(message, channelMember));
    }

    private void subscribe(ChannelMember member) {
        this.channelFlux
                .filter(message -> !message.getSender().equalsIgnoreCase(member.getName()))
                .doOnNext(message -> message.setReceiver(member.getName()))
                .map(ChannelMessage::toString)
                .subscribe(member::receiveMessage);
    }

    private void postMessage(String message, ChannelMember channelMember) {
        ChannelMessage channelMessage = new ChannelMessage();
        channelMessage.setMessage(message);
        channelMessage.setSender(channelMember.getName());
        this.channelSink.tryEmitNext(channelMessage);
    }
}
