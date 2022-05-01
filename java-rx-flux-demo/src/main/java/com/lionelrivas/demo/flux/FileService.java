package com.lionelrivas.demo.flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

public class FileService {

    public Flux<String> read(Path path) {
        return Flux.generate(fileOpener(path), 
                contentReader(),
                fileCloser());
    }

    private static Callable<BufferedReader> fileOpener(Path path) {
        return () -> Files.newBufferedReader(path);
    }

    private static BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> contentReader() {
        return (bufferedReader, synchronousSink) -> {
            try {
                String line = bufferedReader.readLine();
                if (Objects.isNull(line)) {
                    synchronousSink.complete();
                } else {
                    synchronousSink.next(line);
                }
            } catch (IOException e) {
                synchronousSink.error(new RuntimeException(e.getMessage()));
            }
            return bufferedReader;
        };
    }

    private static Consumer<BufferedReader> fileCloser() {
        return bufferedReader -> {
            try {
                try (bufferedReader) {
                    System.out.println("closing file reader...");
                }
            } catch (IOException ex) {
                Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
    }

}
