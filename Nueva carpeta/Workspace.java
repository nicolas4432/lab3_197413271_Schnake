

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Una clase que representa a la zona del Workspace.
 * Contiene todos los archivos con los que se esta trabajando
 * @version 1.0, 11/08/20
 * @author Nicolas Schnake
 */

public class Workspace {
    //Atributos
    List<TextoArchivo> listaArchivos = new ArrayList<TextoArchivo>();       //Lista de archivos

    //Metodos
    /**
     * Metodo para agregar un archivo al workspace
     * @param nombre Nombre del archivo que se desea agregar
     * @param contenido Contenido que tendra el archivo
     */
    public void agregarArchivo(String nombre, String contenido){
        TextoArchivo archivo = new TextoArchivo();
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");                            
        archivo.datoSet(formatoFecha.format(fechaActual), nombre, contenido);
        listaArchivos.add(archivo);
    }
    /**
     * Metodo para obtener un archivo del workspace
     * @param numero Posicion del archivo que se desea obtener
     * @return Retorna el archivo de la posicion ingresada
     */
    public TextoArchivo devolverLista(int numero){
        return listaArchivos.get(numero);
    }
}
