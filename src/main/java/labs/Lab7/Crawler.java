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

    public static final FIFO URLPool = new FIFO(100);

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
        in.close(); // закрываем поток ввода

        // Проверяем значения переменных, выбрасываем исключения если не подходит по условию
        if (depth > MAXDepth) throw new IllegalArgumentException("depth must be <" + MAXDepth);
        if (numThreads > MAXThreads) throw new IllegalArgumentException("num of threads must be < " + MAXThreads);
        if (depth <= 0) throw new IllegalArgumentException("Incorrect input data: depth must be > 0");
        if (numThreads <= 0) throw new IllegalArgumentException("Incorrect input data: num of threads must be > 0");

        // Стартовые значения для пула ссылок
        URLPool.put(new URLDepthPair(URL, 0));



    }

    private Thread createThread (FIFO pool){
        return new Thread(new CrawlerTask(pool));
    }

    /**
     * Класс, содержащий код для выполнения в каждом потоке
     */
    public class CrawlerTask implements Runnable {

        private final FIFO pool; // Поле класса FIFO

        /**
         * Конструктор класса с параметром
         * @param pool передача буфера очереди FIFO в конструктор класса
         */
        public CrawlerTask(FIFO pool) {
            this.pool = pool;
        }

        /**
         * Переопределение метода run
         */
        @Override
        public void run() {
            while(!pool.isEmpty()) {
                // TODO
            }
        }
    }

    public static void func() throws InterruptedException, IOException {

        // Временная переменная для хранения пары URLDepthPair
        URLDepthPair temp = URLPool.get();

        // Опеределяем URL соединение
        URLConnection urlSocket = new URL(temp.getURL()).openConnection();
        urlSocket.setConnectTimeout(10_1000);

        /*
        // Работа с потоками данных URL-соединения
        // InputStream stream_in = socket.getInputStream();
        // OutputStream stream_out = socket.getOutputStream();
        // BufferedReader input = new BufferedReader(new InputStreamReader(stream_in));
        // PrintWriter output = new PrintWriter(stream_out, true);

        // Получение страницы
        while (true) {
            String line = input.readLine();
            if (line == null) break; // Чтение документа завершено
            System.out.println(line);
        }
         */


    }




}
