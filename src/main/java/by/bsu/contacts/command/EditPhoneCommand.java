package by.bsu.contacts.command;

import by.bsu.contacts.controller.Request;
import by.bsu.contacts.controller.Response;
import by.bsu.contacts.dao.ContactsDAO;
import by.bsu.contacts.dao.DataAccessException;
import by.bsu.contacts.domain.Contact;
import by.bsu.contacts.xslt.XsltHelper;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditPhoneCommand implements Command {
    private static String XSLT_VIEW = "/xslt/edit_phone.xsl";

    @Override
    public void execute(Request request, Response response) throws IOException {
        ContactsDAO dao = ContactsDAO.getInstance();
        int id = Integer.parseInt(request.getParameter("id"));
        final Contact contact = new Contact(id);
        if (request.isPost()) {
            try {
                contact.setPhone(request.getParameter("phone"));
            } catch (DataAccessException e) {
                e.printStackTrace();
                response.sendInAppRedirect("/error");
            }
            response.sendInAppRedirect("/");
        } else {
            List<Contact> list = new ArrayList<Contact>(){{ add(contact); }};
            Document contactsXml = XsltHelper.buildContactsXml(list, request.getRequestId());
            XsltHelper.transform(contactsXml, XSLT_VIEW, response.getWriter());
        }
    }
}
