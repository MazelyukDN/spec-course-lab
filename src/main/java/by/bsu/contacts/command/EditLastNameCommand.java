package by.bsu.contacts.command;

import by.bsu.contacts.controller.Request;
import by.bsu.contacts.controller.Response;
import by.bsu.contacts.dao.DataAccessException;
import by.bsu.contacts.domain.Contact;
import by.bsu.contacts.xslt.XsltHelper;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditLastNameCommand implements Command {
    private static final String XSLT_VIEW = "/xslt/edit_last_name.xsl";

    @Override
    public void execute(Request request, Response response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        final Contact contact = new Contact(id);
        if (request.isPost()) {
            try {
                contact.setLastName(request.getParameter("last_name"));
            } catch (DataAccessException e) {
                e.printStackTrace();
                response.sendInAppRedirect("/error");
            }
            response.sendInAppRedirect("/edit_phone?id=" + request.getParameter("id"));
        } else {
            List<Contact> list = new ArrayList<Contact>(){{ add(contact); }};
            Document contactsXml = XsltHelper.buildContactsXml(list, request.getRequestId());
            XsltHelper.transform(contactsXml, XSLT_VIEW, response.getWriter());
        }
    }
}
