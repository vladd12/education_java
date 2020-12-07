package labs.Lab8;

import java.util.LinkedList;

/**
 * Класс для работы методом "First In, First Out" (реализация очереди)
 */
public class FIFO {

    private final int maxSize; // Максимальное количество элементов в буфере
    private final LinkedList<URLDepthPair> items; // Поле класса для хранения списка объектов URLDepthPair
    private final LinkedList<URLDepthPair> checkedItems; // Поле класса для хранения списка просмотренных URLDepthPair
    private int waitingThreads; // Поле класса для количества ожидающих потоков

    /**
     * Конструктор класса
     * @param maxSize максимальное количество элементов в буфере
     */
    public FIFO(int maxSize) {
        this.maxSize = maxSize;
        this.items = new LinkedList<>();
        this.checkedItems = new LinkedList<>();
        this.waitingThreads = 0;
    }

    /**
     * Функция добавления в буфер обрабатываемого объекта
     * @param obj объект класса URLDepthPair
     * @return true, если объект был добавлен, false в противном случае
     */
    public boolean put(URLDepthPair obj) {
        boolean flagAdded = false;
        synchronized (items) {
            if (items.size() < maxSize) {
                items.addLast(obj); // Объект добавляется в конец списка
                flagAdded = true;
                items.notify();
            }
        }
        return flagAdded;
    }

    /**
     * Функция получения объекта из буфера
     * @return объект класса URLDepthPair из начала списка
     */
    public synchronized URLDepthPair get() throws InterruptedException {
        // synchronized - потокобезопасный метод класса
        URLDepthPair item = null;
        while (items.size() == 0) {
            waitingThreads++;
            items.wait(); // Ожидаем, если нет объектов
            waitingThreads--;
        }
        if (items.size() > 0) item = items.removeFirst(); // Получаем объект из начала списка
        checkedItems.add(item); // Добавляем объект в список просмотренных
        return item;
    }

    /**
     * Функция проверяет, пустой ли буфер
     * @return возвращает true, если буфер пустой, false в противном случае
     */
    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    /**
     * Функция добавления в список просмотренной ссылки
     * @param obj прсмотренная ссылка
     */
    public void putCheckedItems(URLDepthPair obj) {
        synchronized (checkedItems) {
            checkedItems.add(obj); // Добавляем объект в список просмотренных
        }
    }

    /**
     * Функция получения всего списка просмотренных ссылок
     * @return список LinkedList<URLDepthPair> всех просмотренных ссылок
     */
    public synchronized LinkedList<URLDepthPair> getCheckedItems() {
        return this.checkedItems;
    }

    /**
     * Getter для количества ожидающих потоков
     * @return количество ожидающих потоков
     */
    public int getWaitCount() {
        return this.waitingThreads;
    }
}