package zinovev_lab_4;

//класс комплексных чисел
public class Complex {
    //реальная часть комплексного числа
    private double r;
    //мнимая часть комплексного числа
    private double i;

    //получение реальной части комплексного числа
    public double getR() {
        return this.r;
    }

    //получение мнимой части комплексного числа
    public double getI() {
        return this.i;
    }

    //конструктор
    public Complex (double r, double i) {
        this.r = r;
        this.i = i;
    }

    //умножение
    public Complex multiply(Complex c) {
        return new Complex(this.r * c.getR() - this.i * c.getI(),
                this.r * c.getI() + this.i * c.getR());
    }

    //сложение
    public Complex add(Complex c) {
        return new Complex(this.r + c.getR(), this.i + c.getI());
    }

    //модуль
    public Complex abs() {
        return new Complex(this.r < 0 ? this.r *(-1) : this.r,
                this.i < 0 ? this.i *(-1) : this.i);
    }

    //квадрат модуля
    public double absSqr() {
        return this.r * this.r + this.i * this.i;
    }

    //комплексное сопряжение
    public Complex inverseI() {
        return new Complex(this.r, this.i * (-1));
    }
}