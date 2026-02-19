package Account;

import java.util.Scanner;
import Account.BankAccount;

public class SeguroCoche {

    public void elegirSeguro(BankAccount cuenta) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== SEGURO DE COCHE ===");
        System.out.println("1. Terceros");
        System.out.println("2. Terceros Ampliado");
        System.out.println("3. Todo Riesgo");

        String opcion = sc.nextLine();
        double precio = 0.0;
        String nombreSeguro = "";

        switch (opcion) {

            case "1":
                System.out.println("Has elegido Seguro de Coche - Terceros");
                precio = 160;
                nombreSeguro = "Seguro de Coche - Terceros";
                break;

            case "2":
                System.out.println("Has elegido Seguro de Coche - Terceros Ampliado");
                precio = 280;
                nombreSeguro = "Seguro de Coche - Terceros Ampliado";
                break;

            case "3":
                System.out.println("Has elegido Seguro de Coche - Todo Riesgo");
                precio = 520;
                nombreSeguro = "Seguro de Coche - Todo Riesgo";
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
