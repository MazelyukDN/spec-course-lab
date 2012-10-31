package by.bsu.contacts.command;

import by.bsu.contacts.controller.Request;
import by.bsu.contacts.controller.Response;
import by.bsu.contacts.valuelisthandler.IteratorException;

import java.io.IOException;

public interface Command {
    void execute(Request request, Response response) throws IOException, IteratorException;
}
