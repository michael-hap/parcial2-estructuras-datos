package co.edu.uniquindio.parcial2.ejercicio1;

import co.edu.uniquindio.parcial2.estructuras.Pila;

public class EvaluadorExpresiones {

    public static double[] calcularVariables(Pila<Caja> pila) {

        if (pila.tamanio() != 4) {
            throw new IllegalArgumentException(
                    "La pila debe contener exactamente cuatro cajas");
        }

        Pila<Caja> auxiliar = new Pila<>();
        int[] valores = new int[4];

        // Leer los valores desde la cima
        for (int i = 0; i < 4; i++) {
            Caja caja = pila.desapilar();
            valores[i] = caja.getValor();
            auxiliar.apilar(caja);
        }

        // Restaurar la pila original
        while (!auxiliar.estaVacia()) {
            pila.apilar(auxiliar.desapilar());
        }

        double A = valores[0];
        double B = (double) valores[1] / A;
        double C = valores[2] + A;
        double D = (valores[3] + C) * A;

        System.out.println("\n=== VALORES DE LAS VARIABLES ===");
        System.out.println("A = " + A);
        System.out.println("B = " + B);
        System.out.println("C = " + C);
        System.out.println("D = " + D);

        return new double[]{A, B, C, D};
    }

    public static double evaluarPostfija(String expresion, double[] variables) {

        Pila<Double> pila = new Pila<>();

        String[] elementos = expresion.split("\\s+");

        System.out.println("\n=== EVALUACION POSTFIJA ===");
        System.out.printf("%-12s %-30s %-20s%n",
                "Elemento", "Operacion", "Pila (fondo -> cima)");

        for (String elemento : elementos) {

            double valor;
            String operacion;

            switch (elemento) {
                case "A" -> {
                    valor = variables[0];
                    pila.apilar(valor);
                    operacion = "Apilar A";
                }
                case "B" -> {
                    valor = variables[1];
                    pila.apilar(valor);
                    operacion = "Apilar B";
                }
                case "C" -> {
                    valor = variables[2];
                    pila.apilar(valor);
                    operacion = "Apilar C";
                }
                case "D" -> {
                    valor = variables[3];
                    pila.apilar(valor);
                    operacion = "Apilar D";
                }
                case "+", "-", "*", "/" -> {
                    if (pila.tamanio() < 2) {
                        throw new IllegalArgumentException(
                                "Expresion postfija invalida");
                    }

                    double segundo = pila.desapilar();
                    double primero = pila.desapilar();

                    switch (elemento) {
                        case "+" -> valor = primero + segundo;
                        case "-" -> valor = primero - segundo;
                        case "*" -> valor = primero * segundo;
                        default -> {
                            if (segundo == 0) {
                                throw new ArithmeticException(
                                        "Division por cero");
                            }
                            valor = primero / segundo;
                        }
                    }

                    pila.apilar(valor);

                    operacion = primero + " " + elemento
                            + " " + segundo + " = " + valor;
                }
                default -> throw new IllegalArgumentException(
                        "Elemento desconocido: " + elemento);
            }

            System.out.printf("%-12s %-30s %-20s%n",
                    elemento, operacion, mostrarPila(pila));
        }

        if (pila.tamanio() != 1) {
            throw new IllegalArgumentException(
                    "La expresion postfija no es valida");
        }

        return pila.desapilar();
    }

    private static String mostrarPila(Pila<Double> pila) {

        Pila<Double> auxiliar = new Pila<>();
        String resultado = "[";

        while (!pila.estaVacia()) {
            auxiliar.apilar(pila.desapilar());
        }

        while (!auxiliar.estaVacia()) {
            double valor = auxiliar.desapilar();
            resultado += valor;

            if (!auxiliar.estaVacia()) {
                resultado += ", ";
            }

            pila.apilar(valor);
        }

        return resultado + "]";
    }
}
