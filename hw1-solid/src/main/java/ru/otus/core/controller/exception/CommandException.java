package ru.otus.core.controller.exception;

public class CommandException extends ControllerException {
	private static final long serialVersionUID = 1L;

	public CommandException(Exception ex) {
        super(ex);
    }

    public CommandException(String msg) {
        super(msg);
    }
}
