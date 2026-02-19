package Access;

import Account.BankAccount;
import Person.Person;

import java.util.ArrayList;
import java.util.Scanner;

public class metodospago {

    public ArrayList<Person> persons = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void Bizum(int amount, BankAccount account) {

        System.out.println("Bienvenido a Bizum");
        System.out.println("1. Iniciar sesión");

        String opcion = sc.nextLine();

        if (!opcion.equals("1")) return;

        System.out.println("\nIntroduce tu ID:");
        String id = sc.nextLine();

        Person p = null;
        for (Person x : persons) {
            if (x.id.equals(id)) {
                p = x;
                break;
            }
        }

        if (p == null) {
            System.out.println("ID no encontrado.");
            return;
        }

        if (!p.active) {
            System.out.println("Este usuario está bloqueado.");
            return;
        }

        int intentos = 0;

        while (intentos < 3) {
            System.out.println("Introduce tu contraseña:");
            String pass = sc.nextLine();

            if (!p.password.equals(pass)) {
                intentos++;
                if (intentos < 3) {
                    System.out.println("Contraseña incorrecta. Te quedan " + (3 - intentos) + " intentos.");
                } else {
                    p.active = false;
                    System.out.println("Usuario bloqueado: " + p.id);
                    return;
                }
            } else {
                System.out.println("Inicio de sesión correcto. Bienvenido " + p.name);

                pagar(amount, account);
                return;
            }
        }
    }

    private void pagar(int amount, BankAccount account) {

        if (amount > account.balance) {
            System.out.println("No tienes suficiente saldo.");
            return;
        }

        account.balance -= amount;
        account.addHistory("PAYMENT (BIZUM)", amount);


        System.out.println("Pago realizado correctamente: " + amount + "€");
    }
}


