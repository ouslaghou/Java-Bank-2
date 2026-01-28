package Access;

import Person.Employee;
import Person.Manager;
import Person.User;
import java.util.ArrayList;
import java.util.Scanner;

public class AccessScreen {

    ArrayList<User> users = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

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
                    System.out.println("Select user type:");
                    System.out.println("1. Client");
                    System.out.println("2. Employee");
                    System.out.println("3. Manager");

                    int type = sc.nextInt();
                    sc.nextLine();

                    User newUser;

                    if (type == 1) {
                        newUser = new User("", "", "");
                    } else if (type == 2) {
                        newUser = new Employee("", "", "", 0);
                    } else {
                        newUser = new Manager("", "", "", "");
                    }

                    newUser = newUser.register();
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
            System.out.println("The account associated with this id is blocked.");
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
            }

            System.out.println("Wrong password, please try again");
            tries++;
        }

        System.out.println("You have failed to log in, your account has been blocked.");
        currentUser.active = false;
    }


    public void accountMenu(User currentUser) {

        System.out.println("Welcome " + currentUser.name);
        System.out.println("Your role: " + currentUser.role);

        if (currentUser.role.equals("manager")) {
            managerMenu(currentUser);
        } else if (currentUser.role.equals("employee")) {
            employeeMenu(currentUser);
        } else {
            userMenu(currentUser);
        }
    }


    public void managerMenu(User currentUser) {
        int option = 0;

        while (option != 7) {
            System.out.println("1. Create BankAccount");
            System.out.println("2. Make a deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Recharge SIM card");
            System.out.println("6. View all users");
            System.out.println("7. Log Out");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:  break;
                case 2:  break;
                case 3:  break;
                case 4:  break;
                case 5:  break;
                case 6: showAllUsers(); break;
                case 7: return;
            }
        }
    }


    public void employeeMenu(User currentUser) {
        int option = 0;

        while (option != 6) {
            System.out.println("1. Create BankAccount");
            System.out.println("2. Make a deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Recharge SIM card");
            System.out.println("6. Log Out");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:  break;
                case 2:  break;
                case 3:  break;
                case 4:  break;
                case 5:  break;
                case 6: return;
            }
        }
    }

    public void userMenu(User currentUser) {
        int option = 0;

        while (option != 5) {
            System.out.println("1. Make a deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer Money");
            System.out.println("4. Recharge SIM card");
            System.out.println("5. Log Out");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1: /* deposit */ break;
                case 2: /* withdraw */ break;
                case 3: /* transfer */ break;
                case 4: /* recharge */ break;
                case 5: return;
            }
        }
    }

    public void showAllUsers() {
        for (User u : users) {
            System.out.println(u.id + " - " + u.name + " (" + u.role + ")");
        }
    }
}
