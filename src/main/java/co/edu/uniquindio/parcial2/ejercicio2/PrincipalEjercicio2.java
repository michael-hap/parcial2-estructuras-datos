package co.edu.uniquindio.parcial2.ejercicio2;

import co.edu.uniquindio.parcial2.estructuras.Pila;

public class PrincipalEjercicio2 {

    public static void main(String[] args) {

        Pila<String> historial = new Pila<>();

        historial.apilar("A1");
        historial.apilar("A2");
        historial.apilar("A3");
        historial.apilar("A4");
        historial.apilar("A5");
        historial.apilar("A6");

        System.out.println("=== EJERCICIO 2 ===");

        System.out.println("\nPila original:");
        mostrarPila(historial);

        Pila<String> segmento =
                GestionHistorial.obtenerSegmento(historial, 2, 4);

        System.out.println("\nSegmento (2, 4):");
        mostrarPila(segmento);

        System.out.println("\nPila original despues:");
        mostrarPila(historial);

        // Prueba con una sola posicion
        Pila<String> unico =
                GestionHistorial.obtenerSegmento(historial, 3, 3);

        System.out.println("\nSegmento (3, 3):");
        mostrarPila(unico);

        // Pruebas con posiciones invalidas
        probarRango(historial, 0, 3);
        probarRango(historial, 5, 2);
        probarRango(historial, 2, 8);

        // Prueba con pila vacia
        Pila<String> vacia = new Pila<>();
        probarRango(vacia, 1, 2);

        System.out.println("\nPila original al finalizar:");
        mostrarPila(historial);

        // Seleccionar todo el historial
        probarRango(historial, 1, 6);

        // Seleccionar solamente el ultimo elemento
        probarRango(historial, 6, 6);

    }

    private static void probarRango(
            Pila<String> pila, int inicio, int fin) {

        Pila<String> segmento =
                GestionHistorial.obtenerSegmento(
                        pila, inicio, fin);

        System.out.println("\nSegmento ("
                + inicio + ", " + fin + "):");

        mostrarPila(segmento);
    }

    private static <T> void mostrarPila(Pila<T> pila) {

        Pila<T> auxiliar = new Pila<>();

        System.out.print("Cima -> ");

        while (!pila.estaVacia()) {
            T dato = pila.desapilar();
            System.out.print(dato + " -> ");
            auxiliar.apilar(dato);
        }

        System.out.println("Fondo");

        while (!auxiliar.estaVacia()) {
            pila.apilar(auxiliar.desapilar());
        }
    }
}
