package Account;

import java.io.Serializable;

// Tarjeta de débito
public class DebitCard extends Card implements Serializable {

    public DebitCard() {
        this.type = "DEBIT"; // Indicamos el tipo
    }
}
