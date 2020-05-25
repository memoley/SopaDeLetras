/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubicacion;

import gui.Celda;

/**
 *
 * @author memol
 */
public abstract class Solucionador {
    private static Celda[][] Celdas;
    private static String laPalabra;
    private static String palabraFormada;
    private static boolean bandera;
    private static Direccion direccion;
    
    public static final void solucionar(String[] words,Celda[][] celdas){
        Celdas = celdas;
        palabraFormada="";
        direccion = null;
        for(int x = 0;x<words.length;x++){
            String palabra = words[x];
            laPalabra = palabra;
            bandera = false;
            for(Celda[] fila:celdas){
                for(Celda celda:fila){
                    if(celda.getText().equals(palabra.substring(0,1))){
                        expandirCeldas(celda,1);
                        direccion = null;
                        
                    }
                    if(bandera) break;
                }
                if(bandera) break;
            }
           palabraFormada = "";
        }
    }
    
    private static void expandirCeldas(Celda celda,int in){
        celda.setSelected(true);
        palabraFormada+=celda.getText();
        if(laPalabra.equals(palabraFormada)){
            System.out.println(laPalabra+" ENCONTRADA!!!!");
            bandera = true;
            celda.setRespuesta(true);
             celda.setSelected(false);
            return;
        }
        int i = celda.getFila(),j = celda.getColumna();
        //System.out.println(laPalabra.substring(in,in+1));
        for(int x =(i-1);x<=(i+1);x++){
           for(int y =(j-1);y<=(j+1);y++){
               if(isValido(x,y)){
                   if(Celdas[x][y].getText().equals(laPalabra.substring(in,in+1))){
                       if(direccion == null) definirDireccion(celda, Celdas[x][y]);
                       else if(in == 1) definirDireccion(celda, Celdas[x][y]);
                       if(!Celdas[x][y].isSelected() && verificarDireccion(celda,Celdas[x][y])){
                        expandirCeldas(Celdas[x][y],++in);in--;
                       }
                   }
               }
               if(bandera)break;
            }
           if(bandera) break;
        }
        if(!bandera){
            palabraFormada = palabraFormada.substring(0,palabraFormada.length()-1);
            //if(!celda.isRespuesta())celda.setForeground(Color.BLACK); 
        }else celda.setRespuesta(true);
        celda.setSelected(false);
    }
    
    private static boolean isValido(int i,int j){
        return i>=0 && j>=0 && i<Celdas.length && j<Celdas[0].length;
    }
    
    private static void definirDireccion(Celda origen,Celda celda){
      int iniX = origen.getFila();
      int iniY = origen.getColumna();
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
    
    public static boolean verificarDireccion(Celda origen,Celda celda){
        int finX = origen.getFila();
        int finY = origen.getColumna();
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
    
}
