package ru.otus.tanks.controller;

import ru.otus.core.controller.Command;
import ru.otus.core.domain.functionable.Flowable;

public class FlowableCommand implements Command {

    private final Flowable flowable;
    private final Thread thread;
    private boolean running;

    public FlowableCommand( Flowable flowable ) {
        this.running = true;
        this.flowable = flowable;
        this.thread = new Thread(
                () -> {
                    while ( this.running ) {
                        // execute next command
                        try {
                            this.flowable.waitCommand().execute();
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                        // exit condition
                        if ( this.flowable.isTerminatingForce() ) {
                            this.running = false;
                        }
                        else
                            if ( this.flowable.isTerminating() ) {
                                if ( this.flowable.peekCommand() == null ) {
                                    this.running = false;
                                }
                            }
                    }
                    // mark flowable object as terminated
                    this.flowable.terminated();
                });
    }
    
    @Override
    public void execute() {
        this.thread.start();
    }

}
