import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;

import domain.Battle;
import domain.Field;
import Ships.Ship;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest(Battle.class)
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
        field.makeShot(11,11);
        field.makeShot(1,1);
        field.makeShot(1,1);
        field.makeShot(2,1);
        field.makeShot(6,6);
        field.makeShot(3,3);
        field.makeShot(3,3);
        field.makeShot(3,4);

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


    @Test
    public void testPlaceShip() {
        Field mock = spy(new Field());
        boolean res1 = mock.placeShip(1, 2, 3, 4, Ship.DESTROYER);
        boolean res2 = mock.placeShip(2, 2, 4, 2, Ship.CRUISER);
        assertFalse(res1);
        assertTrue(res2);

    }

    @Test
    public void printField() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Field mock = spy(new Field());
        mock.printField();
        assertTrue(byteArrayOutputStream.toString()
                .contains("  1 2 3 4 5 6 7 8 9 10\n" +
                        "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                        "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n"));
    }

    @Test
    public void shot() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Field mock = spy(new Field());
        mock.placeShip(2, 2, 3, 2, Ship.DESTROYER);
        mock.makeShot(29,29);
        mock.makeShot(1,1);
        mock.makeShot(2,2);
        mock.makeShot(2,2);
       verify(mock).miss(1,1);
       verify(mock).hit(2,2);
       verify(mock).alreadyHit();
        assertTrue(byteArrayOutputStream.toString()
                .contains("Error! You entered the wrong coordinates! Try again:"));
    }
    @Test
    public void alreadyHit() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Field mock = spy(new Field());
        mock.alreadyHit();
        assertTrue(byteArrayOutputStream.toString()
                .contains("\nYou hit a ship!"));
    }
    @Test
    public void miss() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Field mock = spy(new Field());
        mock.miss(1,1);
        assertTrue(byteArrayOutputStream.toString()
                .contains("You missed!"));
    }
    @Test
    public void hit() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Field mock = spy(new Field());
        mock.hit(2,2);
        assertTrue(byteArrayOutputStream.toString()
                .contains("You sank a ship!"));
    }
}