package Access;

import Person.User;

import java.time.LocalTime;
import java.util.Scanner;
import java.util.ArrayList;

public class AccessScreen {

    ArrayList<User> users = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    User dummyUser = new User(null, null, null, null);

    public void menu() {
        int option = 0;
        while (option != 3) {
            System.out.println("Welcome to JavaBank");
            System.out.println("1. Create Account");
            System.out.println("2. Log In");
            System.out.println("3. Close Application");
            System.out.println("Please enter your numbered choice (1, 2 or 3)");

            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (option) {
                case 1:
                    User newUser = dummyUser.register();
                    users.add(newUser);
                    String password;
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Closing application...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose 1, 2 or 3.");
            }
        }
    }

    public void login() {
        System.out.println("Please enter user ID:");
        String enteredId = sc.nextLine();

        User currentUser = null;
        for (User u : users) {
            if (u.id != null && u.id.equals(enteredId)) {
                currentUser = u;
                break;
            }
        }

        if (currentUser == null) {
            System.out.println("Stated ID is not found, please enter a valid ID.");
            return;
        }

        if (!currentUser.active) {
            System.out.println("The account associated with this ID is blocked.\nContact a system administrator.");
            return;
        }

        System.out.println("Please enter your password:");
        String enteredPassword = sc.nextLine();

        if (equals(enteredPassword)) {
            System.out.println("You have successfully logged in.");
            accountMenu(currentUser);
        } else {
            System.out.println("Mal");
            System.out.println("Prueba a log in en otro momento");
            accountMenu(currentUser);
        }
    }

    public void accountMenu(User currentUser) {
        int option = 0;
        while (option != 7) {
            System.out.println("Welcome " + currentUser.name);
            System.out.println("1. Create BankAccount");
            System.out.println("2. Make a deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Recharge SIM card");
            System.out.println("6. Transaction history");
            System.out.println("7. Log Out");
            System.out.println("Please enter your numbered choice:");

            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (option) {
                case 1:


                case 2:
                    System.out.println("¿Cuánto dinero quieres ingresar?");
                    double ingreso = sc.nextDouble();
                    sc.nextLine();
                    if (ingreso > 0) {
                        currentUser.saldo += ingreso;
                        currentUser.lastDeposit = ingreso;
                        currentUser.depositCount++;
                        LocalTime ahora = LocalTime.now();
                        System.out.println("Has ingresado €" + ingreso);
                        System.out.println("--------------------------");
                        System.out.println("Hora de la transacción: " + currentUser.getTime());
                        System.out.println("--------------------------");
                    } else {
                        System.out.println("Cantidad inválida.");
                    }
                    break;
                case 3:
                    System.out.println("¿Cuánto dinero quieres retirar?");
                    double retiro = sc.nextDouble();
                    sc.nextLine();
                    if (retiro > 0 && retiro <= currentUser.saldo) {
                        currentUser.saldo -= retiro;
                        currentUser.lastWithdrawal = retiro;
                        currentUser.withdrawalCount++;
                        System.out.println("Has retirado €" + retiro);
                        System.out.println("--------------------------");
                        System.out.println("Hora de la transacción: " + currentUser.getTime());
                        System.out.println("--------------------------");

                    } else {
                        System.out.println("Cantidad inválida o saldo insuficiente.");
                    }
                    break;
                case 4:

                case 5:
                    System.out.println("Introduce el 0 para volver atrás.");
                    System.out.println("Seleccione la cantidad que quieres recargar");
                    double recargar = sc.nextDouble();
                    sc.nextLine();

                    if (recargar == 0) {
                        System.out.println("Volviendo a entrada.");//Para que pueda volver atrás
                        break;
                    }

                    if (recargar > currentUser.saldo) {
                        System.out.println("No tienes saldo suficiente para recargar.");
                    } else {
                      currentUser.saldo -= recargar;
                        System.out.println("Recarga exitosa.");
                        System.out.println("--------------------------");
                        System.out.println("Hora de la transacción: " + currentUser.getTime());
                        System.out.println("--------------------------");
                    }
                    System.out.println("Tu saldo actual es: " + currentUser.saldo); //Muestra el saldo de la cuenta seleccionada activa donde esta
                    currentUser.lastTransfer = recargar;
                    currentUser.transferCount++;

                case 6:
                    System.out.println("Historial de movimientos");
                    System.out.println("-------------------------");
                    System.out.println("Último depósito: " + currentUser.lastDeposit + "--Día y hora de este--" + currentUser.getTime());
                    System.out.println("Último retiro: " + currentUser.lastWithdrawal + "--Día y hora de este--"  + currentUser.getTime());
                    System.out.println("Última transferencia/recarga: " + currentUser.lastTransfer + "--Día y hora de este--"  + currentUser.getTime());
                    System.out.println("Saldo actual: " + currentUser.saldo);
                    System.out.println("-------------------------");
                    System.out.println("Total depósitos realizados: " + currentUser.depositCount);
                    System.out.println("Total retiros realizados: " + currentUser.withdrawalCount);
                    System.out.println("Total transferencia  realizadas: " + currentUser.transferCount);
                    System.out.println("-------------------------");
                    System.out.println("<-Total de transacciones->");


                    break;

                case 7:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 7.");
            }
        }
    }
}
