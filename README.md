# Parcial II — Estructuras de Datos

**Universidad del Quindío**  
**Programa:** Ingeniería de Sistemas y Computación  
**Asignatura:** Estructuras de Datos  
**Entrega:** Parcial II — 2026-2  
**Repositorio:** https://github.com/michael-hap/parcial2-estructuras-datos

## Descripción del proyecto

Este repositorio contiene las implementaciones en Java de los ejercicios 1 a 4 del segundo parcial de Estructuras de Datos. Se emplean estructuras enlazadas propias para las operaciones principales con pilas y colas, y una implementación de árbol binario de búsqueda (ABB). El ejercicio 5 (árboles AVL y rojo-negro) se desarrolla manualmente en el documento de respuestas, conforme a las indicaciones del enunciado.

El objetivo es aplicar las reglas de cada estructura, preservar el orden de sus elementos cuando corresponda y mostrar los procedimientos y resultados de las pruebas solicitadas.

## Tecnologías y requisitos

- **Lenguaje:** Java 21.
- **Gestor del proyecto:** Apache Maven (`pom.xml`).
- **IDE sugerido:** IntelliJ IDEA (también puede utilizarse otro IDE compatible con Maven).
- **Dependencia declarada:** JUnit Jupiter 5.11.4, para futuras pruebas automatizadas. La versión revisada del repositorio no incluye clases de prueba en `src/test/java`.

## Estructura del código

```text
src/main/java/co/edu/uniquindio/parcial2/
├── estructuras/
│   ├── Nodo.java
│   ├── Pila.java
│   ├── Cola.java
│   ├── NodoArbol.java
│   └── ArbolBinarioBusqueda.java
├── ejercicio1/
│   ├── Caja.java
│   ├── GestionCajas.java
│   ├── EvaluadorExpresiones.java
│   ├── PrincipalEjercicio1.java
│   └── PruebaPila.java
├── ejercicio2/
│   ├── GestionHistorial.java
│   └── PrincipalEjercicio2.java
├── ejercicio3/
│   ├── Paquete.java
│   ├── TipoEntrega.java
│   ├── SelectorPaquetes.java
│   └── PrincipalEjercicio3.java
└── ejercicio4/
    └── PrincipalEjercicio4.java
```

## Ejercicio 1 — Retiro de cajas y evaluación de expresiones

**Método principal:** `GestionCajas.retirarCaja(Pila<Caja> pila, String codigo)`.

Cada caja posee un código y un valor entero. El método busca la caja por código y la retira. Luego clasifica **las posiciones** de las cajas restantes, contadas desde la cima después del retiro: si el valor de la caja retirada es par, las posiciones impares van arriba; si es impar, van arriba las posiciones pares. Se conserva el orden relativo de las cajas dentro de cada grupo. Se utilizan pilas propias auxiliares.

Se ejecutan los retiros `C3`, `C4` y `C7` consecutivamente sobre la misma pila. El resultado final, de cima a fondo, es:

```text
C1:2 -> C6:9 -> C2:4 -> C5:3
```

Después se calculan los valores `A = 2`, `B = 4.5`, `C = 6` y `D = 18`, a partir de las cuatro cajas restantes. La expresión se evalúa usando una pila propia de números `Double`:

```text
Infija:   (A+B)*C-(A-B)*D
Postfija: A B + C * A B - D * -
Resultado: 84.0
```

La clase `EvaluadorExpresiones` evalúa la secuencia postfija ya suministrada y muestra en consola el elemento procesado, la operación y el estado de la pila (de fondo a cima). **La conversión infija a postfija se presenta como procedimiento en el documento de respuestas; el programa no implementa un conversor general.**

**Comprobaciones incluidas:** tres retiros exitosos, búsqueda de `C99` (retorna `false` y conserva la pila) y retiro sobre una pila vacía (retorna `false`).

## Ejercicio 2 — Obtener un segmento del historial

**Método principal:** `GestionHistorial.obtenerSegmento(Pila<T> original, int inicio, int fin)`.

Se recibe una pila genérica y un rango de posiciones inclusivo, contado desde la cima. Se usa una pila auxiliar para invertir temporalmente la secuencia y reconstruir la original mientras se construye una nueva pila con el segmento requerido. El contenido y el orden de la pila original se conservan. Para rangos inválidos se retorna una pila vacía.

```text
Original:       A6 -> A5 -> A4 -> A3 -> A2 -> A1
Segmento (2,4): A5 -> A4 -> A3
Segmento (3,3): A4
```

**Comprobaciones incluidas:** rangos `(2,4)`, `(3,3)`, `(0,3)`, `(5,2)`, `(2,8)`, `(1,6)` y `(6,6)`, además del caso de pila vacía. La clase principal imprime también la pila original para comprobar que su orden se conserva.

## Ejercicio 3 — Selección de paquetes para una jornada

**Método principal:** `SelectorPaquetes.seleccionarPaquetes(Cola<Paquete> pendientes, int capacidadDisponible)`.

Se utiliza la cola propia `Cola<T>`. Los paquetes se consideran primero por prioridad **URGENTE** y después **NORMAL**, respetando el orden de registro dentro de cada tipo. Si un paquete cabe en la capacidad restante, se selecciona; si no cabe, permanece pendiente y se continúa con el siguiente. Los pendientes conservan el orden de llegada original.

El procesamiento y la reorganización se llevan a cabo mediante métodos recursivos (`clasificarPaquetes`, `restaurarCola`, `procesarPaquetes` y `reconstruirPendientes`), sin ciclos `for`, `while` o `do-while` en esos métodos.

**Prueba con capacidad de 14 kg:**

