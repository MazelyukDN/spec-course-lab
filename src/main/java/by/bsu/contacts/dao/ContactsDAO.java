package by.bsu.contacts.dao;

import by.bsu.contacts.domain.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsDAO {
    private List<Contact> contacts;

    private ContactsDAO(){
        contacts = new ArrayList<Contact>();
        contacts.add(new Contact("Jack", "Johnson", "12345678"));
        contacts.add(new Contact("Nick", "Berton", "99999999"));
        contacts.add(new Contact("Dasha", "Mazelyuk", "+37525 6114942"));
    }

    private static ContactsDAO dao;

    public static ContactsDAO getInstance(){
        if (dao == null){
            dao = new ContactsDAO();
        }
        return dao;
    }

    public List<Contact> getAll(){
        return contacts;
    }

    public Contact get(int id){
        for (Contact contact : contacts){
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    public boolean save(Contact contact) {
        if (contact == null) {
            return false;
        }
        for(int i = 0; i < contacts.size(); i++){
            if (contacts.get(i).getId() == contact.getId()){
                contacts.set(i, contact);
                return true;
            }
        }
        contacts.add(contact);
        return true;
    }

    public boolean delete(Contact contact){
        for(int i = 0; i < contacts.size(); i++){
            if (contacts.get(i).getId() == contact.getId()){
                contacts.remove(i);
                return true;
            }
        }
        return false;
    }

}
