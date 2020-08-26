/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nico4
 */
public class Commit {
        //Atributos
    	String autor;
	String comentario;
        String fecha;
        List<TextoArchivo> listaArchivos = new ArrayList<TextoArchivo>();
        List<TextoArchivo> diferencias = new ArrayList<TextoArchivo>();
        
        // Constructor
        public Commit(String autor, String comentario) {
            Date fechaActual = new Date();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
            this.fecha = formatoFecha.format(fechaActual);
            this.autor = autor;
            this.comentario = comentario;
        }
}
