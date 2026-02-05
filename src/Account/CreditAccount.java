package Account;

import Access.FileManager;
import java.util.Scanner;

public class CreditAccount extends BankAccount {

    public double creditLimit;
    public double creditUsed = 0;

    public CreditAccount(String ownerId, String entity, String office,
                         String accNumber, String dc, String IBAN,
                         String alias, double creditLimit) {
        super(ownerId, entity, office, accNumber, dc, IBAN, alias);
        this.creditLimit = creditLimit;
    }

    private boolean canUseCredit(double amount) {
        if (creditBlocked || operationsBlocked) {
            System.out.println("Credit usage is blocked due to debt.");
            return false;
        }
        return (creditUsed + amount) <= creditLimit;
    }

    @Override
    public void deposit(int amount, BankAccount account) {
        // Depósito normal a saldo
        account.balance += amount;
        account.addHistory("DEPOSIT", amount);
        System.out.println("Deposited " + amount + "€");
    }

    @Override
    public void withdraw(int amount, BankAccount account) {
        if (operationsBlocked) {
            System.out.println("Operations are blocked due to unpaid debt.");
            return;
        }

        // Primero saldo
        if (amount <= account.balance) {
            account.balance -= amount;
            account.addHistory("WITHDRAW (BALANCE)", amount);
            System.out.println("Withdraw successful.");
            return;
        }

        // Luego crédito
        double remaining = amount - account.balance;
        double fromBalance = account.balance;
        account.balance = 0;

        if (!canUseCredit(remaining)) {
            System.out.println("Not enough credit available.");
            account.balance += fromBalance; // revert
            return;
        }

        creditUsed += remaining;
        account.addHistory("WITHDRAW (BALANCE+ CREDIT)", amount);
        System.out.println("Withdraw using balance + credit successful.");
    }

    @Override
    public void transfer(double amount, BankAccount account) {
        if (operationsBlocked) {
            System.out.println("Operations are blocked due to unpaid debt.");
            return;
        }

        Scanner sc = new Scanner(System.in);
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
            System.out.println("Destination account not found.");
            return;
        }

        // Primero saldo
        if (amount <= account.balance) {
            account.balance -= amount;
            destAcc.balance += amount;
            account.addHistory("TRANSFER SENT (BALANCE) to " + destAcc.accNumber, amount);
            destAcc.addHistory("TRANSFER RECEIVED from " + account.accNumber, amount);
            System.out.println("Transfer successful.");
            return;
        }

        // Luego crédito
        double remaining = amount - account.balance;
        double fromBalance = account.balance;
        account.balance = 0;

        if (!canUseCredit(remaining)) {
            System.out.println("Not enough credit available.");
            account.balance += fromBalance; // revert
            return;
        }

        creditUsed += remaining;
        destAcc.balance += amount;
        account.addHistory("TRANSFER SENT (BALANCE + CREDIT) to " + destAcc.accNumber, amount);
        destAcc.addHistory("TRANSFER RECEIVED from " + account.accNumber, amount);
        System.out.println("Transfer using balance + credit successful.");
    }

    @Override
    public void rechargeSIM(int amount, BankAccount account) {
        if (operationsBlocked) {
            System.out.println("Operations are blocked due to unpaid debt.");
            return;
        }

        if (amount <= account.balance) {
            account.balance -= amount;
            account.addHistory("SIM RECHARGE (BALANCE)", amount);
            System.out.println("SIM recharge done.");
            return;
        }

        double remaining = amount - account.balance;
        double fromBalance = account.balance;
        account.balance = 0;

        if (!canUseCredit(remaining)) {
            System.out.println("Not enough credit available.");
            account.balance += fromBalance;
            return;
        }

        creditUsed += remaining;
        account.addHistory("SIM RECHARGE (BALANCE + CREDIT)", amount);
        System.out.println("SIM recharge using credit done.");
    }

    @Override
    public void movimientos(int amount, BankAccount account, String balance) {
        account.showHistory();
    }
}
