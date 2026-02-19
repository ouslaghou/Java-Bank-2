package Account;

import java.io.Serializable;

public class Card implements Serializable {

    String numCard ="";
    int pin=0;
    boolean active = true;
    System titular;

    public Card() {
        numCard = GeneradorTarjeta.generarTarjeta(16);
        pin = (int)(Math.random() * 9000) + 1000;
        active = true;
        this.titular = titular;

    }
}
