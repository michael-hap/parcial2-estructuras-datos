package co.edu.uniquindio.parcial2.ejercicio1;

import co.edu.uniquindio.parcial2.estructuras.Pila;

public class PruebaPila {

    public static void main(String[] args) {

        Pila<String> pila = new Pila<>();

        System.out.println("=== PRUEBA DE PILA ===");

        pila.apilar("C1");
        pila.apilar("C2");
        pila.apilar("C3");

        System.out.println("Cantidad de cajas: " + pila.tamanio());
        System.out.println("Caja en la cima: " + pila.obtenerCima());

        System.out.println("Retirando: " + pila.desapilar());

        System.out.println("Nueva cima: " + pila.obtenerCima());
        System.out.println("Cantidad restante: " + pila.tamanio());

        System.out.println("¿Esta vacia?: " + pila.estaVacia());
    }
}
