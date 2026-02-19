package Access;

import Person.Person;
import Account.BankAccount;
import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static void savePersons(ArrayList<Person> persons) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("personas.dat"))) {
            oos.writeObject(persons);
        } catch (Exception e) { System.out.println("Error saving persons"); }
    }

    public static ArrayList<Person> loadPersons() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("personas.dat"))) {
            return (ArrayList<Person>) ois.readObject();
        } catch (Exception e) { return new ArrayList<>(); }
    }

    public static void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cuentas.dat"))) {
            oos.writeObject(accounts);
        } catch (Exception e) { System.out.println("Error saving accounts"); }
    }

    public static void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cuentas.dat"))) {
            accounts = (ArrayList<BankAccount>) ois.readObject();
        } catch (Exception e) { accounts = new ArrayList<>(); }
    }


    }

