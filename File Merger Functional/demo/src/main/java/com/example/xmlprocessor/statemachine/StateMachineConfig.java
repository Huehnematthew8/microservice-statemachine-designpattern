package com.example.xmlprocessor.statemachine;

import com.example.xmlprocessor.statemachine.Events;
import com.example.xmlprocessor.statemachine.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.WAITING_FOR_FIRST_FILE)
                .state(States.WAITING_FOR_SECOND_FILE)
                .state(States.PROCESSING)
                .end(States.COMPLETION);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.WAITING_FOR_FIRST_FILE).target(States.WAITING_FOR_SECOND_FILE).event(Events.FIRST_FILE_RECEIVED)
                .and()
                .withExternal()
                .source(States.WAITING_FOR_SECOND_FILE).target(States.PROCESSING).event(Events.SECOND_FILE_RECEIVED)
                .and()
                .withExternal()
                .source(States.PROCESSING).target(States.COMPLETION).event(Events.FILES_MERGED);
    }
}
