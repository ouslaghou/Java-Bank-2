package Account;

import java.util.Random;

public class GeneradorTarjeta {

    public static String generarTarjeta(int longitud) {
        Random random = new Random();
        int[] numeros = new int[longitud];

        // Primer dígito (por ejemplo 4 = Visa, puedes cambiarlo)
        numeros[0] = 4;

        // Generar números aleatorios excepto el último
        for (int i = 1; i < longitud - 1; i++) {
            numeros[i] = random.nextInt(10);
        }

        // Calcular dígito verificador (último)
        numeros[longitud - 1] = calcularDigitoLuhn(numeros);

        // Convertir a String
        StringBuilder tarjeta = new StringBuilder();
        for (int n : numeros) {
            tarjeta.append(n);
        }

        return tarjeta.toString();
    }

    private static int calcularDigitoLuhn(int[] numeros) {
        int suma = 0;
        boolean duplicar = true;

        // Empezamos desde el penúltimo
        for (int i = numeros.length - 2; i >= 0; i--) {
            int digito = numeros[i];

            if (duplicar) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }

            suma += digito;
            duplicar = !duplicar;
        }

        return (10 - (suma % 10)) % 10;
    }

    public static void main(String[] args) {
        String tarjetaRandom = generarTarjeta(16);
        System.out.println("Tarjeta generada: " + tarjetaRandom);
    }
}
