package ru.otus.core.domain.functionable;

import java.util.concurrent.BlockingQueue;

import ru.otus.core.controller.Command;
import ru.otus.core.domain.exception.ReadPropertyException;
import ru.otus.core.domain.exception.WritePropertyException;

public interface Flowable {

    BlockingQueue<Command> getCommandQueue() throws ReadPropertyException;
    void setCommandQueue(BlockingQueue<Command> commandQueue) throws WritePropertyException;

    Thread getThread() throws ReadPropertyException;
    void setThread(Thread thread) throws WritePropertyException;

}
