package school.lemon.changerequest.java.junit.hw1.task1.api;

/**
 * Generator of Contacts IDs
 */
public interface ContactIdGenerator {
    long MAXIMUM_ID_NUMBER = 10_000;

    /**
     * Generate new id
     *
     * @return id
     */
    Long generate();
}
