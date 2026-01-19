package Person;
import Account.BankAccount;

import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends Person { //Clase hija que hereda de la clase Person.
    public String id = "";
    public ArrayList<BankAccount> bankAccounts = new ArrayList<>(); //Lista para almacenar todas las cuentas del banco

    public User( String name, String password, String birthDate, String id) { //Clase usuario con diferentes atributos.
        super(name, password, birthDate); //Llama al constructor padre.
        this.active=true;
        this.id = id;
    }

    @Override //Sobreescribe la clase register
    public User register(){
        Scanner sc = new Scanner(System.in);
        String name, birthdate, password; //Almacena su nombre, cumpleaños y contraseña.
        boolean checkP=false, checkD=false;
        System.out.println("Please enter your name and surnames");
        name = sc.nextLine();

        System.out.println("Please enter your password");
        password = sc.nextLine();
        checkPassword(password);
        while (!checkP){
            System.out.println("The password you entered is incorrect");
            System.out.println("The password must contain:");
            System.out.println("* 1 uppercase letter");
            System.out.println("* 1 lowercase letter");
            System.out.println("* 1 number");
            System.out.println("* 1 special character");
            password = sc.nextLine(); //Se guarda la contraseña con los parámetros preestablecidos.
            checkPassword(password);
        }

        System.out.println("Please enter your birthdate (dd/mm/yyyy)");
        birthdate = sc.nextLine();
        checkD = checkDate(birthdate);
        while(!checkD){
            System.out.println("The date you entered is incorrect, please try again");
            System.out.println("Remember to use the following format: dd/mm/yyyy");
            birthdate = sc.nextLine(); //Se guarda la fecha de nacimiento con el formato correcto.
            checkD = checkDate(birthdate);
        }
        id = id+1;
        User newUser = new User(name, password, birthdate, id);
        System.out.println("The register process has ended");
        System.out.println("Your data:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + id);
        return newUser;
    }

    @Override
    public boolean checkDate(String date){
        String regex = "[,\\.\\s]"; //Formato para la fecha
        String[] myArray = date.split(regex);
        int element1 = Integer.parseInt(myArray[0]);
        int element2 = Integer.parseInt(myArray[1]);
        int element3 = Integer.parseInt(myArray[2]);
        int year = Year.now().getValue();

        if (element1 > 32 || element1 <0){//check if the day is between 1 and 31
            return false;
        }
        if(  element2 == 4 || element2 == 6 || element2 == 9 ||  element2 == 11 ){//check if it is a 30-day month
            if (element1 >30){
                return false;
            }
        }
        if (element2 == 2  ) { //check if february
            if (element3 % 4 == 0) {
                if (element1 > 29) {//leap year
                    return false;
                }
            } else {
                if (element1 > 28) {//normal year
                    return false;
                }
            }
        }
        if (element3 < 1900 || element3 > year) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkPassword(String password){ //regex password
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}"; //La contraseña debe cuadrar con  el formato establecido.
        if(password.matches(pattern)){
            return true;
        }
        else {
            return false;
        }
    }

}
