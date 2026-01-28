package Person;

import java.io.Serializable;

public abstract class Person implements Serializable {

    public String id;
    public String name;
    public String password;
    public String birthDate;
    public boolean active = true;
    public String role;

    public Person(String name, String password, String birthDate, String role) {
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.role = role;
    }

    public abstract Person register();
    public abstract boolean checkDate(String date);
    public abstract boolean checkPassword(String password);
}
