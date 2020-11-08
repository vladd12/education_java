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
        System.out.println(canMove("Ферзь", "C4", "D6"));

        // Задача 3
        System.out.println(canComplete());

    }

    // Задача 1
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
    public static boolean canComplete() {

        return false;
    }
}
