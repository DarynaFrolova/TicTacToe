import java.util.Random;
import java.util.Scanner;

public class ConsoleAI {
    public final static int SIZE = 3;
    public static int DOTS_TO_WIN = 3;
    public final static char DOT_EMPTY = '•';
    public final static char DOT_X = 'X';
    public final static char DOT_0 = '0';
    public final static char[][] MAP = new char[SIZE][SIZE];
    public final static Scanner scanner = new Scanner(System.in);
    public final static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {

            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_0)) {
                System.out.println("Победил ИИ");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }

        System.out.println("Игра окончена");
        scanner.close();
    }

    public static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                MAP[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) { // Печать шапки игрового поля
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MAP[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        if (MAP[y][x] == DOT_EMPTY) {
            return true;
        }
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (MAP[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        MAP[y][x] = DOT_X;
    }

    public static void aiTurn() { // (Блокировка ходов игрока только для поля 3х3)
        int x = 0;
        int y = 0;
        boolean change = false;
        do {
            if (MAP[1][1] == DOT_EMPTY) {
                x = 1;
                y = 1;
                change = true;
                break;
            }
            for (int i = 0, j = 0; i < SIZE; i++) { // Блокировка заполнения последней клетки в i-й строчке (при заполненых Х-м двух других клеток)
                if (MAP[i][j] == DOT_X && MAP[i][j + 1] == DOT_X && MAP[i][j + 2] != DOT_0) {
                    x = j + 2;
                    y = i;
                    change = true;
                    break;
                }
            }
            for (int i = 0, j = 0; i < SIZE; i++) { // Блокировка заполнения средней клетки в i-й строчке
                if (MAP[i][j] == DOT_X && MAP[i][j + 2] == DOT_X && MAP[i][j + 1] != DOT_0) {
                    x = j + 1;
                    y = i;
                    change = true;
                    break;
                }
            }
            for (int i = 2, j = 0; i >= 0; i--) { // Блокировка заполнения первой клетки в i-й строчке
                if (MAP[i][j + 2] == DOT_X && MAP[i][j + 1] == DOT_X && MAP[i][j] != DOT_0) {
                    x = j;
                    y = i;
                    change = true;
                    break;
                }
            }
            for (int i = 0, j = 0; j < SIZE; j++) { // Блокировка заполнения последней клетки в j-м столбце
                if (MAP[i][j] == DOT_X && MAP[i + 1][j] == DOT_X && MAP[i + 2][j] != DOT_0) {
                    x = j;
                    y = i + 2;
                    change = true;
                    break;
                }
            }
            for (int i = 0, j = 0; j < SIZE; j++) { // Блокировка заполнения средней клетки в j-м столбце
                if (MAP[i][j] == DOT_X && MAP[i + 2][j] == DOT_X && MAP[i + 1][j] != DOT_0) {
                    x = j;
                    y = i + 1;
                    change = true;
                    break;
                }
            }
            for (int i = 0, j = 0; j < SIZE; j++) { // Блокировка заполнения первой клетки в j-м столбце
                if (MAP[i + 2][j] == DOT_X && MAP[i + 1][j] == DOT_X && MAP[i][j] != DOT_0) {
                    x = j;
                    y = i;
                    change = true;
                    break;
                }
            }
            if (MAP[0][0] == DOT_X && MAP[1][1] == DOT_X && MAP[2][2] != DOT_0) { // Блокировка заполнения последней клетки главной диагонали
                x = 2;
                y = 2;
                change = true;
                break;
            }
            if (MAP[2][2] == DOT_X && MAP[0][0] == DOT_X && MAP[1][1] != DOT_0) { // Блокировка заполнения средней клетки главной диагонали
                x = 1;
                y = 1;
                change = true;
                break;
            }
            if (MAP[1][1] == DOT_X && MAP[2][2] == DOT_X && MAP[0][0] != DOT_0) { // Блокировка заполнения первой клетки главной диагонали
                x = 0;
                y = 0;
                change = true;
                break;
            }
            if (MAP[0][2] == DOT_X && MAP[1][1] == DOT_X && MAP[2][0] != DOT_0) { // Блокировка заполнения последней клетки побочной диагонали
                x = 0;
                y = 2;
                change = true;
                break;
            }
            if (MAP[0][2] == DOT_X && MAP[2][0] == DOT_X && MAP[1][1] != DOT_0) { // Блокировка заполнения средней клетки побочной диагонали
                x = 1;
                y = 1;
                change = true;
                break;
            }
            if (MAP[2][0] == DOT_X && MAP[1][1] == DOT_X && MAP[0][2] != DOT_0) { // Блокировка заполнения первой клетки побочной диагонали
                x = 2;
                y = 0;
                change = true;
                break;
            }
            if (!change) {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            }
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        MAP[y][x] = DOT_0;
    }

    private static boolean checkWin(char symbol) { // (Для полей и 3х3, и 5х5)
        if (DOTS_TO_WIN == 3 && (checkWinHor(symbol) || checkWinVer(symbol) || checkWinDiag(symbol)) ||
                (DOTS_TO_WIN == 4 && (checkWinHor(symbol) || checkWinVer(symbol) || checkWinDiag(symbol) || checkWinOtherDiag(symbol)))) {
            return true;
        }
        return false;
    }

    private static boolean checkWinHor(char symbol) { // Строчки
        for (int i = 0; i < SIZE; i++) {
            int d = 0;
            for (int j = 0; j < SIZE; j++) {
                if (MAP[i][j] == symbol) {
                    d++;
                }
            }
            if ((DOTS_TO_WIN == 3 && d == 3) || (DOTS_TO_WIN == 4 && d == 4 && MAP[i][0] == DOT_EMPTY)
                    || (DOTS_TO_WIN == 4 && d == 4 && MAP[i][SIZE - 1] == DOT_EMPTY) || (DOTS_TO_WIN == 4 && d == 5)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkWinVer(char symbol) { // Столбцы
        for (int j = 0; j < SIZE; j++) {
            int d = 0;
            for (int i = 0; i < SIZE; i++) {
                if (MAP[i][j] == symbol) {
                    d++;
                }
            }
            if ((DOTS_TO_WIN == 3 && d == 3) || (DOTS_TO_WIN == 4 && d == 4 && MAP[0][j] == DOT_EMPTY)
                    || (DOTS_TO_WIN == 4 && d == 4 && MAP[SIZE - 1][j] == DOT_EMPTY) || (DOTS_TO_WIN == 4 && d == 5)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkWinDiag(char symbol) { // Диагонали
        boolean diag = false;
        int d = 0;
        for (int i = 0, j = 0; i < SIZE; i++, j++) { // Главная диагональ
            if (MAP[i][j] == symbol) {
                d++;
            }
            if ((DOTS_TO_WIN == 3 && d == 3) || (DOTS_TO_WIN == 4 && d == 4 && MAP[0][0] == DOT_EMPTY)
                    || (DOTS_TO_WIN == 4 && d == 4 && MAP[SIZE - 1][SIZE - 1] == DOT_EMPTY) || (DOTS_TO_WIN == 4 && d == 5)) {
                diag = true;
            }
        }
        d = 0;
        for (int i = 0, j = SIZE - 1; i < SIZE; i++, j--) { // Побочная диагональ
            if (MAP[i][j] == symbol) {
                d++;
            }
            if ((DOTS_TO_WIN == 3 && d == 3) || (DOTS_TO_WIN == 4 && d == 4 && MAP[0][SIZE - 1] == DOT_EMPTY)
                    || (DOTS_TO_WIN == 4 && d == 4 && MAP[SIZE - 1][0] == DOT_EMPTY) || (DOTS_TO_WIN == 4 && d == 5)) {
                diag = true;
            }
        }
        return diag;
    }

    private static boolean checkWinOtherDiag(char symbol) { // Диагонали сверху и снизу от главной и побочной в поле 5х5
        boolean diag = false;
        int d = 0;
        for (int i = 0, j = 1; i < SIZE - 1; i++, j++) { // Диагональ сверху от главной
            if (MAP[i][j] == symbol) {
                d++;
            }
            if (d == 4) {
                diag = true;
            }
        }
        d = 0;
        for (int i = 1, j = 0; i < SIZE; i++, j++) {  // Диагональ снизу от главной
            if (MAP[i][j] == symbol) {
                d++;
            }
            if (d == 4) {
                diag = true;
            }
        }
        d = 0;
        for (int i = 0, j = 3; i < SIZE - 1; i++, j--) { // Диагональ сверху от побочной
            if (MAP[i][j] == symbol) {
                d++;
            }
            if (d == 4) {
                diag = true;
            }
        }
        for (int i = 1, j = 4; i < SIZE; i++, j--) { // Диагональ снизу от побочной
            if (MAP[i][j] == symbol) {
                d++;
            }
            if (d == 4) {
                diag = true;
            }
        }
        return diag;
    }
}