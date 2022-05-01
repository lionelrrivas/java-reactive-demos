package com.lionelrivas.demo.context;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.util.context.Context;

public class ContextModifierDemo {

    public static void main(String[] args) {
        
        BookService bookService = new BookService();
        bookService.getBook()
                .repeat(3)
                .contextWrite(new UserService().contextCategoryModifier())
                .contextWrite(Context.of("user", "mike"))
                .subscribe(new GenericSubscriber());
    }
}
