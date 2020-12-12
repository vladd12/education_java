package modules;

import java.util.Arrays;

public class Module5 {

    public static void main(String[] args) {

        // Задача 1
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(decrypt(new int[] { 72, 33, -73, 84, -12, -3, 13, -13, -68 }));
        System.out.println(Arrays.toString(encrypt("Sunshine")) + "\n");

        // Задача 2
        System.out.println(canMove("Ладья", "A8", "H8"));
        System.out.println(canMove("Слон", "A7", "G1"));
        System.out.println(canMove("Ферзь", "C4", "D6") + "\n");

        // Задача 3
        System.out.println(canComplete("butl","beautiful"));
        System.out.println(canComplete("butlz","beautiful"));
        System.out.println(canComplete("tulb","beautiful"));
        System.out.println(canComplete("bbutl","beautiful") + "\n");

        // Задача 4
        System.out.println(sumDigProd(new int[]{16, 28}));
        System.out.println(sumDigProd(new int[]{0}));
        System.out.println(sumDigProd(new int[]{1, 2, 3, 4, 5, 6}));

    }

    // Задача 1
    // Создайте две функции, которые принимают строку и массив и возвращают закодированное или декодированное сообщение.
    // Первая буква строки или первый элемент массива представляет собой символьный код этой буквы.
    // Следующие элементы-это различия между символами.
    public static int[] encrypt(String str) {
        int[] array = new int[str.length()];
        array[0] = str.charAt(0);
        for (int i = 1; i < array.length; i++) {
            array[i] = str.charAt(i) - str.charAt(i - 1);
        }
        return array;
    }

    public static String decrypt(int[] array) {
        String result = "";
        int temp=array[0];
        result = result + (char)array[0];
        for (int i = 1; i < array.length; i++) {
            temp+=array[i];
            result = result + (char)temp;
        }
        return result;
    }

    // Задача 2
    // Создайте функцию, которая принимает имя шахматной фигуры, ее положение и целевую позицию.
    // Функция должна возвращать true, если фигура может двигаться к цели, и false, если она не может этого сделать.
    // Возможные входные данные - "пешка", "конь", "слон", "ладья", "ферзь" и "король".
    public static boolean canMove(String name, String position, String way) {
        // Строки с путями в нижний регистр
        position = position.toLowerCase();
        way = way.toLowerCase();
        boolean flag = false;
        int path;

        // В зависимости от фигуры выполняем проверку
        switch (name) {
            case "Пешка":
                path = Math.abs((int)position.charAt(1) - (int)way.charAt(1));
                if (path <= 2) flag = true;
            case "Конь":
                path = Math.abs((int)position.charAt(1) - (int)way.charAt(1)) + Math.abs(position.charAt(0)) - Math.abs(way.charAt(0));
                if (path == 3 && position.charAt(0) != way.charAt(0) && (int)position.charAt(1) != (int)way.charAt(1)) flag = true;
            case "Слон":
                path = Math.abs((int)position.charAt(1) - (int)way.charAt(1)) - Math.abs((int)position.charAt(0) - (int)way.charAt(0));
                if (path == 0) flag = true;
            case "Ладья":
                if ((int)position.charAt(0) == (int)way.charAt(0) || (int)position.charAt(1) == (int)way.charAt(1)) flag = true;
            case "Ферзь":
                path = Math.abs((int)position.charAt(1) - (int)way.charAt(1)) - Math.abs((int)position.charAt(0) - (int)way.charAt(0));
                if (path == 0 || ((int)position.charAt(0) == (int)way.charAt(0) || (int)position.charAt(1) == (int)way.charAt(1))) flag = true;
            case "Король":
                if (Math.abs((int)position.charAt(0) - (int)way.charAt(0)) < 2 && Math.abs((int)position.charAt(1) - (int)way.charAt(1)) < 2) flag = true;
        }
        return flag;
    }

    // Задача 3
    // Входная строка может быть завершена, если можно добавить дополнительные буквы, и никакие буквы не
    // должны быть удалены, чтобы соответствовать слову. Кроме того, порядок букв во входной строке должен
    // быть таким же, как и порядок букв в последнем слове. Создайте функцию, которая, учитывая входную строку,
    // определяет, может ли слово быть завершено.
    public static boolean canComplete(String str1, String str2) {
        // Первый блок проверок
        boolean flag = false; // Временная переменная
        // Если вторая строка не содержит хотя бы один символ первой строки
        for (int i = 0; i < str1.length(); i++) {
            if (!str2.contains(String.valueOf(str1.charAt(i)))) {
                flag = true;
                break;
            }
        }
        // Если прошлый цикл нашёл символ, подходящий по условию и длина первой строки больше длины второй
        if (flag || str1.length() > str2.length()) return false;

        // Второй блок проверок
        String temp = ""; // Временная строка
        for (int i = 0; i < str1.length(); i++) {
            // Еcли последний символ первой строки, то копируем вторую до конца
            if (i == str1.length() - 1) {
                temp = temp + str2.substring(str2.indexOf(str1.charAt(i)));
                break;
            }
            else {
                // Если следующий символ не найден после первого во второй строке, то возвращаем аalse
                if (str2.indexOf(str1.charAt(i + 1), str2.indexOf(str1.charAt(i)) + 1) == -1) return false;
                // Копируем из второй строки подстроки с помощью символов из первой строки по этому правилу
                // после чего сравниваем временную строку со второй
                temp = temp + str2.substring(str2.indexOf(str1.charAt(i)), str2.indexOf(str1.charAt(i + 1), str2.indexOf(str1.charAt(i)) + 1));
            }
        }
        return str2.equals(temp);
    }

    // Задача 4
    public static int sumDigProd(int[] arr) {
        int sum = 0, temp;
        for (int i : arr) { // Сумма всех элементов массива через цикл
            sum = sum + i;
        }
        while (sum > 0) { // Цикл, который будет умножать цифры числа, пока результат не станет 1 символом
            if (sum < 10) break; // Выход из цикла при < 10
            temp = sum;
            sum = 1;
            while (temp > 0) { // Разложение числа на цифры
                sum = sum * (temp % 10); // Умножение
                temp = temp / 10;
            }
        }
        return sum;
    }

    // Задача 5



}
