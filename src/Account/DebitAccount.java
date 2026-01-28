package Account;

import Access.FileManager;

import java.util.Scanner;

public class DebitAccount extends BankAccount {

    Scanner sc = new Scanner(System.in);

    public DebitAccount(String ownerId, String entity, String office, String accNumber, String dc, String IBAN, String alias) {
        super(ownerId, entity, office, accNumber, dc, IBAN, alias);
    }

    @Override
    public void deposit(int amount, BankAccount account) {
        account.balance += amount;
        account.lastDeposit = amount;
        System.out.println("Deposited " + amount);
    }

    @Override
    public void withdraw(int amount, BankAccount account) {
        if (amount > account.balance) {
            System.out.println("Insufficient funds");
            return;
        }
        account.balance -= amount;
        account.lastWithdrawal = amount;
        System.out.println("Withdraw successful");
    }

    @Override
    public void transfer(double amount, BankAccount account) {
        System.out.println("Enter destination account number:");
        String dest = sc.nextLine();

        BankAccount destAcc = null;

        for (BankAccount ba : FileManager.accounts) {
            if (ba.accNumber.equals(dest)) {
                destAcc = ba;
                break;
            }
        }

        if (destAcc == null) {
            System.out.println("Destination account not found");
            return;
        }

        if (amount > account.balance) {
            System.out.println("Insufficient funds");
            return;
        }

        account.balance -= amount;
        destAcc.balance += amount;

        System.out.println("Transfer successful");
    }

    @Override
    public void rechargeSIM(int amount, BankAccount account) {
        System.out.println("SIM recharge done");
    }

    @Override
    public void movimientos(int amount, BankAccount account, String balance) {
        System.out.println("Last deposit: " + account.lastDeposit);
        System.out.println("Last withdrawal: " + account.lastWithdrawal);
    }
}
