package Person;

public class Employee extends User {
    public String tipoUsuario = "empleado";

    public Employee(String name, String password, String birthDate, int id) {
        super(name, password, birthDate, String.valueOf(id));
        this.tipoUsuario = "empleado";
    }
}