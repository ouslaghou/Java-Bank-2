package Account;

import Access.metodospago;
import Access.FileManager;
import Person.Person;

import java.util.ArrayList;
import java.util.Scanner;

public class tiendaonline {

    public static void tienda(BankAccount cuentaActual, ArrayList<Person> persons) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("----------------------------------------------------");
            System.out.println(" ¡Welcome to our online shop! ");
            System.out.println("----------------------------------------------------");
            System.out.println("1. Tecnología y Electrónica");
            System.out.println("2. Hogar y Electrodomésticos");
            System.out.println("3. Movilidad");
            System.out.println("4. Fitness y Descanso");
            System.out.println("5. Oportunidades");
            System.out.println("6. Salir");
            System.out.println("----------------------------------------------------");

            String categoria = sc.nextLine();

            if (categoria.equals("6")) return;

            switch (categoria) {

                case "1":
                    while (true) {
                        System.out.println("(Elige una) Tipos:");
                        System.out.println("1. Smartphones");
                        System.out.println("2. Portátiles");
                        System.out.println("3. Tablets");
                        System.out.println("4. Consolas");
                        System.out.println("5. Gaming");
                        System.out.println("6. Volver");

                        String opcion = sc.nextLine();
                        if (opcion.equals("6")) break;

                        switch (opcion) {

                            // ---------------- SMARTPHONES ----------------
                            case "1":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. iPhone 17 Pro Max (1499€)",
                                        "2. Xiaomi 17 Pro Max (896€)",
                                        1499, 896);
                                break;

                            // ---------------- PORTÁTILES ----------------
                            case "2":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. ASUS TUF Gaming F15 (1199€)",
                                        "2. Lenovo IdeaPad 3 (549€)",
                                        1199, 549);
                                break;

                            // ---------------- TABLETS ----------------
                            case "3":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. iPad Air (699€)",
                                        "2. Samsung Galaxy Tab S9 (799€)",
                                        699, 799);
                                break;

                            // ---------------- CONSOLAS ----------------
                            case "4":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. PS5 (499€)",
                                        "2. XBOX (423€)",
                                        499, 423);
                                break;

                            // ---------------- GAMING ----------------
                            case "5":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. Corsair Vengeance 16GB (49€)",
                                        "2. Kingston Fury 16GB (69€)",
                                        49, 69);
                                break;
                        }
                    }
                    break;

                // ---------------------------------------------------------
                // 2. HOGAR
                // ---------------------------------------------------------
                case "2":
                    while (true) {
                        System.out.println("1. Electrodomésticos grandes");
                        System.out.println("2. Electrodomésticos pequeños");
                        System.out.println("3. Muebles");
                        System.out.println("4. Volver");

                        String opcion = sc.nextLine();
                        if (opcion.equals("4")) break;

                        switch (opcion) {

                            case "1":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. Lavadora AEG (939€)",
                                        "2. Frigorífico LG (2139€)",
                                        939, 2139);
                                break;

                            case "2":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. Microondas LG (321€)",
                                        "2. Batidora Princess (89€)",
                                        321, 89);
                                break;

                            case "3":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. Aparador nórdico (569€)",
                                        "2. Cama canapé (876€)",
                                        569, 876);
                                break;
                        }
                    }
                    break;

                case "3":
                    while (true) {
                        System.out.println("1. Motocicletas");
                        System.out.println("2. Bicicletas");
                        System.out.println("3. Patinetes");
                        System.out.println("4. Cargadores eléctricos");
                        System.out.println("5. Volver");

                        String mov = sc.nextLine();
                        if (mov.equals("5")) break;

                        switch (mov) {

                            case "1":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. Cecotec Shark 49cc (2199€)",
                                        "2. Yamaha MT-07 (7709€)",
                                        2199, 7709);
                                break;

                            case "2":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. Fiido C11 (999€)",
                                        "2. Fiido D3 Pro (499€)",
                                        999, 499);
                                break;

                            case "3":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. Bongo GS65 XXL (499€)",
                                        "2. Ausom L1 (408€)",
                                        499, 408);
                                break;

                            case "4":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. Tesla Wall Connector (795€)",
                                        "2. Cargador rápido (13000€)",
                                        795, 13000);
                                break;
                        }
                    }
                    break;

                case "4":
                    while (true) {
                        System.out.println("1. Cintas de correr");
                        System.out.println("2. Bancos de remo");
                        System.out.println("3. Equipos de descanso");
                        System.out.println("4. Volver");

                        String fit = sc.nextLine();
                        if (fit.equals("4")) break;

                        switch (fit) {

                            case "1":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. Drumfit 180 (389€)",
                                        "2. Drumfit 140 (229€)",
                                        389, 229);
                                break;

                            case "2":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. RW700 (1299€)",
                                        "2. Rower 20000 (1799€)",
                                        1299, 1799);
                                break;

                            case "3":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. Somier metálico (549€)",
                                        "2. Kanguro estándar (199€)",
                                        549, 199);
                                break;
                        }
                    }
                    break;

                case "5":
                    while (true) {
                        System.out.println("1. Productos reacondicionados");
                        System.out.println("2. Outlet");
                        System.out.println("3. Volver");

                        String op = sc.nextLine();
                        if (op.equals("3")) break;

                        switch (op) {

                            case "1":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. Apple Watch SE 2 (173€)",
                                        "2. Apple Watch SE 3 (223€)",
                                        173, 223);
                                break;

                            case "2":
                                pagarProducto(sc, cuentaActual, persons,
                                        "1. Conga X100 (499€)",
                                        "2. Conga Y100 Spin (299€)",
                                        499, 299);
                                break;
                        }
                    }
                    break;
            }
        }
    }

    private static void pagarProducto(Scanner sc, BankAccount cuentaActual, ArrayList<Person> persons,
                                      String opcion1, String opcion2,
                                      int precio1, int precio2) {

        System.out.println(opcion1);
        System.out.println(opcion2);

        String eleccion = sc.nextLine();
        int precio = 0;

        switch (eleccion) {
            case "1": precio = precio1; break;
            case "2": precio = precio2; break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        System.out.println("Has elegido un producto de " + precio + "€");

        metodospago mp = new metodospago();
        mp.persons = persons; // Lista usuarios
        mp.Bizum(precio, cuentaActual);
    }
}
