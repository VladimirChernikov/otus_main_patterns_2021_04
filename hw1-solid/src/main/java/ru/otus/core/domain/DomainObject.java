package ru.otus.core.domain;

public interface DomainObject {
    public Object getProperty(String propertyName);
    public void setProperty(String propertyName, Object propertyValue);
    public boolean hasProperty(String propertyName);
} 
