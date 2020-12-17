package labs.Lab8;

import java.io.IOException;

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
        Crawler.calculate(this.pool, this.max_depth);
    }
}
