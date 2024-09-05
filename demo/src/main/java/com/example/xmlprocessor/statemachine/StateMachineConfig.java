package com.example.xmlprocessor.statemachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<StrategyStates, StrategyEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<StrategyStates, StrategyEvents> states)
            throws Exception {
        states
                .withStates()
                .initial(StrategyStates.IDLE)
                .state(StrategyStates.PARSING)
                .state(StrategyStates.PROCESSING)
                .state(StrategyStates.COMPLETED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<StrategyStates, StrategyEvents> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(StrategyStates.IDLE).target(StrategyStates.PARSING).event(StrategyEvents.NEW_MESSAGE_RECEIVED)
                .and()
                .withExternal()
                .source(StrategyStates.PARSING).target(StrategyStates.PROCESSING).event(StrategyEvents.STRATEGY_DETERMINED)
                .and()
                .withExternal()
                .source(StrategyStates.PROCESSING).target(StrategyStates.COMPLETED).event(StrategyEvents.PROCESSING_DONE);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<StrategyStates, StrategyEvents> config)
            throws Exception {
        config
                .withConfiguration()
                .listener(new StateMachineListenerAdapter<StrategyStates, StrategyEvents>() {
                    @Override
                    public void stateChanged(State<StrategyStates, StrategyEvents> from, State<StrategyStates, StrategyEvents> to) {
                        System.out.println("State changed from " + (from != null ? from.getId() : "none") + " to " + to.getId());
                    }
                });
    }
}


enum StrategyStates {
    IDLE,           // Waiting for an XML message
    PARSING,        // Reading the XML message
    PROCESSING,     // Processing the XML message
    COMPLETED       // Message processing is complete
}

enum StrategyEvents {
    NEW_MESSAGE_RECEIVED,  // A new XML message is received
    STRATEGY_DETERMINED,   // Strategy determined after parsing
    PROCESSING_DONE        // Processing completed
}