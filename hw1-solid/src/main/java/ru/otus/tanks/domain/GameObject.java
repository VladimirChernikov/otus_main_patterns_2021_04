package ru.otus.tanks.domain;

import java.util.Map;

import ru.otus.core.domain.DomainObject;

public class GameObject implements DomainObject {

    private final Map<String, Object> props;

    public GameObject( Map<String, Object> props ) {
        this.props = props;
    }

	@Override
	public Object getProperty(String propertyName) {
        return this.props.get( propertyName );
	}

	@Override
	public void setProperty(String propertyName, Object propertyValue) {
        this.props.put( propertyName, propertyValue );
	}

    @Override
    public boolean hasProperty( String propertyName ) {
        return this.props.containsKey( propertyName );
    }

}
