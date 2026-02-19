package Account;


import java.io.Serializable;
import java.util.ArrayList;

// Clase base para cualquier cuenta bancaria
// Debe ser serializable porque se guarda en cuentas.dat
public abstract class BankAccount implements Serializable {

    public String ownerId;      // ID del dueño de la cuenta
    public String entity;       // Código de entidad
    public String office;       // Oficina
    public String accNumber;    // Número de cuenta
    public String dc;           // Dígito de control
    public String IBAN;         // IBAN generado
    public String accountAlias; // Nombre visible de la cuenta
    public double balance = 0;  // Saldo

    public ArrayList<String> history = new ArrayList<>(); // Movimientos
    public ArrayList<Card> cards = new ArrayList<>();     // Tarjetas asociadas

    // Campos para el sistema de crédito
    public boolean debtor = false;
    public int monthsInDebt = 0;
    public boolean creditBlocked = false;
    public boolean operationsBlocked = false;

    public BankAccount(String ownerId, String entity, String office,
                       String accNumber, String dc, String IBAN, String alias) {

        this.ownerId = ownerId;
        this.entity = entity;
        this.office = office;
        this.accNumber = accNumber;
        this.dc = dc;
        this.IBAN = IBAN;
        this.accountAlias = alias;
    }

    public BankAccount() {

    }

    // Añadir movimiento al historial
    public void addHistory(String type, double amount) {
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter fmt =
                java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

        history.add(type + " | " + amount + "€ | " + now.format(fmt));
    }

    // Mostrar historial
    public void showHistory() {
        System.out.println("\n=== HISTORIAL: " + accountAlias + " ===");
        if (history.isEmpty()) {
            System.out.println("No hay movimientos.");
            return;
        }
        for (String entry : history) System.out.println(entry);
    }

    // Métodos abstractos que implementan las subclases
    public abstract void deposit(int amount, BankAccount account);
    public abstract void withdraw(int amount, BankAccount account);
    public abstract void transfer(double amount, BankAccount account);
    public abstract void rechargeSIM(int amount, BankAccount account);
    public abstract void movimientos(int amount, BankAccount account, String balance);



}
