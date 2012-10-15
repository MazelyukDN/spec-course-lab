package by.bsu.contacts.controller.filters;

import by.bsu.contacts.controller.Request;
import by.bsu.contacts.controller.Response;

import java.io.IOException;

public interface IFilter {

    boolean doFilter(Request request, Response response) throws IOException;

}
