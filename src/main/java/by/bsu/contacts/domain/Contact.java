package by.bsu.contacts.domain;

public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;

    public Contact(int id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Contact(String firstName, String lastName, String phone) {
        this(getNewId(), firstName, lastName, phone);
    }

    private static int counter = 0;

    private static int getNewId(){
        return ++counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
