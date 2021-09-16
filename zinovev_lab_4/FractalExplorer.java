package zinovev_lab_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    //ширина и высота отображения в пикселях
    private int displaySize;
    //отображение
    private JImageDisplay imageDisplay;
    //фрактал
    private FractalGenerator gen;
    //диапазон комплексной плоскости, которая выводится на экран
    private Rectangle2D.Double rec;

    //конструктор. size - размер отображения
    public FractalExplorer (int size) {
        displaySize = size;
        gen = new Mandelbrot();
        imageDisplay = new JImageDisplay(size, size);
        rec = new Rectangle2D.Double();
        gen.getInitialRange(rec);
    }

    // создание и показ интерфейса
    private void createAndShowGUI() {
        //создание основного окна с заголовком
        JFrame frame = new JFrame();
        frame.setTitle("Fractal explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //создание кнопоки Reset и добавление
        JButton reset = new JButton("Reset display");
        frame.getContentPane().add(imageDisplay, BorderLayout.CENTER);
        frame.getContentPane().add(reset, BorderLayout.SOUTH);

        //добавление обработчиков событий для элементов управления
        reset.addActionListener(new ResetButtonListener());
        imageDisplay.addMouseListener(new ImageDisplayListener());

        frame.pack ();
        frame.setVisible (true);
        frame.setResizable (false);
    }

    //отрисовка фрактала
    private void drawFractal () {
        for (int x=0; x< displaySize; x++) {
            for (int y = 0; y < displaySize; y++) {
                //перевод из пиксельный координат в координаты в пространстве фрактала
                double xCoord = FractalGenerator.getCoord(rec.x, rec.x + rec.width,
                        displaySize, x);
                double yCoord = FractalGenerator.getCoord(rec.y, rec.y + rec.height,
                        displaySize, y);

                //количество итерацийдля текущего пикселя
                int numIters = gen.numIterations(xCoord, yCoord);

                //если точка не выходит за границы - окрашивание пикселя в черный цвет
                //Иначе значение цвета пикселя выбирается на основе количества итераций
                if (numIters == -1) {
                    imageDisplay.drawPixel(x, y, 0);
                } else {
                    //цветовое пространство HSV
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);

                    imageDisplay.drawPixel(x, y, rgbColor);
                }
            }
        }
        // обновление отображения
        imageDisplay.repaint();
    }

    // запуск оконного приложения
    public static void main(String args[]) {
        // инициализация фрактала (ссылка на базовый класс для отображения разных видов фракталов)
        FractalExplorer fractalExplorer = new FractalExplorer(500);

        // создание и показ интерфейса
        fractalExplorer.createAndShowGUI();
        // отрисовка фрактала
        fractalExplorer.drawFractal();
    }

    //класс для обработки событий java.awt.event.ActionListener от кнопки reset
    public class ResetButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            //сброс диапазона к начальному, определенному генератором
            gen.getInitialRange(rec);
            //перерисовка фрактала
            drawFractal();
        }
    }

    //класс для обработки событий  java.awt.event.MouseListener с дисплея
    public class ImageDisplayListener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);

            //получение координат клика
            int x = e.getX();
            int y = e.getY();

            //перевод из пиксельный координат в координаты в пространстве фрактала
            double xCoord = FractalGenerator.getCoord (rec.x, rec.x + rec.width,
                    displaySize, x);
            double yCoord = FractalGenerator.getCoord (rec.y, rec.y + rec.height,
                    displaySize, y);

            gen.recenterAndZoomRange(rec, xCoord, yCoord, 0.5);
            //перерисовка фрактала
            drawFractal();
        }
    }
}
