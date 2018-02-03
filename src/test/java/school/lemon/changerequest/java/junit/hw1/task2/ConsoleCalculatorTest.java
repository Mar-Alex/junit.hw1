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


    private static final String MENU = "Console calculator:\n" +
            "Enter 'add' to perform addition.\n" +
            "Enter 'sub' to perform subtraction.\n" +
            "Enter 'mul' to perform multiplication.\n" +
            "Enter 'div' to perform division.\n" +
            "Enter 'exit' to exit.\n" +
            "Enter 'help' to see help message.";

    @Test
    public void testAddition() {
        // given
        systemInMock.provideLines("add", "1", "2",
                "add", "-3", "4",
                "add", "5", "-6",
                "add", "-7", "-8", "exit");
        // when
        ConsoleCalculator.main(new String[]{});
        // then
        Mockito.verify(out, Mockito.times(4)).println("Enter first number:");
        Mockito.verify(out, Mockito.times(4)).println("Enter second number:");
        Mockito.verify(out, Mockito.times(1)).println("Result of 1+2 is 3");
        Mockito.verify(out, Mockito.times(1)).println("Result of -3+4 is 1");
        Mockito.verify(out, Mockito.times(1)).println("Result of 5+-6 is -1");
        Mockito.verify(out, Mockito.times(1)).println("Result of -7+-8 is -15");
    }

    @Test
    public void testSubtraction() {
        // given
        systemInMock.provideLines("sub", "1", "2",
                "sub", "-3", "4",
                "sub", "5", "-6",
                "sub", "-7", "-8", "exit");
        // when
        ConsoleCalculator.main(new String[]{});
        // then
        Mockito.verify(out, Mockito.times(4)).println("Enter first number:");
        Mockito.verify(out, Mockito.times(4)).println("Enter second number:");
        Mockito.verify(out, Mockito.times(1)).println("Result of 1-2 is -1");
        Mockito.verify(out, Mockito.times(1)).println("Result of -3-4 is -7");
        Mockito.verify(out, Mockito.times(1)).println("Result of 5--6 is 11");
        Mockito.verify(out, Mockito.times(1)).println("Result of -7--8 is 1");
    }

    @Test
    public void testMultiplying() {
        // given
        systemInMock.provideLines("mul", "1", "2",
                "mul", "-3", "4",
                "mul", "5", "-6",
                "mul", "-7", "-8", "exit");
        // when
        ConsoleCalculator.main(new String[]{});
        // then
        Mockito.verify(out, Mockito.times(4)).println("Enter first number:");
        Mockito.verify(out, Mockito.times(4)).println("Enter second number:");
        Mockito.verify(out, Mockito.times(1)).println("Result of 1*2 is 2");
        Mockito.verify(out, Mockito.times(1)).println("Result of -3*4 is -12");
        Mockito.verify(out, Mockito.times(1)).println("Result of 5*-6 is -30");
        Mockito.verify(out, Mockito.times(1)).println("Result of -7*-8 is 56");
    }

    @Test
    public void testDividing() {
        // given
        systemInMock.provideLines("div", "2", "1",
                "div", "-5", "2",
                "div", "5", "-3",
                "div", "-6", "-2", "exit");
        // when
        ConsoleCalculator.main(new String[]{});
        // then
        Mockito.verify(out, Mockito.times(4)).println("Enter first number:");
        Mockito.verify(out, Mockito.times(4)).println("Enter second number:");
        Mockito.verify(out, Mockito.times(1)).println("Result of 2/1 is 2");
        Mockito.verify(out, Mockito.times(1)).println("Result of -5/2 is -2");
        Mockito.verify(out, Mockito.times(1)).println("Result of 5/-3 is -1");
        Mockito.verify(out, Mockito.times(1)).println("Result of -6/-2 is 3");
    }

    @Test
    public void testDividingZero() {
        // given
        systemInMock.provideLines("div", "0", "2",
                "div", "0", "-3", "exit");
        // when
        ConsoleCalculator.main(new String[]{});
        // then
        Mockito.verify(out, Mockito.times(2)).println("Enter first number:");
        Mockito.verify(out, Mockito.times(2)).println("Enter second number:");
        Mockito.verify(out, Mockito.times(1)).println("Result of 0/2 is 0");
        Mockito.verify(out, Mockito.times(1)).println("Result of 0/-3 is 0");
    }

    @Test(expected = ArithmeticException.class)
    public void testDividingByZero() {
        // given
        systemInMock.provideLines("div", "3", "0", "exit");
        // when
        ConsoleCalculator.main(new String[]{});
    }

    @Test
    public void testExit() {
        // given
        systemInMock.provideLines("exit");
        // when
        ConsoleCalculator.main(new String[]{});
        // then
        Mockito.verify(out, Mockito.times(1)).println("Bye-bye.");
    }

    @Test
    public void testMenu() {
        // given
        systemInMock.provideLines("exit");
        // when
        ConsoleCalculator.main(new String[]{});
        // then
        Mockito.verify(out, Mockito.times(1)).println(MENU);
    }

    @Test
    public void testHelp() {
        // given
        systemInMock.provideLines("help", "exit");
        // when
        ConsoleCalculator.main(new String[]{});
        // then
        Mockito.verify(out, Mockito.times(2)).println(MENU);
    }

    @Test
    public void testIncorrectChoice() {
        // given
        systemInMock.provideLines("incorrect choice", "exit");
        // when
        ConsoleCalculator.main(new String[]{});
        // then
        Mockito.verify(out, Mockito.times(1)).println("Non-appropriate choice. please type 'help' to see help message.");
    }
}
