package ru.otus.tanks.controller;

import ru.otus.core.controller.Command;
import ru.otus.core.controller.exception.CommandException;
import ru.otus.core.domain.functionable.Rotatable;

public class RotateCommand implements Command {

    private final Rotatable rotatableObject;

    public RotateCommand( Rotatable rotatableObject ) {
        this.rotatableObject = rotatableObject;
    }
    
	@Override
	public void execute() throws CommandException {
        try {
            var currentDirection = this.rotatableObject.getDirection();
            var angularVelocity = this.rotatableObject.getAngularVelocity();
            var directionsCount = this.rotatableObject.getDirectionsCount();
            var newDirection = (currentDirection + angularVelocity) % directionsCount ;
            this.rotatableObject.setDirection( newDirection );
        } catch(RuntimeException e){
            e.printStackTrace();
            throw new CommandException(e);
        }
	}

}
