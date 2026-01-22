package Person;

public abstract class Person {

    public String name="", birthDate ="", password="";
    public boolean active=true;



    public Person( String name, String password, String birthDate) { //Atributos de la clase persona.
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
    }

    abstract Person register();

    abstract boolean checkPassword(String password);

    abstract boolean checkDate(String date);
}
