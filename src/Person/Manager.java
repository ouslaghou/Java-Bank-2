package Person;

import java.util.Scanner;

public class Manager extends Person {

    public static int lastManagerId = 0;

    public Manager(String name, String password, String birthDate) {
        super(name, password, birthDate, "manager");
    }

    @Override
    public Person register() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter manager name:");
        this.name = sc.nextLine();

        System.out.println("Enter password:");
        this.password = sc.nextLine();

        System.out.println("Enter birthdate:");
        this.birthDate = sc.nextLine();

        this.id = "M" + String.format("%06d", ++lastManagerId);

        return this;
    }

    @Override
    public boolean checkDate(String date) { return true; }

    @Override
    public boolean checkPassword(String password) { return true; }
}
