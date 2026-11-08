package co.edu.uniquindio.parcial2.estructuras;

import java.util.LinkedList;
import java.util.Queue;

public class ArbolBinarioBusqueda {

    private NodoArbol raiz;

    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    // INSERTAR

    public void insertar(int codigo) {
        raiz = insertarRecursivo(raiz, codigo);
    }

    private NodoArbol insertarRecursivo(NodoArbol actual, int codigo) {

        if (actual == null) {
            return new NodoArbol(codigo);
        }

        if (codigo < actual.getCodigo()) {
            actual.setIzquierdo(
                    insertarRecursivo(actual.getIzquierdo(), codigo)
            );
        } else if (codigo > actual.getCodigo()) {
            actual.setDerecho(
                    insertarRecursivo(actual.getDerecho(), codigo)
            );
        }

        // Si el código es igual, no se inserta porque debe ser único.
        return actual;
    }

    // RECORRIDO INORDEN: IZQUIERDA - RAÍZ - DERECHA

    public void recorridoInorden() {
        recorridoInordenRecursivo(raiz);
        System.out.println();
    }

    private void recorridoInordenRecursivo(NodoArbol actual) {

        if (actual == null) {
            return;
        }

        recorridoInordenRecursivo(actual.getIzquierdo());
        System.out.print(actual.getCodigo() + " ");
        recorridoInordenRecursivo(actual.getDerecho());
    }

    // RECORRIDO PREORDEN: RAÍZ - IZQUIERDA - DERECHA

    public void recorridoPreorden() {
        recorridoPreordenRecursivo(raiz);
        System.out.println();
    }

    private void recorridoPreordenRecursivo(NodoArbol actual) {

        if (actual == null) {
            return;
        }

        System.out.print(actual.getCodigo() + " ");
        recorridoPreordenRecursivo(actual.getIzquierdo());
        recorridoPreordenRecursivo(actual.getDerecho());
    }

    // RECORRIDO POSTORDEN: IZQUIERDA - DERECHA - RAÍZ

    public void recorridoPostorden() {
        recorridoPostordenRecursivo(raiz);
        System.out.println();
    }

    private void recorridoPostordenRecursivo(NodoArbol actual) {

        if (actual == null) {
            return;
        }

        recorridoPostordenRecursivo(actual.getIzquierdo());
        recorridoPostordenRecursivo(actual.getDerecho());
        System.out.print(actual.getCodigo() + " ");
    }

    // RECORRIDO POR NIVELES

    public void recorridoPorNiveles() {

        if (raiz == null) {
            return;
        }

        Queue<NodoArbol> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {

            NodoArbol actual = cola.poll();

            System.out.print(actual.getCodigo() + " ");

            if (actual.getIzquierdo() != null) {
                cola.add(actual.getIzquierdo());
            }

            if (actual.getDerecho() != null) {
                cola.add(actual.getDerecho());
            }
        }

        System.out.println();
    }

    // CONTAR CÓDIGOS DENTRO DE UN RANGO INCLUSIVO

    public int contarEquiposEnRango(int inferior, int superior) {

        if (inferior > superior) {
            return 0;
        }

        return contarEquiposEnRangoRecursivo(
                raiz, inferior, superior
        );
    }

    private int contarEquiposEnRangoRecursivo(
            NodoArbol actual, int inferior, int superior) {

        if (actual == null) {
            return 0;
        }

        int codigo = actual.getCodigo();

        if (codigo < inferior) {
            // Todo lo que está a la izquierda también es menor.
            return contarEquiposEnRangoRecursivo(
                    actual.getDerecho(), inferior, superior
            );
        }

        if (codigo > superior) {
            // Todo lo que está a la derecha también es mayor.
            return contarEquiposEnRangoRecursivo(
                    actual.getIzquierdo(), inferior, superior
            );
        }

        // El código está dentro del rango.
        return 1
                + contarEquiposEnRangoRecursivo(
                actual.getIzquierdo(), inferior, superior
        )
                + contarEquiposEnRangoRecursivo(
                actual.getDerecho(), inferior, superior
        );
    }

    // ELIMINAR UN NODO

    public void eliminar(int codigo) {
        raiz = eliminarRecursivo(raiz, codigo);
    }

    private NodoArbol eliminarRecursivo(
            NodoArbol actual, int codigo) {

        if (actual == null) {
            return null;
        }

        if (codigo < actual.getCodigo()) {

            actual.setIzquierdo(
                    eliminarRecursivo(actual.getIzquierdo(), codigo)
            );

        } else if (codigo > actual.getCodigo()) {

            actual.setDerecho(
                    eliminarRecursivo(actual.getDerecho(), codigo)
            );

        } else {

            // Caso 1: el nodo no tiene hijos.
            if (actual.getIzquierdo() == null
                    && actual.getDerecho() == null) {
                return null;
            }

            // Caso 2: el nodo solo tiene hijo derecho.
            if (actual.getIzquierdo() == null) {
                return actual.getDerecho();
            }

            // Caso 2: el nodo solo tiene hijo izquierdo.
            if (actual.getDerecho() == null) {
                return actual.getIzquierdo();
            }

            // Caso 3: el nodo tiene dos hijos.
            // Se utiliza el sucesor inorden.
            NodoArbol sucesor = encontrarMinimo(actual.getDerecho());

            actual = new NodoArbol(sucesor.getCodigo());

            actual.setIzquierdo(
                    eliminarRecursivoPorSucesor(
                            raiz, sucesor.getCodigo()
                    )
            );
        }

        return actual;
    }

    // Eliminar el sucesor de su posición original.
    private NodoArbol eliminarRecursivoPorSucesor(
            NodoArbol actual, int codigo) {

        if (actual == null) {
            return null;
        }

        if (codigo < actual.getCodigo()) {

            actual.setIzquierdo(
                    eliminarRecursivoPorSucesor(
                            actual.getIzquierdo(), codigo
                    )
            );

        } else if (codigo > actual.getCodigo()) {

            actual.setDerecho(
                    eliminarRecursivoPorSucesor(
                            actual.getDerecho(), codigo
                    )
            );

        } else {

            if (actual.getIzquierdo() == null) {
                return actual.getDerecho();
            }

            if (actual.getDerecho() == null) {
                return actual.getIzquierdo();
            }
        }

        return actual;
    }

    // ENCONTRAR EL MENOR NODO DE UN SUBÁRBOL

    private NodoArbol encontrarMinimo(NodoArbol actual) {

        if (actual.getIzquierdo() == null) {
            return actual;
        }

        return encontrarMinimo(actual.getIzquierdo());
    }

    public NodoArbol getRaiz() {
        return raiz;
    }
}
