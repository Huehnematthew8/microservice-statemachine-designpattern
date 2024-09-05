package com.example.xmlprocessor.statemachine;

import org.springframework.statemachine.annotation.OnStateEntry;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

@Component
@WithStateMachine
public class StrategyStateActions {

    @OnStateEntry(target = "IDLE")
    public void onIdle() {
        System.out.println("Waiting for an XML message...");
    }

    @OnStateEntry(target = "PARSING")
    public void onParsing() {
        // Logic to parse the XML message to determine which strategy to use
        System.out.println("Parsing XML message to determine strategy...");
    }

    @OnStateEntry(target = "PROCESSING")
    public void onProcessing() {
        // Logic to process the XML message based on the selected strategy
        System.out.println("Processing XML message with selected strategy...");
    }

    @OnStateEntry(target = "COMPLETED")
    public void onCompleted() {
        // Logic after the message is processed
        System.out.println("Message processing completed and returned.");
    }
}
