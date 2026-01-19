package Account;

import Person.User;

public class CreditAccount extends BankAccount {  //Declara la clase CreditAccount que hereda de BankAccount.
    double creditLimit = 0.0; //Límite de crédito disponible para esta cuenta
    double creditPercentage = 0.0;
    public CreditAccount(String entity, String office, String accNumber, String dc, String IBAN, String accountAlias, double creditLimit, double creditPercentage){
        super(entity, office, accNumber, dc, IBAN, accountAlias); //Llama al constructor de BankAccount para inicializar los atributos heredados.
        this.creditLimit = creditLimit;
        this.creditPercentage = creditPercentage;
    }

    public CreditAccount(String entity, String office, String accNumber, String dc, String IBAN, double creditLimit, double creditPercentage){ // Constructor, recibe todos los datos de la cuenta y los parámetros de crédito
        super(entity, office, accNumber, dc, IBAN); //Llama al constructor de BankAccount que asigna un alias automático
        this.creditLimit = creditLimit;
        this.creditPercentage = creditPercentage;
    }

    @Override
    public void deposit(int amount, BankAccount account) {
    //Sobreescribe la clase deposit.
    }

    @Override
    public void withdraw(int amount, BankAccount account) {
        //Sobreescribe la clase withdraw.
    }

    @Override
    public void transfer(double amount, BankAccount account) {
        //Sobreescribe la clase transfer.
    }

    @Override
    public void rechargeSIM(int amount, BankAccount account) {
        //Sobreescribe la clase rechargeSIM.
    }

    @Override
    public void selectAccount(User user) {
        //Sobreescribe la clase selectAccount.
    }
}
