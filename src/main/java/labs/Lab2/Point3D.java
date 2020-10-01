package labs.Lab2;

/**
 * Класс трёхмерной точки
 */
public class Point3D extends Point2D {
    private double z; // Координата по оси Z

    /**
     * Конструктор по умолчанию
     */
    public Point3D() {
        super();
        this.z = 0;
    }

    /**
     * Конструктор с параметрами (конструктор инициализации)
     * @param x параметр для координаты по оси X
     * @param y параметр для координаты по оси Y
     * @param z параметр для координаты по оси Z
     */
    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    /**
     * Возвращает координату точки по оси Z
     * @return координата точки по оси Z
     */
    public double getZ() {
        return this.z;
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
        return (this.getX() == point.getX() && this.getY() == point.getY() && this.z == point.getZ());
    }

    /**
     * Метод вычисляет расстояние между двумя точками
     * @param point входная точка, до которой надо вычислить расстояние относительно текущей точки
     * @return расстояние между текущей и входной точкой
     */
    public double distanceTo(Point3D point) {
        return Math.sqrt((this.getX() - point.getX()) * (this.getX() - point.getX()) +
        (this.getY() - point.getY()) * (this.getY() - point.getY()) + (this.z - point.getZ()) * (this.z - point.getZ()));
    }
}
