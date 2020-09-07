

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Una clase que representa un commit.
 * Contiene a la zona Workspace, Index, Local y Remote repository
 * @version 1.0, 11/08/20
 * @author Nicolas Schnake
 */

public class Commit {
        //Atributos
    	String autor;                                                       //Nombre del autor del commit
	String comentario;                                                  //Comentario
        String fecha;                                                       //Fecha de creacion del commit
        List<TextoArchivo> listaArchivos = new ArrayList<TextoArchivo>();   //Lista de archivos
        List<TextoArchivo> diferencias = new ArrayList<TextoArchivo>();     //Lista de archivos con las diferencias respecto al commit anterior
        
        // Constructor
        /**
         * Inicializa las variables autor y comentario al crear un commit
         * @param autor String con el nombre del commit
         * @param comentario String comentario del commit
         */
        public Commit(String autor, String comentario) {
            Date fechaActual = new Date();                                      //Variable para general la fecha del commit
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
            this.fecha = formatoFecha.format(fechaActual);
            this.autor = autor;
            this.comentario = comentario;
        }
        
        
        
}
