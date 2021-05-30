package ru.otus.core.domain.functionable;

import ru.otus.core.domain.exception.ReadPropertyException;
import ru.otus.core.domain.exception.WritePropertyException;

public interface Rotatable {

    Integer getDirection() throws ReadPropertyException;
    void setDirection(Integer newDirection) throws WritePropertyException;

    Integer getAngularVelocity() throws ReadPropertyException;

    Integer getDirectionsCount() throws ReadPropertyException;

}
