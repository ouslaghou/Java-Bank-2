package Person;

public abstract class Person {
    public int id=0;
    public String name="", birthDate ="", password="";
    public boolean active=true;
    public String role = "user";



    public Person( String name, String password, String birthDate) {
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.active = true;
    }

    abstract Person register();

    abstract boolean checkPassword(String password);

    abstract boolean checkDate(String date);
}
