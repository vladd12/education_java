package labs.Lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Класс, содержащий код для выполнения в каждом потоке
 */
public class CrawlerTask implements Runnable {

    private final FIFO pool; // Поле класса FIFO
    private final int max_depth; // Поле для хранения глубины поиска

    /**
     * Конструктор класса с параметром
     * @param pool передача буфера очереди FIFO в конструктор класса
     */
    public CrawlerTask(FIFO pool, int max_depth) {
        this.pool = pool;
        this.max_depth = max_depth;
    }

    /**
     * Переопределение метода run
     */
    @Override
    public void run() {
        // System.out.println("Thread created.");
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
}
