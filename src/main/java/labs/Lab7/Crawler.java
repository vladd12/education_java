package labs.Lab7;

import java.io.*;
import java.util.*;
import java.net. *;

public class Crawler {

    public static final String HTTP = "http://";
    public static final String HTTP_S = "https://";
    public static final String BEFORE_URL = "a href=";

    public static final int MAXDepth = 4;
    public static final int MAXThreads = 4;
    public static final int activeThreads = 0;

    public static final int port = 80; // Номер порта сайтов
    public static final int timeout = 1000; // Время ожидания сокета в мс

    public static LinkedList<URLDepthPair> CheckedURL = new LinkedList<>();
    public static LinkedList<URLDepthPair> UncheckedURL = new LinkedList<>();
    public static FIFO URLPool;

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
        System.out.print("Input a num of threads: ");
        int numThreads = in.nextInt();
        in.close(); // закрываем поток ввода

        // Проверяем значения переменных, выбрасываем исключения
        if (depth > MAXDepth) throw new IllegalArgumentException("depth must be <" + MAXDepth);
        if (numThreads > MAXThreads) throw new IllegalArgumentException("num of threads must be < " + MAXThreads);
        if (depth <= 0) throw new IllegalArgumentException("Incorrect input data: depth must be > 0");
        if (numThreads <= 0) throw new IllegalArgumentException("Incorrect input data: num of threads must be > 0");

        // Стартовые значения для пула ссылок
        URLPool = new FIFO(100);

        // Добавляем первую ссылку в список
        UncheckedURL.add(new URLDepthPair(URL, 0));

        // Создание сокета, задание его параметров
        Socket socket = new Socket(UncheckedURL.get(0).getURL(), port);
        socket.setSoTimeout(timeout);

        // Работа с потоками данных сокета
        InputStream stream_in = socket.getInputStream();
        OutputStream stream_out = socket.getOutputStream();
        BufferedReader input = new BufferedReader(new InputStreamReader(stream_in));
        PrintWriter output = new PrintWriter(stream_out, true);

        // Формирование запроса для получения страницы
        output.println("GET / HTTP/1.1\nHost: " + URL + "\nConnection: close\n");

        // Получение страницы
        while (true) {
            String line = input.readLine();
            if (line == null) break; // Чтение документа завершено
            System.out.println(line);
        }




    }

    /**
     * Класс, содержащий код для выполнения в каждом потоке
     */
    public static class pool implements Runnable {
        /**
         * Переопределение метода run
         */
        @Override
        public void run() {

        }
    }






}
