package by.bsu.contacts.controller.filters;

import by.bsu.contacts.controller.Request;
import by.bsu.contacts.controller.Response;

import java.io.IOException;

public class AddHelloFilter implements IFilter {

    public static final String HELLO = "Hello ";
    private IFilter innerFilter;

    public AddHelloFilter(IFilter inner) {
        innerFilter = inner;
    }

    @Override
    public boolean doFilter(Request request, Response response) throws IOException {
        response.getWriter().write(HELLO);

        if (innerFilter != null) {
            return innerFilter.doFilter(request, response);
        }
        return true;
    }
}
