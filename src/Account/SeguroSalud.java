package Account;

import java.util.Scanner;
import Account.BankAccount;

public class SeguroSalud {

    public void elegirSeguro(BankAccount cuenta) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== SEGURO DE SALUD ===");
        System.out.println("1. Básico");
        System.out.println("2. Medio");
        System.out.println("3. Premium");

        String opcion = sc.nextLine();
        double precio = 0.0;
        String nombreSeguro = "";

        switch (opcion) {

            case "1":
                System.out.println("Has elegido Seguro de Salud - Básico");
                precio = 600;
                nombreSeguro = "Seguro de Salud - Básico";
                break;

            case "2":
                System.out.println("Has elegido Seguro de Salud - Medio");
                precio = 900;
                nombreSeguro = "Seguro de Salud - Medio";
                break;

            case "3":
                System.out.println("Has elegido Seguro de Salud - Premium");
                precio = 1500;
                nombreSeguro = "Seguro de Salud - Premium";
                break;

            default:
                System.out.println("Opción no válida.");
                return;
        }

        // Método de pago
        System.out.println("\n¿Cómo quieres pagar?");
        System.out.println("1. Cuenta bancaria");
        System.out.println("2. Tarjeta");

        String pago = sc.nextLine();

        switch (pago) {

            case "1":
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
                System.out.println("Has pagado " + precio + "€ con tarjeta.");
                break;

            default:
                System.out.println("Método de pago no válido.");
                break;
        }
    }
}
