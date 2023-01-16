import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@RunWith(MockitoJUnitRunner.class)
public class BattleTest {
    @Mock
    Field field;
    @Mock
    List<Field> fields;

    @Test
    public void startGame() {
        ByteArrayInputStream in =
                new ByteArrayInputStream(
                        ("A1\nA5\nC1\nC4\nE1\nE3\nG1\nG3\nI1\nI2\n\n\nA1\nA5\nC1\nC4\nE1\nE3\nG1\nG3\nI1\nI2\n\n\n" +
                                "A2\n\n\nA2\n\n\nA3\n\n\nA3\n\n\nA4\n\n\nA4\n\n\nA5\n\n\nA5\n\n\nC1\n\n\nA6\n\n\nC2\n\n\nA7\n\n\nC3\n\n\nC1\n\n\n" +
                                "C4\n\n\nC4\n\n\nE1\n\n\nE6\n\n\nE2\n\n\nE7\n\n\nE3\n\n\nE3\n\n\nG1\n\n\nG1\n\n\nG3\n\n\nG3\n\n\nG2\n\n\nG7\n\n\n" +
                                "I1\n\n\nI1\n\n\nI2\nvI2\n\n\n" +
                                "A1\n\n\nA1\n\n\n\n\n").getBytes());
        System.setIn(in);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);


        when(fields.get(anyInt())).thenReturn(field);
        when(field.placeShip(anyInt(), anyInt(), anyInt(), anyInt(), any())).thenReturn(true);
        when(field.placeShip(anyInt(), anyInt(), anyInt(), anyInt(), any())).thenReturn(true);

//        Battle battle = new Battle();
//        battle.startGame();
        Main.main(new String[2]);
        assertTrue(byteArrayOutputStream.toString()
                .contains("You sank the last ship. You won. Congratulations!"));
    }
}