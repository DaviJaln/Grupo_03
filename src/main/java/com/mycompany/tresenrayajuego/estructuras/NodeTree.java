/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tresenrayajuego.estructuras;

/**
 *
 * @author pycca
 */

import java.util.LinkedList;

public class NodeTree<E> {

    private E content;
    private final LinkedList<Tree<E>> children;

    public NodeTree(E content) {
        this.content = content;
        this.children = new LinkedList<>();
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public LinkedList<Tree<E>> getChildren() {
        return children;
    }

    public void addChild(Tree<E> hijo) {
        if (hijo != null) {
            children.add(hijo);
        }
    }
}