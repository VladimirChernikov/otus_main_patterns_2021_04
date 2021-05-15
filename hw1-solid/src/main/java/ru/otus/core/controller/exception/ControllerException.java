package ru.otus.core.controller.exception;

public abstract class ControllerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ControllerException(Exception ex) {
        super(ex);
    }

    public ControllerException(String msg) {
        super(msg);
    }
}
