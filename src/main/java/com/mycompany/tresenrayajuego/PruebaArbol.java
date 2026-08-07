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

        // Tablero que estará en la raíz
        Tablero tableroPadre = new Tablero();
        tableroPadre.colocarSimbolo(1, 1, 'X');

        // Creamos el árbol principal
        Tree<Tablero> arbol = new Tree<>(tableroPadre);

        // Creamos dos posibles estados hijos
        Tablero tableroHijo1 = tableroPadre.copy();
        tableroHijo1.colocarSimbolo(0, 0, 'O');

        Tablero tableroHijo2 = tableroPadre.copy();
        tableroHijo2.colocarSimbolo(0, 2, 'O');

        // Cada tablero hijo se convierte en otro árbol
        Tree<Tablero> hijo1 = new Tree<>(tableroHijo1);
        Tree<Tablero> hijo2 = new Tree<>(tableroHijo2);

        // Agregamos ambos hijos a la raíz
        arbol.getRoot().addChild(hijo1);
        arbol.getRoot().addChild(hijo2);

        System.out.println("TABLERO RAIZ");
        arbol.getRoot().getContent().mostrarTablero();

        System.out.println("\nCantidad de hijos: "
                + arbol.getRoot().getChildren().size());

        System.out.println("\nHIJO 1");
        arbol.getRoot()
                .getChildren()
                .get(0)
                .getRoot()
                .getContent()
                .mostrarTablero();

        System.out.println("\nHIJO 2");
        arbol.getRoot()
                .getChildren()
                .get(1)
                .getRoot()
                .getContent()
                .mostrarTablero();

        System.out.println("\nEl arbol padre es hoja?: "
                + arbol.isLeaf());

        System.out.println("El hijo 1 es hoja?: "
                + hijo1.isLeaf());
        System.out.println("\nPRUEBA DE GENERACION AUTOMATICA DE HIJOS");

        // Estado actual
        Tablero tableroActual = new Tablero();

        tableroActual.colocarSimbolo(0, 0, 'X');
        tableroActual.colocarSimbolo(1, 1, 'O');
        
        System.out.println("TABLERO ACTUAL");
        tableroActual.mostrarTablero();

        // Creamos el árbol cuya raíz contiene el tablero actual
        Tree<Tablero> arbolEstados = new Tree<>(tableroActual);

        // Generamos todos los posibles estados para X
        ArrayList<Tablero> estados = tableroActual.generateStates('X');

        // Cada estado se convierte en un árbol hijo
        for (Tablero estado : estados) {

            Tree<Tablero> hijo = new Tree<>(estado);

            arbolEstados.getRoot().addChild(hijo);
        }

        System.out.println("\nCantidad de hijos generados: "
            + arbolEstados.getRoot().getChildren().size());

        // Mostramos todos los hijos
        for (Tree<Tablero> hijo : arbolEstados.getRoot().getChildren()) {

            System.out.println("\nHIJO");

            hijo.getRoot().getContent().mostrarTablero();
            }
        for (Tree<Tablero> hijo : arbolEstados.getRoot().getChildren()) {

        Tablero tableroHijo = hijo.getRoot().getContent();

        ArrayList<Tablero> respuestasOponente = tableroHijo.generateStates('O');

        for (Tablero respuesta : respuestasOponente) {

            Tree<Tablero> nieto = new Tree<>(respuesta);

            hijo.getRoot().addChild(nieto);
            }
        }
        System.out.println("\nSEGUNDO NIVEL DEL ARBOL");

        int totalRespuestas = 0;

        for (Tree<Tablero> hijo :
            arbolEstados.getRoot().getChildren()) {

            int cantidad = hijo.getRoot().getChildren().size();

            System.out.println("Este hijo tiene "+ cantidad + " respuestas de O");

            totalRespuestas += cantidad;
        }

        System.out.println("Total de estados del segundo nivel: "+ totalRespuestas);
        System.out.println("\nUTILIDADES MINIMAS POR FAMILIA");

        int numeroFamilia = 1;

        for (Tree<Tablero> hijo : arbolEstados.getRoot().getChildren()) {

            int utilidadMinima = Integer.MAX_VALUE;

            System.out.println("\nFamilia " + numeroFamilia);

            for (Tree<Tablero> respuesta :
                hijo.getRoot().getChildren()) {

                    Tablero tableroRespuesta =
                    respuesta.getRoot().getContent();

                    int utilidad = tableroRespuesta.calculateUtility('X');

                    System.out.println("Utilidad: " + utilidad);

                    if (utilidad < utilidadMinima) {
                        utilidadMinima = utilidad;
                    }
                }

                System.out.println("Utilidad minima de la familia: "+ utilidadMinima);

                numeroFamilia++;
            }
    }
}
