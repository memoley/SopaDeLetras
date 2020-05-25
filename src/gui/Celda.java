/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JButton;

/**
 *
 * @author memol
 */
public class Celda extends JButton{
    private boolean selected;
    private int fila;
    private int columna;
    private boolean respuesta;

    public Celda(String text, int fila,int columna) {
        super(text);
        this.fila = fila;
        this.columna = columna;
        this.selected = false;
        this.respuesta = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }
    
}
