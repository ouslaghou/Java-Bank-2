package Account;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class BankAccount implements Accounting, Serializable {

    public String entity="9999", office="8888";
    public String dc="", accNumber="";
    public String IBAN="";
    public String accountAlias="";
    public double balance=0.0;
    int numNewAccount=0;
    ArrayList<BankAccount> accounts=new ArrayList<BankAccount>();
    Scanner sc =new Scanner(System.in);

    public BankAccount(String entity, String office, String accNumber, String dc, String IBAN, String accountAlias) {
        this.entity = entity;
        this.office = office;
        this.accNumber = accNumber;
        this.dc = dc;
        this.IBAN = IBAN;
        this.accountAlias = accountAlias;
        this.balance=0.0;
    }


}
