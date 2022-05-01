package com.lionelrivas.demo.mono;

import com.lionelrivas.demo.consumers.Completer;
import com.lionelrivas.demo.consumers.ErrorConsumer;
import com.lionelrivas.demo.consumers.NextConsumer;

public class UserRepositoryDemo {
    
    private static final UserRepository userRepository = new UserRepository();
    
    public static void main(String[] args) {
        userRepository.findById(1).subscribe(new NextConsumer(), 
                new ErrorConsumer(), 
                new Completer());
        
    }
}
