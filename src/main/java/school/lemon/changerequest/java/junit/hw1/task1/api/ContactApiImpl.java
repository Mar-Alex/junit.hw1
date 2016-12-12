package school.lemon.changerequest.java.junit.hw1.task1.api;

import school.lemon.changerequest.java.junit.hw1.task1.model.CallNumber;
import school.lemon.changerequest.java.junit.hw1.task1.model.Contact;

import java.util.Arrays;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static school.lemon.changerequest.java.junit.hw1.task1.api.ContactNotFoundException.NO_CONTACT_FOR_CALL_NUMBER;
import static school.lemon.changerequest.java.junit.hw1.task1.api.ContactNotFoundException.NO_CONTACT_WITH_ID;

public class ContactApiImpl implements ContactApi {
    private final Contact[] contacts = new Contact[CONTACT_API_ARRAY_SIZE];
    private final ContactIdGenerator contactIdGenerator;
    private int size = 0;

    public ContactApiImpl(ContactIdGenerator contactIdGenerator) {
        this.contactIdGenerator = contactIdGenerator;
        init();
    }

    private void init() {
        Long first = contactIdGenerator.generate();
        contacts[size++] = new Contact(first, new CallNumber(randomNumeric(2), randomNumeric(3), randomNumeric(8)), "Iurii", "Sergiichuk");
        Long second = contactIdGenerator.generate();
        contacts[size++] = new Contact(second, new CallNumber(randomNumeric(2), randomNumeric(3), randomNumeric(8)), "Dmytro", "Mudrov");
        Long third = contactIdGenerator.generate();
        contacts[size++] = new Contact(third, new CallNumber("+1", "0000", "0000000"), "Test", "Test");
    }

    @Override
    public Contact find(CallNumber callNumber) throws ContactNotFoundException {
        notNull(callNumber);
        for (Contact contact : contacts) {
            if (callNumber.equals(contact.getCallNumber())) {
                return contact;
            }
        }
        throw new ContactNotFoundException(String.format("Contact with callNumber=%s wasn't found", callNumber),
                NO_CONTACT_FOR_CALL_NUMBER);
    }

    @Override
    public Contact get(Long id) throws ContactNotFoundException {
        notNull(id);
        for (int i = 0; i < size; i++) {
            if (id.equals(contacts[i].getId())) {
                return contacts[i];
            }
        }
        throw new ContactNotFoundException(String.format("Contact with id=%d wasn't found", id), NO_CONTACT_WITH_ID);
    }

    @Override
    public boolean delete(Long id) {
        notNull(id);
        for (int i = 0; i < size; i++) {
            if (id.equals(contacts[i].getId())) {
                System.arraycopy(contacts, i + 1, contacts, i, size - i);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(Contact contact) throws ContactNotFoundException {
        notNull(contact);
        for (int i = 0; i < size; i++) {
            if (contact.getId().equals(contacts[i].getId())) {
                contacts[i] = new Contact(contact);
                return true;
            }
        }
        return false;
    }

    @Override
    public Contact create(Contact contact) {
        checkSize();
        notNull(contact);
        contact.setId(contactIdGenerator.generate());
        contacts[size++] = contact;
        return contact;
    }

    private void checkSize() {
        if (size == contacts.length) {
            throw new IllegalStateException("Array is full!");
        }
    }

    @Override
    public Contact[] getAll() {
        return Arrays.copyOf(contacts, contacts.length);
    }

    private void notNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
    }
}
