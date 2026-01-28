package Person;

import java.time.Year;
import java.util.Scanner;

public class Employee extends User {

    final int employeeId;
    public static int id = 0;

    public Employee(String name, String password, String birthDate, int employeeId) {
        super(name, password, birthDate);
        this.employeeId = employeeId;
        this.role = "employee";
    }

    @Override
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
            System.out.println("The password must contain:");
            System.out.println("* 1 uppercase letter");
            System.out.println("* 1 lowercase letter");
            System.out.println("* 1 number");
            System.out.println("* 1 special character");
            password = sc.nextLine();
            checkP = checkPassword(password);
        }

        System.out.println("Please enter your birthdate (dd/mm/yyyy)");
        birthdate = sc.nextLine();
        checkD = checkDate(birthdate);

        while (!checkD) {
            System.out.println("The date you entered is incorrect, please try again");
            System.out.println("Remember to use the following format: dd/mm/yyyy");
            birthdate = sc.nextLine();
            checkD = checkDate(birthdate);
        }

        id++;
        String newId = createId(id);

        User newUser = new User(name, password, birthdate);

        System.out.println("The register process has ended");
        System.out.println("Your data:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + newId);

        return newUser;
    }

    @Override
    public boolean checkPassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

    @Override
    public boolean checkDate(String date) {
        String regex = "[/,.\\s]";
        String[] myArray = date.split(regex);

        if (myArray.length != 3) {
            return false;
        }

        int day, month, yearInput;

        try {
            day = Integer.parseInt(myArray[0]);
            month = Integer.parseInt(myArray[1]);
            yearInput = Integer.parseInt(myArray[2]);
        } catch (Exception e) {
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

    public String createId(int id) {
        return String.format("%08d", id);
    }
}
