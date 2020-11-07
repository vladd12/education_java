package labs.Lab4;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * Основной класс для работы с записью изображений
 */
public class JImageDisplay extends JComponent {

    // Содержимое изображения
    private final BufferedImage image;

    /**
     * Конструктор класса изображения
     * @param width ширина изображения в пикселях
     * @param height высота изображения в пикселях
     */
    public JImageDisplay(int width, int height) {
        int TYPE_INT_RGB = 1;
        image = new BufferedImage(width, height, TYPE_INT_RGB);
        Dimension SIZE = new Dimension(width, height);
        setPreferredSize(SIZE);
    }

    /**
     * Это очень странная реализация, и я даже не знаю, будет ли она работать
     */
    @Override
    protected void paintComponent (Graphics g) {
        g.drawImage (image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    /**
     * Метод для установки всех пикселей изображения в чёрный цвет (значение RGB 0)
     */
    public void clearImage() {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.setRGB(i, j, 0);
            }
        }
    }

    /**
     * Метод для установки определённого пикселя в определённый цвет
     * @param x координата пикселя по ширине
     * @param y координата пикселя по высоте
     * @param rgbColor RGB код устанавливаемого цвета
     */
    public void drawPixel(int x, int y, int rgbColor) {
        image.setRGB(x, y, rgbColor);
    }
}
