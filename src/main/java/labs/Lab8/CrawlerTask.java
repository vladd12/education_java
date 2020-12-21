package labs.Lab8;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс, содержащий код для выполнения в каждом потоке
 */
public class CrawlerTask implements Runnable {

    private final FIFO pool;        // Поле класса FIFO

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
        URLDepthPair temp = pool.get();         // Получаем элемент URLDepthPair из пула
        ArrayList<String> links = null;         // Создаём список строк
        try {
            links = Crawler.calculate(temp);    // В этот список попадают наденные ссылки
        } catch (IOException e) {
            e.printStackTrace();
        }

        // В ходе цикла каждая строка, если до этого её не было в пуле
        assert links != null;
        for (String link : links) {
            URLDepthPair newPair = new URLDepthPair(link, temp.getDepth() + 1);
            if (!pool.getCheckedItems().contains(link)) pool.put(newPair);
        }
    }
}