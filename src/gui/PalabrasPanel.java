/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author memol
 */
public class PalabrasPanel extends JPanel{
    private JLabel[] palabras;
    private String[] words;
    private int palabrasEncontrada;
    
    public PalabrasPanel(String[] words) {
        super.setLayout(new GridLayout(words.length,1));
        this.words = words;
        this.palabrasEncontrada = 0;
        palabras = new JLabel[words.length];
        for(int i = 0;i < words.length;i++){
            palabras[i] = new JLabel(words[i]);
            super.add(palabras[i]);
        }
    }
    
    public void tacharPalabra(String palabra){
        for(JLabel etiqueta:palabras){
            if(etiqueta.getText().equals(palabra) && etiqueta.getBackground()!=Color.GRAY){
                etiqueta.setForeground(Color.GRAY);
                palabrasEncontrada++;
            }
        }
    }

    public int getPalabrasEncontrada() {
        return palabrasEncontrada;
    }
    
}
