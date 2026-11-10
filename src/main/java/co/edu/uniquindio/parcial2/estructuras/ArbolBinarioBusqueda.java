package co.edu.uniquindio.parcial2.estructuras;

public class ArbolBinarioBusqueda {

    private NodoArbol raiz;

    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    public void insertar(int codigo) {
        raiz = insertarRecursivo(raiz, codigo);
    }

    private NodoArbol insertarRecursivo(NodoArbol actual, int codigo) {
        if (actual == null) {
            return new NodoArbol(codigo);
        }

        if (codigo < actual.getCodigo()) {
            actual.setIzquierdo(insertarRecursivo(actual.getIzquierdo(), codigo));
        } else if (codigo > actual.getCodigo()) {
            actual.setDerecho(insertarRecursivo(actual.getDerecho(), codigo));
        }

        return actual;
    }

    public void recorridoInorden() {
        recorridoInordenRecursivo(raiz);
        System.out.println();
    }

    private void recorridoInordenRecursivo(NodoArbol actual) {
        if (actual == null) return;

        recorridoInordenRecursivo(actual.getIzquierdo());
        System.out.print(actual.getCodigo() + " ");
        recorridoInordenRecursivo(actual.getDerecho());
    }

    public void recorridoPreorden() {
        recorridoPreordenRecursivo(raiz);
        System.out.println();
    }

    private void recorridoPreordenRecursivo(NodoArbol actual) {
        if (actual == null) return;

        System.out.print(actual.getCodigo() + " ");
        recorridoPreordenRecursivo(actual.getIzquierdo());
        recorridoPreordenRecursivo(actual.getDerecho());
    }

    public void recorridoPostorden() {
        recorridoPostordenRecursivo(raiz);
        System.out.println();
    }

    private void recorridoPostordenRecursivo(NodoArbol actual) {
        if (actual == null) return;

        recorridoPostordenRecursivo(actual.getIzquierdo());
        recorridoPostordenRecursivo(actual.getDerecho());
        System.out.print(actual.getCodigo() + " ");
    }

    // Recorrido por niveles utilizando nuestra propia Cola.
    public void recorridoPorNiveles() {
        if (raiz == null) return;

        Cola<NodoArbol> cola = new Cola<>();
        cola.encolar(raiz);

        while (!cola.estaVacia()) {
            NodoArbol actual = cola.desencolar();

            System.out.print(actual.getCodigo() + " ");

            if (actual.getIzquierdo() != null) {
                cola.encolar(actual.getIzquierdo());
            }

            if (actual.getDerecho() != null) {
                cola.encolar(actual.getDerecho());
            }
        }

        System.out.println();
    }

    public int contarEquiposEnRango(int inferior, int superior) {
        if (inferior > superior) return 0;

        return contarEquiposEnRangoRecursivo(raiz, inferior, superior);
    }

    private int contarEquiposEnRangoRecursivo(
            NodoArbol actual, int inferior, int superior) {

        if (actual == null) return 0;

        int codigo = actual.getCodigo();

        if (codigo < inferior) {
            return contarEquiposEnRangoRecursivo(
                    actual.getDerecho(), inferior, superior);
        }

        if (codigo > superior) {
            return contarEquiposEnRangoRecursivo(
                    actual.getIzquierdo(), inferior, superior);
        }

        return 1
                + contarEquiposEnRangoRecursivo(
                actual.getIzquierdo(), inferior, superior)
                + contarEquiposEnRangoRecursivo(
                actual.getDerecho(), inferior, superior);
    }

    public void eliminar(int codigo) {
        raiz = eliminarRecursivo(raiz, codigo);
    }

    private NodoArbol eliminarRecursivo(NodoArbol actual, int codigo) {
        if (actual == null) return null;

        if (codigo < actual.getCodigo()) {
            actual.setIzquierdo(
                    eliminarRecursivo(actual.getIzquierdo(), codigo));
        } else if (codigo > actual.getCodigo()) {
            actual.setDerecho(
                    eliminarRecursivo(actual.getDerecho(), codigo));
        } else {
            if (actual.getIzquierdo() == null) {
                return actual.getDerecho();
            }

            if (actual.getDerecho() == null) {
                return actual.getIzquierdo();
            }

            NodoArbol sucesor = encontrarMinimo(actual.getDerecho());

            actual.setCodigo(sucesor.getCodigo());

            actual.setDerecho(
                    eliminarRecursivo(actual.getDerecho(), sucesor.getCodigo()));
        }

        return actual;
    }

    private NodoArbol encontrarMinimo(NodoArbol actual) {
        if (actual.getIzquierdo() == null) {
            return actual;
        }

        return encontrarMinimo(actual.getIzquierdo());
    }

    // Mostrar el árbol con ramas.
    public void mostrarArbol() {
        if (raiz == null) {
            System.out.println("El arbol esta vacio.");
            return;
        }

        mostrarArbolRecursivo(raiz, "", true, "RAIZ: ");
    }

    private void mostrarArbolRecursivo(
            NodoArbol actual, String prefijo,
            boolean esUltimo, String etiqueta) {

        if (actual == null) return;

        System.out.println(
                prefijo
                        + (esUltimo ? "└── " : "├── ")
                        + etiqueta
                        + actual.getCodigo()
        );

        String nuevoPrefijo = prefijo + (esUltimo ? "    " : "│   ");

        if (actual.getDerecho() != null && actual.getIzquierdo() != null) {

            mostrarArbolRecursivo(
                    actual.getDerecho(), nuevoPrefijo, false, "D: ");

            mostrarArbolRecursivo(
                    actual.getIzquierdo(), nuevoPrefijo, true, "I: ");

        } else if (actual.getDerecho() != null) {

            mostrarArbolRecursivo(
                    actual.getDerecho(), nuevoPrefijo, true, "D: ");

        } else if (actual.getIzquierdo() != null) {

            mostrarArbolRecursivo(
                    actual.getIzquierdo(), nuevoPrefijo, true, "I: ");
        }
    }

    public NodoArbol getRaiz() {
        return raiz;
    }
}
