/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nico4
 */
public class Index {
    //Atributos
    List<TextoArchivo> listaArchivos = new ArrayList<TextoArchivo>();

    //Metodos
    public void agregarArchivoI(TextoArchivo archivo){
        listaArchivos.add(archivo);
    }    
}
