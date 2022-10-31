import java.util.Arrays;
import java.util.Scanner;

public class Field {
    static Scanner scanner=new Scanner(System.in);
    private char[][] field;
    private int health;

    public Field(){
        field = new char[10][10];
        for (char[] chars : field) {
            Arrays.fill(chars, '~');
        }
    }

    public boolean placeShip(int row1,int col1,int row2,int col2,Ship ship){
        if(row1==row2) {
            return this.placeShipHorizontally(row1, col1,col2,ship);
        } else if (col1==col2) {
            return this.placeShipVertically(col1,row1,row2,ship);
        }
        else{
            System.out.println("\nError! Wrong ship location! Try again:\n");
            return false;
        }
    }

    private boolean placeShipVertically(int col1, int row1, int row2, Ship ship) {
        if(row1>row2){
            int tmp=row1;
            row1=row2;
            row2=tmp;
        }
        if((row2-row1)+1!=ship.getSize()){
            System.out.printf("\nError! Wrong length of the %s! Try again:\n\n", ship.getName());
            return false;
        }
        for (int c = col1 - 1; c <= col1 + 1; c++) {
            if (c < 0 || c > 9) {
                continue;
            }
            for (int r = row1 - 1; r <= row2 + 1; r++) {
                if (r < 0 || r > 9) {
                    continue;
                }
                if (this.field[r][c] == 'O') {
                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                    return false;
                }
            }
        }

        for (int row = row1; row <= row2; row++) {
            this.field[row][col1] = 'O';
        }
        this.health += ship.getSize();
        return true;
    }

    private boolean placeShipHorizontally(int row1, int col1, int col2,Ship ship) {
        if(col1>col2){
            int tmp=col1;
            col1=col2;
            col2=tmp;
        }
        if((col2-col1)+1!=ship.getSize()){
            System.out.printf("\nError! Wrong length of the %s! Try again:\n\n", ship.getName());
            return false;
        }
        for (int r = row1 - 1; r <= row1 + 1; r++) {
            if (r < 0 || r > 9) {
                continue;
            }
            for (int c = col1 - 1; c <= col2 + 1; c++) {
                if (c < 0 || c > 9) {
                    continue;
                }
                if (this.field[r][c] == 'O') {
                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                    return false;
                }
            }
        }
        for (int col = col1; col <= col2; col++) {
            this.field[row1][col] = 'O';
        }
        this.health += ship.getSize();
        return true;

    }

    public void printHiddenField() {
        System.out.print(" ");
        for (int i = 0; i < field.length; i++) {
            System.out.printf(" %d", i + 1);
        }
        System.out.println();
        char row;
        for (int i = 0; i < field.length; i++) {
            row = (char) ('A' + i);
            System.out.printf("%c", row);
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 'O') {
                    System.out.print(" ~");
                }
                else {
                    System.out.printf(" %c", field[i][j]);
                }
            }
            System.out.println();
        }
    }

    public  void printField() {
        System.out.print("\n ");
        for (int i = 0; i < field.length; i++) {
            System.out.printf(" %d", i + 1);
        }
        System.out.println();
        char row;
        for (int i = 0; i < field.length; i++) {
            row = (char) ('A' + i);
            System.out.printf("%c", row);
            for (int j = 0; j < field[i].length; j++) {
                System.out.printf(" %c", field[i][j]);
            }
            System.out.println();
        }
    }

    public boolean shot(int x, int y) {
        if ((x < 0 || x > 9) || (y < 0 || y > 9)) {
            System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            return false;
        }

        if (field[y][x] == 'O') {
            if (health != 1) {
                field[y][x] = 'X';
                health--;
                printHiddenField();
                for (int c = y - 1; c <= y + 1; c++) {
                    if (c < 0 || c > 9) {
                        continue;
                    }
                    for (int r = x - 1; r <= x + 1; r++) {
                        if (r < 0 || r > 9) {
                            continue;
                        }
                        if (this.field[r][c] == 'O') {
                            System.out.println("\nYou hit a ship!");
                            return true;
                        }
                    }
                }
                System.out.println("\nYou sank a ship!");
                return true;
            }
            else {
                field[y][x] = 'X';
                health--;
                printHiddenField();
                System.out.println("You sank the last ship. You won. Congratulations!");
                return true;
            }
        }
        else if (field[y][x] == '~') {
            field[y][x] = 'M';
            printHiddenField();
            System.out.println("\nYou missed!");
            return true;
        }
        else if (field[y][x] == 'X') {
            printHiddenField();
            System.out.print("\nYou hit a ship!");
            return true;
        }
        return false;
    }
    public int getHealth() {
        return health;
    }


}
