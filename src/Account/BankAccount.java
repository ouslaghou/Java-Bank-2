package Account;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class BankAccount implements Serializable {

    public String ownerId;
    public String entity;
    public String office;
    public String accNumber;
    public String dc;
    public String IBAN;
    public String accountAlias;
    public double balance = 0;

    public ArrayList<String> history = new ArrayList<>();
    public ArrayList<Card> cards = new ArrayList<>();

    // Campos de crédito / deuda (usados en CreditAccount)
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

    // ============================
    // HISTORIAL
    // ============================
    public void addHistory(String type, double amount) {
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter fmt =
                java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

        history.add(type + " | " + amount + "€ | " + now.format(fmt));
    }

    public void showHistory() {
        System.out.println("\n=== ACCOUNT HISTORY: " + accountAlias + " ===");
        if (history.isEmpty()) {
            System.out.println("No movements recorded.");
            return;
        }
        for (String entry : history) {
            System.out.println(entry);
        }
    }

    // ============================
    // MÉTODOS ABSTRACTOS
    // ============================
    public abstract void deposit(int amount, BankAccount account);
    public abstract void withdraw(int amount, BankAccount account);
    public abstract void transfer(double amount, BankAccount account);
    public abstract void rechargeSIM(int amount, BankAccount account);
    public abstract void movimientos(int amount, BankAccount account, String balance);
}
