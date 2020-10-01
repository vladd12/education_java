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
        System.out.println(Arrays.toString(split("((())())(()(()()))")));

        // Задача 3
        System.out.println(toSnakeCase("strStrStr"));

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

        return "";
    }

    public static String toSnakeCase(String str) {
        int n = 0;
        int index = 0;
        int start_index = 0;
        for (int i = 0; i < str.length(); i++) { // считаем количество больших букв
            if ((int)str.charAt(i) > 64 && (int)str.charAt(i) < 91) {
                n = n + 1;
            }
        }
        int[] arr_indexes = new int[n];
        String[] subs_str = new String[n+1];
        for (int i = 0; i < str.length(); i++) { // находим индекс каждой большой буквы
            if ((int)str.charAt(i) > 64 && (int)str.charAt(i) < 91) {
                arr_indexes[index] = i;
                index = index + 1;
            }
        }
        for (int i = 0; i < n+1; i++) { // разделяем строку на подстроки по индексу
            if (i == n) subs_str[i] = str.substring(start_index);
            else {
                subs_str[i] = str.substring(start_index, arr_indexes[i]);
                start_index = arr_indexes[i];
            }
        }
        String result = "";
        for (int i = 0; i < n+1; i++) { // разделяем строку на подстроки по индексу
            if (i == n) result = result + subs_str[i].toLowerCase();
            else result = result + subs_str[i].toLowerCase() + "_";
        }
        return result;
    }



}
