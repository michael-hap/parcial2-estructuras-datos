package co.edu.uniquindio.parcial2.ejercicio3;

import co.edu.uniquindio.parcial2.estructuras.Cola;

public class SelectorPaquetes {

    private int capacidadRestante;

    public Cola<Paquete> seleccionarPaquetes(
            Cola<Paquete> pendientes,
            int capacidadDisponible) {

        Cola<Paquete> seleccionados = new Cola<>();

        capacidadRestante = capacidadDisponible;

        if (capacidadDisponible <= 0 || pendientes.estaVacia()) {
            return seleccionados;
        }

        Cola<Paquete> urgentes = new Cola<>();
        Cola<Paquete> normales = new Cola<>();
        Cola<Paquete> respaldo = new Cola<>();

        int cantidad = pendientes.tamanio();

        clasificarPaquetes(
                pendientes, cantidad, urgentes, normales, respaldo
        );

        restaurarCola(respaldo, cantidad, pendientes);

        Cola<Paquete> pendientesUrgentes = new Cola<>();
        Cola<Paquete> pendientesNormales = new Cola<>();

        System.out.println("\n--- Procesando paquetes urgentes ---");

        procesarPaquetes(
                urgentes,
                urgentes.tamanio(),
                seleccionados,
                pendientesUrgentes
        );

        System.out.println("\n--- Procesando paquetes normales ---");

        procesarPaquetes(
                normales,
                normales.tamanio(),
                seleccionados,
                pendientesNormales
        );

        Cola<Paquete> resultadoPendientes = new Cola<>();

        reconstruirPendientes(
                pendientes,
                cantidad,
                pendientesUrgentes,
                pendientesNormales,
                resultadoPendientes
        );

        pendientes.vaciar();

        restaurarCola(
                resultadoPendientes,
                resultadoPendientes.tamanio(),
                pendientes
        );

        return seleccionados;
    }

    private void clasificarPaquetes(
            Cola<Paquete> original,
            int cantidad,
            Cola<Paquete> urgentes,
            Cola<Paquete> normales,
            Cola<Paquete> respaldo) {

        if (cantidad == 0) {
            return;
        }

        Paquete paquete = original.desencolar();

        respaldo.encolar(paquete);

        if (paquete.getTipo() == TipoEntrega.URGENTE) {
            urgentes.encolar(paquete);
        } else {
            normales.encolar(paquete);
        }

        clasificarPaquetes(
                original,
                cantidad - 1,
                urgentes,
                normales,
                respaldo
        );
    }

    private void restaurarCola(
            Cola<Paquete> respaldo,
            int cantidad,
            Cola<Paquete> original) {

        if (cantidad == 0) {
            return;
        }

        original.encolar(respaldo.desencolar());

        restaurarCola(
                respaldo,
                cantidad - 1,
                original
        );
    }

    private void procesarPaquetes(
            Cola<Paquete> porProcesar,
            int cantidad,
            Cola<Paquete> seleccionados,
            Cola<Paquete> pendientes) {

        if (cantidad == 0) {
            return;
        }

        Paquete paquete = porProcesar.desencolar();

        if (paquete.getPeso() <= capacidadRestante) {

            seleccionados.encolar(paquete);
            capacidadRestante -= paquete.getPeso();

            System.out.println(
                    paquete.getCodigo()
                            + " seleccionado. Capacidad restante: "
                            + capacidadRestante + " kg"
            );

        } else {

            pendientes.encolar(paquete);

            System.out.println(
                    paquete.getCodigo()
                            + " queda pendiente. Peso: "
                            + paquete.getPeso() + " kg"
            );
        }

        procesarPaquetes(
                porProcesar,
                cantidad - 1,
                seleccionados,
                pendientes
        );
    }

    private void reconstruirPendientes(
            Cola<Paquete> original,
            int cantidad,
            Cola<Paquete> pendientesUrgentes,
            Cola<Paquete> pendientesNormales,
            Cola<Paquete> resultado) {

        if (cantidad == 0) {
            return;
        }

        Paquete paquete = original.desencolar();

        if (pendientesUrgentes.contiene(paquete)
                || pendientesNormales.contiene(paquete)) {

            resultado.encolar(paquete);
        }

        original.encolar(paquete);

        reconstruirPendientes(
                original,
                cantidad - 1,
                pendientesUrgentes,
                pendientesNormales,
                resultado
        );
    }

    public int getCapacidadRestante() {
        return capacidadRestante;
    }
}
