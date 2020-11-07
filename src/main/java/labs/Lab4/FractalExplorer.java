package labs.Lab4;

import javax.swing.*;
import java.awt.*;
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

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Fractal Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();

        contentPane.setLayout(new BorderLayout());
        imageDisplay = new JImageDisplay(size, size);
        contentPane.add(imageDisplay, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset Display");
        resetButton.addActionListener(e -> imageDisplay.clearImage());
        contentPane.add(resetButton, BorderLayout.SOUTH);

        JComboBox<FractalGenerator> jComboBox = new JComboBox<>();
        jComboBox.addItem(new Mandelbrot());
        jComboBox.addActionListener(e -> {
            fractalGenerator = (FractalGenerator) jComboBox.getSelectedItem();
        });
        contentPane.add(jComboBox, BorderLayout.NORTH);
    }

}
