package by.bsu.contacts.command;

import by.bsu.contacts.controller.Request;
import by.bsu.contacts.controller.Response;
import by.bsu.contacts.domain.Contact;

import java.io.IOException;

public class DeleteContactCommand implements Command {

    @Override
    public void execute(Request request, Response response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Contact contact = new Contact(id);
        Contact.delete(contact);
        response.sendInAppRedirect("/");
    }
}
