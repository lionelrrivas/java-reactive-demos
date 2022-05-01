package com.lionelrivas.demo.flux;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileServiceDemo {

    public static void main(String[] args) {

        Path path = Paths.get("src/main/resources/loren-ipsum.txt");

        FileService fileService = new FileService();
        fileService.read(path)
                .subscribe(new GenericSubscriber());

    }
}
