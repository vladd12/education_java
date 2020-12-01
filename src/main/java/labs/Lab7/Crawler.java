package labs.Lab7;

import java.util.*;

public class Crawler {

    public static final String HTTP = "http://";
    public static final String HTTP_S = "https://";
    private static LinkedList<URLDepthPair> CheckedURL = new LinkedList<>();
    private static LinkedList<URLDepthPair> UncheckedURL = new LinkedList<>();

    /**
     * Точка входа программы
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // сканер для ввода URL и глубины поиска
        System.out.print("Input a URL: ");
        String URL = in.nextLine();
        System.out.print("Input a depth: ");
        int depth = in.nextInt();
        in.close(); // закрываем поток ввода
    }








}
