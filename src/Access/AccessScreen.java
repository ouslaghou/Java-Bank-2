package Access;

import Person.*;
import Account.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class AccessScreen implements Serializable {

    ArrayList<Person> persons;
    Scanner sc = new Scanner(System.in);

    public AccessScreen() {
        persons = FileManager.loadPersons();
        FileManager.loadAccounts();
        linkAccountsToUsers();
        rebuildLastIds();
    }

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

    private void linkAccountsToUsers() {
        for (BankAccount acc : FileManager.accounts) {
            for (Person p : persons) {
                if (p instanceof User && p.id.equals(acc.ownerId)) {
                    ((User) p).bankAccounts.add(acc);
                }
            }
        }
    }

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

    private void managerMenu(Manager m) {
        int option = 0;

        while (option != 8) {
            System.out.println("\n=== MANAGER MENU ===");
            System.out.println("1. Create Client");
            System.out.println("2. Create Employee");
            System.out.println("3. Create Manager");
            System.out.println("4. Create Bank Account");
            System.out.println("5. View All Users");
            System.out.println("6. Block User");
            System.out.println("7. Close Month (Credit System)");
            System.out.println("8. Logout");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> createClient();
                case 2 -> createEmployee();
                case 3 -> createManager();
                case 4 -> createBankAccount();
                case 5 -> showAllUsers();
                case 6 -> blockUser();
                case 8 -> {
                    FileManager.savePersons(persons);
                    FileManager.saveAccounts();
                    return;
                }
            }
        }
    }

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

    //menu seguros

    private void clientMenu(User u) {
        int option = 0;

        while (option != 12) {
            System.out.println("\n=== CLIENT MENU ===");
            System.out.println("1. View My Accounts");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Account History");
            System.out.println("6. Solicitar tarjeta");
            System.out.println("7. Solicitar cuenta de crédito");
            System.out.println("8. Logout");
            System.out.println("9. Contratar seguro");
            System.out.println("10. Tienda online");
            System.out.println("11. Inversiones");

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

                case 6 -> solicitarTarjeta(u);

                case 7 -> solicitarCuentaCredito(u);

                case 8 -> {
                    FileManager.savePersons(persons);
                    FileManager.saveAccounts();
                    return;
                }

                //  CONTRATAR SEGURO
                case 9 -> {
                    BankAccount acc = selectAccount(u);
                    InsuranceService seguroService = new InsuranceService();
                    seguroService.contratarSeguro(acc);
                }

                case 10 -> tiendaonline(u);


                default -> System.out.println("Opción no válida.");
            }
        }
    }

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

        String dc = "00";
        String iban = "ES00" + acc;

        BankAccount newAcc = new DebitAccount(
                p.id,
                "9999",
                "8888",
                acc,
                dc,
                iban,
                "Account " + acc
        );

        ((User) p).bankAccounts.add(newAcc);
        FileManager.accounts.add(newAcc);

        FileManager.saveAccounts();
        FileManager.savePersons(persons);

        System.out.println("\nAccount created for user " + p.name + " (" + p.id + ")");
        System.out.println("IBAN: " + iban);
    }

    private void solicitarCuentaCredito(User u) {
        System.out.println("\n=== SOLICITAR CUENTA DE CRÉDITO ===");
        System.out.println("1. 500€");
        System.out.println("2. 1000€");
        System.out.println("3. 5000€");

        int opt = sc.nextInt();
        sc.nextLine();

        double limit = switch (opt) {
            case 1 -> 500;
            case 2 -> 1000;
            case 3 -> 5000;
            default -> {
                System.out.println("Opción no válida.");
                yield -1;
            }
        };

        if (limit == -1) return;

        System.out.println("Número de cuenta:");
        String acc = sc.nextLine();

        BankAccount newAcc = new CreditAccount(
                u.id, "9999", "8888", acc, "00", "ES00" + acc,
                "Cuenta Crédito " + acc, limit
        );

        u.bankAccounts.add(newAcc);
        FileManager.accounts.add(newAcc);

        FileManager.saveAccounts();
        FileManager.savePersons(persons);

        System.out.println("Cuenta de crédito creada con límite de " + limit + "€");
    }

    private void showAllUsers() {
        System.out.println("=== LIST OF ALL USERS ===");

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

    private void tiendaonline(User u) {

        if (u.bankAccounts.isEmpty()) {
            System.out.println("No tienes cuentas para usar en la tienda.");
            return;
        }

        BankAccount cuentaActual = u.bankAccounts.get(0);

        tiendaonline.tienda(cuentaActual, persons);
    }

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
        Card c = null;

        if (u.bankAccounts.isEmpty()) {
            System.out.println("No tienes cuentas, no puedes solicitar tarjeta.");
            return;
        }

        System.out.println("¿De crédito o de débito? 1/2 ");
        int opc = sc.nextInt();
        sc.nextLine();

        if (opc == 1) {
            c = new CreditCard();
            System.out.println("Tarjeta de crédito creada");
        } else if (opc == 2) {
            c = new DebitCard();
            System.out.println("Tarjeta de débito creada");
        } else {
            System.out.println("Elija una opción correcta");
            return;
        }

        BankAccount acc = selectAccount(u);
        acc.cards.add(c);

        FileManager.saveAccounts();
        FileManager.savePersons(persons);

        System.out.println("Tarjeta creada correctamente.");
    }

    private void closeMonth() {
        System.out.println("\n=== CIERRE DE MES ===");

        for (Person p : persons) {
            if (!(p instanceof User u)) continue;

            for (BankAccount acc : u.bankAccounts) {
                if (!(acc instanceof CreditAccount ca)) continue;

                double deuda = ca.creditUsed;

                if (deuda <= 0) {
                    ca.debtor = false;
                    ca.monthsInDebt = 0;
                    ca.creditBlocked = false;
                    ca.operationsBlocked = false;
                    continue;
                }

                double restante = deuda;

                for (BankAccount other : u.bankAccounts) {
                    if (restante <= 0) break;

                    if (other.balance > 0) {
                        double pago = Math.min(other.balance, restante);
                        other.balance -= pago;
                        restante -= pago;
                    }
                }

                if (restante <= 0) {
                    ca.creditUsed = ((CreditAccount) acc).creditUsed;
                    ca.debtor = false;
                    ca.monthsInDebt = 0;
                    ca.creditBlocked = false;
                    ca.operationsBlocked = false;
                    System.out.println("Deuda saldada para " + u.name);
                } else {
                    ca.creditUsed = restante;
                    ca.debtor = true;
                    ca.monthsInDebt++;

                    if (ca.monthsInDebt == 1) {
                        ca.creditBlocked = true;
                        System.out.println(u.name + " entra en deuda (mes 1). Crédito bloqueado.");
                    } else if (ca.monthsInDebt == 2) {
                        ca.operationsBlocked = true;
                        System.out.println(u.name + " sigue en deuda (mes 2). Operaciones bloqueadas.");
                    } else if (ca.monthsInDebt >= 3) {
                        System.out.println(" Solicitud judicial de embargo para " + u.name);
                    }
                }
            }
        }

        FileManager.saveAccounts();
        FileManager.savePersons(persons);

        System.out.println("Cierre de mes completado.");
    }
}
