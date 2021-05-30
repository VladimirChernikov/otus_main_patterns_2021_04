package ru.otus.core.domain.functionable;

import ru.otus.core.controller.Command;
import ru.otus.core.domain.exception.ReadPropertyException;
import ru.otus.core.domain.exception.WritePropertyException;

public interface Flowable {

    Command waitCommand() throws ReadPropertyException;
    Command peekCommand() throws ReadPropertyException;

    void appendCommand( Command cmd ) throws WritePropertyException;
    void prependCommand( Command cmd ) throws WritePropertyException;

    void terminate() throws WritePropertyException;
    void terminateForce() throws WritePropertyException;
    void terminated() throws WritePropertyException;
    boolean isTerminating() throws ReadPropertyException;
    boolean isTerminatingForce() throws ReadPropertyException;
    boolean isTerminated() throws ReadPropertyException;

}
