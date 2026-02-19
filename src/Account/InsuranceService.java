package Account;

import java.util.Scanner;
import Account.BankAccount;

public class InsuranceService {

    public void contratarSeguro(BankAccount cuenta) {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== TIPOS DE SEGURO ===");
        System.out.println("1. Seguro de Vida");
        System.out.println("2. Seguro de Hogar");
        System.out.println("3. Seguro de Coche");
        System.out.println("4. Seguro de Salud");

        String opcion = sc.nextLine();

        switch (opcion) {

            case "1":
                new SeguroVida().elegirSeguro(cuenta);
                break;

            case "2":
                new SeguroHogar().elegirSeguro(cuenta);
                break;

            case "3":
                new SeguroCoche().elegirSeguro(cuenta);
                break;

            case "4":
                new SeguroSalud().elegirSeguro(cuenta);
                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
}
