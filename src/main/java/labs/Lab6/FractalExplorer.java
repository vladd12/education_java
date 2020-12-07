package labs.Lab6;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

/**
 * Класс пользовательского интерфейса для отображения фракталов
 */
public class FractalExplorer {

    // Приватные поля класса
    private final Rectangle2D.Double aDouble;
    private FractalGenerator fractalGenerator;
    private JImageDisplay imageDisplay;
    private final int size;
    private  int rows;

    // Отключаемые элементы
    private JComboBox<FractalGenerator> jComboBox;
    private JButton resetButton;
    private JButton saveButton;

    /**
     * Конструктор класса
     * @param size размер окна
     */
    public FractalExplorer (int size){
        if (size <= 0) throw new IllegalArgumentException("Size must be better than 0; got " + size);
        else this.size = size;
        aDouble = new Rectangle2D.Double();
        fractalGenerator = new Mandelbrot();
        fractalGenerator.getInitialRange(aDouble);
    }

    /**
     * Метод для создания пользовательского интерфейса
     */
    private void createAndShowGUI() {
        // Первоначальные установки для окна
        JFrame frame = new JFrame("Fractal Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        fractalGenerator.getInitialRange(aDouble);

        // Установки настройки изображения
        contentPane.setLayout(new BorderLayout());
        imageDisplay = new JImageDisplay(size, size);
        imageDisplay.addMouseListener(new MyMouseListener().mouseListener);
        contentPane.add(imageDisplay, BorderLayout.CENTER);

        // Кнопка сброса изображения
        resetButton = new JButton("Reset Display");
        resetButton.addActionListener(e -> {
            imageDisplay.clearImage();
            fractalGenerator.getInitialRange(aDouble);
            drawFractal();
            }
        );

        // Кнопка сохранения изображения
        saveButton = new JButton("Save Image");
        saveButton.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            FileFilter fileFilter = new FileNameExtensionFilter("PNG Images", "png");
            jFileChooser.setFileFilter(fileFilter);
            jFileChooser.setAcceptAllFileFilterUsed(false);
            if (jFileChooser.showDialog(frame, "Save") == JFileChooser.APPROVE_OPTION) {
                try {
                    ImageIO.write(imageDisplay.getBufferedImage(), "png", jFileChooser.getSelectedFile());
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(frame, ioException.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        // Надстройки для вывода на окне кнопок сброса и сохранения
        JPanel jPanelForButtons = new JPanel();
        jPanelForButtons.add(resetButton);
        jPanelForButtons.add(saveButton);
        contentPane.add(jPanelForButtons, BorderLayout.SOUTH);

        // Выбор фрактала
        jComboBox = new JComboBox<>();
        jComboBox.addItem(new Mandelbrot());
        jComboBox.addItem(new Tricorn());
        jComboBox.addItem(new BurningShip());
        jComboBox.addActionListener(e -> {
            fractalGenerator = (FractalGenerator) jComboBox.getSelectedItem();
            if (fractalGenerator == null) throw new NullPointerException("Select type of fractal.");
            else {
                fractalGenerator.getInitialRange(aDouble);
                drawFractal();
                imageDisplay.repaint();
            }
        });

        // Надстройки для вывода на окне выбора фракталов
        JPanel jPanelForComboBox = new JPanel();
        jPanelForComboBox.add(new JLabel("Fractal"));
        jPanelForComboBox.add(jComboBox);
        contentPane.add(jPanelForComboBox, BorderLayout.NORTH);

        // Свойства окна
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Вспомогательный метод для вывода фрактала на экран
     */
    private void drawFractal() {
        rows = size;
        enableUI(false);
        for (int i = 0; i < size; i++) {
            FractalWorker fractalWorker = new FractalWorker(i);
            fractalWorker.execute();
        }
        imageDisplay.repaint();
    }

    /**
     * Класс для обработки нажатий мыши по дисплею
     */
    private class MyMouseListener extends MouseAdapter {

        public MouseListener mouseListener = new MouseListener() {

            /**
             * Переопределение события при нажатии левой кнопки мышки по фракталу
             * @param e MouseEvent, стандартный элемент управления
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                if (rows <= 0) {
                    double xCord;
                    double yCord;
                    xCord = FractalGenerator.getCoord(aDouble.x, aDouble.x + aDouble.width, size, e.getX());
                    yCord = FractalGenerator.getCoord(aDouble.y, aDouble.y + aDouble.height, size, e.getY());
                    FractalGenerator.recenterAndZoomRange(aDouble, xCord, yCord, 0.5);
                    drawFractal();
                }
            }

            // Эти методы также переопределены, но ничего не делают

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }

        };

    }

    /**
     * Функция отключения элементов интерфейса во время вычисления
     * @param flag при true элементы включены, при false элементы отключены
     */
    private void enableUI (boolean flag){
        if (flag) {
            resetButton.setEnabled(true);
            saveButton.setEnabled(true);
            jComboBox.setEnabled(true);
        }
        else {
            resetButton.setEnabled(false);
            saveButton.setEnabled(false);
            jComboBox.setEnabled(false);
        }
    }

    /**
     * Класс для фонового вычисления значений пикселей изображения фрактала
     */
    private class FractalWorker extends SwingWorker <Object, Object> {
        // Поля класса
        private final int yCoord;
        private int[] rgbPoints;

        /**
         * Конструктор класса
         * @param y целочисленная координата вычисляемой строки
         */
        FractalWorker(int y) {
            this.yCoord = y;
        }

        /**
         * Функция выполняет в фоне вычисления цветов пикселей факториалов
         * @return всегда возвращает null
         */
        @Override
        protected Object doInBackground() {
            rgbPoints = new int[size + 1];
            double xCoord;
            double yCoord;
            int numIters;
            yCoord = FractalGenerator.getCoord(aDouble.y, aDouble.y + aDouble.height, size, this.yCoord);
            for (int i = 0; i < size; i++) {
                xCoord = FractalGenerator.getCoord(aDouble.x, aDouble.x + aDouble.width, size, i);
                numIters = fractalGenerator.numIterations(xCoord, yCoord);
                if (numIters == -1) rgbPoints[i] = 0;
                else {
                    float hue = 0.7f + (float) numIters / 200f;
                    rgbPoints[i] = Color.HSBtoRGB(hue, 1f, 1f);
                }
            }
            return null;
        }

        /**
         * Функция заполняет строки пикселями при готовности строки
         */
        @Override
        protected void done() {
            super.done();
            for (int x = 0; x < size; x++){
                imageDisplay.drawPixel(x, this.yCoord, rgbPoints[x]);
            }
            imageDisplay.repaint(1, yCoord, size, 1);
            rows--;
            if (rows <= 0) enableUI(true);
        }
    }

    /**
     * Главный метод (точка входа)
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(600);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }
}