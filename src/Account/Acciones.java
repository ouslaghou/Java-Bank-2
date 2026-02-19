package Account;

public class Acciones extends Inversiones{

    String empresa = "";
    double cantidad = 0;
    double precioActual = 0;

    public Acciones(double capital) {
        super(capital);
        this.empresa = empresa;
        this.cantidad = cantidad;
        this.precioActual = precioActual;
    }

    @Override
    public double calcularValorActual() {
        return cantidad * precioActual;
    }
}
