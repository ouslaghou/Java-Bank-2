package Account;

import Access.FondoIndexado;
import Person.User;
import java.util.Scanner;

public class PanelInversiones {

    private Scanner sc = new Scanner(System.in);

    public void mostrar(User U) {

        int opcion = 0;

        while (opcion != 4) {

            System.out.println("PANEL DE INVERSIONES");
            System.out.println("1. Crear Plazo Fijo");
            System.out.println("2. Crear Fondo Indexado");
            System.out.println("3. Comprar Acciones");
            System.out.println("4. Ver Inversiones / Volver");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.println("Capital:");
                    double capitalPF = sc.nextDouble();

                    System.out.println("Años:");
                    int anyosPF = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Intereses (ej: 0.03 = 3%):");
                    double intereses = sc.nextDouble();
                    sc.nextLine();

                    PlazoFijo pf = new PlazoFijo(capitalPF);
                    pf.intereses = intereses;
                    pf.anyos = anyosPF;

                    U.inversiones.add(pf);
                    System.out.println("Plazo fijo creado.");
                    break;

                case 2:
                    System.out.println("Capital:");
                    double capitalFI = sc.nextDouble();

                    System.out.println("Años:");
                    int anyosFI = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Rentabilidad anual (ej: 0.07 = 7%):");
                    double rentabilidad = sc.nextDouble();
                    sc.nextLine();

                    FondoIndexado fi = new FondoIndexado(capitalFI);
                    fi.anyos = anyosFI;
                    fi.rentabilidadAnual = rentabilidad;

                    U.inversiones.add(fi);
                    System.out.println("Fondo indexado creado.");
                    break;

                case 3:
                    System.out.println("Empresa:");
                    String empresa = sc.nextLine();

                    System.out.println("Cantidad de acciones:");
                    double cantidad = sc.nextDouble();

                    System.out.println("Precio actual por acción:");
                    double precio = sc.nextDouble();
                    sc.nextLine();

                    Acciones acc = new Acciones(0);
                    acc.empresa = empresa;
                    acc.cantidad = cantidad;
                    acc.precioActual = precio;

                    U.inversiones.add(acc);
                    System.out.println("Acciones compradas.");
                    break;

                case 4:
                    if (U.inversiones.isEmpty()) {
                        System.out.println("No tienes inversiones.");
                        break;
                    }
                    System.out.println("Tus Inversiones");
                    for (Inversiones inv : U.inversiones) {
                        System.out.println("Valor actual: " + inv.calcularValorActual());
                    }
                    return;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
