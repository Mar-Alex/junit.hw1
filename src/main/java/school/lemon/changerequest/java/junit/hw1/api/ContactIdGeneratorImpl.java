package school.lemon.changerequest.java.junit.hw1.api;

/**
 * Some weird implementation of ContactIdGenerator
 */
public class ContactIdGeneratorImpl implements ContactIdGenerator {
    private volatile long lastId = 1000;

    @Override
    public Long generate() {
        if (lastId < MAXIMUM_ID_NUMBER)
            return lastId++;
        throw new IllegalStateException("Maximum id number exceeded limits");
    }
}
