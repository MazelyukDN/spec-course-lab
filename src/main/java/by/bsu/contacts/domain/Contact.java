package by.bsu.contacts.domain;

import by.bsu.contacts.dao.ContactsDAO;
import by.bsu.contacts.dao.DataAccessException;
import by.bsu.contacts.dao.UnitOfWork;

public class Contact {
    private int id = -1;
    private String firstName;
    private String lastName;
    private String phone;

    public Contact(String firstName, String lastName, String phone) {
        this.id = getNewId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        try {
            markNew();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Contact(int id){
        this.id = id;
        ContactsDAO dao = ContactsDAO.getInstance();
        Contact c = dao.get(id);
        this.firstName = c.firstName;
        this.lastName = c.lastName;
        this.phone = c.phone;
        markClean();
    }

    public static void delete(Contact contact){
        contact.markRemoved();
    }

    private static int counter = 0;

    private static int getNewId(){
        return ++counter;
    }

    public void save(){
        ContactsDAO dao = ContactsDAO.getInstance();
        dao.save(this);
    }

    public boolean delete() {
        ContactsDAO dao = ContactsDAO.getInstance();
        return dao.delete(this);
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws DataAccessException {
        this.firstName = firstName;
        markDirty();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws DataAccessException {
        this.lastName = lastName;
        markDirty();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws DataAccessException {
        this.phone = phone;
        markDirty();
    }

    protected void markNew() throws DataAccessException {
        UnitOfWork.getCurrent().registerNew(this);
    }

    protected void markClean() {
        UnitOfWork.getCurrent().registerClean(this);
    }

    protected void markDirty() throws DataAccessException {
        UnitOfWork.getCurrent().registerDirty(this);
    }

    protected void markRemoved() {
        UnitOfWork.getCurrent().registerRemoved(this);
    }
}
