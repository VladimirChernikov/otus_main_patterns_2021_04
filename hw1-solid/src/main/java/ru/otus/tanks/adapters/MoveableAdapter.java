package ru.otus.tanks.adapters;

import java.util.Vector;

import ru.otus.core.domain.DomainObject;
import ru.otus.core.domain.functionable.Moveable;

public class MoveableAdapter implements Moveable {

    private DomainObject object;

    public MoveableAdapter( DomainObject object ) {
        this.object = object;
    }

	@Override
	public Vector<Integer> getPosition() {
		return (Vector<Integer>)this.object.getProperty("MOVEABLE.POSITION");
	}

	@Override
	public void setPosition(Vector<Integer> newPosition) {
		this.object.setProperty("MOVEABLE.POSITION", newPosition);
	}

	@Override
	public Vector<Integer> getVelocity() {
		return (Vector<Integer>)this.object.getProperty("MOVEABLE.VELOCITY");
	}

}
