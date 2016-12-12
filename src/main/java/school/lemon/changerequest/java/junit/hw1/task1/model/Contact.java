package school.lemon.changerequest.java.junit.hw1.task1.model;

/**
 * Class that represents some abstract Contact with call number, name and surname
 */
public class Contact {
    private Long id;
    private CallNumber callNumber;
    private String name;
    private String surname;

    public Contact() {
    }

    public Contact(Long id, CallNumber callNumber, String name, String surname) {
        this.id = id;
        this.callNumber = callNumber;
        this.name = name;
        this.surname = surname;
    }

    public Contact(Contact contact) {
        this.id = contact.id;
        this.callNumber = contact.callNumber;
        this.name = contact.name;
        this.surname = contact.surname;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", callNumber=" + callNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (id != null ? !id.equals(contact.id) : contact.id != null) return false;
        if (callNumber != null ? !callNumber.equals(contact.callNumber) : contact.callNumber != null) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        return surname != null ? surname.equals(contact.surname) : contact.surname == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (callNumber != null ? callNumber.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CallNumber getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(CallNumber callNumber) {
        this.callNumber = callNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
