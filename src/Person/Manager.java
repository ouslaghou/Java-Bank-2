package Person;

public class Manager extends User {

    public Manager() {
        super();
        this.role = "manager";
    }

    public Manager(String name, String password, String birthDate) {
        super(name, password, birthDate);
        this.role = "manager";
    }
}
