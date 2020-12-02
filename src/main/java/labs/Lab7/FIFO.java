package labs.Lab7;

import java.util.LinkedList;

/**
 * Класс для работы методом "First In, First Out" (реализация очереди)
 */
public class FIFO {

    private final int maxSize; // Максимальное количество элементов в буфере
    private final LinkedList<URLDepthPair> items; // Поле класса для хранения списка объектов URLDepthPair
    private final LinkedList<URLDepthPair> checkedItems; // Поле класса для хранения списка просмотренных URLDepthPair

    /**
     * Конструктор класса
     * @param maxSize максимальное количество элементов в буфере
     */
    public FIFO(int maxSize) {
        this.maxSize = maxSize;
        this.items = new LinkedList<>();
        this.checkedItems = new LinkedList<>();
    }

    /**
     * Функция добавления в буфер обрабатываемого объекта
     * @param obj объект класса URLDepthPair
     * @return true, если объект был добавлен, false в противном случае
     */
    public boolean put(URLDepthPair obj) {
        boolean flagAdded = false;
        if (items.size() < maxSize) {
            items.addLast(obj); // Объект добавляется в конец списка
            flagAdded = true;
        }
        return flagAdded;
    }

    /**
     * Функция получения объекта из буфера
     * @return объект класса URLDepthPair из начала списка
     */
    public URLDepthPair get() {
        URLDepthPair item = null;
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
}
