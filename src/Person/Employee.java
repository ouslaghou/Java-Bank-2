package Person;

import Account.BankAccount;

import java.time.Year;
import java.util.Scanner;

public class Employee extends Person {
    final int employeeId;
    public static int id =0;

    public Employee(String id, String name, String password, String birthDate, int employeeId) {
        super( name, password, birthDate); //Hace referencia a la clase padre, en este caso Person
        this.employeeId = employeeId;
    }

    @Override
    public User register(){ //Clase para registrar el usuario.
        Scanner sc = new Scanner(System.in);
        String name, birthdate, password;
        boolean checkP=false, checkD=false;
        System.out.println("Please enter your name and surnames");
        name = sc.nextLine(); //Guarda el nombre.

        System.out.println("Please enter your password");
        password = sc.nextLine(); //Guarda la contraseña.
        checkPassword(password);
        while (!checkP){ //Tras fallar la contraseña este bucle le muestra lo que debe contener.
            System.out.println("The password you entered is incorrect");
            System.out.println("The password must contain:");
            System.out.println("* 1 uppercase letter");
            System.out.println("* 1 lowercase letter");
            System.out.println("* 1 number");
            System.out.println("* 1 special character");
            password = sc.nextLine(); //Guarda la contraseña.
            checkPassword(password);
        }

        System.out.println("Please enter your birthdate (dd/mm/yyyy)");
        birthdate = sc.nextLine(); //Guarda la fecha de nacimiento.
        checkD = checkDate(birthdate);
        while(!checkD){
            System.out.println("The date you entered is incorrect, please try again");
            System.out.println("Remember to use the following format: dd/mm/yyyy");
            birthdate = sc.nextLine(); //Vuelve a guardar la fecha de nacimiento con el correcto formato.
            checkD = checkDate(birthdate);
        }
         id += 1; //En este caso se crea un id por usuario. Pero podrían haber más si se crean más usuario/id.
        String newId = createId(id);
        User newUser = new User(name, password, birthdate, newId);  //Nuevo usuario con diferentes atributos.
        System.out.println("The register process has ended");
        System.out.println("Your data:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + newId);
        return newUser;
    }

    @Override //Sobreescribe la clase checkPassword
    public boolean checkPassword(String password){ //regex password
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}"; //La contraseña debe cuadrar con el formato establecido.
        if(password.matches(pattern)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override //Sobreescribe la clase checkDate
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

    public String createId(int id){
        String newId ="";
        for (int i= String.valueOf(id).length(); i < 8; i++){
            newId = "0" + newId;
        }
        return newId;
    }

    public BankAccount createBankAccount(){
        return null;
    }
}
