package tictactoe;

import java.util.Scanner;

public class Main {
    private static final int SIZE = 3;
    private static final char[][] map = new char[SIZE][SIZE];
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static final char DOT_EMPTY = '_';
    private static final char DOT_NULL = ' ';
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initMap();
        while (true){
            if(humanTurn(DOT_X)) {
                break;
            }
            if(humanTurn(DOT_O)) {
                break;
            }
        }

//        if(checkWrong()) {
//            System.out.println("Impossible");
//        } else if (checkWin(DOT_X)) {
//            System.out.println(DOT_X + " wins");
//        } else if (checkWin(DOT_O)) {
//            System.out.println(DOT_O + " wins");
//        } else if (checkNotFinish()) {
//            System.out.println("Game not finished");
//        } else {
//            System.out.println("Draw");
//        }
    }

    private static boolean humanTurn(char symbol) {
        enterCoords(symbol);
        printMap();
        if(checkWin(symbol)) {
            System.out.println(symbol +" wins");
            return true;
        }
        if(checkDraw()) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }

    private static boolean checkDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(map[i][j] == DOT_EMPTY || map[i][j] == DOT_NULL) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void enterCoords(char symbol) {
        int x = -1;
        int y = -1;
        while (true) {
            System.out.print("Enter the coordinates: ");
            String[] str = scanner.nextLine().split(" ");
            if (str.length == 1) {
                System.out.println("You should enter numbers!");
            } else if (str[0].matches("\\d") && str[1].matches("\\d")) {
                x = Integer.parseInt(str[0]) - 1;
                y = Integer.parseInt(str[1]) - 1;
                if (checkCoords(x, y) && checkOccupied(x, y)) {
                    map[x][y] = symbol;
                    break;
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
     }

    private static boolean checkOccupied(int x, int y) {
        if (map[x][y] != DOT_EMPTY && map[x][y] != DOT_NULL) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkCoords(int x, int y) {
        if (!(x < SIZE && x >= 0 && y < SIZE && y >= 0)) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        } else {
            return true;
        }
    }

    private static boolean checkWrong() {
        int counterDotX = 0;
        int counterDotY = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(map[i][j] == DOT_X) {
                    counterDotX++;
                } else if(map[i][j] == DOT_O) {
                    counterDotY++;
                }
            }
        }
        return Math.abs(counterDotX - counterDotY) >= 2 || checkWin(DOT_X) && checkWin(DOT_O);
    }

    private static boolean checkWin(char symbol) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int counterX = 0;
                int counterY = 0;
                int counterXYRightLeft = 0;
                int counterXYLeftRight = 0;
                // check X and Y  axis
                for (int k = 0; k < SIZE; k++) {
                    if(j + k < SIZE && map[i][j + k] == symbol) {
                        counterX++;
                        if (counterX == SIZE) {
                            return true;
                        }
                    }else {
                        counterX = 0;
                    }
                    if (i + k < SIZE && map[i + k][j] == symbol) {
                        counterY++;
                        if (counterY == SIZE) {
                            return true;
                        }
                    }else {
                        counterY = 0;
                    }
                }
                // check diagonal
                for (int k = 0; k < SIZE; k++) {
                    if (i + k < SIZE && j + k < SIZE && map[i + k][j + k] == symbol) {
                        counterXYLeftRight++;
                        if (counterXYLeftRight == SIZE) {
                            return true;
                        }
                    } else {
                        counterXYLeftRight = 0;
                    }
                    if (i + k < SIZE && j - k >= 0 && map[i + k][j - k] == symbol) {
                        counterXYRightLeft++;
                        if (counterXYRightLeft == SIZE) {
                            return true;
                        }
                    } else {
                        counterXYRightLeft = 0;
                    }
                }
            }
        }

        return false;
    }

    private static boolean checkNotFinish() {
        for (char[] array : map) {
            for (char el : array) {
                if (el == '_' || el == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    private static void printHeaderMap() {
        for (int i = 0; i < (SIZE + 2) * 2 - 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void initMap() {
//        System.out.print("Enter cells: ");
//        String str = scanner.nextLine();
//        for (int i = 0; i < SIZE; i++) {
//            map[i] = str.substring(i * SIZE, i * SIZE + SIZE).toCharArray();
//        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_NULL;
            }
        }

        printMap();
    }

    private static void printMap() {
        printHeaderMap();
        for (int i = 0; i < SIZE; i++) {
            String strMap = String.format("| %s %s %s |",map[i][0],map[i][1],map[i][2]);
            System.out.println(strMap);
        }
        printHeaderMap();
    }

}
