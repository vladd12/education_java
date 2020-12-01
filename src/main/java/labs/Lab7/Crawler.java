package labs.Lab7;

import java.io.IOException;
import java.util.*;
import java.net. *;

public class Crawler {

    public static final String HTTP = "http://";
    public static final String HTTP_S = "https://";
    public static final String BEFORE_URL = "a href=";

    public static final int MAXDepth = 3;
    public static final int MAXThreads = 16;
    public static final int activeThreads = 0;

    public static final int port = 80;

    public static LinkedList<URLDepthPair> CheckedURL = new LinkedList<>();
    public static LinkedList<URLDepthPair> UncheckedURL = new LinkedList<>();

    /**
     * Точка входа программы
     * @param args аргументы командной строки
     */
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in); // сканер для ввода URL и глубины поиска
        System.out.print("Input a URL: ");
        String URL = in.nextLine();
        System.out.print("Input a depth: ");
        int depth = in.nextInt();
        in.close(); // закрываем поток ввода

        UncheckedURL.add(new URLDepthPair(URL, depth));

        Socket socket = new Socket(UncheckedURL.get(1).getURL(), port);
    }








}
