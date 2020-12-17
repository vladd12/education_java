package labs.Lab8;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Crawler {

    public static final String HTTP = "http://";
    public static final String HTTP_S = "https://";
    public static final String BEFORE_URL = "a href=";

    public static final int MAXDepth = 4;
    public static final int MAXThreads = 16;

    public static final FIFO URLPool = new FIFO(1000);

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
        URLPool.put(new URLDepthPair(URL, 0));

        int initialActive = Thread.activeCount();

        // Главный поток следит за состоянием пула адресов
        while (URLPool.getWaitCount() != numThreads) {
            // Создаём потоки
            if (Thread.activeCount() - initialActive < numThreads) {
                new Thread(new CrawlerTask(URLPool, depth)).start();
            }
            else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignore) {
                    System.out.println("InterruptedException, ignoring...");
                }
            }
        }

        outputResult(URLPool);
    }

    /**
     * Функция для создания потоков
     * @param pool буфер ссылок
     * @return возвращает поток, созданный для работы с данным буфером
     */
    private static Thread createThread(FIFO pool, int max_depth){
        return new Thread(new CrawlerTask(pool, max_depth));
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