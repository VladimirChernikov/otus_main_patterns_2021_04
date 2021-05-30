package ru.otus.core.domain.functionable;

import java.util.Vector;

import ru.otus.core.domain.exception.ReadPropertyException;
import ru.otus.core.domain.exception.WritePropertyException;

public interface Moveable {

    Vector<Integer> getPosition() throws ReadPropertyException;
    void setPosition(Vector<Integer> newPosition) throws WritePropertyException;

    Vector<Integer> getVelocity() throws ReadPropertyException;

}
