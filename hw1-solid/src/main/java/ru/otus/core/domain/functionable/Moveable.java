package ru.otus.core.domain.functionable;

import java.util.Vector;

public interface Moveable {

    public Vector<Integer> getPosition();
    public void setPosition(Vector<Integer> newPosition);

    public Vector<Integer> getVelocity();

}
