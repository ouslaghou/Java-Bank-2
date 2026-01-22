package Person;

public class Manager extends User {
    public String tipoUsuario = "gerente";

    public Manager(String name, String password, String birthDate, String id) {
        super(name, password, birthDate, id);
        this.tipoUsuario = "gerente";
    }
}