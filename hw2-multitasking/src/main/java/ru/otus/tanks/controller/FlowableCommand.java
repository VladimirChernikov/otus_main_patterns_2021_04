package ru.otus.tanks.controller;

import ru.otus.core.controller.Command;
import ru.otus.core.domain.functionable.Flowable;

public class FlowableCommand implements Command {

    private final Flowable flowable;
    private final Thread thread;

    public FlowableCommand( Flowable flowable ) {
        this.flowable = flowable;
        this.thread = new Thread(
                () -> {
                    var commandQueue = this.flowable.getCommandQueue();
                    while ( !this.flowable.getThread().isInterrupted() ) {
                        try {
                            commandQueue.take().execute();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
        this.flowable.setThread( this.thread );
    }
    
    @Override
    public void execute() {
        this.thread.start();
    }

}
