package Access;

import Person.Employee;
import Person.Manager;
import Person.User;

import java.util.ArrayList;
import java.util.Scanner;

public class AccessScreen {

    ArrayList<User> users = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    String id = "";
    User dummyUser = new User(null, null, null, null);

    public AccessScreen() {
        users.add(new Manager("Admin", "Admin123!", "01/01/1980", "00000001"));
        users.add(new Employee("Empleado1", "Empleado123!", "01/01/1990", 1));
    }

    public void menu() {
        int option = 0;

        while (option != 3) {
            System.out.println("Welcome to JavaBank ");
            System.out.println("1. Create Account");
            System.out.println("2. Log In");
            System.out.println("3. Close Application");
            System.out.println("Please enter your numbered choice (1, 2 or 3)");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    User newUser = dummyUser.register();
                    users.add(newUser);
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    return;
            }
        }
    }

    public void login() {
        System.out.println("Please enter user id: ");
        id = sc.nextLine();

        User currentUser = null;

        for (User u : users) {
            if (u.id != null && u.id.equals(id)) {
                currentUser = u;
            }
        }

        if (currentUser == null) {
            System.out.println("Stated id is not found, please enter a valid id");
            return;
        }

        if (!currentUser.active) {
            System.out.println("The account associated with this id is blocked.");
            return;
        }

        int tries = 0;

        while (tries != 3) {
            System.out.println("Please enter password: ");
            String pass = sc.nextLine();

            if (pass.equals(currentUser.password)) {
                System.out.println("You have successfully logged in");

                if (currentUser.tipoUsuario.equals("cliente")) {
                    accountMenuCliente(currentUser);
                } else if (currentUser.tipoUsuario.equals("empleado")) {
                    accountMenuEmpleado(currentUser);
                } else if (currentUser.tipoUsuario.equals("gerente")) {
                    accountMenuGerente(currentUser);
                }
                return;
            } else {
                System.out.println("Wrong password, please try again");
                tries++;

                if (tries == 3) {
                    System.out.println("You have failed to log in, your account has been blocked.");
                    currentUser.active = false;
                }
            }
        }
    }

    public void accountMenuCliente(User currentUser) {
        int option = 0;

        while (option != 5) {
            System.out.println("1. Make a deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer Money");
            System.out.println("4. Recharge SIM");
            System.out.println("5. Log Out");

            option = sc.nextInt();
            sc.nextLine();
        }
    }

    public void accountMenuEmpleado(User currentUser) {
        int option = 0;

        while (option != 5) {
            System.out.println("1. Create BankAccount for a client");
            System.out.println("2. Make a deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Log Out");

            option = sc.nextInt();
            sc.nextLine();
        }
    }

    public void accountMenuGerente(User currentUser) {
        int option = 0;

        while (option != 6) {
            System.out.println("1. Create BankAccount");
            System.out.println("2. Create User");
            System.out.println("3. Block User");
            System.out.println("4. Make a deposit");
            System.out.println("5. Transfer Money");
            System.out.println("6. Log Out");

            option = sc.nextInt();
            sc.nextLine();
        }
    }
}