package Access;

import Person.*;
import Account.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AccessScreen {

    ArrayList<Person> persons;
    Scanner sc = new Scanner(System.in);

    public AccessScreen() {
        persons = FileManager.loadPersons();
        FileManager.loadAccounts();
        linkAccountsToUsers();
        rebuildLastIds();
    }

    // ============================
    // RECONSTRUIR IDs DESDE FICHERO
    // ============================
    private void rebuildLastIds() {
        int maxClient = 0;
        int maxEmployee = 0;
        int maxManager = 0;

        for (Person p : persons) {

            if (p.role.equals("client")) {
                int num = Integer.parseInt(p.id);
                if (num > maxClient) maxClient = num;
            }

            if (p.role.equals("employee")) {
                int num = Integer.parseInt(p.id.substring(1));
                if (num > maxEmployee) maxEmployee = num;
            }

            if (p.role.equals("manager")) {
                int num = Integer.parseInt(p.id.substring(1));
                if (num > maxManager) maxManager = num;
            }
        }

        User.lastId = maxClient;
        Employee.lastEmployeeId = maxEmployee;
        Manager.lastManagerId = maxManager;
    }

    // ============================
    // VINCULAR CUENTAS A USUARIOS
    // ============================
    private void linkAccountsToUsers() {
        for (BankAccount acc : FileManager.accounts) {
            for (Person p : persons) {
                if (p instanceof User && p.id.equals(acc.ownerId)) {
                    ((User) p).bankAccounts.add(acc);
                }
            }
        }
    }

    // ============================
    // MENÚ PRINCIPAL
    // ============================
    public void menu() {

        int option = 0;

        while (option != 3) {
            System.out.println("\n=== JAVA BANK ===");
            System.out.println("1. Register User");
            System.out.println("2. Log In");
            System.out.println("3. Exit");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> registerUser();
                case 2 -> login();
                case 3 -> {
                    FileManager.savePersons(persons);
                    FileManager.saveAccounts();
                    System.out.println("Data saved. Goodbye.");
                }
            }
        }
    }

    // ============================
    // REGISTRO DE USUARIOS
    // ============================
    private void registerUser() {
        System.out.println("\nSelect user type:");
        System.out.println("1. Client");
        System.out.println("2. Employee");
        System.out.println("3. Manager");

        int type = sc.nextInt();
        sc.nextLine();

        Person p;

        if (type == 1) p = new User("", "", "");
        else if (type == 2) p = new Employee("", "", "");
        else p = new Manager("", "", "");

        p = p.register();
        persons.add(p);

        FileManager.savePersons(persons);
        System.out.println("User registered successfully. ID: " + p.id);
    }

    // ============================
    // LOGIN
    // ============================
    private void login() {
        System.out.println("\nEnter ID:");
        String id = sc.nextLine();

        Person p = null;

        for (Person x : persons) {
            if (x.id.equals(id)) {
                p = x;
                break;
            }
        }

        if (p == null) {
            System.out.println("ID not found.");
            return;
        }

        System.out.println("Enter password:");
        String pass = sc.nextLine();

        if (!pass.equals(p.password)) {
            System.out.println("Wrong password.");
            return;
        }

        System.out.println("Login successful. Welcome " + p.name + " (" + p.id + ")");

        switch (p.role) {
            case "manager" -> managerMenu((Manager) p);
            case "employee" -> employeeMenu((Employee) p);
            case "client" -> clientMenu((User) p);
        }
    }

    // ============================
    // MENÚ GERENTE
    // ============================
    private void managerMenu(Manager m) {
        int option = 0;

        while (option != 7) {
            System.out.println("\n=== MANAGER MENU ===");
            System.out.println("1. Create Client");
            System.out.println("2. Create Employee");
            System.out.println("3. Create Manager");
            System.out.println("4. Create Bank Account");
            System.out.println("5. View All Users");
            System.out.println("6. Block User");
            System.out.println("7. Logout");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> createClient();
                case 2 -> createEmployee();
                case 3 -> createManager();
                case 4 -> createBankAccount();
                case 5 -> showAllUsers();
                case 6 -> blockUser();
                case 7 -> {
                    FileManager.savePersons(persons);
                    FileManager.saveAccounts();
                    return;
                }
            }
        }
    }

    // ============================
    // MENÚ EMPLEADO
    // ============================
    private void employeeMenu(Employee e) {
        int option = 0;

        while (option != 5) {
            System.out.println("\n=== EMPLOYEE MENU ===");
            System.out.println("1. Create Client");
            System.out.println("2. Create Bank Account");
            System.out.println("3. View Clients");
            System.out.println("4. Block Client");
            System.out.println("5. Logout");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> createClient();
                case 2 -> createBankAccount();
                case 3 -> showClients();
                case 4 -> blockClient();
                case 5 -> {
                    FileManager.savePersons(persons);
                    FileManager.saveAccounts();
                    return;
                }
            }
        }
    }

    // ============================
    // MENÚ CLIENTE
    // ============================
    private void clientMenu(User u) {
        int option = 0;

        while (option != 6) {
            System.out.println("\n=== CLIENT MENU ===");
            System.out.println("1. View My Accounts");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Account History");
            System.out.println("6. Solictar tarjeta");
            System.out.println("7. Logout");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> showUserAccounts(u);
                case 2 -> deposit(u);
                case 3 -> withdraw(u);
                case 4 -> transfer(u);
                case 5 -> {
                    BankAccount acc = selectAccount(u);
                    acc.showHistory();
                }
                case 6 -> {
                    solicitarTarjeta(u);

                }
                case 7 -> {
                    FileManager.savePersons(persons);
                    FileManager.saveAccounts();
                    return;
                }
            }
        }
    }

    // ============================
    // CREAR USUARIOS
    // ============================
    private void createClient() {
        Person p = new User("", "", "").register();
        persons.add(p);
        FileManager.savePersons(persons);
        System.out.println("Client created. ID: " + p.id);
    }

    private void createEmployee() {
        Person p = new Employee("", "", "").register();
        persons.add(p);
        FileManager.savePersons(persons);
        System.out.println("Employee created. ID: " + p.id);
    }

    private void createManager() {
        Person p = new Manager("", "", "").register();
        persons.add(p);
        FileManager.savePersons(persons);
        System.out.println("Manager created. ID: " + p.id);
    }

    // ============================
    // CREAR CUENTAS
    // ============================
    private void createBankAccount() {
        System.out.println("Enter owner ID:");
        String id = sc.nextLine();

        Person p = null;

        for (Person x : persons) {
            if (x.id.equals(id) && x instanceof User) {
                p = x;
                break;
            }
        }

        if (p == null) {
            System.out.println("Client not found.");
            return;
        }

        System.out.println("Enter account number:");
        String acc = sc.nextLine();

        BankAccount newAcc = new DebitAccount(
                p.id, "9999", "8888", acc, "00", "ES00" + acc, "Account " + acc
        );

        ((User) p).bankAccounts.add(newAcc);
        FileManager.accounts.add(newAcc);

        FileManager.saveAccounts();
        FileManager.savePersons(persons);

        System.out.println("Account created for user " + p.name + " (" + p.id + ")");
    }

    // ============================
    // MOSTRAR USUARIOS
    // ============================
    private void showAllUsers() {
        System.out.println("\n=== LIST OF ALL USERS ===");

        for (Person p : persons) {
            System.out.println(
                    "ID: " + p.id +
                            " | Name: " + p.name +
                            " | Role: " + p.role +
                            " | Active: " + (p.active ? "YES" : "NO")
            );
        }
    }

    private void showClients() {
        System.out.println("\n=== CLIENT LIST ===");

        for (Person p : persons) {
            if (p.role.equals("client")) {
                System.out.println("ID: " + p.id + " | Name: " + p.name);
            }
        }
    }

    private void showUserAccounts(User u) {
        System.out.println("\n=== ACCOUNTS OF " + u.name + " (" + u.id + ") ===");

        for (BankAccount acc : u.bankAccounts) {
            System.out.println(
                    acc.accountAlias + " | " +
                            acc.accNumber + " | Balance: " + acc.balance
            );
        }
    }

    // ============================
    // BLOQUEAR USUARIOS
    // ============================
    private void blockUser() {
        System.out.println("Enter ID to block:");
        String id = sc.nextLine();

        for (Person p : persons) {
            if (p.id.equals(id)) {
                p.active = false;
                System.out.println("User blocked: " + p.id);
                return;
            }
        }
        System.out.println("User not found.");
    }

    private void blockClient() {
        System.out.println("Enter client ID to block:");
        String id = sc.nextLine();

        for (Person p : persons) {
            if (p.id.equals(id) && p.role.equals("client")) {
                p.active = false;
                System.out.println("Client blocked: " + p.id);
                return;
            }
        }
        System.out.println("Client not found.");
    }

    // ============================
    // OPERACIONES CLIENTE
    // ============================
    private BankAccount selectAccount(User u) {
        System.out.println("\nSelect account for user " + u.name + " (" + u.id + "):");

        for (int i = 0; i < u.bankAccounts.size(); i++) {
            System.out.println((i + 1) + ". " + u.bankAccounts.get(i).accountAlias);
        }

        int opt = sc.nextInt();
        sc.nextLine();

        if (opt < 1 || opt > u.bankAccounts.size()) {
            System.out.println("Invalid option. Please select a valid account.");
            return selectAccount(u);
        }

        return u.bankAccounts.get(opt - 1);
    }

    private void deposit(User u) {
        BankAccount acc = selectAccount(u);
        System.out.println("Amount:");
        int amount = sc.nextInt();
        sc.nextLine();
        acc.deposit(amount, acc);
    }

    private void withdraw(User u) {
        BankAccount acc = selectAccount(u);
        System.out.println("Amount:");
        int amount = sc.nextInt();
        sc.nextLine();
        acc.withdraw(amount, acc);
    }

    private void transfer(User u) {
        BankAccount acc = selectAccount(u);
        System.out.println("Amount:");
        double amount = sc.nextDouble();
        sc.nextLine();
        acc.transfer(amount, acc);
    }

    public void solicitarTarjeta(User u) {
        Card c= null;
        if (u.bankAccounts.isEmpty()) {
            System.out.println("No tienes cuentas, no puedes solicitar tarjeta.");
            return;
        }
        System.out.println("De credito o de debito? 1/2 ");
        int opc = sc.nextInt();
        if (opc == 1 ){
            c = new CreditCard();
            System.out.println("Tarjeta de credito creada");
        } else if (opc == 2) {
            c = new DebitCard();
            System.out.println("Tarjeta de debito creada");
        }else {
            System.out.println("Elija una opcion correcta");
        }
        BankAccount acc = selectAccount(u);
        acc.cards.add(c);

        FileManager.saveAccounts();
        FileManager.savePersons(persons);

        System.out.println("Tarjeta creada correctamente.");
    }

}