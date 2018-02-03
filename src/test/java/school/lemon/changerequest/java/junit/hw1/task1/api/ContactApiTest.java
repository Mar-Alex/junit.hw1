package school.lemon.changerequest.java.junit.hw1.task1.api;

import org.junit.Before;
import org.junit.Test;

import school.lemon.changerequest.java.junit.hw1.task1.model.*;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.*;

public class ContactApiTest {

    ContactApi contactApi = null;
    Contact testContact = null;

    @Before
    public void setUp() {
        contactApi = new ContactApiImpl(new ContactIdGeneratorImpl());
        testContact = new Contact((long) 0, new CallNumber("+1", "2345", "6789012"), "Alex", "Marukhnenko");
    }

    @Test
    public void findTest() {
        contactApi.create(testContact);
        Contact foundContact = contactApi.find(new CallNumber("+1", "2345", "6789012"));
        assertEquals(testContact, foundContact);
    }

    @Test(expected = ContactNotFoundException.class)
    public void findNotExistedContactTest() {
        contactApi.find(new CallNumber("a", "bcde", "fghi"));
    }

    @Test(expected = NullPointerException.class)
    public void findNullTest() {
        contactApi.find(null);
    }


    @Test
    public void getTest() {
        Contact[] list = new Contact[3];
        for (int i = 0; i < 3; i++)
            list[i] = contactApi.get((long) (1000 + i));

        initContactsTest(list);

        fillWithContacts();
        Contact last = contactApi.get(testContact.getId());
        assertEquals(last, testContact);
    }

    @Test(expected = ContactNotFoundException.class)
    public void getNotExistedContactTest() {
        contactApi.get((long) 0);
    }

    @Test(expected = NullPointerException.class)
    public void getNullTest() {
        contactApi.get(null);
    }

    private boolean cmpWithContact(Contact contact, Long id, String name, String surname) {
        if (id != null ? !id.equals(contact.getId()) : contact.getId() != null) return false;
        if (name != null ? !name.equals(contact.getName()) : contact.getName() != null) return false;
        return surname != null ? surname.equals(contact.getSurname()) : contact.getSurname() == null;
    }

    @Test
    public void deleteTest() {
        Contact[] list = contactApi.getAll();

        boolean result = contactApi.delete((long) 1001);
        assertTrue(result);
        assertEquals(2, contactApi.filledContactsAmount());
        Contact[] afterDelete = contactApi.getAll();

        assertEquals(list[0], afterDelete[0]);
        assertEquals(list[2], afterDelete[1]);

        contactApi.delete((long) 1002);
        assertEquals(1, contactApi.filledContactsAmount());
        afterDelete = contactApi.getAll();

        assertEquals(list[0], afterDelete[0]);

        contactApi.delete((long) 1000);
        assertEquals(0, contactApi.filledContactsAmount());
    }

    @Test
    public void deleteNotExistedContactTest() {
        boolean result = contactApi.delete((long) 0);
        assertFalse(result);
    }

    @Test(expected = NullPointerException.class)
    public void deleteNullTest() {
        contactApi.delete(null);
    }


    @Test
    public void updateTest() {
        Contact beforeUpdate = contactApi.get((long) 1002);
        beforeUpdate.setName("TestName");
        beforeUpdate.setSurname("TestSurName");
        boolean result = contactApi.update(beforeUpdate);
        Contact afterUpdate = contactApi.get((long) 1002);
        assertEquals(beforeUpdate, afterUpdate);
        assertTrue(result);
    }

    @Test
    public void updateNotExistedContactTest() {
        boolean result = contactApi.update(testContact);
        assertFalse(result);
    }

    @Test(expected = NullPointerException.class)
    public void updateNullTest() throws Exception {
        contactApi.update(null);
    }

    @Test
    public void createTest() {
        assertEquals(3, contactApi.filledContactsAmount());
        try {
            contactApi.find(testContact.getCallNumber());
            fail();
        } catch (ContactNotFoundException e) {
        }

        contactApi.create(testContact);
        assertEquals(4, contactApi.filledContactsAmount());

        Contact result = contactApi.find(testContact.getCallNumber());
        assertEquals(testContact, result);
    }

    @Test(expected = NullPointerException.class)
    public void createNullTest() {
        contactApi.create(null);
    }

    @Test(expected = IllegalStateException.class)
    public void createFullArrayTest() {
        fillWithContacts();
        Contact contact = new Contact((long) 0, new CallNumber(randomNumeric(2), randomNumeric(3), randomNumeric(8)), "Test", "Test");
        contactApi.create(contact);
    }

    @Test
    public void getAllTest() {
        Contact[] list = contactApi.getAll();
        initContactsTest(list);

        contactApi.create(testContact);
        list = contactApi.getAll();
        initContactsTest(list);
        assertEquals(testContact, list[3]);
    }

    @Test
    public void getAllEmptyListTest() {
        deleteAllInitContacts();
        Contact[] emptyList = contactApi.getAll();
        assertEquals(0, emptyList.length);
    }

    private void initContactsTest(Contact[] list) {
        boolean firstRes = cmpWithContact(list[0], (long) 1000, "Iurii", "Sergiichuk");
        assertTrue(firstRes);

        boolean secondRes = cmpWithContact(list[1], (long) 1001, "Dmytro", "Mudrov");
        assertTrue(secondRes);

        boolean thirdRes = cmpWithContact(list[2], (long) 1002, "Test", "Test");
        assertTrue(thirdRes);
    }

    @Test
    public void filledContactsAmountTest() {
        int amount = contactApi.filledContactsAmount();
        assertEquals(3, amount);

        contactApi.create(testContact);
        amount = contactApi.filledContactsAmount();
        assertEquals(4, amount);

        contactApi.delete((long) 1003);
        amount = contactApi.filledContactsAmount();
        assertEquals(3, amount);

        fillWithContacts();
        amount = contactApi.filledContactsAmount();
        assertEquals(ContactApi.CONTACT_API_ARRAY_SIZE, amount);
    }

    private void deleteAllInitContacts() {
        if (contactApi != null) {
            contactApi.delete((long) 1000);
            contactApi.delete((long) 1001);
            contactApi.delete((long) 1002);
        }
    }

    private void fillWithContacts() {
        for (int i = 0; i < ContactApi.CONTACT_API_ARRAY_SIZE - 4; i++) {
            Contact contact = new Contact((long) 0, new CallNumber(randomNumeric(2), randomNumeric(3), randomNumeric(8)), "Test" + i, "Test" + i);
            contactApi.create(contact);
        }
        contactApi.create(testContact);
    }
}