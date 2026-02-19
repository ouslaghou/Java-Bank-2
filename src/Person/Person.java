package Person;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    // Fecha válida dd/MM/yyyy y no futura
    public boolean checkDate(String date) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate d = LocalDate.parse(date, fmt);
            return !d.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Contraseña  8+ caracteres, mayúscula, minúscula, número
    public boolean checkPassword(String password) {
        if (password == null || password.length() < 8) return false;

        boolean upper = false, lower = false, digit = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) upper = true;
            else if (Character.isLowerCase(c)) lower = true;
            else if (Character.isDigit(c)) digit = true;
        }

        return upper && lower && digit;
    }

    public abstract Person register();
}
