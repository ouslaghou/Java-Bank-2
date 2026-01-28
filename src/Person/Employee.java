package Person;

public class Employee extends User {

    public Employee() {
        super();
        this.role = "employee";
    }

    public Employee(String name, String password, String birthDate) {
        super(name, password, birthDate);
        this.role = "employee";
    }
}
