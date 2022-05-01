package com.lionelrivas.demo.flux;

import com.lionelrivas.demo.consumers.NextConsumer;
import java.time.Duration;
import java.util.List;
import reactor.core.publisher.Flux;

public class UserRepositoryDemo {
    
    private static UserRepository userRepository;
    
    public static void main(String[] args) throws Exception {
        List<Integer> ids = List.of(1,2,3,4,5);
        userRepository = new UserRepository();
        
        // call blocks until all users are added to the list on the server.
//        List<User> users = userRepository.findListByIds(ids);
//        System.out.println("received users " + users);
        
        // elements are consumed as they are published
        userRepository.findFluxByIds(ids).subscribe(new NextConsumer());
        
        // publishes a long value every interval specified by seconds
        Flux.interval(Duration.ofSeconds(1)).subscribe(new NextConsumer());
        Thread.sleep(3000);
    }

}
