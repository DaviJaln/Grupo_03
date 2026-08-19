/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tresenrayajuego;

import com.mycompany.tresenrayajuego.logica.Partida;

public class PruebaPartida {

    public static void main(String[] args) {

        Partida partida =
                new Partida('X', 'O', true);

        // Fila 0
        partida.getTablero().colocarSimbolo(0, 0, 'X');
        partida.getTablero().colocarSimbolo(0, 1, 'O');
        partida.getTablero().colocarSimbolo(0, 2, 'X');

        // Fila 1
        partida.getTablero().colocarSimbolo(1, 0, 'X');
        partida.getTablero().colocarSimbolo(1, 1, 'O');
        partida.getTablero().colocarSimbolo(1, 2, 'O');

        // Fila 2
        partida.getTablero().colocarSimbolo(2, 0, 'O');
        partida.getTablero().colocarSimbolo(2, 1, 'X');
        partida.getTablero().colocarSimbolo(2, 2, 'X');

        System.out.println("TABLERO DE PRUEBA");

        partida.getTablero().mostrarTablero();

        System.out.println(
                "\nTablero lleno?: "
                + partida.getTablero().isFull());

        System.out.println(
                "Partida terminada?: "
                + partida.partidaTerminada());

        System.out.println(
                "Empate?: "
                + partida.esEmpate());

        char ganador =
                partida.obtenerGanador();

        if (ganador == ' ') {
            System.out.println(
                    "No existe ganador.");
        } else {
            System.out.println(
                    "Ganador: " + ganador);
        }
    }
}