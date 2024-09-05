package com.example.xmlprocessor.statemachine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final StrategyService strategyService;

    public ApplicationRunner(StrategyService strategyService) {
        this.strategyService = strategyService;
    }

    @Override
    public void run(String... args) throws Exception {
        strategyService.handleNewMessage();  // Simulate receiving a new message
        strategyService.determineStrategy(); // Simulate determining strategy
        strategyService.processMessage();    // Simulate processing message
    }
}

