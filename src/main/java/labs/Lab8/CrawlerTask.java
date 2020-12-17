package labs.Lab8;

import java.io.IOException;
import java.util.ArrayList;

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
        URLDepthPair temp = null;
        try {
            temp = pool.get();
        } catch (InterruptedException ignored) {
            System.out.println("InterruptedException, ignoring...");
        }
        assert temp != null;

        ArrayList<String> links = null;
        try {
            links = Crawler.calculate(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String link : links) {
            URLDepthPair newPair = new URLDepthPair(link, temp.getDepth() + 1);
            if (!pool.getCheckedItems().contains(link)) {
                pool.put(newPair);
            }
        }
    }
}
