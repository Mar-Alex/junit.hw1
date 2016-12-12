package school.lemon.changerequest.java.junit.hw1.task2;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleCalculatorTest {
    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    private PrintStream originalOut = System.out;
    @Mock
    private PrintStream out;

    @Before
    public void setUp() {
        System.setOut(out);
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testAddition() {
        // given
        systemInMock.provideLines("add", "1", "2", "exit");
        // when
        ConsoleCalculator.main(new String[]{});
        // then
        Mockito.verify(out, Mockito.times(1)).println("Enter first number:");
        Mockito.verify(out, Mockito.times(1)).println("Enter second number:");
        Mockito.verify(out, Mockito.times(1)).println("Result of 1+2 is 3");
    }
}
