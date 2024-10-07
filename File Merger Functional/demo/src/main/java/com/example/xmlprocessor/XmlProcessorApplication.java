package com.example.xmlprocessor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class XmlProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmlProcessorApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            // Code to run at application startup
            System.out.println("Application has started successfully.");

            // Example: Initialize some data, log messages, etc.
            System.out.println("Ready to receive XML files for processing.");
        };
    }
}