```text
Seleccionados: P2 (7 kg), P4 (5 kg), P7 (2 kg)
Pendientes:    P1 (4 kg), P3 (3 kg), P5 (6 kg), P6 (4 kg)
Capacidad restante: 0 kg
```

La clase principal muestra la cola inicial, la evaluación de los paquetes y las colas resultantes.

## Ejercicio 4 — Árbol binario de búsqueda (ABB)

**Estructura propia:** `ArbolBinarioBusqueda`, apoyada en `NodoArbol`.

La implementación permite insertar códigos enteros sin duplicados, contar recursivamente los códigos dentro de un rango inclusivo, recorrer el árbol en inorden, preorden, postorden y por niveles, mostrar su estructura y eliminar nodos. El conteo por rango aprovecha el orden del ABB para descartar subárboles que no pueden contener códigos válidos.

**Datos insertados, en orden:**

```text
41, 20, 52, 16, 6, 13, 12, 60, 22, 47, 50, 60, 43, 44
```

El código repetido `60` no se agrega por segunda vez.

**Resultados de la ejecución:**

```text
Inorden:   6 12 13 16 20 22 41 43 44 47 50 52 60
Preorden:  41 20 16 6 13 12 22 52 47 43 44 50 60
Postorden: 12 13 6 16 22 20 44 43 50 47 60 52 41
Niveles:   41 20 52 16 22 47 60 6 43 50 13 44 12
Rango [12,50]: 10 equipos
```

Al eliminar `41`, la implementación lo reemplaza por su **sucesor inorden, `43`**. Los dibujos del ABB original y del resultante de la eliminación deben incorporarse a mano en el documento de respuestas.

**Nota de implementación:** el recorrido por niveles utiliza `java.util.Queue` y `java.util.LinkedList`; las demás operaciones del ABB se apoyan en nodos propios y recursividad. Conviene confirmar con el docente si también exige una cola propia para ese recorrido, debido a la condición general de utilizar estructuras propias.

## Ejercicio 5 — Árboles AVL y rojo-negro (solución manual)

El enunciado solicita:

1. Insertar en un AVL, en el orden dado: `alma, beso, azul, cine, eco, dedo, bota, hilo, lago, isla, nube, pato, oro, mapa, gato`.
2. Mostrar los árboles de cada paso, los **factores de equilibrio (FE)** y todas las rotaciones realizadas.
3. Eliminar `cine`; si el nodo tiene dos hijos, reemplazarlo por el **mayor elemento de su subárbol izquierdo** y reequilibrar cuando sea necesario.
4. Obtener el recorrido postorden del AVL resultante y utilizar esa secuencia para construir un árbol rojo-negro, documentando inserciones, colores y correcciones.

Este ejercicio **no cuenta con una clase Java** en el repositorio revisado. Sus árboles y procedimientos se entregan como dibujos a mano, mediante fotografías o imágenes claras dentro del documento de respuestas.

## Ejecución del proyecto

### Desde IntelliJ IDEA

1. Clonar o abrir este repositorio.
2. Configurar **JDK 21** como SDK del proyecto.
3. Abrir `pom.xml` como proyecto Maven y permitir la descarga de dependencias.
4. Ejecutar por separado el método `main` de cada clase `PrincipalEjercicio1`, `PrincipalEjercicio2`, `PrincipalEjercicio3` y `PrincipalEjercicio4`.

### Desde terminal

Para compilar el proyecto:

```bash
mvn clean compile
```

Para ejecutar cada ejercicio con Maven, usando el plugin `exec`:

```bash
mvn exec:java -Dexec.mainClass=co.edu.uniquindio.parcial2.ejercicio1.PrincipalEjercicio1
mvn exec:java -Dexec.mainClass=co.edu.uniquindio.parcial2.ejercicio2.PrincipalEjercicio2
mvn exec:java -Dexec.mainClass=co.edu.uniquindio.parcial2.ejercicio3.PrincipalEjercicio3
mvn exec:java -Dexec.mainClass=co.edu.uniquindio.parcial2.ejercicio4.PrincipalEjercicio4
```

`mvn test` puede utilizarse para verificar la compilación, pero en el estado revisado **no hay archivos de pruebas JUnit**. Las comprobaciones descritas arriba están implementadas en las clases principales y se ejecutan desde consola.

## Evidencias y entrega

El **documento de respuestas** complementa este repositorio e incluye los procedimientos y resultados de los cinco ejercicios, las tablas solicitadas y fotografías legibles de los árboles realizados a mano. En particular, la evaluación postfija del ejercicio 1 debe mostrar el elemento procesado, la operación y el estado de la pila después de cada paso.

Según las indicaciones del parcial:

- La entrega se realiza **únicamente por Google Classroom** y la envía **un solo integrante**.
- Se debe adjuntar el documento de soluciones y el enlace a este repositorio, verificando que el docente pueda acceder.
- La fecha límite indicada es el **martes 10 de noviembre de 2026 a las 11:59 p. m. (hora de Colombia)**.
- **Todos los integrantes** deben poder explicar, probar y modificar las soluciones de los ejercicios, pues la sustentación aplica un factor multiplicador de **0 a 1** a la calificación del parcial.

## Trabajo colaborativo

El desarrollo se organizó en un repositorio compartido con ramas de trabajo:

- `ejercicios-1-2-5`: ejercicios 1 y 2 (Java) y desarrollo manual del ejercicio 5.
- `ejercicios-3-4`: ejercicios 3 y 4 (Java).
- `main`: integración de las implementaciones revisadas.

Las contribuciones individuales pueden consultarse en el historial de commits y en las ramas del repositorio. Los nombres completos y las identificaciones de los integrantes se registran en el documento oficial de entrega.

---

**Repositorio:** https://github.com/michael-hap/parcial2-estructuras-datos
