package labs.Lab2;

/**
 * Класс трёхмерной точки
 */
public class Point3D {
    private double x; // Координата по оси X
    private double y; // Координата по оси Y
    private double z; // Координата по оси Z

    /**
     * Конструктор по умолчанию
     */
    public Point3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /**
     * Конструктор с параметрами (конструктор инициализации)
     * @param x параметр для координаты по оси X
     * @param y параметр для координаты по оси Y
     * @param z параметр для координаты по оси Z
     */
    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Возвращает координату точки по оси X
     * @return координата точки по оси X
     */
    public double getX() {
        return this.x;
    }

    /**
     * Возвращает координату точки по оси Y
     * @return координата точки по оси Y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Возвращает координату точки по оси Z
     * @return координата точки по оси Z
     */
    public double getZ() {
        return this.z;
    }

    /**
     * Устанавливает новый параметр для координаты точки по оси X
     * @param x новое значение координаты точки по оси X
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Устанавливает новый параметр для координаты точки по оси Y
     * @param y новое значение координаты точки по оси Y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Устанавливает новый параметр для координаты точки по оси Z
     * @param z новое значение координаты точки по оси Z
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Метод сравнения двух объектов класса точки
     * @param point входная точка, с которой будет сравниваться текущая точка
     * @return true, если точки эквивалентны, или false, если точки неэквивалентны
     */
    public boolean equals(Point3D point) {
        return (this.x == point.getX() && this.y == point.getY() && this.z == point.getZ());
    }

    /**
     * Метод вычисляет расстояние между двумя точками
     * @param point входная точка, до которой надо вычислить расстояние относительно текущей точки
     * @return расстояние между текущей и входной точкой
     */
    public double distanceTo(Point3D point) {
        return Math.sqrt((this.x - point.getX()) * (this.x - point.getX()) + (this.y - point.getY()) * (this.y - point.getY()) + (this.z - point.getZ()) * (this.z - point.getZ()));
    }
}
