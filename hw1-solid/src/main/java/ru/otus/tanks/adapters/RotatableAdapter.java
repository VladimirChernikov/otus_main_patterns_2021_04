package ru.otus.tanks.adapters;

import ru.otus.core.domain.DomainObject;
import ru.otus.core.domain.exception.ReadPropertyException;
import ru.otus.core.domain.exception.WritePropertyException;
import ru.otus.core.domain.functionable.Rotatable;

public class RotatableAdapter implements Rotatable {

    private DomainObject object;

    public RotatableAdapter( DomainObject object ) {
        this.object = object;
    }

	@Override
	public Integer getDirection() throws ReadPropertyException {
        Integer result = null;
        String propertyName = "ROTATABLE.DIRECTION";

        try {
            result = (Integer)this.object.getProperty(propertyName);
            if (result == null) {
                throw new ReadPropertyException( String.format("Unable to read property %s of object %s", propertyName, this.object) );
            }
        } catch(Exception e){
            throw new ReadPropertyException(e);
        }
		return result;
	}

	@Override
	public void setDirection(Integer newDirection) throws WritePropertyException {
        try {
            this.object.setProperty("ROTATABLE.DIRECTION", newDirection);
        } catch(Exception e){
            throw new WritePropertyException(e);
        }
	}

	@Override
	public Integer getAngularVelocity() throws ReadPropertyException {
        Integer result = null;
        String propertyName = "ROTATABLE.ANGULAR_VELOCITY";

        try {
            result = (Integer)this.object.getProperty(propertyName);
            if (result == null) {
                throw new ReadPropertyException( String.format("Unable to read property %s of object %s", propertyName, this.object) );
            }
        } catch(Exception e){
            throw new ReadPropertyException(e);
        }
		return result;
	}

	@Override
	public Integer getDirectionsCount() throws ReadPropertyException {
        Integer result = null;
        String propertyName = "ROTATABLE.DIRECTIONS_COUNT";

        try {
            result = (Integer)this.object.getProperty(propertyName);
            if (result == null) {
                throw new ReadPropertyException( String.format("Unable to read property %s of object %s", propertyName, this.object) );
            }
        } catch(Exception e){
            throw new ReadPropertyException(e);
        }
		return result;
	}

}
