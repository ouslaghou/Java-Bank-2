package Account;

import java.io.Serializable;

public class Seguro implements Serializable {

    public String nombre = "";
    public double precio = 0.0;
    public String descripcion = "";

    public Seguro(String nombre, double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public void mostrarInfo() {
        System.out.println("\nSeguro contratado: " + nombre);
        System.out.println("Precio anual: " + precio + "€");
        System.out.println("Descripción: " + descripcion);
    }
}
