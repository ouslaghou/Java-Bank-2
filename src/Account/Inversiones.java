package Account;

import java.util.Scanner;

public abstract class Inversiones {
    public double capital;

    public Inversiones(double capital) {
        this.capital = capital;

    }
    public abstract double calcularValorActual();

}
