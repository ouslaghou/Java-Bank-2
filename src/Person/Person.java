package Person;

import java.io.Serializable;

public abstract class Person implements Serializable {

    public String name="", birthDate ="", password="";
    public boolean active=true;

    public Person( String name, String password, String birthDate) {
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
    }

    abstract Person register();

    abstract boolean checkPassword(String password);

    abstract boolean checkDate(String date);
}
