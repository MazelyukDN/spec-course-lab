package by.bsu.contacts.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public interface Response {
    PrintWriter getWriter() throws IOException;
    OutputStream getOutputStream() throws IOException;
    void sendRedirect(String s) throws IOException;
    void sendInAppRedirect(String s) throws IOException;
}
