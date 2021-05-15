package ru.otus.tanks.controller;

import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ru.otus.core.controller.Command;
import ru.otus.core.controller.exception.CommandException;
import ru.otus.core.domain.functionable.Moveable;

public class MoveCommand implements Command {

    private final Moveable moveableObject;

    public MoveCommand( Moveable moveableObject ) {
        this.moveableObject = moveableObject;
    }
    
	@Override
	public void execute() throws CommandException {
        try {
            var currentPosition = this.moveableObject.getPosition();
            var velocity = this.moveableObject.getVelocity();
            var newPosition = IntStream
                               .range(0, currentPosition.size())
                               .map( i -> currentPosition.get(i) + velocity.get(i) )
                               .boxed()
                               .collect( Collectors.toCollection(Vector::new) );
            this.moveableObject.setPosition( newPosition );
        } catch(NullPointerException e){
            e.printStackTrace();
            throw new CommandException(e);
        }
	}

}
