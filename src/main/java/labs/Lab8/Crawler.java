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
     * Функция ищет ссылки и добавляет их в пул, при нахождении
     * @param max_depth указанная глубина поиска
     */
    public static void calculate(FIFO pool, int max_depth) {
        try {
            while(!pool.isEmpty()) {

                // Временная переменная для хранения пары URLDepthPair
                URLDepthPair temp = null;
                try {
                    temp = pool.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Опеределяем URL соединение
                assert temp != null;
                URLConnection urlSocket = new URL(temp.getURL()).openConnection();
                urlSocket.setConnectTimeout(10_1000);

                // Работа с потоками данных URL-соединения
                InputStream stream_in = null;
                try {
                    stream_in = urlSocket.getInputStream();
                } catch (IOException ignored) {
                    System.out.println("Some IOException, ignored;");
                }
                assert stream_in != null;
                BufferedReader input = new BufferedReader(new InputStreamReader(stream_in));

                // Получение страницы и её обработка
                String str;
                if (input != null) {
                    while ((str = input.readLine()) != null) {
                        // System.out.println(str); // Для отладки
                        if (temp.getDepth() < max_depth) {
                            while(str.length() > 0) {
                                String newURL;
                                if (str.contains(BEFORE_URL + "\"" + HTTP)) {
                                    newURL = str.substring(str.indexOf(BEFORE_URL + "\"" + HTTP) + BEFORE_URL.length() + 1); // Обрезаем адрес слева
                                    newURL = newURL.substring(0, newURL.indexOf("\"")); // Обрезаем адрес справа
                                }
                                else if (str.contains(BEFORE_URL + "\"" + HTTP_S)) {
                                    newURL = str.substring(str.indexOf(BEFORE_URL + "\"" + HTTP_S) + BEFORE_URL.length() + 1); // Обрезаем адрес слева
                                    newURL = newURL.substring(0, newURL.indexOf("\"")); // Обрезаем адрес справа
                                }
                                else break;

                                // Меняем строку
                                str = str.substring(str.indexOf(newURL) + newURL.length() + 1);

                                // Нашли новую ссылку
                                URLDepthPair foundURL = new URLDepthPair(newURL, temp.getDepth());
                                if (!pool.getCheckedItems().contains(foundURL)) {
                                    foundURL.setDepth(foundURL.getDepth() + 1); // Увеличиваем глубину
                                    pool.put(foundURL); // Добавили её в пул
                                }
                            }
                        }
                        else break;
                    }
                }

                // Закрываем потоки
                input.close();
                stream_in.close();
                urlSocket.getInputStream().close();

                // Добавляем просмотренную ссылку в список просмотренных
                if (temp.getDepth() <= max_depth && !pool.getCheckedItems().contains(temp)) pool.putCheckedItems(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
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