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
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author memol
 */
public class MenuPrincipal extends JFrame implements ActionListener{
     private JButton facil;
    private JButton medio;
    private JButton dificil;
    
    public MenuPrincipal(){
        super("Sopa de letras");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLayout(new GridLayout(1,2));
        super.setBounds(0,0,500,450);
        super.setLocationRelativeTo(null);
        JPanel panelBtns = new JPanel();
        panelBtns.setLayout(new GridLayout(3,1));
        facil = inicilizarBoton("FACIL", new Color(3,221,27));
        medio = inicilizarBoton("MEDIO", new Color(2,161,235));
        dificil = inicilizarBoton("DIFICIL", new Color(247,3,80));
        panelBtns.add(facil);
        panelBtns.add(medio);
        panelBtns.add(dificil);
        JLabel jLabel = new JLabel(instrucciones());
        super.add(panelBtns);
        super.add(jLabel);
        super.setVisible(true);
        facil.addActionListener(this);
        medio.addActionListener(this);
        dificil.addActionListener(this);
    }
    
    
    
    private JButton inicilizarBoton(String txt,Color color){
        JButton jButton = new JButton(txt);
        jButton.setBackground(color);
        jButton.setForeground(Color.WHITE);
        return jButton;
    }
    
    private String instrucciones(){
        String texto = "<html><body>INSTRUCCIONES<br><br><p style=\"color:red;\">SELECCIONAR</p>"
                + "Debes de ir haciendo click en cada casilla que conforma la palabra,desde su comienzo hasta su final en una sola dirección.<br>"
                + "<br><p style=\"color:red;\">RESPUESTA CORRECTA</p>"
                + "Cuando las casillas seleccionadas hacen una palabra a buscar, la palabra se hará de color gris claro en el tablero, y nuestro seleccionador cambiará de color.<br>"
                + "<br><p style=\"color:red;\">ELIMINAR</p>"
                + "Si se quiere eliminar completa o particial la palabra, siempre se deberá ir presionando el último cuadro seleccionado.<br>"
                + "<br><p style=\"color:red;\">Boton SOLUCIÓN</p>"
                + "Cuando se presiona, nos muestra las palabras en la sopa de letras, ubicadas con el color de texto rojo.<br>"
                +"</body></html>";

        return texto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(facil)){
            Juego juego = new Juego("../sopas/Sopa.txt");
        }else if(e.getSource().equals(medio)){
            Juego juego = new Juego("../sopas/Paises.txt");
        }else{
            Juego juego = new Juego("../sopas/Literatura.txt");
        }
    }
    
}
