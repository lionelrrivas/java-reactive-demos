package com.lionelrivas.demo.operators;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import java.util.Comparator;
import java.util.function.Function;
import reactor.core.publisher.Flux;

public class TransformDemo {

    public static void main(String[] args) {
        getPersons()
                .transform(filterAndMap())
                .subscribe(new GenericSubscriber());
    }

    private static Flux<Person> getPersons() {
        return Flux
                .range(1, 10)
                .map(i -> new Person());
    }
    
    private static Function<Flux<Person>, Flux<Person>> filterAndMap() {
        return flux -> flux
                .filter(person -> person.getAge() > 10)
                .sort(Comparator.comparing(Person::getName))
                .doOnDiscard(Person.class, person -> System.out.println("doOnDiscard() " + person))
                .doOnNext(person -> person.setName(person.getName().toUpperCase()));
    }
}
