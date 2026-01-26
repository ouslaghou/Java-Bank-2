package Access;

import Person.User;
import java.util.Scanner;
import java.util.ArrayList;

public class AccessScreen {

    ArrayList<User> users = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    String id = "";

    public void menu() {
        int option = 0;

        while (option != 3) {
            System.out.println("Welcome to JavaBank ");
            System.out.println("1. Create Account");
            System.out.println("2. Log In");
            System.out.println("3. Close Application");
            System.out.println("Please enter your numbered choice (1, 2 or 3)");

            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine(); // limpia el buffer
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // descarta entrada inválida
                continue;
            }

            switch (option) {
                case 1:
                    User newUser = new User(null, null, null, "00000000").register();
                    users.add(newUser);
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
        System.out.println("Please enter user id: ");
        id = sc.nextLine();

        User currentUser = null;

        for (User u : users) {
            if (u.id != null && u.id.equals(id)) {
                currentUser = u;
                break;
            }
        }

        if (currentUser == null) {
            System.out.println("Stated id is not found, please enter a valid id");
            return;
        }

        if (!currentUser.active) {
            System.out.println("The account associated with this id is blocked.\nContact a system admin for more information.");
            return;
        }

        int tries = 0;

        while (tries < 3) {
            System.out.println("Please enter password: ");
            String pass = sc.nextLine();

            if (pass.equals(currentUser.password)) {
                System.out.println("You have successfully logged in");
                accountMenu(currentUser);
                return;
            } else {
                System.out.println("Wrong password, please try again");
                tries++;
            }
        }

        System.out.println("You have failed to log in, your account has been blocked.\nPlease contact a system admin.");
        currentUser.active = false;
    }

    public void accountMenu(User currentUser) {
        int option = 0;

        while (option != 6) {
            System.out.println("Welcome " + currentUser.name);
            System.out.println("1. Create BankAccount");
            System.out.println("2. Make a deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Recharge SIM card");
            System.out.println("6. Log Out");
            System.out.println("Please enter your numbered choice (1 to 6)");

            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine(); // limpia el buffer
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (option) {
                case 1:
                    System.out.println("Bank account creation not implemented yet.");
                    break;
                case 2:
                    System.out.println("Deposit not implemented yet.");
                    break;
                case 3:
                    System.out.println("Withdraw not implemented yet.");
                    break;
                case 4:
                    System.out.println("Transfer not implemented yet.");
                    break;
                case 5:
                    System.out.println("SIM recharge not implemented yet.");
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 6.");
            }
        }
    }
}
