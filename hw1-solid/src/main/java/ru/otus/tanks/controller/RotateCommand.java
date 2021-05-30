package ru.otus.tanks.controller;

import ru.otus.core.controller.Command;
import ru.otus.core.domain.functionable.Rotatable;

public class RotateCommand implements Command {

    private final Rotatable rotatableObject;

    public RotateCommand( Rotatable rotatableObject ) {
        this.rotatableObject = rotatableObject;
    }
    
    @Override
    public void execute() {
        var currentDirection = this.rotatableObject.getDirection();
        var angularVelocity = this.rotatableObject.getAngularVelocity();
        var directionsCount = this.rotatableObject.getDirectionsCount();
        var newDirection = (currentDirection + angularVelocity) % directionsCount ;
        this.rotatableObject.setDirection( newDirection );
    }

}
