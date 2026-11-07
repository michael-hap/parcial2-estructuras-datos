package co.edu.uniquindio.parcial2.ejercicio1;

import co.edu.uniquindio.parcial2.estructuras.Pila;

public class GestionCajas {

    public static boolean retirarCaja(Pila<Caja> pila, String codigo) {

        if (pila.estaVacia()) {
            return false;
        }

        Pila<Caja> auxiliar = new Pila<>();
        Caja retirada = null;

        // Buscar y retirar la caja solicitada
        while (!pila.estaVacia()) {
            Caja actual = pila.desapilar();

            if (retirada == null && actual.getCodigo().equals(codigo)) {
                retirada = actual;
            } else {
                auxiliar.apilar(actual);
            }
        }

        // Restaurar el orden original sin la caja retirada
        while (!auxiliar.estaVacia()) {
            pila.apilar(auxiliar.desapilar());
        }

        if (retirada == null) {
            return false;
        }

        Pila<Caja> impares = new Pila<>();
        Pila<Caja> pares = new Pila<>();

        int posicion = 1;

        // Clasificar según la posición desde la cima
        while (!pila.estaVacia()) {
            Caja actual = pila.desapilar();

            if (posicion % 2 == 0) {
                pares.apilar(actual);
            } else {
                impares.apilar(actual);
            }

            posicion++;
        }

        // Elegir qué posiciones deben quedar arriba
        Pila<Caja> grupoSuperior;
        Pila<Caja> grupoInferior;

        if (retirada.getValor() % 2 == 0) {
            grupoSuperior = impares;
            grupoInferior = pares;
        } else {
            grupoSuperior = pares;
            grupoInferior = impares;
        }

        // Primero ubicar el grupo inferior
        while (!grupoInferior.estaVacia()) {
            pila.apilar(grupoInferior.desapilar());
        }

        // Después ubicar el grupo superior
        while (!grupoSuperior.estaVacia()) {
            pila.apilar(grupoSuperior.desapilar());
        }

        return true;
    }
}
