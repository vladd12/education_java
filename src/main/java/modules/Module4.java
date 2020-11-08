package modules;

// Модуль 4/6

import java.util.Arrays;

public class Module4 {
    public static void main(String[] args) {

        // Задача 1
        System.out.println("Задача 1.");
        System.out.println(bessie(10, 7, "hello my name is Bessie and this is my essay") + "\n");

        // Задача 2
        System.out.println("Задача 2.");
        System.out.println(Arrays.toString(split("()()()")));
        System.out.println(Arrays.toString(split("((()))")));
        System.out.println(Arrays.toString(split("((()))(())()()(()())")));
        System.out.println(Arrays.toString(split("((())())(()(()()))")) + "\n");

        // Задача 3
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("getColor") + "\n");

        // Задача 4
        System.out.println(overTime(new double[] {9, 17, 30, 1.5}));
        System.out.println(overTime(new double[] {16, 18, 30, 1.8}));
        System.out.println(overTime(new double[] {13.25, 15, 30, 1.5}) + "\n");

        // Задача 5
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 meters") + "\n");

        // Задача 6
        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4) + "\n");

        // Задача 7



    }

    // Задача 1
    public static String bessie(int n, int k, String str) {
        if ((n < 1 || n > 100) || (k < 1 || k > 80)) return "Error input params!"; // Некорректные входные данные для функции
        String result = "";
        int indexStart = 0, indexEnd = 0, k_save = k;
        while (n > 0) { // Пока количество слов не равно нулю
            for (int i = indexStart; i < str.length(); i++) {
                if (str.charAt(i) == ' ' || i == str.length()-1) {
                    indexEnd = i;
                    if (i == str.length()-1) indexEnd = indexEnd + 1;
                    break;
                }
            }
            if (indexEnd - indexStart > 15) return "Error! A word containing more than 15 characters was found."; // Найдено слово, длина которого больше 15 символов
            String word = str.substring(indexStart, indexEnd);
            indexStart = indexEnd + 1;
            if (k_save >= word.length()) {
                result = result + word + " ";
                k_save = k_save - word.length();
            }
            else {
                k_save = k;
                result = result + "\n" + word + " ";
                k_save = k_save - word.length();
            }
            n = n - 1;
        }
        return result;
    }

    // Задача 2
    public static String[] split (String str) {
        int left = 0, right = 0, k = 0, groups = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == '(') left++;
            if (str.charAt(i) == ')') right++;
            if (left == right) {
                groups++;
                left = right = 0;
            }
        }
        int iter = str.length() - (left + right);
        left = right = 0;
        String[] res = new String[groups];
        String temp = "";
        for (int i = 0; i < iter; i++)
        {
            if (str.charAt(i) == '(') {
                left++;
                temp+="(";
            }
            if (str.charAt(i) ==')') {
                right++;
                temp+=")";
            }
            if (left == right) {
                res[k] = temp;
                temp = "";
                k++;
            }
        }
        return res;
    }

    // Задача 3
    public static String toCamelCase(String str) {
        String result = "";
        for(int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '_') {
                result = result + Character.toUpperCase(str.charAt(i+1));
                i++;
            }
            else result = result + str.charAt(i);
        }
        return result;
    }

    public static String toSnakeCase(String str) {
        String result = "";
        for(int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) result = result + "_" + Character.toLowerCase(str.charAt(i));
            else result = result + str.charAt(i);
        }
        return result;
    }

    // Задача 4
    public static String overTime(double[] arr) {
        double result;
        if (arr[1] >= 17) result = (17 - arr[0]) * arr[2] + (arr[1] - 17) * arr[2] * arr[3];
        else result = (arr[1] - arr[0]) * arr[2];
        result = Math.round(result * 100.0) / 100.0; // Округление до сотых
        return "$" + result + "0";
    }

    // Задача 5
    public static String BMI(String weight, String height) {
        int index = 0;
        String[] first, second;
        double weightDouble, heightDouble;
        double BMI;

        // Поиск индекса пробела в первой строке
        for (int i = 0; i < weight.length(); i++) {
            if (weight.charAt(i) == ' ') {
                index = i;
                break;
            }
        }

        // Разделение строки на массив подстрок (2 подстроки)
        first = new String[] { weight.substring(0, index), weight.substring(index + 1) };
        weightDouble = Double.parseDouble(first[0]); // Парсим первую подстроку в double
        if (first[1].equals("pounds")) weightDouble = weightDouble * 0.45359237; // Если фунты, то переводим в килограммы

        // Поиск индекса пробела во второй строке
        for (int i = 0; i < height.length(); i++) {
            if (height.charAt(i) == ' ') {
                index = i;
                break;
            }
        }

        // Разделение строки на массив подстрок (2 подстроки)
        second = new String[] { height.substring(0, index), height.substring(index + 1) };
        heightDouble = Double.parseDouble(second[0]); // Парсим первую подстроку в double
        if (second[1].equals("inches")) heightDouble = heightDouble * 0.0254; // Если дюймы, то переводим в метры

        BMI = weightDouble / (heightDouble * heightDouble); // Вычисление индекса массы тела
        BMI = Math.round(BMI * 10.0) / 10.0; // Округление до десятых

        // Возвращаем соответствующую строку
        if (BMI < 18.5) return BMI + " Underweight";
        else if (BMI >= 18.5 && BMI <= 24.9) return BMI + " Normal weight";
        else return BMI + " Overweight";
    }

    // Задача 6
    public static int bugger(int num) {
        int count = 0;
        int temp;
        while (num > 0) {
            if (num < 10) break; // Выход из цикла при < 10
            temp = num;
            num = 1;
            while (temp > 0) { // Разложение числа на цифры
                num = num * (temp % 10); // Умножение
                temp = temp / 10;
            }
            count++; // Счётчик умножения
        }
        return count;
    }

    // Задача 7



}
