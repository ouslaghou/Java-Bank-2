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

    public int lastDeposit;
    public int lastWithdrawal;

    public ArrayList<String> history = new ArrayList<>();

    public BankAccount(String ownerId, String entity, String office, String accNumber, String dc, String IBAN, String alias) {
        this.ownerId = ownerId;
        this.entity = entity;
        this.office = office;
        this.accNumber = accNumber;
        this.dc = dc;
        this.IBAN = IBAN;
        this.accountAlias = alias;
    }

    public void addHistory(String type, double amount) {
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter format =
                java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

        String entry = type + " | " + amount + "€ | " + now.format(format);
        history.add(entry);
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

    public abstract void deposit(int amount, BankAccount account);
    public abstract void withdraw(int amount, BankAccount account);
    public abstract void transfer(double amount, BankAccount account);
    public abstract void rechargeSIM(int amount, BankAccount account);
    public abstract void movimientos(int amount, BankAccount account, String balance);
}
