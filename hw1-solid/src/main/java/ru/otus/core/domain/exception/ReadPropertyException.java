package ru.otus.core.domain.exception;

public class ReadPropertyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ReadPropertyException(Exception ex) {
        super(ex);
    }

    public ReadPropertyException(String msg) {
        super(msg);
    }
}
