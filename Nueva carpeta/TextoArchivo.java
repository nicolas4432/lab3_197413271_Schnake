

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Una clase que representa a un archivo dentro del repositorio.
 * Es un archivo que possee fecha de modificacion, nombre y contenido
 * @version 1.0, 11/08/20
 * @author Nicolas Schnake
 */

public class TextoArchivo {
        //Atributos
	String fecha;           //Fecha de creacion del archivo o modificacion
	String nombre;          //Nombre del archivo
        String contenido;       //Contenido del archivo
        
        //Metodos
        /**
         * Metodo Get para obetener datos del archivo
         * @param archivo Archivo del cual se quiere obtener el dato
         * @param dato String del nombre del dato que se requiere
         * @return Retorna un String con el dato solicitado 
         */
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
        /**
         * Metodo Set para asignarle valores a los atributos del archivo
         * @param fecha String con la fecha
         * @param nombre String con el nombre del archivo
         * @param contenido String con el contenido del archivo
         */
        public void datoSet(String fecha, String nombre, String contenido){
            this.fecha = fecha;
            this.nombre = nombre;
            this.contenido = contenido;
        }
}

