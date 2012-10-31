package by.bsu.contacts.command;

import by.bsu.contacts.controller.Request;
import by.bsu.contacts.controller.Response;
import by.bsu.contacts.dao.ContactsDAO;
import by.bsu.contacts.domain.Contact;
import by.bsu.contacts.valuelisthandler.ContactListHandler;
import by.bsu.contacts.valuelisthandler.IteratorException;
import by.bsu.contacts.xslt.XsltHelper;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.List;

public class ListContactsCommand implements Command {
    private static final String XSLT_VIEW = "/xslt/contacts.xsl";
    public static final int PAGE_COUNT = 5;

    @Override
    public void execute(Request request, Response response) throws IOException, IteratorException {
        ContactListHandler contactListHandler = new ContactListHandler();
        ContactsDAO dao = ContactsDAO.getInstance();

        contactListHandler.setContactDao(dao);
        contactListHandler.executeSearch();

        String parameterPage = request.getParameter("page");
        int currentPage = parameterPage != null ? Integer.valueOf(parameterPage) : 1;
        List<Contact> contacts = null;
        for (int i = 0; i < currentPage; i++) {
            contacts = contactListHandler.getNextElements(PAGE_COUNT);
        }

        int totalPages = (int)Math.ceil(contactListHandler.getSize() / (double)PAGE_COUNT);
        Document contactsXml = XsltHelper.buildContactsXml(contacts, request.getRequestId(), currentPage, totalPages);
        XsltHelper.transform(contactsXml, XSLT_VIEW, response.getWriter());
    }
}
