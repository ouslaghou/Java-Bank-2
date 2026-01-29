package Account;

public class CreditAccount extends BankAccount {

    double creditLimit;
    double creditPercentage;

    public CreditAccount(String ownerId, String entity, String office, String accNumber, String dc, String IBAN, String alias, double limit, double percentage) {
        super(ownerId, entity, office, accNumber, dc, IBAN, alias);
        this.creditLimit = limit;
        this.creditPercentage = percentage;
    }

    @Override
    public void deposit(int amount, BankAccount account) {
        account.balance += amount;
        account.addHistory("CREDIT DEPOSIT", amount);
    }

    @Override
    public void withdraw(int amount, BankAccount account) {
        if (amount > creditLimit) {
            System.out.println("Credit limit exceeded");
            return;
        }
        account.balance -= amount;
        account.addHistory("CREDIT WITHDRAW", amount);
    }

    @Override
    public void transfer(double amount, BankAccount account) {}

    @Override
    public void rechargeSIM(int amount, BankAccount account) {}

    @Override
    public void movimientos(int amount, BankAccount account, String balance) {
        account.showHistory();
    }
}
