package com.lionelrivas.demo.mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import reactor.core.publisher.Mono;

public class FileService {

    private static final Path PATH = Paths.get("src/main/resources/section01/assignment");

    public Mono<String> read(String fileName) {
        return Mono.fromSupplier(() -> readFile(fileName));
    }

    public Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(() -> writeFile(fileName, content));
    }

    public Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(() -> deleteFile(fileName));
    }

    private static void writeFile(String fileName, String content) {
        System.out.println("writeFile(): writing " + fileName);
        try {
            Files.writeString(PATH.resolve(fileName), content);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void deleteFile(String fileName) {
        System.out.println("deletFile(): deleting " + fileName);
        try {
            Files.delete(PATH.resolve(fileName));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String readFile(String fileName) {
        System.out.println("readFile(): reading " + fileName);
        try {
            return Files.readString(PATH.resolve(fileName));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
