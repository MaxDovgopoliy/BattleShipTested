import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FieldTest {

    @Test
    void placeShip() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Field field = new Field();
        field.placeShip(1, 2, 3, 4, Ship.DESTROYER);
        field.placeShip(1, 2, 1, 1, Ship.BATTLESHIP);
        field.placeShip(1, 2, 1, 1, Ship.DESTROYER);
        field.placeShip(1, 3, 1, 4, Ship.DESTROYER);

        field.placeShip(4, 3, 3, 3, Ship.BATTLESHIP);
        field.placeShip(4, 3, 3, 3, Ship.DESTROYER);
        field.placeShip(4, 4, 3, 4, Ship.DESTROYER);
        field.printField();
        field.shot(11,11);
        field.shot(1,1);
        field.shot(1,1);
        field.shot(2,1);
        field.shot(6,6);
        field.shot(3,3);
        field.shot(3,3);
        field.shot(3,4);

        List<String> expectedValue =List.of("You sank the last ship. You won. Congratulations!",
                "You hit a ship!","  1 2 3 4 5 6 7 8 9 10\n" +
                        "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "B ~ X X ~ ~ ~ ~ ~ ~ ~\n" +
                        "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "G ~ ~ ~ ~ ~ ~ M ~ ~ ~\n" +
                        "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
        assertTrue( byteArrayOutputStream.toString().contains(expectedValue.get(0)));
        assertTrue( byteArrayOutputStream.toString().contains(expectedValue.get(1)));
        assertTrue( byteArrayOutputStream.toString().contains(expectedValue.get(2)));
        System.setOut(System.out);
    }


}