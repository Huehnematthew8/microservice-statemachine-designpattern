package com.example.xmlprocessor.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Service
public class StrategyService {

    @Autowired
    private StateMachine<StrategyStates, StrategyEvents> stateMachine;

    public void handleNewMessage() {
        stateMachine.sendEvent(StrategyEvents.NEW_MESSAGE_RECEIVED);
    }

    public void determineStrategy() {
        stateMachine.sendEvent(StrategyEvents.STRATEGY_DETERMINED);
    }

    public void processMessage() {
        stateMachine.sendEvent(StrategyEvents.PROCESSING_DONE);
    }
}
