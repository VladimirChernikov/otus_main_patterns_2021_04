package ru.otus.tanks.adapters;

import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

import ru.otus.core.controller.Command;
import ru.otus.core.domain.DomainObject;
import ru.otus.core.domain.exception.ReadPropertyException;
import ru.otus.core.domain.exception.WritePropertyException;
import ru.otus.core.domain.functionable.Flowable;

public class FlowableAdapter implements Flowable {

    private DomainObject object;
    private Thread thread;

    public FlowableAdapter( DomainObject object ) {
        this.object = object;
    }

	@Override
	public Thread getThread() throws ReadPropertyException {
        Thread result = null;
        String propertyName = "FLOWABLE.THREAD";

        try {
            result = (Thread)this.object.getProperty(propertyName);
            if (result == null) {
                throw new ReadPropertyException( String.format("Unable to read property %s of object %s", propertyName, this.object) );
            }
        } catch(Exception e){
            throw new ReadPropertyException(e);
        }
        return result;
	}

	@Override
	public void setThread(Thread thread) throws WritePropertyException {
        try {
            this.object.setProperty("FLOWABLE.THREAD", thread);
        } catch(Exception e){
            throw new WritePropertyException(e);
        }
	}

	@Override
	public BlockingQueue<Command> getCommandQueue() throws ReadPropertyException {
        BlockingDeque<Command> result = null;
        String propertyName = "FLOWABLE.COMMAND_QUEUE";

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

	@Override
	public void setCommandQueue(BlockingQueue<Command> commandQueue) throws WritePropertyException {
        try {
            this.object.setProperty("FLOWABLE.COMMAND_QUEUE", commandQueue);
        } catch(Exception e){
            throw new WritePropertyException(e);
        }
	}


}
