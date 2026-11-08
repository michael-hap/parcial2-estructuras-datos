package co.edu.uniquindio.parcial2.ejercicio3;

public class Paquete {

    private String codigo;
    private TipoEntrega tipo;
    private int peso;

    public Paquete(String codigo, TipoEntrega tipo, int peso) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código no puede estar vacío");
        }

        if (tipo == null) {
            throw new IllegalArgumentException("El tipo es obligatorio");
        }

        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser positivo");
        }

        this.codigo = codigo;
        this.tipo = tipo;
        this.peso = peso;
    }

    public String getCodigo() {
        return codigo;
    }

    public TipoEntrega getTipo() {
        return tipo;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return codigo + " | " + tipo + " | " + peso + " kg";
    }
}
