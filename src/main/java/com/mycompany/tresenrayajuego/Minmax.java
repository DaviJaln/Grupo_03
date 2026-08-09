/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tresenrayajuego;

import com.mycompany.tresenrayajuego.logica.Minimax;
import com.mycompany.tresenrayajuego.modelo.Tablero;

/**
 *
 * @author pycca
 */
public class Minmax {

    public static void main(String[] args) {

        Tablero tablero = new Tablero();

        tablero.colocarSimbolo(0, 0, 'X');
        tablero.colocarSimbolo(0, 1, 'O');
        tablero.colocarSimbolo(0, 2, 'X');

        tablero.colocarSimbolo(1, 0, 'X');
        tablero.colocarSimbolo(1, 1, 'O');
        tablero.colocarSimbolo(1, 2, 'O');

        tablero.colocarSimbolo(2, 0, 'O');
        tablero.colocarSimbolo(2, 1, 'X');
        tablero.colocarSimbolo(2, 2, 'X');

        System.out.println("TABLERO ACTUAL");
        tablero.mostrarTablero();

        System.out.println("\nTablero lleno?: "
                + tablero.isFull());

        Minimax minimax = new Minimax();

        int[] movimiento =
                minimax.obtenerMejorMovimiento(
                        tablero,
                        'X',
                        'O');

        if (movimiento == null) {

            System.out.println(
                    "\nNo existe movimiento disponible.");

        } else {

            System.out.println(
                    "\nMOVIMIENTO ELEGIDO");

            System.out.println(
                    "[" + movimiento[0]
                    + "][" + movimiento[1] + "]");
        }
    }
}