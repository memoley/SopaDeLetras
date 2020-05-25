/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import listeners.TableroConfigListener;
import listeners.TableroListener;

/**
 *
 * @author memol
 */
public class Juego extends JFrame{
    public  String[] words;
    private String[][] sopa;
    private Tablero tablero;
    private PalabrasPanel panel;
    private TableroConfig tablconfig;
    private int filas;
    private int columnas;
    private int palabras;
    
    public Juego(String file){
        super("Sopa de letras");
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.setBounds(0,0,900,700);
        super.setLocationRelativeTo(null);
        leerSopa(file);
        tablero = new Tablero(sopa,words,filas,columnas);
        panel = new PalabrasPanel(words);
        tablconfig = new TableroConfig();
        tablero.addTableroListener(new TableroListener() {
            @Override
            public void tacharPalabra(String palabra) {
                panel.tacharPalabra(palabra);
                if(panel.getPalabrasEncontrada()==words.length)
                    tablero.bloqueo();
            }
        });
        tablconfig.addTableroConfigListener(new TableroConfigListener() {
            @Override
            public void mostrarPalabras() {
                tablero.mostrarSolucion();
            }

            @Override
            public void salir() {
                dispose();
            }
        });
        JScrollPane tbl = new JScrollPane(tablero);
        super.add(tbl,BorderLayout.CENTER);
        super.add(panel,BorderLayout.EAST);
        super.add(tablconfig,BorderLayout.SOUTH);
        super.setVisible(true);
    }
    
    private void leerSopa(String file){
         Scanner scanner = null; 
         String line;
        scanner = new Scanner(getClass().getResourceAsStream(file));
        for(int i = 1;i<=3;i++){
            switch(i){
                case 1: this.filas = scanner.nextInt();
                        break;
                case 2: this.columnas = scanner.nextInt();
                        break;
                case 3: this.palabras = scanner.nextInt();             
            }  
        }
        words = new String[palabras];
        sopa = new String[filas][columnas];
        scanner.nextLine();
        for(int i = 0;i<palabras;i++){
            line = scanner.nextLine();
            words[i] = line;
        }
        for(int i = 0;i<filas;i++){
            line = scanner.nextLine();
            for(int j = 0;j<columnas;j++){
                sopa[i][j] = line.substring(j,j+1);
            }
        }
    }
    
}
