package Account;

import java.io.Serializable;

// Tarjeta de crédito
public class CreditCard extends Card implements Serializable {

    public CreditCard() {
        this.type = "CREDIT";
    }
}
