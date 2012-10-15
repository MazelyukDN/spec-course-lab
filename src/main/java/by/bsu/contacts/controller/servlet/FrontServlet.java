package by.bsu.contacts.controller.servlet;

import by.bsu.contacts.controller.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontServlet extends javax.servlet.http.HttpServlet{
    private FrontController controller;

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Request request = new HttpRequest(req);
        Response response = new HttpResponse(resp);
        controller.process(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        AppHelper.getInstance().setContext(getServletContext());
        controller = new FrontController();
    }
}


