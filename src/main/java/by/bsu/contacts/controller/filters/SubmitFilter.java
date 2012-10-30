package by.bsu.contacts.controller.filters;

import by.bsu.contacts.controller.Request;
import by.bsu.contacts.controller.Response;

import javax.servlet.http.HttpSession;
import java.io.IOException;


public class SubmitFilter implements IFilter {

    private static int c = 0;
    private IFilter innerFilter;

    public SubmitFilter(IFilter inner) {
        innerFilter = inner;
    }

    @Override
    public boolean doFilter(Request request, Response response) throws IOException {
        HttpSession session = request.getSession();

        String id = request.getRequestId();
        String formId = request.getParameter("reqId");

        if (request.isPost() && id != null && !id.equals(formId)) {
            response.sendInAppRedirect("/error");
            return false;
        }
        if (request.isPost()){
            session.setAttribute("reqId", ++c);
        }

        if (innerFilter != null) {
            return innerFilter.doFilter(request, response);
        }
        return true;
    }
}
