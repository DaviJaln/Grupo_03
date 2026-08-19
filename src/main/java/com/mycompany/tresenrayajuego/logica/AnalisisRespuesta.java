/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tresenrayajuego.logica;

import com.mycompany.tresenrayajuego.modelo.Tablero;

/**
 *
 * @author pycca
 */
public class AnalisisRespuesta {

    private Tablero tablero;
    private int utilidad;

    public AnalisisRespuesta(Tablero tablero, int utilidad) {

        this.tablero = tablero;
        this.utilidad = utilidad;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getUtilidad() {
        return utilidad;
    }
}
