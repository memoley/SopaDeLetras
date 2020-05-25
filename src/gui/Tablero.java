/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import listeners.TableroListener;
import ubicacion.Pila;
import ubicacion.Solucionador;

/**
 *
 * @author memol
 */
public class Tablero extends JPanel{
    private Celda[][] celdas;
    private String[] palabras;
    private Color pincel;
    private Celda foco;
    private Pila pila;
    private boolean visible;
    
    private TableroListener listener;
    
    private String[][] letras;

    public Tablero(String[][] letras,String[] palabras,int filas,int columnas){
        super.setLayout(new GridLayout(filas,columnas));
        this.palabras = palabras;
        this.letras = letras;
        this.visible = false;
        setPincel();
        this.pila = new Pila();
        this.celdas = new Celda[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda(letras[i][j],i,j);
                
            }
        }
        Solucionador.solucionar(palabras,celdas);
        for(Celda[] fila:celdas){
            for(Celda celda:fila){
                celda.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(celda.isSelected()){
                            if(celda==pila.peek()){
                                pila.pop();
                                if(!pila.isEmpty())
                                    foco = (Celda)pila.peek();
                                else
                                    foco = null;
                                if(pila.isEmpty())
                                   pila.setDireccion(null);
                                else if(pila.firstElement()==pila.peek())
                                    pila.setDireccion(null);
                            }
                        }else{
                            if(pila.verificarDireccion(celda) && pila.isContinuo(celda)){
                                celda.setBackground(pincel);
                                   pila.push(celda);
                                if(pila.peek() == celda)
                                  celda.setBackground(pincel);
                                if(foco!=null)
                                    foco = (Celda)pila.peek();
                                verificar();
                            }
                        }
                    }
                });
                super.add(celda);
            }
        }
    }
    
    private void verificar(){
        for(String word:palabras){
            if(pila.getPalabra().equals(word)){
                System.out.println(pila.getPalabra()+" VERIFICADA");
                setPincel();
                listener.tacharPalabra(pila.getPalabra());
                pila.removeAllElements();
                
            }
        }
            
    }
    
    private void setPincel(){
        int R = (int)(Math.random()*256);
        int G = (int)(Math.random()*256);
        int B = (int)(Math.random()*256);
        this.pincel = new Color(R, G, B);
    }
    
    public void bloqueo(){
        for(Celda[] fila:celdas){
            for(Celda celda:fila){
                celda.setEnabled(false);
            }
        }
    }

    public void addTableroListener(TableroListener listener) {
        this.listener = listener;
    }
    
    public void mostrarSolucion(){
        for(Celda[] fila:celdas){
            for(Celda celda:fila){
                if(celda.isRespuesta()){
                    if(visible) celda.setForeground(Color.BLACK);
                    else celda.setForeground(Color.RED);
                }
            }
        }
        this.visible =(visible ? false:true);
    }
    
}
