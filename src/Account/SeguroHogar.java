package Account;

import java.util.Scanner;
import Account.BankAccount;

public class SeguroHogar {

    public void elegirSeguro(BankAccount cuenta) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== SEGURO DE HOGAR ===");
        System.out.println("1. Básico");
        System.out.println("2. Intermedio");
        System.out.println("3. Completo");

        String opcion = sc.nextLine();
        double precio = 0.0;
        String nombreSeguro = "";

        switch (opcion) {

            case "1":
                nombreSeguro = "Seguro de Hogar - Básico";
                precio = 150;
                break;

            case "2":
                nombreSeguro = "Seguro de Hogar - Intermedio";
                precio = 240;
                break;

            case "3":
                nombreSeguro = "Seguro de Hogar - Completo";
                precio = 350;
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
                    System.out.println("Seguro contratado correctamente.");
                } else {
                    System.out.println("No tienes suficiente saldo.");
                }
                break;

            case "2":
                System.out.println("Has pagado con tarjeta.");
                break;

            default:
                System.out.println("Método de pago no válido.");
                break;
        }
    }
}
