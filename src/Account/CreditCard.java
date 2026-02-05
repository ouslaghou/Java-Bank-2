package Account;

import java.io.Serializable;

public class CreditCard extends Card implements Serializable {
    public CreditCard() {
        this.type = "CREDIT";
    }
}
