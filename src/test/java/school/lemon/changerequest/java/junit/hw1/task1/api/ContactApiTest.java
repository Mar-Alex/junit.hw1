package school.lemon.changerequest.java.junit.hw1.task1.api;

import org.junit.Before;
import org.junit.Test;

import school.lemon.changerequest.java.junit.hw1.task1.model.*;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.*;

public class ContactApiTest {

    private static ContactApi contactApi = null;

    private static final CallNumber testCallNumber = new CallNumber("+1", "2345", "6789012");
    private static final Contact testContact =
            new Contact((long) 0, testCallNumber, "Alex", "Marukhnenko");

    private static final int numberOfInitContacts = 3;
    private static final long[] initContactsID = {1000, 1001, 1002};

    @Before
    public void setUp() {
        contactApi = new ContactApiImpl(new ContactIdGeneratorImpl());
    }

    @Test
    public void shouldFindExistingContact() {
        contactApi.create(testContact);
        Contact foundContact = contactApi.find(testCallNumber);
        assertEquals(testContact, foundContact);
    }

    @Test(expected = ContactNotFoundException.class)
    public void shouldThrowContactNotFoundExceptionOnNotExistingContactInMethodFind() {
        contactApi.find(new CallNumber("a", "bcde", "fghi"));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionOnNullValueArgumentInMethodFind() {
        contactApi.find(null);
    }


    @Test
    public void shouldGetExistingContact() {
        Contact[] list = new Contact[numberOfInitContacts];
        for (int i = 0; i < numberOfInitContacts; i++)
            list[i] = contactApi.get(initContactsID[i]);

        initContactsTest(list);

        fillWithContactsAndAddTestContactToTheEnd();
        Contact last = contactApi.get(testContact.getId());
        assertEquals(last, testContact);
    }

    private void initContactsTest(Contact[] list) {
        notNull(list);
        assertTrue(list.length >= numberOfInitContacts);

        boolean firstRes = cmpWithContact(list[0], initContactsID[0], "Iurii", "Sergiichuk");
        assertTrue(firstRes);

        boolean secondRes = cmpWithContact(list[1], initContactsID[1], "Dmytro", "Mudrov");
        assertTrue(secondRes);

        boolean thirdRes = cmpWithContact(list[2], initContactsID[2], "Test", "Test");
        assertTrue(thirdRes);
    }

    private boolean cmpWithContact(Contact contact, Long id, String name, String surname) {
        notNull(contact);
        if (id != null ? !id.equals(contact.getId()) : contact.getId() != null) return false;
        if (name != null ? !name.equals(contact.getName()) : contact.getName() != null) return false;
        return surname != null ? surname.equals(contact.getSurname()) : contact.getSurname() == null;
    }


    @Test(expected = ContactNotFoundException.class)
    public void shouldThrowContactNotFoundExceptionOnNotExistingContactInMethodGet() {
        contactApi.get((long) 0);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionOnNullValueArgumentInMethodGet() {
        contactApi.get(null);
    }


    @Test
    public void shouldDeleteExistingContact() {
        Contact[] list = contactApi.getAll();

        boolean result = contactApi.delete(initContactsID[1]);
        assertTrue(result);
        assertEquals(2, contactApi.filledContactsAmount());
        Contact[] afterDelete = contactApi.getAll();

        assertEquals(list[0], afterDelete[0]);
        assertEquals(list[2], afterDelete[1]);

        contactApi.delete(initContactsID[2]);
        assertEquals(1, contactApi.filledContactsAmount());
        afterDelete = contactApi.getAll();

        assertEquals(list[0], afterDelete[0]);

        contactApi.delete(initContactsID[0]);
        assertEquals(0, contactApi.filledContactsAmount());
    }

    @Test
    public void shouldTryToDeleteNotExistingContact() {
        boolean result = contactApi.delete((long) 0);
        assertFalse(result);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionOnNullValueArgumentInMethodDelete() {
        contactApi.delete(null);
    }


    @Test
    public void shouldUpdateExistingContact() {
        Contact beforeUpdate = contactApi.get(initContactsID[2]);
        beforeUpdate.setName("TestName");
        beforeUpdate.setSurname("TestSurName");
        boolean result = contactApi.update(beforeUpdate);
        Contact afterUpdate = contactApi.get(initContactsID[2]);
        assertEquals(beforeUpdate, afterUpdate);
        assertTrue(result);
    }

    @Test
    public void shouldTryToUpdateNotExistingContact() {
        boolean result = contactApi.update(testContact);
        assertFalse(result);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionOnNullValueArgumentInMethodUpdate() throws Exception {
        contactApi.update(null);
    }

    @Test
    public void shouldCreateNewContact() {
        assertEquals(numberOfInitContacts, contactApi.filledContactsAmount());
        try {
            contactApi.find(testContact.getCallNumber());
            fail();
        } catch (ContactNotFoundException e) {
        }

        contactApi.create(testContact);
        assertEquals(numberOfInitContacts + 1, contactApi.filledContactsAmount());

        Contact result = contactApi.find(testContact.getCallNumber());
        assertEquals(testContact, result);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionOnNullValueArgumentInMethodCreate() {
        contactApi.create(null);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowIllegalStateExceptionOnCreatingNewContactInFilledArray() {
        fillWithContactsAndAddTestContactToTheEnd();
        Contact contact = new Contact((long) 0, testCallNumber, "Test", "Test");
        contactApi.create(contact);
    }

    @Test
    public void shouldGetAllContacts() {
        Contact[] list = contactApi.getAll();
        initContactsTest(list);

        contactApi.create(testContact);
        list = contactApi.getAll();
        initContactsTest(list);
        assertEquals(testContact, list[3]);
    }

    @Test
    public void shouldGetEmptyArray() {
        deleteAllInitContacts();
        Contact[] emptyList = contactApi.getAll();
        assertEquals(0, emptyList.length);
    }

    private void deleteAllInitContacts() {
        if (contactApi != null) {
            for (int i = 0; i < numberOfInitContacts; i++)
                contactApi.delete(initContactsID[i]);
        }
    }

    @Test
    public void shouldGetContactsAmountAfterDifferentActions() {
        int amount = contactApi.filledContactsAmount();
        assertEquals(numberOfInitContacts, amount);

        contactApi.create(testContact);
        amount = contactApi.filledContactsAmount();
        assertEquals(numberOfInitContacts + 1, amount);

        contactApi.delete((long) 1003);
        amount = contactApi.filledContactsAmount();
        assertEquals(numberOfInitContacts, amount);

        fillWithContactsAndAddTestContactToTheEnd();
        amount = contactApi.filledContactsAmount();
        assertEquals(ContactApi.CONTACT_API_ARRAY_SIZE, amount);
    }

    private void fillWithContactsAndAddTestContactToTheEnd() {
        for (int i = 0; i < ContactApi.CONTACT_API_ARRAY_SIZE - numberOfInitContacts - 1; i++) {
            Contact contact = new Contact((long) 0, new CallNumber(randomNumeric(2), randomNumeric(3), randomNumeric(8)), "Test" + i, "Test" + i);
            contactApi.create(contact);
        }
        contactApi.create(testContact);
    }


    private void notNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }
}