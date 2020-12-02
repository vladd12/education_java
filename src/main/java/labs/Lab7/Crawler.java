package labs.Lab7;

import java.io.*;
import java.util.*;
import java.net. *;

public class Crawler {

    public static final String HTTP = "http://";
    public static final String HTTP_S = "https://";
    public static final String BEFORE_URL = "a href=";
    public static final FIFO URLPool = new FIFO(100);

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

        // Стартовые значения для пула ссылок
        URLPool.put(new URLDepthPair(URL, 0));
        calculate(depth);

    }

    public static void calculate(int max_depth) throws IOException {
        while(!URLPool.isEmpty()) {
            // Временная переменная для хранения пары URLDepthPair
            URLDepthPair temp = URLPool.get();

            // Опеределяем URL соединение
            URLConnection urlSocket = new URL(temp.getURL()).openConnection();
            urlSocket.setConnectTimeout(10_1000);

            // Работа с потоками данных URL-соединения
            InputStream stream_in = urlSocket.getInputStream();
            OutputStream stream_out = urlSocket.getOutputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(stream_in));
            PrintWriter output = new PrintWriter(stream_out, true);

            // Получение страницы и её обработка
            String str;
            if (input != null) {
                while ((str = input.readLine()) != null) {
                    if (str.contains(BEFORE_URL + "\"") && temp.getDepth() < max_depth) {
                        String newURL = null;
                        if (str.contains(HTTP)) {
                            newURL = str.substring(str.indexOf(BEFORE_URL + "\"" + HTTP) + BEFORE_URL.length() + 1); // Обрезаем адрес слева
                            newURL = newURL.substring(0, newURL.indexOf("\"")); // Обрезаем адрес справа
                        }
                        else if (str.contains(HTTP_S)) {
                            newURL = str.substring(str.indexOf(BEFORE_URL + "\"" + HTTP_S) + BEFORE_URL.length() + 1); // Обрезаем адрес слева
                            newURL = newURL.substring(0, newURL.indexOf("\"")); // Обрезаем адрес справа
                        }

                        // Нашли новую ссылку
                        URLDepthPair foundURL = new URLDepthPair(newURL, temp.getDepth() + 1);
                        URLPool.put(foundURL);
                    }

                }
            }





        }


    }




}
