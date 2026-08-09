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

        System.out.println("ESTADO INICIAL");

        partida.getTablero().mostrarTablero();

        System.out.println("Turno actual: "
                + partida.getTurnoActual());

        System.out.println("Partida terminada?: "
                + partida.partidaTerminada());


        System.out.println("\nJUGADA DEL HUMANO");

        partida.jugarHumano(0, 0);

        partida.getTablero().mostrarTablero();

        System.out.println("Turno actual: "
                + partida.getTurnoActual());

        System.out.println("Partida terminada?: "
                + partida.partidaTerminada());


        System.out.println("\nJUGADA DE LA COMPUTADORA");

        partida.jugarComputadora();

        partida.getTablero().mostrarTablero();

        System.out.println("Turno actual: "
                + partida.getTurnoActual());

        System.out.println("Partida terminada?: "
                + partida.partidaTerminada());


        System.out.println("\nSEGUNDA JUGADA DEL HUMANO");

        partida.jugarHumano(0, 1);

        partida.getTablero().mostrarTablero();

        System.out.println("Turno actual: "
                + partida.getTurnoActual());

        System.out.println("Partida terminada?: "
                + partida.partidaTerminada());

        System.out.println("Empate?: "
                + partida.esEmpate());

        char ganador = partida.obtenerGanador();

        if (ganador == ' ') {
            System.out.println("Todavia no existe ganador.");
        } else {
            System.out.println("Ganador: " + ganador);
        }
    }
}