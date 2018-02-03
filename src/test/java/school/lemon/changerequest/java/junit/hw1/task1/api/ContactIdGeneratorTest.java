package school.lemon.changerequest.java.junit.hw1.task1.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactIdGeneratorTest {

    ContactIdGenerator generator = null;

    @Before
    public void init() {
        generator = new ContactIdGeneratorImpl();
    }

    @Test
    public void generateWithSameBeginTest() {
        assertEquals(generator.generate(), new ContactIdGeneratorImpl().generate());
    }


    @Test
    public void stepOfGenerationTest() {
        Long first = generator.generate();
        Long second = generator.generate();
        long step = second - first;
        assertEquals(1, step);
    }

    @Test(expected = IllegalStateException.class)
    public void generatingLimitTest() throws Exception {
        long interval = ContactIdGenerator.MAXIMUM_ID_NUMBER - generator.generate();
        try {
            for (int i = 0; i < interval - 1; i++)
                generator.generate();
        } catch (IllegalStateException e) {
            throw new Exception("Exception was thrown before achieving maximum ID");
        }
        generator.generate();
    }


}