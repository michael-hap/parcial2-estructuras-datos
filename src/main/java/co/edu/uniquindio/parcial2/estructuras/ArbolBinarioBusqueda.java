package co.edu.uniquindio.parcial2.estructuras;

import java.util.LinkedList;
import java.util.Queue;

public class ArbolBinarioBusqueda {

    private NodoArbol raiz;

    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    // INSERTAR UN CÓDIGO

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

        // Si el código ya existe, no se vuelve a insertar.
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

    // CONTAR CÓDIGOS DENTRO DE UN RANGO

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

        // Si es menor que el límite inferior,
        // no es necesario revisar su subárbol izquierdo.
        if (codigo < inferior) {
            return contarEquiposEnRangoRecursivo(
                    actual.getDerecho(), inferior, superior
            );
        }

        // Si es mayor que el límite superior,
        // no es necesario revisar su subárbol derecho.
        if (codigo > superior) {
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

    // ELIMINAR UN CÓDIGO

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

            // Caso 1 y 2: el nodo no tiene hijo izquierdo
            // o solamente tiene hijo derecho.
            if (actual.getIzquierdo() == null) {
                return actual.getDerecho();
            }

            // Caso 2: solamente tiene hijo izquierdo.
            if (actual.getDerecho() == null) {
                return actual.getIzquierdo();
            }

            // Caso 3: tiene dos hijos.
            // Se busca el sucesor inorden.
            NodoArbol sucesor = encontrarMinimo(actual.getDerecho());

            actual.setCodigo(sucesor.getCodigo());

            // Se elimina el sucesor de su posición original.
            actual.setDerecho(
                    eliminarRecursivo(
                            actual.getDerecho(), sucesor.getCodigo()
                    )
            );
        }

        return actual;
    }

    // ENCONTRAR EL MENOR CÓDIGO DE UN SUBÁRBOL

    private NodoArbol encontrarMinimo(NodoArbol actual) {

        if (actual.getIzquierdo() == null) {
            return actual;
        }

        return encontrarMinimo(actual.getIzquierdo());
    }

    // OBTENER LA RAÍZ

    public NodoArbol getRaiz() {
        return raiz;
    }
}
