package Person;

import Account.BankAccount;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends Person implements Serializable {

    public ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    public static int lastId = 0;

    public User(String name, String password, String birthDate) {
        super(name, password, birthDate, "client");
    }

    @Override
    public Person register() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter client name:");
        this.name = sc.nextLine();

        // PASSWORD VALIDATION
        do {
            System.out.println("Enter password (min 8 chars, upper, lower, digit, special):");
            this.password = sc.nextLine();
            if (!checkPassword(this.password))
                System.out.println("Invalid password format.");
        } while (!checkPassword(this.password));

        // DATE VALIDATION
        do {
            System.out.println("Enter birthdate (dd/MM/yyyy):");
            this.birthDate = sc.nextLine();
            if (!checkDate(this.birthDate))
                System.out.println("Invalid date.");
        } while (!checkDate(this.birthDate));

        this.id = String.format("%07d", ++lastId);

        return this;
    }
}
