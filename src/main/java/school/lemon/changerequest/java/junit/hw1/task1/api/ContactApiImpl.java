package school.lemon.changerequest.java.junit.hw1.task1.api;

import school.lemon.changerequest.java.junit.hw1.task1.model.CallNumber;
import school.lemon.changerequest.java.junit.hw1.task1.model.Contact;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static school.lemon.changerequest.java.junit.hw1.task1.api.ContactNotFoundException.NO_CONTACT_FOR_CALL_NUMBER;
import static school.lemon.changerequest.java.junit.hw1.task1.api.ContactNotFoundException.NO_CONTACT_WITH_ID;

public class ContactApiImpl implements ContactApi {
    private final Map<Long, Contact> contacts = new LinkedHashMap<>();
    private final ContactIdGenerator contactIdGenerator;

    public ContactApiImpl(ContactIdGenerator contactIdGenerator) {
        this.contactIdGenerator = contactIdGenerator;
        init();
    }

    private void init() {
        Long first = contactIdGenerator.generate();
        contacts.put(first, new Contact(first, new CallNumber(randomNumeric(2), randomNumeric(3), randomNumeric(8)), "Iurii", "Sergiichuk"));
        Long second = contactIdGenerator.generate();
        contacts.put(second, new Contact(second, new CallNumber(randomNumeric(2), randomNumeric(3), randomNumeric(8)), "Dmytro", "Mudrov"));
        Long third = contactIdGenerator.generate();
        contacts.put(third, new Contact(third, new CallNumber("+1", "0000", "0000000"), "Test", "Test"));
    }

    @Override
    public Contact find(CallNumber callNumber) throws ContactNotFoundException {
        notNull(callNumber);
        for (Contact contact : contacts.values()) {
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
        Contact contact = contacts.get(id);
        if (contact == null) {
            throw new ContactNotFoundException(String.format("Contact with id=%d wasn't found", id), NO_CONTACT_WITH_ID);
        }
        return contact;
    }

    @Override
    public boolean delete(Long id) {
        notNull(id);
        return contacts.remove(id) != null;
    }

    @Override
    public boolean update(Contact contact) throws ContactNotFoundException {
        notNull(contact);
        Contact contactFromCache = get(contact.getId());
        if (contactFromCache.equals(contact)) {
            return false;
        }
        contacts.put(contact.getId(), new Contact(contact));
        return true;
    }

    @Override
    public Contact create(Contact contact) {
        notNull(contact);
        contact.setId(contactIdGenerator.generate());
        contacts.put(contact.getId(), contact);
        return contact;
    }

    @Override
    public List<Contact> getAll() {
        return new ArrayList<>(contacts.values());
    }

    private void notNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
    }
}
