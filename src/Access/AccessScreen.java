package Access;

import Person.User;
import Person.Employee;
import Person.Manager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AccessScreen {

    ArrayList<User> users = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void menu() {

        loadUsersFromFile();
        System.out.println(new File("usuarios.dat").getAbsolutePath());
        fixLastId();

        int option = 0;

        while (option != 3) {
            System.out.println("Welcome to JavaBank ");
            System.out.println("1. Create Account");
            System.out.println("2. Log In");
            System.out.println("3. Close Application");

            option = Integer.parseInt(sc.nextLine());


            switch (option) {
                case 1:
                    createUser();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    saveUsersToFile();
                    return;
            }
        }
    }

    private void createUser() {
        System.out.println("Select user type:");
        System.out.println("1. Client");
        System.out.println("2. Employee");
        System.out.println("3. Manager");

        int type = Integer.parseInt(sc.nextLine());


        User newUser;

        if (type == 1) newUser = new User();
        else if (type == 2) newUser = new Employee();
        else newUser = new Manager();

        newUser = newUser.register();
        users.add(newUser);

        saveUsersToFile();
        fixLastId();
    }

    public void login() {
        System.out.println("Enter user ID:");
        String id = sc.nextLine();

        User currentUser = null;

        for (User u : users) {
            if (u.id.equals(id)) {
                currentUser = u;
                break;
            }
        }

        if (currentUser == null) {
            System.out.println("ID not found.");
            return;
        }

        if (!currentUser.active) {
            System.out.println("Account blocked.");
            return;
        }


        System.out.println("Enter password:");
        String pass = sc.nextLine().trim();
        System.out.println("DEBUG stored password = [" + currentUser.password + "]"); System.out.println("DEBUG typed password = [" + pass + "]"); System.out.println("DEBUG equals? " + pass.equals(currentUser.password));

        if (!pass.equals(currentUser.password)) {
            System.out.println("Wrong password.");
            return;
        }

        accountMenu(currentUser);
    }

    public void accountMenu(User currentUser) {

        System.out.println("Welcome " + currentUser.name);
        System.out.println("Role: " + currentUser.role);

        switch (currentUser.role) {
            case "manager":
                managerMenu(currentUser);
                break;

            case "employee":
                employeeMenu(currentUser);
                break;

            default:
                userMenu(currentUser);
        }
    }

    private void managerMenu(User u) {
        System.out.println("Manager menu (not implemented)");
    }

    private void employeeMenu(User u) {
        System.out.println("Employee menu (not implemented)");
    }

    private void userMenu(User u) {
        System.out.println("User menu (not implemented)");
    }

    public void saveUsersToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.dat"));
            oos.writeObject(users);
            oos.close();
        } catch (Exception e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public void loadUsersFromFile() {
        try {
            File f = new File("usuarios.dat");
            if (!f.exists()) return;

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            users = (ArrayList<User>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    private void fixLastId() {
        int max = 0;
        for (User u : users) {
            try {
                int idNum = Integer.parseInt(u.id);
                if (idNum > max) max = idNum;
            } catch (Exception ignored) {}
        }
        User.lastId = max;
    }
}
