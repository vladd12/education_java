package labs.Lab1;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String string = scan.nextLine(); // Для ввода произвольной строки
        System.out.println("Слово " + string + " является палиндромом -> " + isPalindrome(string));
        String[] arr = new String[]{"java", "Palindrome", "madam", "racecar", "apple", "kayak", "song", "noon"};
        for (String i: arr) {
            System.out.println("Слово " + i + " является палиндромом -> " + isPalindrome(i));
        }
    }

    /**
     * Функция возвращает входную строку в обратном порядке
     * @param str входная строка
     * @return реверсированная входная строка
     */
    public static String reverseString(String str) {
        String temp = "";
        int n = str.length() - 1;
        while (n >= 0) {
            temp = temp + str.charAt(n);
            n--;
        }
        return temp;
    }

    /**
     * Функция определяет, является ли входная строка палиндромом
     * @param str входная строка
     * @return true, если строка является палиндромом, или false, если строка не им не является
     */
    public static boolean isPalindrome(String str) {
        return (str.equals(reverseString(str)));
    }
}