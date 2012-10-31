package by.bsu.contacts.valuelisthandler;

import by.bsu.contacts.dao.ContactsDAO;

import java.util.List;

public class ContactListHandler extends ValueListHandler {
    private ContactsDAO contactDAO;

    public void executeSearch() throws IteratorException {
         setList(contactDAO.getAll());
    }

    public void setContactDao(ContactsDAO contactDao) {
        this.contactDAO = contactDao;
    }
}
