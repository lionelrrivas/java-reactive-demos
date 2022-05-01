package com.lionelrivas.demo.mono;

import com.lionelrivas.demo.consumers.NextConsumer;

public class PipelineExecutionDemo {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        //builds the pipeline
        userRepository.findById(1);
        userRepository.findById(1);

        //executes the pipeline by subscribing
        userRepository.findById(1).subscribe(new NextConsumer());
        
        //executes the pipeline with block
        User user = userRepository.findById(1).block();
        System.out.println(user);

    }
}
