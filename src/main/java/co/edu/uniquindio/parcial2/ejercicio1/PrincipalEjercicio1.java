package co.edu.uniquindio.parcial2.ejercicio1;

import co.edu.uniquindio.parcial2.estructuras.Pila;

public class PrincipalEjercicio1 {

    public static void main(String[] args) {

        Pila<Caja> pila = new Pila<>();

        // Agregar las cajas en el orden indicado
        pila.apilar(new Caja("C1", 2));
        pila.apilar(new Caja("C2", 4));
        pila.apilar(new Caja("C3", 6));
        pila.apilar(new Caja("C4", 8));
        pila.apilar(new Caja("C5", 3));
        pila.apilar(new Caja("C6", 9));
        pila.apilar(new Caja("C7", 5));

        System.out.println("=== EJERCICIO 1: RETIRO DE CAJAS ===");

        System.out.println("\nPila inicial:");
        mostrarPila(pila);

        // Primer retiro
        boolean resultado1 = GestionCajas.retirarCaja(pila, "C3");
        System.out.println("\nRetirar C3: " + resultado1);
        mostrarPila(pila);

        // Segundo retiro
        boolean resultado2 = GestionCajas.retirarCaja(pila, "C4");
        System.out.println("\nRetirar C4: " + resultado2);
        mostrarPila(pila);

        // Tercer retiro
        boolean resultado3 = GestionCajas.retirarCaja(pila, "C7");
        System.out.println("\nRetirar C7: " + resultado3);
        mostrarPila(pila);

        System.out.println("\n=== PILA FINAL ===");
        mostrarPila(pila);


        // Calcular las variables de la expresion
        double[] variables =
                EvaluadorExpresiones.calcularVariables(pila);

        // Expresion original:
        // (A+B)*C - (A-B)*D

        // Expresion convertida a notacion postfija
        String postfija = "A B + C * A B - D * -";

        System.out.println("\nExpresion infija:");
        System.out.println("(A+B)*C-(A-B)*D");

        System.out.println("\nExpresion postfija:");
        System.out.println(postfija);

        // Evaluar utilizando nuestra pila propia
        double resultado =
                EvaluadorExpresiones.evaluarPostfija(
                        postfija, variables);

        System.out.println("\nRESULTADO FINAL: " + resultado);

        // Comprobar que la pila de cajas no fue modificada
        System.out.println("\nPila despues de evaluar:");
        mostrarPila(pila);


        // Prueba: retirar una caja inexistente
        System.out.println("\n=== CAJA INEXISTENTE ===");

        boolean resultado4 = GestionCajas.retirarCaja(pila, "C99");

        System.out.println("Retirar C99: " + resultado4);
        mostrarPila(pila);

        // Prueba: pila vacía
        System.out.println("\n=== PILA VACIA ===");

        Pila<Caja> pilaVacia = new Pila<>();

        boolean resultado5 = GestionCajas.retirarCaja(pilaVacia, "C1");

        System.out.println("Retirar C1 de pila vacia: " + resultado5);
        mostrarPila(pilaVacia);

    }

    // Mostrar sin modificar la pila original
    public static void mostrarPila(Pila<Caja> pila) {

        Pila<Caja> auxiliar = new Pila<>();

        System.out.print("Cima -> ");

        while (!pila.estaVacia()) {
            Caja caja = pila.desapilar();
            System.out.print(caja + " -> ");
            auxiliar.apilar(caja);
        }

        System.out.println("Fondo");

        // Restaurar la pila
        while (!auxiliar.estaVacia()) {
            pila.apilar(auxiliar.desapilar());
        }
    }

}
