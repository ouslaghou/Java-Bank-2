package Person;

public class Manager extends User {
    public Manager(String name, String password, String birthDate, String id) {
        super(name, password, birthDate);
        this.role = "manager";

    }
}
