package co.edu.uniquindio.parcial2.estructuras;

import java.util.NoSuchElementException;
import java.util.Objects;

public class Cola<T> {

    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamanio;

    public Cola() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    // Agrega un elemento al final de la cola.
    public void encolar(T dato) {

        Nodo<T> nuevo = new Nodo<>(dato);

        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }

        tamanio++;
    }

    // Retira y devuelve el elemento del frente.
    public T desencolar() {

        if (estaVacia()) {
            throw new NoSuchElementException("La cola está vacía");
        }

        T dato = frente.getDato();

        frente = frente.getSiguiente();
        tamanio--;

        if (frente == null) {
            fin = null;
        }

        return dato;
    }

    // Consulta el elemento que está al frente.
    public T obtenerFrente() {

        if (estaVacia()) {
            throw new NoSuchElementException("La cola está vacía");
        }

        return frente.getDato();
    }

    // Comprueba si la cola está vacía.
    public boolean estaVacia() {
        return frente == null;
    }

    // Devuelve la cantidad de elementos.
    public int tamanio() {
        return tamanio;
    }

    // Elimina todos los elementos de la cola.
    public void vaciar() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    // Comprueba si un elemento existe en la cola.
    public boolean contiene(T dato) {
        return contieneRecursivo(frente, dato);
    }

    // Busca un elemento mediante recursividad.
    private boolean contieneRecursivo(Nodo<T> actual, T dato) {

        if (actual == null) {
            return false;
        }

        if (Objects.equals(actual.getDato(), dato)) {
            return true;
        }

        return contieneRecursivo(actual.getSiguiente(), dato);
    }

    // Muestra los elementos de la cola.
    public void mostrar() {
        mostrarRecursivo(frente);
    }

    // Recorre la cola mediante recursividad.
    private void mostrarRecursivo(Nodo<T> actual) {

        if (actual == null) {
            return;
        }

        System.out.println(actual.getDato());

        mostrarRecursivo(actual.getSiguiente());
    }
}