package Account;

import java.io.Serializable;

public abstract class BankAccount implements Serializable {

    public String ownerId;   // ⭐ VINCULA LA CUENTA CON EL USUARIO
    public String entity;
    public String office;
    public String accNumber;
    public String dc;
    public String IBAN;
    public String accountAlias;
    public double balance = 0;

    public int lastDeposit;
    public int lastWithdrawal;

    public BankAccount(String ownerId, String entity, String office, String accNumber, String dc, String IBAN, String alias) {
        this.ownerId = ownerId;
        this.entity = entity;
        this.office = office;
        this.accNumber = accNumber;
        this.dc = dc;
        this.IBAN = IBAN;
        this.accountAlias = alias;
    }

    public abstract void deposit(int amount, BankAccount account);
    public abstract void withdraw(int amount, BankAccount account);
    public abstract void transfer(double amount, BankAccount account);
    public abstract void rechargeSIM(int amount, BankAccount account);
    public abstract void movimientos(int amount, BankAccount account, String balance);
}
