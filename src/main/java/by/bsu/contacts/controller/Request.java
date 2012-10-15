package by.bsu.contacts.controller;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface Request {
    Map getParameterMap();
    String getContextPath();
    String getCommandName();
    String getParameter(String s);
    HttpSession getSession();
    String getMethod();
    boolean isPost();
    String getRequestId();
}
