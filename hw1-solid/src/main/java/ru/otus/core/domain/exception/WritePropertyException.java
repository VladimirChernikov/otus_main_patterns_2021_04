package ru.otus.core.domain.exception;

public class WritePropertyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WritePropertyException(Exception ex) {
        super(ex);
    }

    public WritePropertyException(String msg) {
        super(msg);
    }
}
