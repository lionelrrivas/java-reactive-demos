package com.lionelrivas.demo.flux;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import reactor.core.publisher.Flux;

public class UserRepository {

    private static final Faker FAKER = Faker.instance();

    public Flux<User> findFluxByIds(List<Integer> ids) {
        return Flux.range(0, ids.size())
                .map(i -> {
                    User user = randomUser();
                    System.out.println("publishing user " + user);
                    return user;
                });

    }

    public List<User> findListByIds(List<Integer> ids) {
        List<User> users = new ArrayList<>();
        IntStream.range(0, ids.size()).forEach(i -> {
            User user = randomUser();
            System.out.println("adding user " + user + " to list.");
            users.add(user);
        });
        return users;
    }

    private static User randomUser() {
        sleep();
        return buildUser();
    }

    private static User buildUser() {
        User user = new User();
        user.setFirstName(FAKER.name().firstName());
        user.setLastName(FAKER.name().lastName());

        return user;
    }

    private static void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
