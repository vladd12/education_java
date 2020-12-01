package labs.Lab7;

public class URLDepthPair {

    private String URL; // поле класса со строкой URL
    private int depth; // поле класса с глубиной поиска

    /**
     * Конструктор класса по умолчанию
     */
    public URLDepthPair() {
        this.URL = null;
        this.depth = 0;
    }

    /**
     * Конструктор класса с параметрами
     * @param URL строка URL
     * @param depth глубина поиска
     */
    public URLDepthPair(String URL, int depth) { //
        this.URL = URL;
        this.depth = depth;
    }

    /**
     * Getter для строки URL
     * @return текущая строка URL объекта
     */
    public String getURL() {
        return URL;
    }

    /**
     * Getter для глубины поиска
     * @return текущая глубина поиска объекта
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Setter для строки URL
     * @param URL новая строка URL
     */
    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     * Setter для глубины поиска
     * @param depth новая глубина поиска
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
}
