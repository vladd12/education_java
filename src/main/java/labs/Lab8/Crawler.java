package labs.Lab8;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class Crawler {

    public static final String HTTP = "http://";
    public static final String HTTP_S = "https://";
    public static final String BEFORE_URL = "a href=";
    public static final int MAXDepth = 4;
    public static final int MAXThreads = 16;
    public static FIFO URLPool;

    /**
     * Точка входа программы
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // Сканер для ввода URL и глубины поиска
        System.out.print("Input a URL: ");
        String URL = in.nextLine();
        System.out.print("Input a depth: ");
        int depth = in.nextInt();
        System.out.print("Input a num of threads: ");
        int numThreads = in.nextInt();
        in.close();     // закрываем поток ввода

        // Проверяем значения переменных, выбрасываем исключения если не подходит по условию
        if (depth > MAXDepth) throw new IllegalArgumentException("Depth must be <" + MAXDepth);
        if (numThreads > MAXThreads) throw new IllegalArgumentException("Num of threads must be < " + MAXThreads);
        if (depth <= 0) throw new IllegalArgumentException("Incorrect input data: depth must be > 0");
        if (numThreads <= 0) throw new IllegalArgumentException("Incorrect input data: num of threads must be > 0");

        // Стартовые значения для пула ссылок
        URLPool = new FIFO(depth);
        URLPool.put(new URLDepthPair(URL, 0));

        // Начальное число активных потоков
        int initialActive = Thread.activeCount();

        // Главный поток следит за состоянием пула адресов
        while (URLPool.getWaitCount() != numThreads) {
            // Создаём потоки
            if (Thread.activeCount() - initialActive < numThreads) {
                new Thread(new CrawlerTask(URLPool)).start();
            }
            else {
                try {
                    Thread.sleep(100);              // "Успыляем" главный поток на 0.1 секунду
                } catch (InterruptedException ignore) {
                    System.out.println("InterruptedException, ignoring...");
                }
            }
        }
        outputResult(URLPool);
    }

    /**
     * Функция ищет ссылки и добавляет их в пул, при нахождении
     */
    public static ArrayList<String> calculate(URLDepthPair temp) throws IOException {

        ArrayList<String> URLs = new ArrayList<>();

        URLConnection urlSocket = new URL(temp.getURL()).openConnection();
        urlSocket.setConnectTimeout(10_1000);

        // Работа с потоками данных URL-соединения
        InputStream stream_in;
        try {
            stream_in = urlSocket.getInputStream();
        } catch (IOException ignored) {
            System.out.println("Some IOException, ignored;");
            return URLs;
        }
        BufferedReader input = new BufferedReader(new InputStreamReader(stream_in));

        // Получение страницы и её обработка
        while (true) {
            String str;
            int beginIndex = 0;
            int endIndex;

            try {
                str = input.readLine();
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
                return URLs;
            }

            if (str == null) return URLs;
            while (true) {
                beginIndex = str.indexOf(BEFORE_URL,beginIndex);
                if (beginIndex == -1) break;
                beginIndex = beginIndex + BEFORE_URL.length();
                endIndex = str.indexOf("\"", beginIndex);
                String foundURL = str.substring(beginIndex,endIndex);
                URLs.add(foundURL);
                beginIndex = endIndex;
            }
        }
    }

    /**
     * Функция вывода списка найденных просмотренных ссылок
     */
    public static void outputResult(FIFO pool) {
        for (Object checked : pool.getCheckedItems()) {
            System.out.println(checked.toString());
        }
    }
}