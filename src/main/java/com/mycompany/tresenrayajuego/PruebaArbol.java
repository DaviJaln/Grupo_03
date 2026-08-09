/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tresenrayajuego;

/**
 *
 * @author pycca
 */
import com.mycompany.tresenrayajuego.estructuras.Tree;
import com.mycompany.tresenrayajuego.modelo.Tablero;
import java.util.ArrayList;

public class PruebaArbol {

    public static void main(String[] args) {

        // Creamos un tablero inicial que servirá como raíz
        Tablero tableroPadre = new Tablero();

        // Colocamos una X en el centro
        tableroPadre.colocarSimbolo(1, 1, 'X');

        // Creamos un árbol cuya raíz contiene ese tablero
        Tree<Tablero> arbol = new Tree<>(tableroPadre);

        // Creamos una copia del tablero padre
        Tablero tableroHijo1 = tableroPadre.copy();

        // En la copia colocamos una O en [0][0]
        tableroHijo1.colocarSimbolo(0, 0, 'O');

        // Creamos otra copia independiente
        Tablero tableroHijo2 = tableroPadre.copy();

        // En esta segunda copia colocamos O en [0][2]
        tableroHijo2.colocarSimbolo(0, 2, 'O');

        // Convertimos cada tablero hijo en un subárbol
        Tree<Tablero> hijo1 = new Tree<>(tableroHijo1);
        Tree<Tablero> hijo2 = new Tree<>(tableroHijo2);

        // Agregamos ambos subárboles como hijos de la raíz
        arbol.getRoot().addChild(hijo1);
        arbol.getRoot().addChild(hijo2);

        // Mostramos el tablero contenido en la raíz
        System.out.println("TABLERO RAIZ");
        arbol.getRoot().getContent().mostrarTablero();

        // Mostramos cuántos hijos tiene la raíz
        System.out.println("\nCantidad de hijos: "
                + arbol.getRoot().getChildren().size());

        // Mostramos el tablero del primer hijo
        System.out.println("\nHIJO 1");
        arbol.getRoot()
                .getChildren()
                .get(0)
                .getRoot()
                .getContent()
                .mostrarTablero();

        // Mostramos el tablero del segundo hijo
        System.out.println("\nHIJO 2");
        arbol.getRoot()
                .getChildren()
                .get(1)
                .getRoot()
                .getContent()
                .mostrarTablero();

        // El árbol padre no debería ser hoja porque tiene hijos
        System.out.println("\nEl arbol padre es hoja?: "
                + arbol.isLeaf());

        // hijo1 sí debería ser hoja porque no tiene descendientes
        System.out.println("El hijo 1 es hoja?: "
                + hijo1.isLeaf());

        // ------------------------------------------------------------
        // PRUEBA DE GENERACIÓN AUTOMÁTICA DE ESTADOS
        // ------------------------------------------------------------

        System.out.println(
                "\nPRUEBA DE GENERACION AUTOMATICA DE HIJOS");

        // Creamos el estado actual del juego
        Tablero tableroActual = new Tablero();

        // Simulamos dos jugadas ya realizadas
        tableroActual.colocarSimbolo(0, 0, 'X');
        tableroActual.colocarSimbolo(1, 1, 'O');

        // Mostramos el estado actual
        System.out.println("TABLERO ACTUAL");
        tableroActual.mostrarTablero();

        // Creamos el árbol de estados
        // La raíz contiene el tablero actual
        Tree<Tablero> arbolEstados =
                new Tree<>(tableroActual);

        // Generamos todos los posibles movimientos de X
        // Como hay 7 casillas libres, se generan 7 estados
        ArrayList<Tablero> estados =
                tableroActual.generateStates('X');

        // Recorremos cada estado generado
        for (Tablero estado : estados) {

            // Convertimos cada estado en un subárbol
            Tree<Tablero> hijo =
                    new Tree<>(estado);

            // Lo agregamos como hijo de la raíz
            arbolEstados.getRoot().addChild(hijo);
        }

        // Mostramos la cantidad de hijos generados
        System.out.println(
                "\nCantidad de hijos generados: "
                + arbolEstados.getRoot()
                        .getChildren()
                        .size());

        // Recorremos los hijos del primer nivel
        for (Tree<Tablero> hijo :
                arbolEstados.getRoot().getChildren()) {

            System.out.println("\nHIJO");

            // Mostramos el tablero guardado en cada hijo
            hijo.getRoot()
                    .getContent()
                    .mostrarTablero();
        }

        // ------------------------------------------------------------
        // GENERACIÓN DEL SEGUNDO NIVEL
        // ------------------------------------------------------------

        // Recorremos cada posible movimiento de X
        for (Tree<Tablero> hijo :
                arbolEstados.getRoot().getChildren()) {

            // Obtenemos el tablero de ese hijo
            Tablero tableroHijo =
                    hijo.getRoot().getContent();

            // Generamos todas las posibles respuestas de O
            ArrayList<Tablero> respuestasOponente =
                    tableroHijo.generateStates('O');

            // Recorremos cada respuesta de O
            for (Tablero respuesta :
                    respuestasOponente) {

                // Convertimos la respuesta en otro subárbol
                Tree<Tablero> nieto =
                        new Tree<>(respuesta);

                // Lo agregamos como hijo del movimiento de X
                hijo.getRoot().addChild(nieto);
            }
        }

        System.out.println(
                "\nSEGUNDO NIVEL DEL ARBOL");

        // Variable para contar todas las respuestas de O
        int totalRespuestas = 0;

        // Recorremos los movimientos de X
        for (Tree<Tablero> hijo :
                arbolEstados.getRoot().getChildren()) {

            // Obtenemos cuántas respuestas de O tiene ese movimiento
            int cantidad =
                    hijo.getRoot()
                            .getChildren()
                            .size();

            System.out.println(
                    "Este hijo tiene "
                    + cantidad
                    + " respuestas de O");

            // Acumulamos el total
            totalRespuestas += cantidad;
        }

        // Debería dar 42
        System.out.println(
                "Total de estados del segundo nivel: "
                + totalRespuestas);

        // ------------------------------------------------------------
        // PARTE MIN DEL MINIMAX
        // ------------------------------------------------------------

        System.out.println(
                "\nUTILIDADES MINIMAS POR FAMILIA");

        // Sirve para numerar las familias
        int numeroFamilia = 1;

        // Empezamos con el valor entero más pequeño posible
        // porque vamos a buscar el máximo
        int utilidadMaxima = Integer.MIN_VALUE;

        // Aquí guardamos todas las familias que empaten
        // con la mejor utilidad
        ArrayList<Integer> mejoresFamilias =
                new ArrayList<>();

        // Recorremos cada posible movimiento de X
        for (Tree<Tablero> hijo :
                arbolEstados.getRoot().getChildren()) {

            // Para buscar el mínimo empezamos con el valor más grande
            int utilidadMinima =
                    Integer.MAX_VALUE;

            System.out.println(
                    "\nFamilia " + numeroFamilia);

            // Recorremos todas las posibles respuestas de O
            for (Tree<Tablero> respuesta :
                    hijo.getRoot().getChildren()) {

                // Obtenemos el tablero de esa respuesta
                Tablero tableroRespuesta =
                        respuesta.getRoot()
                                .getContent();

                // Calculamos la utilidad desde la perspectiva de X
                int utilidad =
                        tableroRespuesta
                                .calculateUtility('X');

                // Mostramos la utilidad calculada
                System.out.println(
                        "Utilidad: " + utilidad);

                // Si encontramos una utilidad menor,
                // actualizamos el mínimo de esta familia
                if (utilidad < utilidadMinima) {
                    utilidadMinima = utilidad;
                }
            }

            // Mostramos el peor resultado posible de esta familia
            System.out.println(
                    "Utilidad minima de la familia: "
                    + utilidadMinima);

            // --------------------------------------------------------
            // PARTE MAX DEL MINIMAX
            // --------------------------------------------------------

            // Si el mínimo de esta familia es mejor
            // que el mejor encontrado hasta ahora
            if (utilidadMinima > utilidadMaxima) {

                // Actualizamos la mejor utilidad
                utilidadMaxima =
                        utilidadMinima;

                // Las familias anteriores ya no son las mejores
                mejoresFamilias.clear();

                // Guardamos la nueva mejor familia
                mejoresFamilias.add(numeroFamilia);

            // Si esta familia empata con la mejor utilidad
            } else if (utilidadMinima
                    == utilidadMaxima) {

                // También la guardamos
                mejoresFamilias.add(numeroFamilia);
            }

            // Pasamos a la siguiente familia
            numeroFamilia++;
        }

        // Mostramos el máximo entre todos los mínimos
        System.out.println(
                "\nUTILIDAD MAXIMA ENTRE LOS MINIMOS: "
                + utilidadMaxima);

        // Mostramos las familias que empataron
        System.out.println(
                "Familias empatadas con la mejor utilidad: "
                + mejoresFamilias);

        // ------------------------------------------------------------
        // DESEMPATE
        // ------------------------------------------------------------

        System.out.println(
                "\nMOVIMIENTOS DE LAS MEJORES FAMILIAS");

        // Guarda la mayor prioridad encontrada
        int mejorPrioridad =
                Integer.MIN_VALUE;

        // Guarda el número de la familia elegida
        int familiaElegida = -1;

        // Guarda la posición [fila, columna] elegida
        int[] movimientoElegido = null;

        // Recorremos solamente las familias empatadas
        for (int familia : mejoresFamilias) {

            // Obtenemos el hijo correspondiente
            // Restamos 1 porque las listas empiezan en índice 0
            Tree<Tablero> hijo =
                    arbolEstados.getRoot()
                            .getChildren()
                            .get(familia - 1);

            // Obtenemos el tablero de ese hijo
            Tablero estadoHijo =
                    hijo.getRoot().getContent();

            // Descubrimos qué casilla cambió
            int[] movimiento =
                    obtenerMovimiento(
                            tableroActual,
                            estadoHijo);

            // Calculamos la prioridad de ese movimiento
            int prioridad =
                    prioridadMovimiento(
                            movimiento[0],
                            movimiento[1]);

            // Si esta prioridad supera a la mejor actual
            if (prioridad > mejorPrioridad) {

                // Actualizamos la prioridad
                mejorPrioridad = prioridad;

                // Guardamos la familia
                familiaElegida = familia;

                // Guardamos la posición elegida
                movimientoElegido = movimiento;
            }
        }

        // Recorremos de nuevo las familias empatadas
        // para mostrar qué movimiento representa cada una
        for (int familia : mejoresFamilias) {

            // Obtenemos el hijo correspondiente
            Tree<Tablero> hijo =
                    arbolEstados.getRoot()
                            .getChildren()
                            .get(familia - 1);

            // Obtenemos el tablero
            Tablero estadoHijo =
                    hijo.getRoot().getContent();

            // Encontramos el movimiento que diferencia
            // al tablero actual de este estado
            int[] movimiento =
                    obtenerMovimiento(
                            tableroActual,
                            estadoHijo);

            // Mostramos la familia y su posición
            System.out.println(
                    "Familia "
                    + familia
                    + " -> ["
                    + movimiento[0]
                    + "]["
                    + movimiento[1]
                    + "]");
        }

        // ------------------------------------------------------------
        // RESULTADO FINAL
        // ------------------------------------------------------------

        System.out.println(
                "\nRESULTADO DEL DESEMPATE");

        // Mostramos la familia elegida
        System.out.println(
                "Familia elegida: "
                + familiaElegida);

        // Mostramos la posición elegida
        System.out.println(
                "Movimiento elegido: ["
                + movimientoElegido[0]
                + "]["
                + movimientoElegido[1]
                + "]");
    }

