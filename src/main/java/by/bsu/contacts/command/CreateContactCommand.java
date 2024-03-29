package by.bsu.contacts.command;

import by.bsu.contacts.controller.Request;
import by.bsu.contacts.controller.Response;
import by.bsu.contacts.domain.Contact;

import java.io.IOException;

public class CreateContactCommand implements Command {
    @Override
    public void execute(Request request, Response response) throws IOException {
        Contact contact = new Contact("", "", "");
        response.sendInAppRedirect("/edit_first_name?id=" + contact.getId());
    }
}
