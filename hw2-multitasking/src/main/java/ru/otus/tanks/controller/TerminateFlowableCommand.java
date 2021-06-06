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
        var commandQueue = this.flowable.getCommandQueue();
        if ( commandQueue.isEmpty() )  {
            this.flowable.getThread().interrupt();
        } else {
            try {
				commandQueue.put( new TerminateFlowableCommand( this.flowable ) );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }

}
