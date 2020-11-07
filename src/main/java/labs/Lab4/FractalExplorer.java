package labs.Lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

/**
 * Класс пользовательского интерфейса для отображения фракталов
 */
public class FractalExplorer {

    // Приватные поля класса
    private Rectangle2D.Double aDouble;
    private FractalGenerator fractalGenerator;
    private JImageDisplay imageDisplay;
    private int size;

    /**
     * Конструктор класса
     * @param size размер окна
     */
    public FractalExplorer (int size){
        if (size <= 0) throw new IllegalArgumentException("Size must be better than 0; got " + size);
        else this.size = size;
        aDouble = new Rectangle2D.Double();
        new Mandelbrot().getInitialRange(aDouble);
        fractalGenerator = new Mandelbrot();
    }

    /**
     * Метод для создания пользовательского интерфейса
     */
    private void createAndShowGUI() {
        JFrame frame = new JFrame("Fractal Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();

        contentPane.setLayout(new BorderLayout());
        imageDisplay = new JImageDisplay(size, size);
        contentPane.add(imageDisplay, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset Display");
        resetButton.addActionListener(e -> {
            imageDisplay.clearImage();
            drawFractal();
            }
        );
        contentPane.add(resetButton, BorderLayout.SOUTH);

        /**
        JComboBox<FractalGenerator> jComboBox = new JComboBox<>();
        jComboBox.addItem(new Mandelbrot());
        jComboBox.addActionListener(e -> { fractalGenerator = (FractalGenerator) jComboBox.getSelectedItem(); });
        contentPane.add(jComboBox, BorderLayout.NORTH);
         **/

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal() {
        double xCoord;
        double yCoord;
        int numIters;
        fractalGenerator.getInitialRange(aDouble);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                xCoord = FractalGenerator.getCoord(aDouble.x, aDouble.x + aDouble.width, size, i);
                yCoord = FractalGenerator.getCoord(aDouble.y, aDouble.y + aDouble.height, size, j);
                numIters = fractalGenerator.numIterations(xCoord, yCoord);
                if (numIters == -1) imageDisplay.drawPixel(i, j, 0);
                else {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    imageDisplay.drawPixel(i, j, rgbColor);
                }
            }
        }
        imageDisplay.repaint();
    }

    class MyMouseListener extends MouseAdapter {

        public MouseListener mouseListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (size <= 0) {
                    double xCord;
                    double yCord;
                    xCord = FractalGenerator.getCoord(aDouble.x, aDouble.x + aDouble.width, size, e.getX());
                    yCord = FractalGenerator.getCoord(aDouble.y, aDouble.y + aDouble.height, size, e.getY());
                    FractalGenerator.recenterAndZoomRange(aDouble, xCord, yCord, 0.5);
                    drawFractal();
                }
            }

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
}



