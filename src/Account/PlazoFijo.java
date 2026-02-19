package Account;

public class PlazoFijo extends Inversiones{

    double intereses = 0;
    int anyos = 0;

    public PlazoFijo(double capital) {
        super(capital);
        this.intereses = intereses;
        this.anyos = anyos;   }

    @Override
    public double calcularValorActual() {
        return capital * Math.pow(1 + intereses, anyos);
    }

}
