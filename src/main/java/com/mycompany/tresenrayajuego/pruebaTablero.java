/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tresenrayajuego;
import  com.mycompany.tresenrayajuego.modelo.Tablero;
import java.util.ArrayList;


/**
 *
 * @author pycca
 */
public class pruebaTablero {
    public static void main(String[] args) {

       Tablero tablero = new Tablero();

        System.out.println("Tablero inicial:");
        tablero.mostrarTablero();

        System.out.println("\nColocar X en [0][0]: "
                + tablero.colocarSimbolo(0, 0, 'X'));

        System.out.println("Colocar O en [1][1]: "
                + tablero.colocarSimbolo(1, 1, 'O'));

        System.out.println("Intentar ocupar nuevamente [0][0]: "
                + tablero.colocarSimbolo(0, 0, 'O'));

        System.out.println("Intentar colocar un simbolo invalido: "
                + tablero.colocarSimbolo(0, 1, 'A'));

        System.out.println("Intentar usar una posicion invalida: "
                + tablero.colocarSimbolo(3, 0, 'X'));

        System.out.println("\nTablero final:");
        tablero.mostrarTablero();
        System.out.println("\nPRUEBA DE VICTORIA POR FILA");

 // PRUEBA 1: VICTORIA POR FILA
        System.out.println("PRUEBA DE VICTORIA POR FILA");

        Tablero tableroFila = new Tablero();

        tableroFila.colocarSimbolo(0, 0, 'X');
        tableroFila.colocarSimbolo(0, 1, 'X');
        tableroFila.colocarSimbolo(0, 2, 'X');

        tableroFila.mostrarTablero();

        System.out.println("X es ganador?: "
                + tableroFila.isWinner('X'));

        System.out.println();


        // PRUEBA 2: VICTORIA POR COLUMNA
        System.out.println("PRUEBA DE VICTORIA POR COLUMNA");

        Tablero tableroColumna = new Tablero();

        tableroColumna.colocarSimbolo(0, 1, 'O');
        tableroColumna.colocarSimbolo(1, 1, 'O');
        tableroColumna.colocarSimbolo(2, 1, 'O');

        tableroColumna.mostrarTablero();

        System.out.println("O es ganador?: "
                + tableroColumna.isWinner('O'));

        System.out.println();


        // PRUEBA 3: VICTORIA POR DIAGONAL PRINCIPAL
        System.out.println("PRUEBA DE DIAGONAL PRINCIPAL");

        Tablero tableroDiagonalPrincipal = new Tablero();

        tableroDiagonalPrincipal.colocarSimbolo(0, 0, 'X');
        tableroDiagonalPrincipal.colocarSimbolo(1, 1, 'X');
        tableroDiagonalPrincipal.colocarSimbolo(2, 2, 'X');

        tableroDiagonalPrincipal.mostrarTablero();

        System.out.println("X es ganador?: "
                + tableroDiagonalPrincipal.isWinner('X'));

        System.out.println();


        // PRUEBA 4: VICTORIA POR DIAGONAL SECUNDARIA
        System.out.println("PRUEBA DE DIAGONAL SECUNDARIA");

        Tablero tableroDiagonalSecundaria = new Tablero();

        tableroDiagonalSecundaria.colocarSimbolo(0, 2, 'O');
        tableroDiagonalSecundaria.colocarSimbolo(1, 1, 'O');
        tableroDiagonalSecundaria.colocarSimbolo(2, 0, 'O');

        tableroDiagonalSecundaria.mostrarTablero();
        
        System.out.println("O es ganador?: "
                + tableroDiagonalSecundaria.isWinner('O'));
        System.out.println("\nPRUEBA DE COPIA");
        
        System.out.println("\nPRUEBA DE COPIA");

        Tablero original = new Tablero();

        original.colocarSimbolo(0, 0, 'X');
        original.colocarSimbolo(1, 1, 'O');

        Tablero copia = original.copy();

        copia.colocarSimbolo(2, 1, 'O');

        System.out.println("Tablero original:");
        original.mostrarTablero();

        System.out.println("\nTablero copia:");
        copia.mostrarTablero();

        System.out.println("\nPRUEBA DE LINEAS DISPONIBLES");

        Tablero tableroUtilidad = new Tablero();

        tableroUtilidad.colocarSimbolo(0, 0, 'X');
        tableroUtilidad.colocarSimbolo(1, 0, 'O');

        tableroUtilidad.mostrarTablero();

        System.out.println("Lineas disponibles para X: "
        + tableroUtilidad.countAvailableLines('X'));

        System.out.println("Lineas disponibles para O: "
        + tableroUtilidad.countAvailableLines('O'));
        System.out.println("\nPRUEBA DE UTILIDAD");


        tableroUtilidad.colocarSimbolo(0, 0, 'X');
        tableroUtilidad.colocarSimbolo(1, 0, 'O');

        tableroUtilidad.mostrarTablero();

        System.out.println("Lineas disponibles X: "
        + tableroUtilidad.countAvailableLines('X'));

        System.out.println("Lineas disponibles O: "
            + tableroUtilidad.countAvailableLines('O'));

        System.out.println("Utilidad para X: "
        + tableroUtilidad.calculateUtility('X'));

        System.out.println("Utilidad para O: "
        + tableroUtilidad.calculateUtility('O'));
        System.out.println("\nPRUEBA DE GENERACION DE ESTADOS");

        Tablero tableroEstados = new Tablero();

        tableroEstados.colocarSimbolo(0, 0, 'X');
        tableroEstados.colocarSimbolo(1, 1, 'O');

        System.out.println("Tablero actual:");
        tableroEstados.mostrarTablero();

        ArrayList<Tablero> estados =
        tableroEstados.generateStates('X');

        System.out.println("\nCantidad de estados generados: "
        + estados.size());

        for (Tablero estado : estados) {
            System.out.println();
            estado.mostrarTablero();
        }
    }   
}
