package ru.otus.tanks.adapters;

import ru.otus.core.domain.DomainObject;
import ru.otus.core.domain.functionable.Rotatable;

public class RotatableAdapter implements Rotatable {

    private DomainObject object;

    public RotatableAdapter( DomainObject object ) {
        this.object = object;
    }

	@Override
	public Integer getDirection() {
		return (Integer)this.object.getProperty("ROTATABLE.DIRECTION");
	}

	@Override
	public void setDirection(Integer newDirection) {
		this.object.setProperty("ROTATABLE.DIRECTION", newDirection);
	}

	@Override
	public Integer getAngularVelocity() {
		return (Integer)this.object.getProperty("ROTATABLE.ANGULAR_VELOCITY");
	}

	@Override
	public Integer getDirectionsCount() {
		return (Integer)this.object.getProperty("ROTATABLE.DIRECTIONS_COUNT");
	}

}
