package by.bsu.contacts.command;

import by.bsu.contacts.controller.Request;
import by.bsu.contacts.controller.Response;
import by.bsu.contacts.xslt.XsltHelper;

import java.io.IOException;

public class ErrorCommand implements Command {
    private static String XSLT_VIEW = "/xslt/error.xsl";

    @Override
    public void execute(Request request, Response response) throws IOException {
        XsltHelper.transform(null, XSLT_VIEW, response.getWriter());
    }
}
