package co.edu.uniquindio.parcial2.ejercicio3;

import co.edu.uniquindio.parcial2.estructuras.Cola;

public class PrincipalEjercicio3 {

    public static void main(String[] args) {

        Cola<Paquete> pendientes = new Cola<>();

        pendientes.encolar(new Paquete("P1", TipoEntrega.NORMAL, 4));
        pendientes.encolar(new Paquete("P2", TipoEntrega.URGENTE, 7));
        pendientes.encolar(new Paquete("P3", TipoEntrega.NORMAL, 3));
        pendientes.encolar(new Paquete("P4", TipoEntrega.URGENTE, 5));
        pendientes.encolar(new Paquete("P5", TipoEntrega.NORMAL, 6));
        pendientes.encolar(new Paquete("P6", TipoEntrega.URGENTE, 4));
        pendientes.encolar(new Paquete("P7", TipoEntrega.NORMAL, 2));

        int capacidadDisponible = 14;

        SelectorPaquetes selector = new SelectorPaquetes();

        System.out.println("COLA ORIGINAL");
        pendientes.mostrar();

        Cola<Paquete> seleccionados =
                selector.seleccionarPaquetes(
                        pendientes,
                        capacidadDisponible
                );

        System.out.println("\nPAQUETES SELECCIONADOS");
        seleccionados.mostrar();

        System.out.println("\nPAQUETES PENDIENTES");
        pendientes.mostrar();

        System.out.println(
                "\nCapacidad restante: "
                        + selector.getCapacidadRestante()
                        + " kg"
        );
    }
}
