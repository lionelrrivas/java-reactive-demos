package com.lionelrivas.demo.sinks;

import com.lionelrivas.demo.sinks.model.ChannelMember;
import com.lionelrivas.demo.sinks.model.SlackChannel;
import com.lionelrivas.demo.util.ThreadUtil;

public class SlackAppDemo {
    
    public static void main(String[] args) {
        SlackChannel slackChannel = new SlackChannel("Reactive Streams");
        
        ChannelMember lionel = new ChannelMember("lionel");
        ChannelMember brad = new ChannelMember("brad");
        ChannelMember john = new ChannelMember("john");
        
        slackChannel.joinChannel(lionel);
        
        ThreadUtil.sleepSeconds(4);
        slackChannel.joinChannel(brad);
        
        ThreadUtil.sleepSeconds(4);
        lionel.sendMessage("hey brad!");
        
        ThreadUtil.sleepSeconds(2);
        brad.sendMessage("hey lionel");
        
        ThreadUtil.sleepSeconds(4);
        slackChannel.joinChannel(john);
        
        ThreadUtil.sleepSeconds(2);
        john.sendMessage("hey dudes!!");
        
        lionel.sendMessage("sup john?");
        
        ThreadUtil.sleepSeconds(2);
        brad.sendMessage("hey john!");
        
    }

}
