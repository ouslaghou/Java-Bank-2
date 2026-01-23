package Person;

public class Employee extends User {

    final int employeeId;

    public Employee(String name, String password, String birthDate, int employeeId) {
        super(name, password, birthDate, String.format("%08d", employeeId));
        this.employeeId = employeeId;
        this.tipoUsuario = "empleado";
    }
}

