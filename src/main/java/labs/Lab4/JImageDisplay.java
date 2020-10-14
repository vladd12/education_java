package labs.Lab4;

import java.awt.image.*;
import javax.swing.*;

public class JImageDisplay extends JComponent {
    private BufferedImage ImageOne;

    public JImageDisplay(int width, int height) {
        int TYPE_INT_RGB = 1;
        ImageOne = new BufferedImage(width, height, TYPE_INT_RGB);
    }
}
