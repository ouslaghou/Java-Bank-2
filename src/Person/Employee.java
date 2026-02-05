package Person;

import java.io.Serializable;
import java.util.Scanner;

public class Employee extends Person implements Serializable {

    public static int lastEmployeeId = 0;

    public Employee(String name, String password, String birthDate) {
        super(name, password, birthDate, "employee");
    }

    @Override
    public Person register() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter employee name:");
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

        this.id = "E" + String.format("%06d", ++lastEmployeeId);

        return this;
    }
}
