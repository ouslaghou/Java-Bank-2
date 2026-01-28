package Access;

import Person.User;
import java.util.Scanner;
import java.util.ArrayList;

public class AccessScreen {

    ArrayList<User> users = FileManager.loadUsers(); // carga desde fichero
    Scanner sc = new Scanner(System.in);
    User dummyUser = new User(null, null, null, null);

    // ============================
    // MAIN MENU
    // ============================
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
                    FileManager.saveUsers(users);
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    System.out.println("Closing application...");
                    FileManager.saveUsers(users);
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }

    // ============================
    // ACCOUNT MENU
    // ============================
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
            System.out.println("Please enter your numbered choice (1–6)");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("BankAccount creation not implemented yet");

                    break;

                case 2:
                    System.out.println("Deposit not implemented yet");
                    break;

                case 3:
                    System.out.println("Withdraw not implemented yet");
                    break;

                case 4:
                    System.out.println("Transfer not implemented yet");
                    break;

                case 5:
                    System.out.println("SIM recharge not implemented yet");
                    break;

                case 6:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }

    // ============================
    // LOGIN
    // ============================
    public void login() {

        System.out.println("Please enter user id: ");
        String id = sc.nextLine();

        User currentUser = null;

        for (User u : users) {
            if (u.id.equals(id)) {
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
            String password = sc.nextLine();

            if (password.equals(currentUser.password)) {
                System.out.println("You have successfully logged in");
                accountMenu(currentUser);
                return;
            }

            System.out.println("Wrong password, please try again");
            tries++;
        }

        System.out.println("You have failed to log in, your account has been blocked.\nPlease contact a system admin to resolve this issue.");
        currentUser.active = false;

        FileManager.saveUsers(users);
    }
}
