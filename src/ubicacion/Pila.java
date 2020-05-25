/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubicacion;

import gui.Celda;
import java.util.Stack;

/**
 *
 * @author memol
 */
public class Pila extends Stack{
    private String palabra;
    private Direccion direccion;
    private int elementos;

    public Pila(){
        this.palabra = "";
    }

    @Override
    public Object push(Object item) {
        if(item instanceof Celda){
            Celda c = (Celda)item;
            this.palabra += c.getText();
            if(!this.isEmpty() && direccion == null){
                definirDireccion(c);
            }
            c.setSelected(true);
            elementos++;
            return super.push(item);
        }
        return null;
    }

    @Override
    public synchronized Object pop() {
        Celda aux = (Celda)super.peek();
        aux.setBackground(null);
        aux.setSelected(false);
        palabra = palabra.substring(0,palabra.length()-1);
        return super.pop();
    }

    @Override
    public synchronized void removeAllElements() {
        for(Object celda: this){
           ((Celda)celda).setSelected(false);
        }
        this.direccion = null;
        this.palabra = "";
        super.removeAllElements(); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void definirDireccion(Celda celda){
      int iniX = ((Celda)this.get(0)).getFila();
      int iniY = ((Celda)this.get(0)).getColumna();
      int finX = celda.getFila();
      int finY = celda.getColumna();
      if(iniX == finX){
          if(iniY>finY) direccion = Direccion.IZQ;
          else direccion = Direccion.DER;
      }else if (iniY == finY){
          if(iniX>finX) direccion = Direccion.ARRIBA;
          else direccion = Direccion.ABAJO;
      }else if(iniX>finX){
          if(iniY>finY) direccion = Direccion.ARRIBA_IZQ;
          else direccion = Direccion.ARRIBA_DER;
      }else{
          if(iniY>finY) direccion = Direccion.ABAJO_IZQ;
          else direccion = Direccion.ABAJO_DER;
      }
    }
    
    public boolean verificarDireccion(Celda celda){
        if(isEmpty() || direccion == null)
            return true;
        int finX = ((Celda)this.peek()).getFila();
        int finY = ((Celda)this.peek()).getColumna();
        switch(direccion){
            case ARRIBA_IZQ: return finX==celda.getFila()+1 && finY==celda.getColumna()+1;
            case ARRIBA: return finY==celda.getColumna();
            case ARRIBA_DER: return finX==celda.getFila()+1 && finY==celda.getColumna()-1;
            case IZQ: return finX==celda.getFila() && finY==celda.getColumna()+1; 
            case DER: return finX==celda.getFila() && finY==celda.getColumna()-1; 
            case ABAJO_IZQ: return finX==celda.getFila()-1 && finY==celda.getColumna()+1;
            case ABAJO: return finY==celda.getColumna();
            case ABAJO_DER: return finX==celda.getFila()-1 && finY==celda.getColumna()-1;
            default: return true;
        }
    }
    
    public boolean isContinuo(Celda celda){
        if(isEmpty()) return true;
        int X = ((Celda)this.peek()).getFila();
        int Y = ((Celda)this.peek()).getColumna();
        for(int i = X-1;i <= X+1;i++){
          for(int j = Y-1;j <= Y+1;j++){
               if(i == celda.getFila() && j == celda.getColumna())
                   return true;
          }  
        }
        return false;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    

    public Direccion getDireccion() {
        return direccion;
    }
    
    public String getPalabra() {
        return palabra;
    }
    
}
