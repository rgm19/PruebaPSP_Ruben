/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebapsp_ruben;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ruben
 */
public class HablaServidor implements Runnable{
    
    private ArrayList<Usuario> misUsuarios;
    Scanner teclado = new Scanner(System.in);

    public HablaServidor(ArrayList<Usuario> misUsuarios) {
        this.misUsuarios = misUsuarios;
    }
    
    @Override
    public void run(){
        String cerrar ="El servidor cierra sus comunicaciones, gracias!";
        String cad="";
        
        while(true){
            cad=teclado.nextLine();
                if(cad.equalsIgnoreCase("quit") || cad.equalsIgnoreCase("exit")){
                    mandarMensaje(cerrar);
                    System.exit(-1);
                }
            mandarMensaje(cad);
        }
    }

    private void mandarMensaje(String cad) {
        System.out.println("[***Servidor***]> "+cad);
        for(Usuario usu : misUsuarios){
            usu.getOUT().println("[***Servidor***]> "+cad);
        }
    }
    
    
    
}
