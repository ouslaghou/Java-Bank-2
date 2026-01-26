package Person;

import Account.BankAccount;
import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends Person implements Serializable {

    public String id = "";
    public ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    public static int lastId = 0; // Control global de IDs

    public User(String name, String password, String birthDate, String id) {
        super(name, password, birthDate);
        this.active = true;
        this.id = id;
    }

    @Override
    public User register() {
        Scanner sc = new Scanner(System.in);
        String name, birthdate, password;
        boolean checkP = false, checkD = false;

        System.out.println("Please enter your name and surnames");
        name = sc.nextLine();

        // PASSWORD VALIDATION
        System.out.println("Please enter your password");
        password = sc.nextLine();
        checkP = checkPassword(password);

        while (!checkP) {
            System.out.println("The password you entered is incorrect");
            System.out.println("The password must contain:");
            System.out.println("* 1 uppercase letter");
            System.out.println("* 1 lowercase letter");
            System.out.println("* 1 number");
            System.out.println("* 1 special character");
            password = sc.nextLine();
            checkP = checkPassword(password);
        }

        // DATE VALIDATION
        System.out.println("Please enter your birthdate (dd/mm/yyyy)");
        birthdate = sc.nextLine();
        checkD = checkDate(birthdate);

        while (!checkD) {
            System.out.println("The date you entered is incorrect, please try again");
            System.out.println("Remember to use the following format: dd/mm/yyyy");
            birthdate = sc.nextLine();
            checkD = checkDate(birthdate);
        }

        // ID AUTOINCREMENTAL SEGURO
        lastId++;
        String newId = String.format("%08d", lastId);

        User newUser = new User(name, password, birthdate, newId);

        System.out.println("The register process has ended");
        System.out.println("Your data:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + newId);

        return newUser;
    }

    @Override
    public boolean checkDate(String date) {

        String regex = "[/.,\\s]";
        String[] myArray = date.split(regex);

        if (myArray.length != 3) {
            return false;
        }

        int day, month, yearInput;

        try {
            day = Integer.parseInt(myArray[0]);
            month = Integer.parseInt(myArray[1]);
            yearInput = Integer.parseInt(myArray[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        int currentYear = Year.now().getValue();

        if (day < 1 || day > 31) return false;
        if (month < 1 || month > 12) return false;

        if (month == 4 || month == 6 || month == 9 || month == 11)
            if (day > 30) return false;

        if (month == 2) {
            boolean leap = (yearInput % 4 == 0);
            if (leap && day > 29) return false;
            if (!leap && day > 28) return false;
        }

        if (yearInput < 1900 || yearInput > currentYear) return false;

        return true;
    }

    @Override
    public boolean checkPassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^*/&+=])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }
}
