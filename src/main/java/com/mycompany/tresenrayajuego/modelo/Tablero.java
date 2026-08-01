/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tresenrayajuego.modelo;

/**
 *
 * @author pycca
 */
public class Tablero {
    public static final int tamanio = 3; 
    public static final char vacio = ' ';
    private final char[][] casillas;
    public Tablero(){
        this.casillas= new char[tamanio][tamanio];
        for(int fila = 0; fila<tamanio; fila++){
            for (int columna = 0; columna<tamanio;columna++){
                casillas[fila][columna] = vacio; 
                        
            }
        }
    }

    /*Haremos un metodo que valide si se ingresara
    correctamente la posicion del tablero, mas que nada
    verificar si es valida o no
    */
    public boolean posicionValida(int fila, int columna){
        return fila>=0 && fila<tamanio && columna>=0 && columna<tamanio;
    }
    /* Crearemos un metodo para consultar una casilla
    sin tratar de dejar tan expuesta la matriz, como el atributo es private
    otras clases no podran acceder directamente a ella:D
    */
    public char obtenerCasilla(int fila, int columna){
        if(!posicionValida(fila,columna)){
            throw new IllegalArgumentException("Posicion fuera del tablero");
        }
        return casillas[fila][columna];
        
    }
    //validamos si la casilla esta vacia con un metodo para poder
    // evitar escribir de nuevo columnas[1][2]==vacio,
    //queremos que nuestro trabajo sea lo más limpio posible y ser organizados.
    public boolean isEmpty(int fila, int columna){
        return obtenerCasilla(fila,columna)==vacio;
    }
    
    //Crearemos el metodo para poner X , O segun quiera el usuario
    //Chequee que usamos ya metodos anteriores, asi evitamos escribir tanto codigo
    //y podemos ahorrarnos daño visual con tanta cosa xd. 
    public boolean colocarSimbolo(int fila, int columna, char simbolo){
        if(!posicionValida(fila,columna)){
            return false;
        }
        if(!isEmpty(fila,columna)){
            return false;
        }
        if(simbolo!='X'&&simbolo!='O'){
            return false;
        }
        casillas[fila][columna]=simbolo;
        return true;
    }
    
    
    
    
}
