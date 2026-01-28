package Person;

import Account.BankAccount;
import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends Person implements Serializable {

    public String id = "";
    public ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    public static int lastId = 0;

    // ✔ Constructor vacío CORRECTO (no borra password)
    public User() {
        super();        // NO PISAR name, password ni birthDate
        this.role = "user";
    }

    // ✔ Constructor con parámetros
    public User(String name, String password, String birthDate) {
        super(name, password, birthDate);
        this.role = "user";
    }

    @Override
    public User register() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your name and surnames");
        this.name = sc.nextLine().trim();

        System.out.println("Please enter your password");
        this.password = sc.nextLine().trim();
        while (!checkPassword(this.password)) {
            System.out.println("Invalid password, try again:");
            this.password = sc.nextLine().trim();
        }

        System.out.println("Please enter your birthdate (dd/mm/yyyy)");
        this.birthDate = sc.nextLine().trim();
        while (!checkDate(this.birthDate)) {
            System.out.println("Invalid date, try again:");
            this.birthDate = sc.nextLine().trim();
        }

        this.id = String.format("%08d", ++lastId);

        System.out.println("Registration complete");
        System.out.println("ID: " + this.id);

        return this;
    }

    @Override
    public boolean checkPassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^*/&+=])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

    @Override
    public boolean checkDate(String date) {
        String[] parts = date.split("[/.,\\s]");
        if (parts.length != 3) return false;

        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            int currentYear = Year.now().getValue();
            if (year < 1900 || year > currentYear) return false;
            if (month < 1 || month > 12) return false;
            if (day < 1 || day > 31) return false;

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
