package zinovev_lab_4;

import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {

    //максимальное число итераций
    public static final int MAX_ITERATIONS = 2000;

    //метод подсчета количества итераций для точки
    public int numIterations(double x, double y) {
        Complex z = new Complex(0, 0);
        Complex c = new Complex(x, y);

        int iterations = MAX_ITERATIONS;
        //количество итераций для точки по формуле z(n)=z(n-1)^2 + c
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            Complex newZ = z.multiply(z).add(c);
            //условие выхода при |z| > 2
            if (newZ.absSqr() > 4) {
                iterations = i;
                break;
            }
            z = newZ;
        }

        //возвращается -1, чтобы показать  точка не выходит за границы
        //иначе возвращается количество итераций для точки
        if (iterations == MAX_ITERATIONS) {
            return -1;
        } else {
            return iterations;
        }
    }

    //метод устанавливает начальный диапазон в (-2 - 1.5i)  (1 + 1.5i)
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }
}