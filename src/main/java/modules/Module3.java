package modules;

// Модуль 3/6

import java.util.ArrayList;
import java.util.List;

public class Module3 {
    public static void main(String[] args) {

        // Задача 1
        System.out.println("Задача 1.");
        System.out.println(solutions(1, 0, -1));
        System.out.println(solutions(1, 0, 0));
        System.out.println(solutions(1, 0, 1) + "\n");

        // Задача 2
        System.out.println("Задача 2.");
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(findZip("all zip files are compressed") + "\n");

        // Задача 3
        System.out.println("Задача 3.");
        System.out.println(checkPerfect(6));
        System.out.println(checkPerfect(28));
        System.out.println(checkPerfect(496));
        System.out.println(checkPerfect(12));
        System.out.println(checkPerfect(97) + "\n");

        // Задача 4
        System.out.println("Задача 4.");
        System.out.println(flipEndChars("Cat, dog, and mouse."));
        System.out.println(flipEndChars("ada"));
        System.out.println(flipEndChars("Ada"));
        System.out.println(flipEndChars("z") + "\n");

        // Задача 5
        System.out.println("Задача 5.");
        System.out.println(isValidHexCode("#CD5C5C"));
        System.out.println(isValidHexCode("#EAECEE"));
        System.out.println(isValidHexCode("#eaecee"));
        System.out.println(isValidHexCode("#CD5C58C"));
        System.out.println(isValidHexCode("#CD5C5Z"));
        System.out.println(isValidHexCode("#CD5C&C"));
        System.out.println(isValidHexCode("CD5C5C") + "\n");

        // Задача 6
        System.out.println("Задача 6.");
        System.out.println(same(new int[]{1, 3, 4, 4, 4}, new int[]{2, 5, 7}));
        System.out.println(same(new int[]{9, 8, 7, 6}, new int[]{4, 4, 3, 1}));
        System.out.println(same(new int[]{2}, new int[]{3, 3, 3, 3, 3}) + "\n");

        // Задача 7
        System.out.println("Задача 7.");
        System.out.println(isKaprekar(3));
        System.out.println(isKaprekar(5));
        System.out.println(isKaprekar(297) + "\n");

        // Задача 8
        System.out.println("Задача 8.");
        System.out.println(longestZero("01100001011000"));
        System.out.println(longestZero("100100100"));
        System.out.println(longestZero("11111") + "\n");

        // Задача 9
        System.out.println("Задача 9.");

    }

    // Задача 1
    public static int solutions(double a, double b, double c) {
        double D = b * b - 4 * a * c;
        if (D < 0) return 0;
        else if (D == 0) return 1;
        else return 2;
    }

    // Задача 2
    public static int findZip(String str) {
        int indexFirstZip = new StringBuilder(str).indexOf("zip");
        int indexSecondZip = new StringBuilder(str).indexOf("zip", indexFirstZip + 3);
        return indexSecondZip;
    }

    // Задача 3
    public static boolean checkPerfect(int num) {
        if (num == 1) return true;
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) sum = sum + i;
        }
        return (sum == num);
    }

    // Задача 4
    public static String flipEndChars(String str) {
        if (str.length() < 2) return "Incompatible.";
        else {
            if (str.charAt(0) == str.charAt(str.length() - 1)) return "Two is a pair.";
            else return (str.charAt(str.length() - 1) + str.substring(1, str.length() - 1) + str.charAt(0));
        }
    }

    // Задача 5
    public static boolean isValidHexCode(String str) {
        boolean flag = true;
        str = str.toUpperCase();
        if (str.charAt(0) != '#' || str.length() != 7) flag = false;
        else {
            for (int i = 1; i < str.length(); i++) {
                if ((int) str.charAt(i) < 48 || (int) str.charAt(i) > 57 && ((int) str.charAt(i) < 65 || (int) str.charAt(i) > 70)) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    // Задача 6
    public static boolean same(int[] arr1, int[] arr2) {
        List<Integer> list1 = new ArrayList<>(); // Список 1
        List<Integer> list2 = new ArrayList<>(); // Список 2
        for (int i: arr1) {
            if (!list1.contains(i)) list1.add(i); // Если в списке уже есть элемент, то не добавлять его
        }
        for (int i: arr2) {
            if (!list2.contains(i)) list2.add(i);
        }
        return (list1.size() == list2.size());
    }

    // Задача 7
    public static boolean isKaprekar(int num) {
        if (num == 0 || num == 1) return true; // 0 и 1 по умолчанию являются таковыми числами
        String str = String.valueOf(num * num); // int в string
        int value = str.length() / 2; // Находим индекс условной середины (если длина строки нечётная, то число округлится до меньшего значения)
        if (value == 0) {
            int Left = 0;
            int Right = Integer.parseInt(str);
            return (Left + Right == num);
        }
        else {
            String left = str.substring(0, value);
            String right = str.substring(value); // или str.substring(value, str.length())
            int Left = Integer.parseInt(left); // Обратно string в int
            int Right = Integer.parseInt(right);
            return (Left + Right == num);
        }
    }

    // Задача 8
    public static String longestZero(String str) {
        int num = 0; int max = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == '1') {
                if (max < num) max = num;
                num = 0;
                continue;
            }
            if (str.charAt(i) == '0') {
                num = num + 1;
            }
        }
        if (max == 0) return "В строке не найдены нули.";
        String result = "";
        while (max > 0) {
            result = result + 0;
            max--;
        }
        return result;
    }

    // Задача 9
    public static int nextPrime(int num) {

        return 0;
    }

}
