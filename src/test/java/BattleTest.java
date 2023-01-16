import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.powermock.api.mockito.PowerMockito.spy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

//@RunWith(PowerMockRunner.class)
@PrepareForTest(Battle.class)
public class BattleTest {

    @Test
    public void startGame1() throws Exception {
        ByteArrayInputStream in =
                new ByteArrayInputStream(
                        ("\n\n\n\n").getBytes());
        System.setIn(in);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Battle mock = spy(new Battle());

        PowerMockito.doNothing().when(mock, "playersInput");
        PowerMockito.doNothing().when(mock, "playersShoot");

        mock.startGame();

        assertTrue(byteArrayOutputStream.toString()
                .contains("Press Enter and pass the move to another player"));
    }

    @Test
    public void playersShoot() throws Exception {
        ByteArrayInputStream in =
                new ByteArrayInputStream(
                        ("A1\nA5\nC1\nC4\nE1\nE3\nG1\nG3\nI1\nI2\n\n\nA1\nA5\nC1\nC4\nE1\nE3\nG1\nG3\nI1\nI2\n\n\n" +
                                "A2\n\n\nA2\n\n\nA3\n\n\nA3\n\n\nA4\n\n\nA4\n\n\nA5\n\n\nA5\n\n\nC1\n\n\nA6\n\n\nC2\n\n\nA7\n\n\nC3\n\n\nC1\n\n\n" +
                                "C4\n\n\nC4\n\n\nE1\n\n\nE6\n\n\nE2\n\n\nE7\n\n\nE3\n\n\nE3\n\n\nG1\n\n\nG1\n\n\nG3\n\n\nG3\n\n\nG2\n\n\nG7\n\n\n" +
                                "I1\n\n\nI1\n\n\nI2\nvI2\n\n\n" +
                                "A1\n\n\nA1\n\n\n").getBytes());
        System.setIn(in);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Battle mock = spy(new Battle());

        mock.playersInput();
        mock.playersShoot();

        assertTrue(byteArrayOutputStream.toString()
                .contains("You won. Congratulations!"));
    }

    @Test
    public void playersInput() throws Exception {
        ByteArrayInputStream in =
                new ByteArrayInputStream(
                        ("A1\nA5\nC1\nC4\nE1\nE3\nG1\nG3\nI1\nI2\n\n\nA1\nA5\nC1\nC4\nE1\nE3\nG1\nG3\nI1\nI2\n\n\n" +
                                "A2\n\n\nA2\n\n\nA3\n\n\nA3\n\n\nA4\n\n\nA4\n\n\nA5\n\n\nA5\n\n\nC1\n\n\nA6\n\n\nC2\n\n\nA7\n\n\nC3\n\n\nC1\n\n\n" +
                                "C4\n\n\nC4\n\n\nE1\n\n\nE6\n\n\nE2\n\n\nE7\n\n\nE3\n\n\nE3\n\n\nG1\n\n\nG1\n\n\nG3\n\n\nG3\n\n\nG2\n\n\nG7\n\n\n" +
                                "I1\n\n\nI1\n\n\nI2\nvI2\n\n\n" +
                                "A1\n\n\nA1\n\n\n").getBytes());
        System.setIn(in);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Battle mock = spy(new Battle());
        mock.playersInput();

        assertTrue(byteArrayOutputStream.toString()
                .contains("Press Enter and pass the move to another player"));
        assertTrue(byteArrayOutputStream.toString()
                .contains("place your ships to the game field"));
    }

}