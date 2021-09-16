package zinovev_lab_4;

import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends javax.swing.JComponent {

    private BufferedImage image;

    //конструктор. width - ширина, height - высота
    public JImageDisplay (int width, int height){
        //TYPE_INT_RGB означает, что красные, зеленые и синие компоненты имеют по 8 битов
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        super.setPreferredSize(new Dimension(width, height));
    }

    // устанавливает все пиксели изображения в черный цвет
    public void clearImage() {
        for (int x = 0; x < image.getHeight(); x++) {
            for (int y = 0; y < image.getWidth(); y++) {
                image.setRGB(x, y,0);
            }
        }
    }

    //устанавливает пиксель в определенный цвет (rgbColor)
    public void drawPixel (int x, int y, int rgbColor) {
        if (x < image.getWidth() && y < image.getHeight())
            image.setRGB(x, y, rgbColor);
    }

    //выводит на экран image
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage (image, 0, 0, image.getWidth(), image.getHeight(), null);
    }
}
