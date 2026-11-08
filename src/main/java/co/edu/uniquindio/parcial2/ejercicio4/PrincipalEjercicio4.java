package co.edu.uniquindio.parcial2.ejercicio4;

import co.edu.uniquindio.parcial2.estructuras.ArbolBinarioBusqueda;

public class PrincipalEjercicio4 {

    public static void main(String[] args) {

        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        // Códigos proporcionados en el ejercicio.
        int[] codigos = {
                41, 20, 52, 16, 6, 13, 12,
                60, 22, 47, 50, 60, 43, 44
        };

        // Insertar los códigos.
        for (int codigo : codigos) {
            arbol.insertar(codigo);
        }

        System.out.println("===== ARBOL BINARIO DE BUSQUEDA =====");

        System.out.println("\nRECORRIDO INORDEN:");
        arbol.recorridoInorden();

        System.out.println("\nRECORRIDO PREORDEN:");
        arbol.recorridoPreorden();

        System.out.println("\nRECORRIDO POSTORDEN:");
        arbol.recorridoPostorden();

        System.out.println("\nRECORRIDO POR NIVELES:");
        arbol.recorridoPorNiveles();

        // Buscar códigos dentro de un rango.
        int inferior = 12;
        int superior = 50;

        int cantidad = arbol.contarEquiposEnRango(
                inferior, superior
        );

        System.out.println("\n===== CONTEO POR RANGO =====");

        System.out.println("Limite inferior: " + inferior);
        System.out.println("Limite superior: " + superior);
        System.out.println("Cantidad encontrada: " + cantidad);

        // Eliminar el código 41.
        System.out.println("\n===== ELIMINACION DEL CODIGO 41 =====");

        System.out.println("\nArbol antes de eliminar:");
        arbol.recorridoPorNiveles();

        arbol.eliminar(41);

        System.out.println("\nArbol despues de eliminar:");
        arbol.recorridoPorNiveles();

        System.out.println("\nRecorrido inorden despues de eliminar:");
        arbol.recorridoInorden();

        System.out.println("\nSe utilizo el sucesor inorden para reemplazar el codigo 41.");
    }
}
