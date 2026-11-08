package co.edu.uniquindio.parcial2.estructuras;

public class NodoArbol {

    private int codigo;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol(int codigo) {
        this.codigo = codigo;
        this.izquierdo = null;
        this.derecho = null;
    }

    public int getCodigo() {
        return codigo;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }
}
