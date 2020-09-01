/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nico4
 */
public class TextoArchivo {
	String fecha;
	String nombre;
        String contenido;
        
        // Constructor
        //public TextoArchivo(String nombre, String contenido) {
            //Date fechaActual = new Date();
            //SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
            //this.fecha = formatoFecha.format(fechaActual);
            //this.nombre = nombre;
            //this.contenido = contenido;
        //}
        
        //Metodos
        public String datoGet(TextoArchivo archivo, String dato){
            String devolvio = new String();
            switch(dato){
                case "nombre":
                    devolvio = archivo.nombre;
                    break;
                    
                case "fecha":
                    devolvio = archivo.fecha;
                    break;
                    
                case "contenido":
                    devolvio = archivo.contenido;
                    break;                    
            }
            return devolvio;
        }
        
        public void datoSet(String fecha, String nombre, String contenido){
            this.fecha = fecha;
            this.nombre = nombre;
            this.contenido = contenido;
        }
}

