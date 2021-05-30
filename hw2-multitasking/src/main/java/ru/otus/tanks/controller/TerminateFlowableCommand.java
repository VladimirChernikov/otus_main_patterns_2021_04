package ru.otus.tanks.controller;

import ru.otus.core.controller.Command;
import ru.otus.core.domain.functionable.Flowable;

public class TerminateFlowableCommand implements Command {

    private final Flowable flowable;

    public TerminateFlowableCommand( Flowable flowable ) {
        this.flowable = flowable;
    }
    
    @Override
    public void execute() {
        this.flowable.terminate();
    }

}
