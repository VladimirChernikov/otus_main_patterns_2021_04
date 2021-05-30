package ru.otus.tanks.adapters;

import java.util.Vector;
import java.util.concurrent.BlockingDeque;

import ru.otus.core.controller.Command;
import ru.otus.core.domain.DomainObject;
import ru.otus.core.domain.exception.ReadPropertyException;
import ru.otus.core.domain.exception.WritePropertyException;
import ru.otus.core.domain.functionable.Flowable;

public class FlowableAdapter implements Flowable {

    private DomainObject object;

    public FlowableAdapter( DomainObject object ) {
        this.object = object;
    }

    @Override
    public Command waitCommand() throws ReadPropertyException {
        Command result = null;
        try {
            result = this.getQueue().takeFirst();
            if (result == null) {
                throw new ReadPropertyException( String.format("Unable to wait for the next command for object %s", this.object) );
            }
        } catch(Exception e){
            throw new ReadPropertyException(e);
        }
        return result;
    }

    @Override
    public Command peekCommand() throws ReadPropertyException {
        Command result = null;
        try {
            result = this.getQueue().peek();
        } catch(Exception e){
            throw new ReadPropertyException(e);
        }
        return result;
    }

    @Override
    public void appendCommand(Command cmd) throws WritePropertyException {
        try {
            this.getQueue().putLast(cmd);
        } catch(Exception e){
            throw new WritePropertyException(e);
        }
    }

    @Override
    public void prependCommand(Command cmd) throws WritePropertyException {
        try {
            this.getQueue().putFirst(cmd);
        } catch(Exception e){
            throw new WritePropertyException(e);
        }
    }

    @Override
    public void terminate() throws WritePropertyException {
        try {
            this.object.setProperty("FLOWABLE.TERMINATE", true);
        } catch(Exception e){
            throw new WritePropertyException(e);
        }
    }

    @Override
    public void terminateForce() throws WritePropertyException {
        try {
            this.object.setProperty("FLOWABLE.TERMINATE_FORCE", true);
        } catch(Exception e){
            throw new WritePropertyException(e);
        }
    }

    @Override
    public void terminated() throws WritePropertyException {
        try {
            this.object.setProperty("FLOWABLE.TERMINATED", true);
        } catch(Exception e){
            throw new WritePropertyException(e);
        }
    }

    @Override
    public boolean isTerminating() throws ReadPropertyException {
        Boolean result = null;
        String propertyName = "FLOWABLE.TERMINATE";
        try {
            result = (Boolean)this.object.getProperty(propertyName);
            if (result == null) {
                throw new ReadPropertyException( String.format("Unable to read property %s of object %s", propertyName, this.object) );
            }
        } catch(Exception e){
            throw new ReadPropertyException(e);
        }
        return result;
    }

    @Override
    public boolean isTerminatingForce() throws ReadPropertyException {
        Boolean result = null;
        String propertyName = "FLOWABLE.TERMINATE_FORCE";
        try {
            result = (Boolean)this.object.getProperty(propertyName);
            if (result == null) {
                throw new ReadPropertyException( String.format("Unable to read property %s of object %s", propertyName, this.object) );
            }
        } catch(Exception e){
            throw new ReadPropertyException(e);
        }
        return result;
    }

    @Override
    public boolean isTerminated() throws ReadPropertyException {
        Boolean result = null;
        String propertyName = "FLOWABLE.TERMINATED";
        try {
            result = (Boolean)this.object.getProperty(propertyName);
            if (result == null) {
                throw new ReadPropertyException( String.format("Unable to read property %s of object %s", propertyName, this.object) );
            }
        } catch(Exception e){
            throw new ReadPropertyException(e);
        }
        return result;
    }

    private BlockingDeque<Command> getQueue() {
        BlockingDeque<Command> result = null;
        String propertyName = "FLOWABLE.QUEUE";

        try {
            result = (BlockingDeque<Command>)this.object.getProperty(propertyName);
            if (result == null) {
                throw new ReadPropertyException( String.format("Unable to read property %s of object %s", propertyName, this.object) );
            }
        } catch(Exception e){
            throw new ReadPropertyException(e);
        }
        return result;
    }


}
