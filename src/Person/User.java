package Person;

import Account.BankAccount;
import Account.Inversiones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends Person implements Serializable {

    public ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    public static int lastId = 0;

    public User(String name, String password, String birthDate) {
        super(name, password, birthDate, "client");
    }
    public ArrayList<Inversiones> inversiones = new ArrayList<>();

    @Override
    public Person register() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter client name:");
        this.name = sc.nextLine();

        System.out.println("Enter password:");
        this.password = sc.nextLine();

        System.out.println("Enter birthdate:");
        this.birthDate = sc.nextLine();

        this.id = String.format("%07d", ++lastId);

        return this;
    }

    @Override
    public boolean checkDate(String date) { return true; }

    @Override
    public boolean checkPassword(String password) { return true; }
}
