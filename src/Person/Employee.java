package Person;

import java.util.Scanner;

public class Employee extends Person {

    public static int lastEmployeeId = 0;

    public Employee(String name, String password, String birthDate) {
        super(name, password, birthDate, "employee");
    }

    @Override
    public Person register() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter employee name:");
        this.name = sc.nextLine();

        System.out.println("Enter password:");
        this.password = sc.nextLine();

        System.out.println("Enter birthdate:");
        this.birthDate = sc.nextLine();

        this.id = "E" + String.format("%06d", ++lastEmployeeId);

        return this;
    }

    @Override
    public boolean checkDate(String date) { return true; }

    @Override
    public boolean checkPassword(String password) { return true; }
}
