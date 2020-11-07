package labs.Lab5;

import java.awt.geom.Rectangle2D;

/**
 * Подкласс для фракталов второго вида
 */
public class Tricorn extends FractalGenerator {

    // Константа с максимальным количеством итераций
    public static final int MAX_ITERATIONS = 2000;

    /**
     * Переопределения метода для указания области рисования конкретного факториала
     * @param range прямоугольный объект
     */
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        // Изменение полей прямоугольника
        range.x = -2.0;
        range.y = -2.0;
        range.height = 4.0;
        range.width = 4.0;
    }

    /**
     * Переопределение функции нахождения количества итерация для фрактала Мандельброта
     * @param x действительная часть комплексного числа
     * @param y мнимая часть комплексного числа
     * @return количество итераций, не превышающее максимальное (2000), или -1, если превышает
     */
    @Override
    public int numIterations(double x, double y) {
        ComplexNumber cn1 = new ComplexNumber(x, y);
        ComplexNumber cn2 = new ComplexNumber();
        int iterations = 0;

        return 0;
    }

    /**
     * Переопределение функции toString для удобной работы
     * @return возвращает имя класса
     */
    @Override
    public String toString() {
        return "Tricorn";
    }
}
