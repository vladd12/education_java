package labs.Lab7;

import java.util.LinkedList;

/**
 * Класс для работы методом "First In, First Out"
 */
public class FIFO {

    private final int maxSize;
    private final LinkedList<URLDepthPair> items;

    public FIFO(int size) {
        this.maxSize = size;
        this.items = new LinkedList<>();
    }

    public boolean put(URLDepthPair obj) {
        boolean flagAdded = false;
        if (items.size() < maxSize) {
            items.addLast(obj);
            flagAdded = true;
        }
        return flagAdded;
    }

    public URLDepthPair get() {
        URLDepthPair item = null;
        synchronized (items) {
            if (items.size() > 0) item = items.removeFirst();
        }
        return item;
    }

}
