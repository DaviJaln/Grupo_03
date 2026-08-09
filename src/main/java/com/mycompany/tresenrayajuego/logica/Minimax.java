/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tresenrayajuego.logica;
import com.mycompany.tresenrayajuego.estructuras.Tree;
import com.mycompany.tresenrayajuego.modelo.Tablero;
import java.util.ArrayList;
/**
 *
 * @author pycca
 */
public class Minimax {
    
    //Crearemos un metodo que podamos analizar el minimax, estado desde el cual
    //la computadora empieza a pensar
    public int[] obtenerMejorMovimiento(Tablero tableroActual,char simboloComputadora,char simboloOponente){
       //validamos si no esta lleno el arbol o si gano uno de los dos
       
       if(tableroActual.isFull()||tableroActual.isWinner(simboloOponente)||tableroActual.isWinner(simboloComputadora)){
           return null;
       }
       
        Tree<Tablero> arbolEstados = new Tree<>(tableroActual);
        //Generamos los posibles estados de acuerdo a lo que sea para la computadora.
        ArrayList<Tablero> estados = tableroActual.generateStates(simboloComputadora);
        /*estados nos devolverá las posibilidades de cada tablero
        Es decir, que a partir de la jugada podriamos generar n cantidad
        de movimientos posibles, donde n es un numero que va a depender netamente
        de cuan lleno este el tablero.
        */
        
        for (Tablero estado : estados){
            Tree<Tablero> hijo = new Tree<>(estado);
            //Este arbol tendrá las posibilidades de la configuracion del tablero
            arbolEstados.getRoot().addChild(hijo);
            
        } 
        //Recorreremos cada posible jugada que generó la computadora
        for(Tree<Tablero> hijo: arbolEstados.getRoot().getChildren()){
            //Sacamos el tablero que está guardado dentro de ese hijo.
            Tablero tableroHijo = hijo.getRoot().getContent();
            

            //Si la computadora ya ganó con esta jugada, no tiene sentido generar una respuesta del oponente
            if(tableroHijo.isWinner(simboloComputadora)){
                continue;
            }
            
            //si el tablero quedó lleno, es empate y ya no se puede hacer nada 
            if(tableroHijo.isFull()){
                continue;
            }

            //Aqui generamos un arreglo que respondera segun lo que hay en ese tablero hijo
            //Algo como si la computadora hicierta esto, que podría hacer el jugador despues
            ArrayList<Tablero> respuestasOponente= tableroHijo.generateStates(simboloOponente);
            //Recorreremos cada respuesta posible
            for(Tablero respuesta:respuestasOponente){
                //Conectamos eso debajo de la jugada de la computadora.
                Tree<Tablero> nieto = new Tree<>(respuesta);
                hijo.getRoot().addChild(nieto);
            }
            
        }
        //Guardaremos el mejor valor encontrado entre las utilidades minima.
        int utilidadMaxima = Integer.MIN_VALUE;
        //Esta lista nos ayudará a guardar arboles cuyos contenidos sean los tableros generados.
        ArrayList<Tree<Tablero>> mejoresJugadas = new ArrayList<>();
        
        //recorremos cada hijo
        for(Tree<Tablero> hijo: arbolEstados.getRoot().getChildren()){
           
            Tablero tableroHijo = hijo.getRoot().getContent();
            
            //SI el hijo da la victoria de la compu, devoldemos la pos donde se produjo esa victoria
            //Si el hijo es hoja pq el tablero esta lleno, evaluamos el tablero en lugar de intentar
            //buscar alguna respuesta(victoria) que no existe
            
            if(tableroHijo.isWinner(simboloComputadora)){
                return obtenerMovimiento(tableroActual,tableroHijo);
                
            }
            //verificamos si el hijo es hoja, entonces analizamos la utilidad
            int utilidadMinima;
            if(hijo.isLeaf()){
                utilidadMinima = tableroHijo.calculateUtility(simboloComputadora);
                
            }else{ //si no es, entonces podemos recorrer para ver cual es la utilidad.
                utilidadMinima = Integer.MAX_VALUE;
                for(Tree<Tablero> respuesta: hijo.getRoot().getChildren()){
                    Tablero tableroRespuesta = respuesta.getRoot().getContent();
                    //sacamos la utilidad de ese tablero
                    int utilidad = tableroRespuesta.calculateUtility(simboloComputadora);
                    //cambiamos de utilidad si es menor la anteriormente claculada
                    if(utilidad<utilidadMinima){
                        utilidadMinima = utilidad;
                    }
                }
               
            }
            if(utilidadMinima>utilidadMaxima){
                utilidadMaxima = utilidadMinima;
                mejoresJugadas.clear();
                mejoresJugadas.add(hijo);
                
            }else if(utilidadMinima == utilidadMaxima){
                mejoresJugadas.add(hijo);
            }
        }
        
        int mejorPrioridad = Integer.MIN_VALUE; //Comparamor prioridades
        int [] movimientoElegido = null;
        for(Tree<Tablero> jugada : mejoresJugadas){ //recorremos las jugadas de minimax.
            Tablero estadoJugada = jugada.getRoot().getContent();
            int[] movimiento = obtenerMovimiento(tableroActual,estadoJugada); //comparamos tablero y el estado de jugada, q casilla cambio
            int prioridad = prioridadMovimiento(movimiento[0],movimiento[1]); //sacamos la prioridad de esa casilla
            if(prioridad>mejorPrioridad){ //comparamos las prioridades
                mejorPrioridad = prioridad;
                movimientoElegido = movimiento; 
            }
        }
        return movimientoElegido;
    }
    //Compararemos los tableros con el original para identificar que posicion podria ser la nueva jugada
   
    private int[]obtenerMovimiento(Tablero original, Tablero estado){
        for (int fila = 0; fila<Tablero.tamanio;fila++){ //recorremos cada fila de cualquier tablero
            for(int columna = 0; columna<Tablero.tamanio; columna++){
                if(original.obtenerCasilla(fila,columna)!= estado.obtenerCasilla(fila, columna)){
                    return new int[]{fila,columna};
                }
            }
            
        }
        return null;
            
    }
    
    //en caso de que algun movimiento tenga la misma utilidad, se define
    //prioridades segun la posicion de donde es mejor
    //centro, esquina o borde.
    private int prioridadMovimiento(int fila, int columna){
        if(fila==1&&columna==1){
            return 3;
        }
        /*Analizaremos filas y columnas
        y esta mas que nada será analizada para ver si es una esquina, por eso el &&
        [0][0]             [0][2]
        [2][0]             [2][2]
        
        */
        if((fila==0||fila==Tablero.tamanio-1)&&(columna==0||columna == Tablero.tamanio-1)){
            return 2; 
        }
        return 1;
    }
    
    
}
