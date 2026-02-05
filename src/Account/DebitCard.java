package Account;

import java.io.Serializable;

public class DebitCard extends Card implements Serializable {
    public DebitCard() {
        this.type = "DEBIT";
    }
}
