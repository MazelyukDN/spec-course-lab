package by.bsu.contacts.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class HttpRequest implements Request{
    private HttpServletRequest request;

    public HttpRequest(HttpServletRequest request){
        this.request = request;
    }

    @Override
    public Map getParameterMap() {
        return request.getParameterMap();
    }

    @Override
    public String getMethod() {
        return request.getMethod();
    }

    public static String POST = "POST";
    @Override
    public boolean isPost() {
        return POST.equalsIgnoreCase(getMethod());
    }

    @Override
    public String getContextPath() {
        return request.getContextPath();
    }

    @Override
    public String getParameter(String s) {
        return request.getParameter(s);
    }

    @Override
    public String getCommandName() {
        String uri = request.getRequestURI();
        int last = uri.lastIndexOf("/");
        String commandName = last > 0 ? uri.substring(uri.indexOf("/") + 1, last) : uri.substring(uri.indexOf("/") + 1);
        return (commandName == null || commandName.length() == 0) ? "list" : commandName;
    }

    @Override
    public HttpSession getSession() {
        return request.getSession();
    }

    @Override
    public String getRequestId() {
        Object reqId = getSession().getAttribute("reqId");
        return reqId == null ? null : reqId.toString();
    }
}
