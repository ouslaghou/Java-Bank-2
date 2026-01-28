package Person;

import java.io.Serializable;

public abstract class Person implements Serializable {

    public String name = "";
    public String birthDate = "";
    public String password = "";
    public boolean active = true;
    public String role = "user";

    public Person() {
        // No tocar nada aquí
    }

    public Person(String name, String password, String birthDate) {
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
    }

    public abstract Person register();
    public abstract boolean checkPassword(String password);
    public abstract boolean checkDate(String date);
}
