/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebapsp_ruben;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author usuario5
 */
public class PruebaPSP_Ruben {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int puerto=21000;
        int cont=0;
        ArrayList<Usuario> misUsuarios = new ArrayList<Usuario>();
        System.out.println("+-----------------------------------------+");
        System.out.println("|-------      SERVIDOR PUBLICO     -------|");
        System.out.println("|-------           (V 1.0)         -------|");
        System.out.println("+-----------------------------------------+");
        
        
        
        try(
                ServerSocket con = new ServerSocket(puerto);
        ){
        
            HablaServidor habla = new HablaServidor(misUsuarios);
            Thread hilo2 = new Thread(habla);
            hilo2.start();
            
            
            while(true){
            HiloServidor h1 = new HiloServidor(con.accept(),misUsuarios,cont);
            Thread hilo = new Thread(h1);
            hilo.start();
            cont++;
            }
            
        }catch(Exception e){
        System.err.println("Error -> "+e.getMessage());
        }
        
        
    }
    
}
