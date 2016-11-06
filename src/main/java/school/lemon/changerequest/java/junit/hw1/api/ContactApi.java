package school.lemon.changerequest.java.junit.hw1.api;

import school.lemon.changerequest.java.junit.hw1.model.CallNumber;
import school.lemon.changerequest.java.junit.hw1.model.Contact;

import java.util.List;

/**
 * Some API to
 */
public interface ContactApi {
    /**
     * Search contact by call number
     *
     * @param callNumber call number to search contact by
     * @return Contact for given callNumber
     * @throws ContactNotFoundException if contact wasn't found
     */
    Contact find(CallNumber callNumber) throws ContactNotFoundException;

    /**
     * Get contact by id
     *
     * @param id id of contact
     * @return Contact with given id
     * @throws ContactNotFoundException if no contact with such id was found
     */
    Contact get(Long id) throws ContactNotFoundException;

    /**
     * Deletes contact by ID
     *
     * @param id id of contact to delete
     * @return true, if contact was deleted
     */
    boolean delete(Long id);

    /**
     * Updates contact
     *
     * @param contact Contact to update
     * @return true, if contact was updated
     * @throws ContactNotFoundException if contact wasn't found
     */
    boolean update(Contact contact) throws ContactNotFoundException;

    /**
     * Create new contact. ID field will be set automatically
     *
     * @param contact contact data
     * @return contact object with filled ID field
     */
    Contact create(Contact contact);

    List<Contact> getAll();
}