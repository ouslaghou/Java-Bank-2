package Account;

import Person.User;

public class CreditAccount extends BankAccount {

    double creditLimit;
    double creditPercentage;

    public CreditAccount(String ownerId, String entity, String office, String accNumber, String dc, String IBAN, String alias, double limit, double percentage) {
        super(ownerId, entity, office, accNumber, dc, IBAN, alias);
        this.creditLimit = limit;
        this.creditPercentage = percentage;
    }

    @Override
    public void deposit(int amount, BankAccount account) {}

    @Override
    public void withdraw(int amount, BankAccount account) {}

    @Override
    public void transfer(double amount, BankAccount account) {}

    @Override
    public void rechargeSIM(int amount, BankAccount account) {}

    @Override
    public void movimientos(int amount, BankAccount account, String balance) {}
}
