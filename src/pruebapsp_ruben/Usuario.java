/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebapsp_ruben;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 *
 * @author usuario5
 */
public class Usuario {
    
    private String nombre;
    private BufferedReader IN;
    private PrintWriter OUT;

    public Usuario() {
    }

    public Usuario(BufferedReader IN, PrintWriter OUT) {
        initNombre();
        this.IN = IN;
        this.OUT = OUT;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BufferedReader getIN() {
        return IN;
    }

    public void setIN(BufferedReader IN) {
        this.IN = IN;
    }

    public PrintWriter getOUT() {
        return OUT;
    }

    public void setOUT(PrintWriter OUT) {
        this.OUT = OUT;
    }  
    
    public void initNombre(){
        String[] nicks = {"Ruben","Juan","Ismael","Manolo","Miguel","Cristian","Samuel","Javier","Francisco","German"};
        
        int i = (int)(Math.random()*9);
        
        nombre=nicks[i];
    }
    
}
