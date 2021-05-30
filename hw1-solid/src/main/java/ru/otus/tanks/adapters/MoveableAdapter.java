package ru.otus.tanks.adapters;

import java.util.Vector;

import ru.otus.core.domain.DomainObject;
import ru.otus.core.domain.exception.ReadPropertyException;
import ru.otus.core.domain.exception.WritePropertyException;
import ru.otus.core.domain.functionable.Moveable;

public class MoveableAdapter implements Moveable {

    private DomainObject object;

    public MoveableAdapter( DomainObject object ) {
        this.object = object;
    }

	@Override
	public Vector<Integer> getPosition() throws ReadPropertyException {
        Vector<Integer> result = null;
        String propertyName = "MOVEABLE.POSITION";

        try {
            result = (Vector<Integer>)this.object.getProperty(propertyName);
            if (result == null) {
                throw new ReadPropertyException( String.format("Unable to read property %s of object %s", propertyName, this.object) );
            }
        } catch(Exception e){
            throw new ReadPropertyException(e);
        }
        return result;
	}

	@Override
	public void setPosition(Vector<Integer> newPosition) throws WritePropertyException {
        try {
            this.object.setProperty("MOVEABLE.POSITION", newPosition);
        } catch(Exception e){
            throw new WritePropertyException(e);
        }
	}

	@Override
	public Vector<Integer> getVelocity() throws ReadPropertyException {
        Vector<Integer> result = null;
        String propertyName = "MOVEABLE.VELOCITY";

        try {
            result = (Vector<Integer>)this.object.getProperty(propertyName);
            if (result == null) {
                throw new ReadPropertyException( String.format("Unable to read property %s of object %s", propertyName, this.object) );
            }
        } catch(Exception e){
            throw new ReadPropertyException(e);
        }
        return result;
	}

}
