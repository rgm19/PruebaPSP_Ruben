/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebapsp_ruben;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author usuario5
 */
public class HiloServidor implements Runnable {
    
    static int cont=1;
    Socket con;
    ArrayList<Usuario> misUsuarios;
    BufferedReader IN;
    PrintWriter OUT;
    Usuario user;
    int ncli;
    boolean bandera=true;

    public HiloServidor(Socket con, ArrayList<Usuario> misUsuarios, int ncli) {
        this.con = con;
        this.misUsuarios = misUsuarios;
        this.ncli = ncli;
    }  
//------------------------------------------------------------------------------
    @Override
    public void run(){
        
        if(cont<=3){
        
            try(
               BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
               PrintWriter out = new PrintWriter(con.getOutputStream(),true);

           ){
               IN=in;
               OUT=out;
               String cad="";
               generarUsuario();
               mostrarComandos();
               while(true){
                   cad=IN.readLine();
                   comprobarComando(cad);
                   if(bandera==false){break;}
                   enviarMensaje(cad);
               }
                borrarUsuario();

           }catch(Exception e){
               System.err.println("Error -> "+e.getMessage());
           }
        
      }else{
        OUT.println("El chat esta al maximo de su capacidad!");
        OUT.println("Por favor intente conectarse mas tarde :)");
      }
    }
//------------------------------------------------------------------------------
    private void enviarMensaje(String cad) {
        for(Usuario aux: misUsuarios){
            if(!aux.equals(user)){
                aux.getOUT().println("["+user.getNombre()+"]> "+cad);
            }
        }
    }
//------------------------------------------------------------------------------
    private void generarUsuario() {
        boolean control=true;
        
        do{
            user= new Usuario(IN,OUT);
            for(Usuario aux: misUsuarios){
                
                if(aux.getNombre().equals(user.getNombre())){
                    control=false;
                }else{
                    control=true;
                }
            }
            
        }while(!control);
        
        
        misUsuarios.add(user);
        System.out.println(user.getNombre()+" Conectado");
        cont++;
    }
//------------------------------------------------------------------------------
    private void borrarUsuario() {
        System.out.println(user.getNombre()+" Desconectado");
        misUsuarios.remove(user);
        
        if(cont<=1){}else{cont--; }
           
    }
//------------------------------------------------------------------------------
    private void mostrarComandos() {
        OUT.println("---------------------------------------------");
        OUT.println("|         Version 1.0 Chat Escolar          |");  
        OUT.println("|                 COMANDOS                  |");
        OUT.println("|-------------------------------------------|");
        OUT.println("|   \\h -> Version y comandos               |");
        OUT.println("|   \\l -> Listado de Clientes              |");
        OUT.println("|   \\q -> Salir                            |");
        OUT.println("---------------------------------------------");      
    }
//------------------------------------------------------------------------------
    private void comprobarComando(String cad) {
        int op=0;
         
        if(cad.equals("\\h"))op=1;
        if(cad.equals("\\l"))op=2;
        if(cad.equals("\\q"))op=3;
        
        switch(op){
            
            case 1:
                mostrarComandos();
                break;
            case 2:
                listadoUsuarios();
                break;
            case 3:
                bandera=false;
                break;
            default:
                break;
        }
    }
//------------------------------------------------------------------------------
    private void listadoUsuarios() {
        for(Usuario aux : misUsuarios){
            user.getOUT().println("- "+aux.getNombre());
        }
    }
//------------------------------------------------------------------------------
}
