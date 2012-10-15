package by.bsu.contacts.controller.filters;

import by.bsu.contacts.controller.Request;
import by.bsu.contacts.controller.Response;

import java.io.IOException;

public class BeastNumberAwareFilter implements IFilter {

    public static final String BEAST_NUMBER = "666";
    private IFilter innerFilter;

    public BeastNumberAwareFilter(IFilter inner) {
        innerFilter = inner;
    }

    @Override
    public boolean doFilter(Request request, Response response) throws IOException {
        for (Object params : request.getParameterMap().values()) {
            for (String param : (String[]) params) {
                if (param.contains(BEAST_NUMBER)) {
                    response.sendRedirect(request.getContextPath() + "/error");
                    return false;
                }
            }
        }
        if (innerFilter != null) {
            return innerFilter.doFilter(request, response);
        }
        return true;
    }
}
