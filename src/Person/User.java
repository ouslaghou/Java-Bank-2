package Person;

import Account.BankAccount;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class User {

    public String name = "";
    public String birthDate = "";
    public String password = "";
    public String id = "";
    public boolean active = true;
    public String tipoUsuario = "cliente";
    public ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    public User(String name, String password, String birthDate, String id) {
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.id = id;
    }

    public User register() {
        Scanner sc = new Scanner(System.in);
        String name, birthdate, password;
        boolean checkP = false, checkD = false;

        System.out.println("Please enter your name and surnames");
        name = sc.nextLine();

        System.out.println("Please enter your password");
        password = sc.nextLine();
        checkP = checkPassword(password);

        while (!checkP) {
            System.out.println("The password you entered is incorrect");
            password = sc.nextLine();
            checkP = checkPassword(password);
        }

        System.out.println("Please enter your birthdate (dd/mm/yyyy)");
        birthdate = sc.nextLine();
        checkD = checkDate(birthdate);

        while (!checkD) {
            System.out.println("The date you entered is incorrect");
            birthdate = sc.nextLine();
            checkD = checkDate(birthdate);
        }

        String newId = String.valueOf(System.currentTimeMillis());
        User newUser = new User(name, password, birthdate, newId);

        System.out.println("Your account has been created");
        System.out.println("Your ID is: " + newId);

        return newUser;
    }

    public boolean checkPassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

    public boolean checkDate(String date) {
        String[] parts = date.split("/");

        if (parts.length != 3) return false;

        int d = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        int currentYear = Year.now().getValue();

        if (d < 1 || d > 31) return false;
        if (m < 1 || m > 12) return false;

        if ((m == 4 || m == 6 || m == 9 || m == 11) && d > 30) return false;

        if (m == 2) {
            boolean leap = (y % 4 == 0);
            if (leap && d > 29) return false;
            if (!leap && d > 28) return false;
        }

        return y >= 1900 && y <= currentYear;
    }
}