package lesson01.part1;

public class Task04 {

    /**
     * Раскомментируй одну строчку, чтобы программа вывела на экран числа 26 и 25.
     *
     * Требования:
     * 1. Программа должна выводить на экран числа 12 и 2.
     * 2. Нужно раскомментировать одну строку.
     * 3. Нельзя изменять (добавлять, удалять) строки с кодом.
     */

    public static void main(String[] args) {
        int x = 27;
        int y = 15;

        // y = x - y; // не эта строка
        // y = y - x; // не эта строка
        // y = y + x; // не эта строка
        // y = y + x; // не эта строка
        y = x / y; //эта строка
        // y = y / x; // не эта строка
        // y = y * x;// не эта строка

        x = x - y;
        y = y - x;
        System.out.println(Math.abs(x));
        System.out.println(Math.abs(y));
    }
}
