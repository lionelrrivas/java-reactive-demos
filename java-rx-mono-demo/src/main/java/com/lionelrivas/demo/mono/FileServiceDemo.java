package com.lionelrivas.demo.mono;

import com.lionelrivas.demo.consumers.Completer;
import com.lionelrivas.demo.consumers.ErrorConsumer;
import com.lionelrivas.demo.consumers.NextConsumer;

public class FileServiceDemo {

    private static final FileService fileService = new FileService();

    public static void main(String[] args) {
        fileService.write("Employee.java", """
                                      public class Employee {
                                        private String firstName;
                                        private String lastName;
                                        
                                        public String getFirstName() {
                                            return firstName;
                                        }
                                      }
                                      """)
                .subscribe(new NextConsumer(),
                        new ErrorConsumer(),
                        new Completer());

        fileService.read("Employee.java")
                .subscribe(new NextConsumer(),
                        new ErrorConsumer(),
                        new Completer());

        fileService.delete("Employee.java")
                .subscribe(new NextConsumer(),
                        new ErrorConsumer(),
                        new Completer());

    }

}
