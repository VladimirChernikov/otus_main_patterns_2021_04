package ru.otus.core.domain;

public interface DomainObject {
    Object getProperty(String propertyName);
    void setProperty(String propertyName, Object propertyValue);
} 
