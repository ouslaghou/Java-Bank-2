package Account;

import java.util.Scanner;

public class SeguroVida {

    public void elegirSeguro(BankAccount cuenta) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== SEGURO DE VIDA ===");
        System.out.println("1. Básico");
        System.out.println("2. Intermedio");
        System.out.println("3. Completo");

        String opcion = sc.nextLine();
        double precio = 0.0;
        String nombreSeguro = "";

        switch (opcion) {

            case "1":
                System.out.println("Has elegido Seguro de Vida - Básico");
                precio = 40;
                nombreSeguro = "Seguro de Vida - Básico";
                break;

            case "2":
                System.out.println("Has elegido Seguro de Vida - Intermedio");
                precio = 120;
                nombreSeguro = "Seguro de Vida - Intermedio";
                break;

            case "3":
                System.out.println("Has elegido Seguro de Vida - Completo");
                precio = 250;
                nombreSeguro = "Seguro de Vida - Completo";
                break;

            default:
                System.out.println("Opción no válida.");
                return;
        }

        // Elegir método de pago
        System.out.println("\n¿Cómo quieres pagar?");
        System.out.println("1. Cuenta bancaria");
        System.out.println("2. Tarjeta");

        String pago = sc.nextLine();

        switch (pago) {

            case "1":
                // Pagar con cuenta bancaria
                if (cuenta.balance >= precio) {
                    cuenta.balance -= precio;
                    cuenta.addHistory("Pago seguro: " + nombreSeguro, precio);
                    System.out.println("Has pagado " + precio + "€ desde tu cuenta bancaria.");
                    System.out.println("Saldo restante: " + cuenta.balance + "€");
                } else {
                    System.out.println("No tienes suficiente saldo.");
                }
                break;

            case "2":
                // Pagar con tarjeta (no descuenta nada)
                System.out.println("Has pagado " + precio + "€ con tarjeta.");
                break;

            default:
                System.out.println("Método de pago no válido.");
                break;
        }
    }
}
