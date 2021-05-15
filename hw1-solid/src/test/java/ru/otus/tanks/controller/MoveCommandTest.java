package ru.otus.tanks.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ru.otus.core.controller.exception.CommandException;
import ru.otus.tanks.adapters.MoveableAdapter;
import ru.otus.tanks.domain.GameObject;

@DisplayName("Прямолинейное равномерное движение без деформации ")
public class MoveCommandTest {

    @ParameterizedTest
    @DisplayName("Для объекта, находящегося в точке (12, 5) и движущегося со скоростью (-7, 3) движение меняет положение объекта на (5, 8)")
    @CsvSource({"12,5, -7,3, 5,8"})
    void shouldChangePositionAccordingToVelocity( int p1,int p2, int v1,int v2, int ep1,int ep2 ){
        //given
        var expectedPosition = new Vector<Integer>( Arrays.asList(ep1,ep2) );
        var currentPosition = new Vector<Integer>( Arrays.asList(p1,p2) );
        var currentVelocity = new Vector<Integer>( Arrays.asList(v1,v2) );

        var tankProps = new HashMap<String,Object>();
        tankProps.put("MOVEABLE.POSITION", currentPosition);
        tankProps.put("MOVEABLE.VELOCITY", currentVelocity);

        var tank = new MoveableAdapter( new GameObject(tankProps) );
        var moveCommand = new MoveCommand( tank );

        assertThat( tank.getPosition() ).isEqualTo( currentPosition );
        assertThat( tank.getVelocity() ).isEqualTo( currentVelocity );

        //when
        moveCommand.execute();

        //then
        assertThat( tank.getPosition() ).isEqualTo( expectedPosition );
        
    }


    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать положение в пространстве, приводит к ошибке")
    @Test
    void shouldThrowAnExceptionIfUnableToGetPosition(){
        //given
        var tankProps = new HashMap<String,Object>();
        tankProps.put("MOVEABLE.VELOCITY", new Vector<Integer>( Arrays.asList(1,2) ));
        var tank = new MoveableAdapter( new GameObject(tankProps) );
        var moveCommand = new MoveCommand( tank );

        //when
        //then
        assertThrows(CommandException.class, () -> moveCommand.execute());
    }

    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать значение мгновенной скорости, приводит к ошибке")
    @Test
    void shouldThrowAnExceptionIfUnableToGetVelocity(){
        //given
        var tankProps = new HashMap<String,Object>();
        tankProps.put("MOVEABLE.POSITION", new Vector<Integer>( Arrays.asList(1,2) ));
        var tank = new MoveableAdapter( new GameObject(tankProps) );
        var moveCommand = new MoveCommand( tank );

        //when
        //then
        assertThrows(CommandException.class, () -> moveCommand.execute());
    }

}
