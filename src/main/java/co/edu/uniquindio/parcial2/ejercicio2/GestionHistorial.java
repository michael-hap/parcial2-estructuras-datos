package co.edu.uniquindio.parcial2.ejercicio2;

import co.edu.uniquindio.parcial2.estructuras.Pila;

public class GestionHistorial {

    public static <T> Pila<T> obtenerSegmento(
            Pila<T> original, int inicio, int fin) {


        Pila<T> resultado = new Pila<>();

        if (inicio < 1 || inicio > fin
                || fin > original.tamanio()) {
            return resultado;
        }

        Pila<T> auxiliar = new Pila<>();

        int total = original.tamanio();

        while (!original.estaVacia()) {
            auxiliar.apilar(original.desapilar());
        }

        for (int posicion = total; posicion >= 1; posicion--) {

            T accion = auxiliar.desapilar();

            original.apilar(accion);

            if (posicion >= inicio && posicion <= fin) {
                resultado.apilar(accion);
            }
        }

        return resultado;
    }
}
