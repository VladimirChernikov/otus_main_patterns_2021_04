package ru.otus.core.controller;

import ru.otus.core.controller.exception.CommandException;

public interface Command {
    public void execute() throws CommandException;
}
