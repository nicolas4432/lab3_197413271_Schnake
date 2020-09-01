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
public class Workspace {
    //Atributos
    List<TextoArchivo> listaArchivos = new ArrayList<TextoArchivo>();

    //Metodos
    public void agregarArchivo(String nombre, String contenido){
        TextoArchivo archivo = new TextoArchivo();
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");                            
        archivo.datoSet(formatoFecha.format(fechaActual), nombre, contenido);
        listaArchivos.add(archivo);
    }
    
    public TextoArchivo devolverLista(int numero){
        return listaArchivos.get(numero);
    }
}
