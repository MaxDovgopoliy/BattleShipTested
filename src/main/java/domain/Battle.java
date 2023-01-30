package domain;

import Ships.Ship;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Battle {
    Scanner scanner = new Scanner(System.in);
    private List<Field> fields = new ArrayList<>();
    private Ship[] ships = Ship.values();
    private final int FIRST_ROW = 'A';

    public void startGame() {
        playersInput();
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        scanner.nextLine();
        playersShoot();
    }

    public void playersShoot() {
        int player = 0;
        boolean isWin = false;
        while (!isWin) {
            switch (player) {
                case 0:
                    firstPlayerMakeShoot();
                    if (fields.get(1).getHealth() == 0) {
                        isWin = true;
                        break;
                    }
                    player = 1;
                    System.out.println("Press Enter and pass the move to another player");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
                case 1:
                    secondPlayerMakeShoot();
                    if (fields.get(0).getHealth() == 0) {
                        isWin = true;
                        break;
                    }
                    player = 0;
                    System.out.println("Press Enter and pass the move to another player");
                    scanner.nextLine();
                    scanner.nextLine();
            }
        }
    }

    public void secondPlayerMakeShoot() {
        fields.get(0).printHiddenField();
        System.out.println("---------------------");
        fields.get(1).printField();
        System.out.println("\nPlayer 2, it's your turn:");
        while (fields.get(0).getHealth() != 0) {
            String shot = scanner.next();
            int x = Integer.parseInt(shot.substring(1)) - 1;
            int y = shot.charAt(0) - FIRST_ROW;
            boolean isShot = fields.get(0).makeShot(x, y);
            if (isShot) {
                break;
            }
        }
    }

    public void playersInput() {
        for (int i = 0; i < 2; i++) {
            fields.add(new Field());
            if (i == 1) {
                System.out.println("\nPress Enter and pass the move to another player\n" +
                        "...");
                scanner.nextLine();
                scanner.nextLine();
            }
            System.out.println("Player " + (i + 1) + ", place your ships to the game field");
            fields.get(i).printField();
            for (Ship ship : ships) {
                System.out.printf("\nEnter the coordinates of the %s (%d cells):\n\n",
                        ship.getName(), ship.getSize());
                while (true) {
                    if ( isPLaced(i, ship)) {
                        fields.get(i).printField();
                        break;
                    }
                }
            }

        }
    }

    private boolean isPLaced(int i, Ship ship) {
        String coordinate1 = scanner.nextLine();
        String coordinate2 = scanner.nextLine();
        int row1 = coordinate1.charAt(0) - FIRST_ROW;
        int col1 = Integer.parseInt(coordinate1.substring(1)) - 1;
        int row2 = coordinate2.charAt(0) - FIRST_ROW;
        int col2 = Integer.parseInt(coordinate2.substring(1)) - 1;
        return fields.get(i).placeShip(row1, col1, row2, col2, ship);
    }

    public void firstPlayerMakeShoot() {
        fields.get(1).printHiddenField();
        System.out.println("---------------------");
        fields.get(0).printField();
        System.out.println("\nPlayer 1, it's your turn:");
        while (fields.get(1).getHealth() != 0) {
            String shot = scanner.next();
            int x = Integer.parseInt(shot.substring(1)) - 1;
            int y = shot.charAt(0) - FIRST_ROW;
            boolean isShot = fields.get(1).makeShot(x, y);
            if (isShot) {
                break;
            }
        }
    }
}
