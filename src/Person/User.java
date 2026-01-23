package Person;
import Account.BankAccount;

import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends Person {
    public String id = "";
    public ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    public String tipoUsuario = "cliente";

    public User( String name, String password, String birthDate, String id) {
        super(name, password, birthDate);
        this.active=true;
        this.id = id;
    }

    @Override
    public User register(){
        Scanner sc = new Scanner(System.in);
        String name, birthdate, password;
        boolean checkP=false, checkD=false;
        System.out.println("Please enter your name and surnames");
        name = sc.nextLine();

        System.out.println("Please enter your password");
        password = sc.nextLine();
        checkP = checkPassword(password);
        while (!checkP){
            System.out.println("The password you entered is incorrect");
            System.out.println("The password must contain:");
            System.out.println("* 1 uppercase letter");
            System.out.println("* 1 lowercase letter");
            System.out.println("* 1 number");
            System.out.println("* 1 special character");
            password = sc.nextLine();
            checkP = checkPassword(password);
        }

        System.out.println("Please enter your birthdate (dd/mm/yyyy)");
        birthdate = sc.nextLine();
        checkD = checkDate(birthdate);
        while(!checkD){
            System.out.println("The date you entered is incorrect, please try again");
            System.out.println("Remember to use the following format: dd/mm/yyyy");
            birthdate = sc.nextLine();
            checkD = checkDate(birthdate);
        }

        int numId = 1;
        try {
            numId = Integer.parseInt(id == null ? "0" : id) + 1;
        } catch (Exception e) {}

        String newId = String.format("%08d", numId);

        User newUser = new User(name, password, birthdate, newId);

        System.out.println("The register process has ended");
        System.out.println("Your data:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + newId);
        return newUser;
    }

    @Override
    public boolean checkDate(String date){
        String regex = "[,\\.\\s/\\-]";
        String[] myArray = date.split(regex);
        if (myArray.length != 3) {
            return false;
        }
        int element1;
        int element2;
        int element3;
        try {
            element1 = Integer.parseInt(myArray[0]);
            element2 = Integer.parseInt(myArray[1]);
            element3 = Integer.parseInt(myArray[2]);
        } catch (NumberFormatException e) {
            return false;
        }
        int year = Year.now().getValue();

        if (element1 > 32 || element1 <0){
            return false;
        }
        if(  element2 == 4 || element2 == 6 || element2 == 9 ||  element2 == 11 ){
            if (element1 >30){
                return false;
            }
        }
        if (element2 == 2  ) {
            if (element3 % 4 == 0) {
                if (element1 > 29) {
                    return false;
                }
            } else {
                if (element1 > 28) {
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
    public boolean checkPassword(String password){
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if(password.matches(pattern)){
            return true;
        }
        else {
            return false;
        }
    }

}
