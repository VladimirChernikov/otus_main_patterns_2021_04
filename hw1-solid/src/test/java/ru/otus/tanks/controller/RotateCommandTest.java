package ru.otus.tanks.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ru.otus.core.controller.exception.CommandException;
import ru.otus.tanks.adapters.RotatableAdapter;
import ru.otus.tanks.domain.GameObject;

@DisplayName("Поворот объекта вокруг оси ")
public class RotateCommandTest {

    @ParameterizedTest
    @DisplayName("Для объекта, направленного в 1 сторону с количеством сторон равным 4 и поворачивающегося со скоростью 10 напрвление меняется на 3")
    @CsvSource({"1, 4, 10, 3"})
    void shouldChangeDirectionAccordingToVelocity( 
                                 int currentDirection
                                ,int directionsCount
                                ,int angularVelocity
                                ,int expectedDirection
                                )
    {
        //given
        var tankProps = new HashMap<String,Object>();
        tankProps.put("ROTATABLE.DIRECTION", currentDirection);
        tankProps.put("ROTATABLE.DIRECTIONS_COUNT", directionsCount);
        tankProps.put("ROTATABLE.ANGULAR_VELOCITY", angularVelocity);

        var tank = new RotatableAdapter( new GameObject(tankProps) );
        var rotateCommand = new RotateCommand( tank );

        //when
        rotateCommand.execute();

        //then
        assertThat( tank.getDirection() ).isEqualTo( expectedDirection );
        
    }


    @DisplayName("Попытка повернуть объект, у которого невозможно прочитать направление, приводит к ошибке")
    @Test
    void shouldThrowAnExceptionIfUnableToGetDirection(){
        //given
        var tankProps = new HashMap<String,Object>();
        tankProps.put("ROTATABLE.ANGULAR_VELOCITY", 10);
        tankProps.put("ROTATABLE.DIRECTIONS_COUNT", 10);
        var tank = new RotatableAdapter( new GameObject(tankProps) );
        var rotateCommand = new RotateCommand( tank );

        //when
        //then
        assertThrows(CommandException.class, () -> rotateCommand.execute());
    }

    @DisplayName("Попытка повернуть объект, у которого невозможно прочитать значение угловой скорости, приводит к ошибке")
    @Test
    void shouldThrowAnExceptionIfUnableToGetAngularVelocity(){
        //given
        var tankProps = new HashMap<String,Object>();
        tankProps.put("ROTATABLE.DIRECTION", 10);
        tankProps.put("ROTATABLE.DIRECTIONS_COUNT", 10);
        var tank = new RotatableAdapter( new GameObject(tankProps) );
        var rotateCommand = new RotateCommand( tank );

        //when
        //then
        assertThrows(CommandException.class, () -> rotateCommand.execute());
    }

    @DisplayName("Попытка повернуть объект, у которого невозможно прочитать количество направлений, приводит к ошибке")
    @Test
    void shouldThrowAnExceptionIfUnableToGetDirectionsCount(){
        //given
        var tankProps = new HashMap<String,Object>();
        tankProps.put("ROTATABLE.DIRECTION", 10);
        tankProps.put("ROTATABLE.ANGULAR_VELOCITY", 10);
        var tank = new RotatableAdapter( new GameObject(tankProps) );
        var rotateCommand = new RotateCommand( tank );

        //when
        //then
        assertThrows(CommandException.class, () -> rotateCommand.execute());
    }

}
