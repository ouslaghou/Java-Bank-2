package Account;

import java.io.Serializable;
import java.util.ArrayList;

// Clase base para cualquier tarjeta (crédito o débito)
// Debe ser serializable porque se guarda dentro de las cuentas
public class Card implements Serializable {

    public String type; // Tipo de tarjeta: CREDIT o DEBIT

}