    // ------------------------------------------------------------
    // MÉTODO obtenerMovimiento
    // ------------------------------------------------------------

    public static int[] obtenerMovimiento(
            Tablero original,
            Tablero estado) {

        // Recorremos todas las filas
        for (int fila = 0;
                fila < Tablero.tamanio;
                fila++) {

            // Recorremos todas las columnas
            for (int columna = 0;
                    columna < Tablero.tamanio;
                    columna++) {

                // Comparamos la misma casilla
                // en el tablero original y en el nuevo estado
                if (original.obtenerCasilla(fila, columna)
                        != estado.obtenerCasilla(fila, columna)) {

                    // Si son diferentes, esa fue la jugada realizada
                    return new int[]{
                        fila,
                        columna
                    };
                }
            }
        }

        // Si no encontramos ninguna diferencia,
        // no hubo movimiento
        return null;
    }

    // ------------------------------------------------------------
    // MÉTODO prioridadMovimiento
    // ------------------------------------------------------------

    public static int prioridadMovimiento(
            int fila,
            int columna) {

        // Si la posición es [1][1], es el centro
        // y recibe la prioridad más alta
        if (fila == 1 && columna == 1) {
            return 3;
        }

        // Si la fila está en un extremo
        // y la columna también está en un extremo,
        // la posición es una esquina
        if ((fila == 0
                || fila == Tablero.tamanio - 1)
                && (columna == 0
                || columna == Tablero.tamanio - 1)) {

            return 2;
        }

        // Cualquier otra posición es un borde
        return 1;
    }
}