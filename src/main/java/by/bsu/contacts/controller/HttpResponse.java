package by.bsu.contacts.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class HttpResponse implements Response {
    private HttpServletResponse response;

    public HttpResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return response.getWriter();
    }

    @Override
    public void sendRedirect(String s) throws IOException {
        response.sendRedirect(s);
    }

    @Override
    public void sendInAppRedirect(String s) throws IOException {
        response.sendRedirect(AppHelper.getInstance().getContextPath() + s);
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return response.getOutputStream();
    }
}
