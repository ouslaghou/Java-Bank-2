package Access;

import Account.Inversiones;

public class FondoIndexado extends Inversiones {
    public double rentabilidadAnual = 0;
    public int anyos = 0;

    public FondoIndexado(double capital) {
        super(capital);
        this.rentabilidadAnual = rentabilidadAnual;
        this.anyos = anyos;
    }

    @Override
    public double calcularValorActual() {
        return capital * Math.pow(1 + rentabilidadAnual, anyos);
    }
}
