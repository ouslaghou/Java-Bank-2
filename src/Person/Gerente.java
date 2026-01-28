package Person;

public class Gerente extends Person {

    public Gerente(String name, String password, String birthDate) {
        super(name, password, birthDate);
    }

    @Override
    public Person register() {
        // Un gerente no se registra desde consola
        return null;
    }

    @Override
    public boolean checkPassword(String password) {
        return true; // No se usa
    }

    @Override
    public boolean checkDate(String date) {
        return true; // No se usa
    }
}
