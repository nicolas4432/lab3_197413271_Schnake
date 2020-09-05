
package com.mycompany.lab;

import java.util.ArrayList;
import java.util.List;

/**
 * Una clase que representa la zona Index del repositorio.
 * Contiene todos los archivos que fueron agregados del Workspace
 * @version 1.0, 11/08/20
 * @author Nicolas Schnake
 */

public class Index {
    //Atributos
    List<TextoArchivo> listaArchivos = new ArrayList<TextoArchivo>();   //Lista de atchivos

    //Metodos
    /**
     * Metodo para agregar archivos al index
     * @param archivo Archivo que ser agrega al lista de index
     */
    public void agregarArchivoI(TextoArchivo archivo){
        listaArchivos.add(archivo);
    }    
}
