package Access;

import Person.User;
import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String FILE_NAME = "users.txt";

    //guardar ficheros
    public static void saveUsers(ArrayList<User> users) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {

            for (User u : users) {
                pw.println(
                        u.id + ";" +
                                u.name + ";" +
                                u.password + ";" +
                                u.birthDate + ";" +
                                u.active
                );
            }

        } catch (Exception e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
//

    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] data = line.split(";");

                User u = new User(
                        data[1],   // name
                        data[2],   // password
                        data[3],   // birthdate
                        data[0]    // id
                );

                u.active = Boolean.parseBoolean(data[4]);

                users.add(u);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No user file found, starting fresh.");
        } catch (Exception e) {
            System.out.println("Error loading users: " + e.getMessage());
        }

        return users;
    }
}
