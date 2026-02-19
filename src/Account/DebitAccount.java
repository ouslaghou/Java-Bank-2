package Account;

import java.io.Serializable;

// Cuenta de débito normal
public class DebitAccount extends BankAccount implements Serializable {

    public DebitAccount(String ownerId, String entity, String office,
                        String accNumber, String dc, String IBAN, String alias) {

        super(ownerId, entity, office, accNumber, dc, IBAN, alias);
    }

    @Override
    public void deposit(int amount, BankAccount account) {
        account.balance += amount;
        account.addHistory("DEPÓSITO", amount);
        System.out.println("Depósito realizado.");
    }

    @Override
    public void withdraw(int amount, BankAccount account) {

        // PERMITIMOS SALDO NEGATIVO
        account.balance -= amount;

        account.addHistory("RETIRADA", amount);
        System.out.println("Retirada realizada (saldo negativo permitido).");
    }

    @Override
    public void transfer(double amount, BankAccount account) {

        // PERMITIMOS SALDO NEGATIVO
        account.balance -= amount;

        System.out.println("Número de cuenta destino:");
        java.util.Scanner sc = new java.util.Scanner(System.in);
        String dest = sc.nextLine();

        BankAccount destAcc = null;
        for (BankAccount ba : Access.FileManager.accounts) {
            if (ba.accNumber.equals(dest)) {
                destAcc = ba;
                break;
            }
        }

        if (destAcc == null) {
            System.out.println("Cuenta destino no encontrada.");
            return;
        }

        destAcc.balance += amount;

        account.addHistory("TRANSFERENCIA ENVIADA", amount);
        destAcc.addHistory("TRANSFERENCIA RECIBIDA", amount);

        System.out.println("Transferencia realizada.");
    }

    @Override
    public void rechargeSIM(int amount, BankAccount account) {
        withdraw(amount, account);
    }

    @Override
    public void movimientos(int amount, BankAccount account, String balance) {
        account.showHistory();
    }
}
