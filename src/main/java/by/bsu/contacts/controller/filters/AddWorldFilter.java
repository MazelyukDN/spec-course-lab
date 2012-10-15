package by.bsu.contacts.controller.filters;

import by.bsu.contacts.controller.Request;
import by.bsu.contacts.controller.Response;

import java.io.IOException;

public class AddWorldFilter implements IFilter {

    public static final String WORLD = "World ";
    private IFilter innerFilter;

    public AddWorldFilter(IFilter inner) {
        innerFilter = inner;
    }

    @Override
    public boolean doFilter(Request request, Response response) throws IOException {
        response.getWriter().write(WORLD);

        if (innerFilter != null) {
            return innerFilter.doFilter(request, response);
        }
        return true;
    }
}