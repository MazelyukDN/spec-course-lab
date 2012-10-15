package by.bsu.contacts.command;

import by.bsu.contacts.controller.Request;
import by.bsu.contacts.controller.Response;
import by.bsu.contacts.dao.ContactsDAO;
import by.bsu.contacts.xslt.XsltHelper;
import org.w3c.dom.Document;

import java.io.IOException;

public class ListContactsCommand implements Command {
    private static String XSLT_VIEW = "/xslt/contacts.xsl";

    @Override
    public void execute(Request request, Response response) throws IOException {
        ContactsDAO dao = ContactsDAO.getInstance();
        Document contactsXml = XsltHelper.buildContactsXml(dao.getAll(), request.getRequestId());
        XsltHelper.transform(contactsXml, XSLT_VIEW, response.getWriter());
    }
}
