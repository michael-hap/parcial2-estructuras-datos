package co.edu.uniquindio.parcial2.ejercicio4;

import co.edu.uniquindio.parcial2.estructuras.ArbolBinarioBusqueda;

public class PrincipalEjercicio4 {

    public static void main(String[] args) {

        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        int[] codigos = {
                41, 20, 52, 16, 6, 13, 12,
                60, 22, 47, 50, 60, 43, 44
        };

        // INSERTAR LOS CODIGOS

        for (int codigo : codigos) {
            arbol.insertar(codigo);
        }

        System.out.println("ARBOL BINARIO DE BUSQUEDA");

        // MOSTRAR ARBOL ORIGINAL

        System.out.println("\nARBOL ORIGINAL:");
        arbol.mostrarArbol();

        // RECORRIDOS

        System.out.println("\nRECORRIDO INORDEN:");
        arbol.recorridoInorden();

        System.out.println("\nRECORRIDO PREORDEN:");
        arbol.recorridoPreorden();

        System.out.println("\nRECORRIDO POSTORDEN:");
        arbol.recorridoPostorden();

        System.out.println("\nRECORRIDO POR NIVELES:");
        arbol.recorridoPorNiveles();

        // CONTEO POR RANGO

        int inferior = 12;
        int superior = 50;

        int cantidad = arbol.contarEquiposEnRango(
                inferior, superior
        );

        System.out.println("\nCONTEO POR RANGO");

        System.out.println("Limite inferior: " + inferior);
        System.out.println("Limite superior: " + superior);
        System.out.println("Cantidad encontrada: " + cantidad);

        // ELIMINAR EL CODIGO 41

        System.out.println("\nELIMINACION DEL CODIGO 41");

        arbol.eliminar(41);

        // MOSTRAR ARBOL DESPUES DE ELIMINAR

        System.out.println("\nARBOL DESPUES DE ELIMINAR:");
        arbol.mostrarArbol();

        System.out.println("\nRECORRIDO INORDEN DESPUES DE ELIMINAR:");
        arbol.recorridoInorden();

        System.out.println(
                "\nSe utilizo el sucesor inorden para reemplazar el codigo 41."
        );
    }
}
