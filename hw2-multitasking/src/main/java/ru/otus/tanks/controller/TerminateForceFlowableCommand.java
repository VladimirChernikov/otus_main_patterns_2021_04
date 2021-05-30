package ru.otus.tanks.controller;

import ru.otus.core.controller.Command;
import ru.otus.core.domain.functionable.Flowable;

public class TerminateForceFlowableCommand implements Command {

    private final Flowable flowable;

    public TerminateForceFlowableCommand( Flowable flowable ) {
        this.flowable = flowable;
    }
    
    @Override
    public void execute() {
        this.flowable.terminateForce();
    }

}
