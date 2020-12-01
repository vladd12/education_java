package labs.Lab7;

import java.io.*;
import java.util.*;
import java.net. *;

public class Crawler {

    public static final String HTTP = "http://";
    public static final String HTTP_S = "https://";
    public static final String BEFORE_URL = "a href=";

    public static final int MAXDepth = 3;
    public static final int MAXThreads = 16;
    public static final int activeThreads = 0;

    public static final int port = 80; // Номер порта сайтов
    public static final int timeout = 1000; // Время ожидания сокета в мс

    public static LinkedList<URLDepthPair> CheckedURL = new LinkedList<>();
    public static LinkedList<URLDepthPair> UncheckedURL = new LinkedList<>();

    /**
     * Точка входа программы
     * @param args аргументы командной строки
     */
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in); // Сканер для ввода URL и глубины поиска
        System.out.print("Input a URL: ");
        String URL = in.nextLine();
        System.out.print("Input a depth: ");
        int depth = in.nextInt();
        in.close(); // закрываем поток ввода

        UncheckedURL.add(new URLDepthPair(URL, depth));

        // Создание сокета, задание его параметров
        Socket socket = new Socket(UncheckedURL.get(0).getURL(), port);
        socket.setSoTimeout(timeout);

        // Работа с потоками данных сокета
        InputStream stream_in = socket.getInputStream();
        OutputStream stream_out = socket.getOutputStream();
        BufferedReader input = new BufferedReader(new InputStreamReader(stream_in));
        PrintWriter output = new PrintWriter(stream_out, true);
    }








}
