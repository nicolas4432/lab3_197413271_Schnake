
package com.mycompany.lab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Una clase que representa a un repositorio con las 4 zonas.
 * Contiene a la zona Workspace, Index, Local y Remote repository
 * @version 1.0, 11/08/20
 * @author Nicolas Schnake
 */

public class Repo {
    //Atributos
    String autor = new String();                        //Nombre del autor
    String nombreR = new String();                      //Nombre del repositorio
    Workspace workspace = new Workspace();              //Objeto que representa al Workspace
    Index index = new Index();                          //Objeto que representa al Index
    RemoteR remote = new RemoteR();                     //Objeto que representa al Remote Repositori
    LocalR local = new LocalR();                        //Objeto que representa al Local Repositori
    
    //Metodos
    /**
     * Agrega crea y archivos a la zona del Workspace
     * @param repositorio Repositorio que se esta utilizando
     */
    public void addWork(Repo repositorio){
        Scanner in = new Scanner(System.in);                        //Variable para pedir datos por consola
        
        System.out.println("");
        //Se pide el nombre del archivo
        System.out.println("Introdusca nombre del archivo:");       
        String nombre = in.nextLine();
        //Se pide el contenido del archivo
        System.out.println("Ingrese el contenido:");                
        String contenido = in.nextLine();
        //Se agrega el archivo al Workspace
        repositorio.workspace.agregarArchivo(nombre, contenido);    
    }
    /**
     * Agrega archivos del Workspace al Index
     * @param repositorio Repositorio que se esta utilizando
     */
    public void add(Repo repositorio){
        Scanner in = new Scanner(System.in);                        //Variable para pedir datos por consola
        String nombreArchivo = "";                                  //Nombre del archivo
        int largoIndex = 0;                                         //Largo del Index
        int estabaEnIndex;                                          //Verificador si el archivo se encuentra en el index
        
        System.out.println("");
        System.out.println("Archivos actuales del Workspace:");
        
        //Se muestran todos los archivos del workspace
        for (TextoArchivo archivoEnLista : repositorio.workspace.listaArchivos){             
            System.out.println("-" + archivoEnLista.datoGet(archivoEnLista, "nombre"));
        } 
        System.out.println("Ingrese nombre archivos, para salir escribir 00");
        //Mientras no se ingrese la opcion de salida 00 se sigue ejecutando la funcion 
        while(!"00".equals(nombreArchivo)){
            nombreArchivo = in.nextLine();
            //Recoror cada archivo en la lista de index
            for (TextoArchivo archivoEnLista : repositorio.workspace.listaArchivos){
                estabaEnIndex = 0;
                //Si el archivo ingresado esta
                if(nombreArchivo.equals(archivoEnLista.datoGet(archivoEnLista, "nombre"))){
                    //Recorro los archivos del index 
                    for (TextoArchivo archivoEnListaI : repositorio.index.listaArchivos){
                       largoIndex = largoIndex + 1;  
                        //Si el archivo esta en index
                        if(nombreArchivo.equals(archivoEnListaI.datoGet(archivoEnListaI, "nombre"))){
                            estabaEnIndex = 1;
                            //y el contenido es distinto
                            if(!(archivoEnLista.datoGet(archivoEnLista, "contenido")).equals(archivoEnListaI.datoGet(archivoEnListaI, "contenido"))){
                                repositorio.index.listaArchivos.remove(archivoEnListaI);
                                //Creo nuevo archivo
                                TextoArchivo archivo = new TextoArchivo();                         
                                archivo.datoSet(archivoEnLista.datoGet(archivoEnLista, "fecha"), archivoEnLista.datoGet(archivoEnLista, "nombre"), archivoEnLista.datoGet(archivoEnLista, "contenido"));
                                //Y se agrega al index
                                repositorio.index.listaArchivos.add(archivo);
                                break;
                            }
                            break;
                        }
                    }
                    //Si el archivo no estaba en el index se agrega
                    if (estabaEnIndex == 0){                        
                        TextoArchivo archivo = new TextoArchivo();                    
                        archivo.datoSet(archivoEnLista.datoGet(archivoEnLista, "fecha"), archivoEnLista.datoGet(archivoEnLista, "nombre"), archivoEnLista.datoGet(archivoEnLista, "contenido"));
                        repositorio.index.listaArchivos.add(archivo);
                        break;
                    }
                }
            }        
        }   
    }
    /**
     * Crea un commit y se agrega a al local repository
     * @param repositorio Repositorio que se esta utilizando
     */
    public void commit(Repo repositorio){
        Scanner in = new Scanner(System.in);                        //Variable para pedir datos por consola
        Commit ultimoCommit;                                        //Ultimo commit generado
        int largoIndex = 0;                                         //Largo del index
        int existeDiferencia;                                       //Verificador si exsiste diferencia entre commits
        TextoArchivo archivoDif;                                    //Archivo presente o ausente al comparar commits
        
        System.out.println("");
        //Se pide el nombre del autor del commit
        System.out.println("Ingrese nombre del autor:");
        String autorC = in.nextLine();
        //Se pide el comentario
        System.out.println("Ingrese su comentario:");
        String comentario = in.nextLine();
        //Se inicializa un commit con el nombre del autor y comentario
        Commit nuevoCommit = new Commit(autorC, comentario);
        
        //Copio todos los archivos del index al commit
        for (TextoArchivo archivoEnListaI : repositorio.index.listaArchivos){
            TextoArchivo archivosCommit = new TextoArchivo();                          
            archivosCommit.datoSet(archivoEnListaI.datoGet(archivoEnListaI, "fecha"), archivoEnListaI.datoGet(archivoEnListaI, "nombre"), archivoEnListaI.datoGet(archivoEnListaI, "contenido"));
            nuevoCommit.listaArchivos.add(archivosCommit);
            largoIndex = largoIndex + 1;
        }
        
        //Si la zona local no esta vacia se realiza la busqueda de diferencias con el commit anterior
        if (!repositorio.local.listaCommits.isEmpty()){
            ultimoCommit = repositorio.local.listaCommits.get(repositorio.local.listaCommits.size() - 1);
            //Para cada archivo en el commit viejo
            for (TextoArchivo archivoEnUC : ultimoCommit.listaArchivos){
            //Si el archivo no esta en el nuevo commit se agrega
                existeDiferencia = 1;
                for(TextoArchivo archivoCommit : nuevoCommit.listaArchivos){
                    //Si el archivo del nuevo commit esta en el anterior no existe diferencia
                    if (archivoEnUC.datoGet(archivoEnUC, "nombre").equals(archivoCommit.datoGet(archivoCommit, "nombre")) && archivoEnUC.datoGet(archivoEnUC, "contenido").equals(archivoCommit.datoGet(archivoCommit, "contenido"))){
                    existeDiferencia = 0;
                    break;
                    }
                }
                //Si el archivo no estaba en el nuevo commit se agrega a la lista de diferencias
                if (existeDiferencia == 1){
                    archivoDif = archivoEnUC;
                    nuevoCommit.diferencias.add(archivoDif);
                }              
            }
            //para cada archivo del nuevo commit
            for (TextoArchivo archivoCommit : nuevoCommit.listaArchivos){
            //Si el archivo no esta en el viejo commit se agrega
                existeDiferencia = 1;
                for(TextoArchivo archivoEnUC : ultimoCommit.listaArchivos){
                    //Si el archivo del nuevo commit esta en el anterior no existe diferencia
                    if (archivoEnUC.datoGet(archivoEnUC, "nombre").equals(archivoCommit.datoGet(archivoCommit, "nombre")) && archivoEnUC.datoGet(archivoEnUC, "contenido").equals(archivoCommit.datoGet(archivoCommit, "contenido"))){
                    existeDiferencia = 0;
                    break;
                    }
                }
                //Se recorren los archivos de la lista diferencias para ver si el archivo actual no esta en la lista
                for(TextoArchivo archivosDiferencia : nuevoCommit.diferencias){
                    //Si esta el archivo no existe diferencia
                    if (archivosDiferencia.datoGet(archivosDiferencia, "nombre").equals(archivoCommit.datoGet(archivoCommit, "nombre")) && archivosDiferencia.datoGet(archivosDiferencia, "contenido").equals(archivoCommit.datoGet(archivoCommit, "contenido"))){
                    existeDiferencia = 0;
                    break;
                    }
                }
                //Si el archivo no estaba en el viejo commit ni en la lista de diferencias se agrega a la lista de diferencias
                if (existeDiferencia == 1){
                    archivoDif = archivoCommit;
                    nuevoCommit.diferencias.add(archivoDif);
                }              
            }            
        }
        //Se agrega el nuevo commit
        repositorio.local.listaCommits.add(nuevoCommit);       
    }
    /**
     * Copia los commits del local repository al remote 
     * @param repositorio Repositorio que se esta utilizando
     */
    public void push(Repo repositorio){
        Commit comentario;                          //Commit para copiar los commit de local a repo
        //Se recoren todos los commit del repositorio local
        for (Commit comentarioLocal : repositorio.local.listaCommits){
            //Si el commit no esta en el repositorio remote se agrega
            if (repositorio.remote.listaCommits.indexOf(comentarioLocal) == -1){
            comentario = comentarioLocal;
            repositorio.remote.listaCommits.add(comentario);
            }
        }
    }
    /**
     * Agrega al workspace todos los archivos de todos los commits que no esten en el workspace
     * @param repositorio Repositorio que se esta utilizando
     */    
    public void pull(Repo repositorio){  
        int existeArchivo;                                  //Verificador si el archivo esta en el workspace
        //Se recorren todos los commit de remote
        for (Commit commitRemote : repositorio.remote.listaCommits){
            //Se recorren todos los archivos del commit
            for (TextoArchivo archivoDeRemote : commitRemote.listaArchivos){
                existeArchivo = 0;
                //Se recorren todos los archivos del workspace
                for (TextoArchivo archivoWork : repositorio.workspace.listaArchivos){
                    //Si tienen el mismo archivo mi verificador cambia
                    if (archivoWork.datoGet(archivoWork, "nombre").equals(archivoDeRemote.datoGet(archivoDeRemote, "nombre"))){
                        existeArchivo = 1;
                        break;
                    }                   
                }
                //Si no se encontro el archivo se agrega a la lista de archivos del workspace
                if (existeArchivo == 0){
                    TextoArchivo archivo = new TextoArchivo();                          
                    archivo.datoSet(archivoDeRemote.datoGet(archivoDeRemote, "fecha"), archivoDeRemote.datoGet(archivoDeRemote, "nombre"), archivoDeRemote.datoGet(archivoDeRemote, "contenido"));
                    repositorio.workspace.listaArchivos.add(archivo);
                }                
            }
        }       
    }
    /**
     * Elimina archivos del workspace
     * @param repositorio Repositorio que se esta utilizando
     */
    public void delWol(Repo repositorio){
        Scanner in = new Scanner(System.in);                        //Variable para pedir datos por consola
        String nombreArchivo = "";

        System.out.println("");
        System.out.println("Archivos actuales del Workspace:");
        
        //Para imprimir todos los archivos del workspace
        for (TextoArchivo archivoEnLista : repositorio.workspace.listaArchivos){             
            System.out.println("-" + archivoEnLista.datoGet(archivoEnLista, "nombre"));
        } 
        System.out.println("Ingrese nombre archivos a eliminar, para salir escribir 00");
        //Mientras no se ingrese la opcion de salida 00 se sigue ejecutando la funcion 
        while(!"00".equals(nombreArchivo)){
            nombreArchivo = in.nextLine();
            //Recoror cada archivo en la lista de index
            for (TextoArchivo archivoEnLista : repositorio.workspace.listaArchivos){
                //Si el archivo ingresado esta
                if(nombreArchivo.equals(archivoEnLista.datoGet(archivoEnLista, "nombre"))){
                    //Elimino
                    repositorio.workspace.listaArchivos.remove(archivoEnLista);
                    break;
                    
                }
            }        
        }  
    }
    /**
     * Metodo Get para obtener datos del repositorio
     * @param repostiroio Repositorio que se esta utilizando
     * @param dato String nombre del dato que se desea obtener
     * @return Se devuelve el dato pedido como String
     */
    public String repoGet(Repo repostiroio, String dato){
        String datoDevuelto = new String();
            
        switch(dato){
                
            case "autor":                    
                datoDevuelto = repostiroio.autor;                    
                break;
                                    
            case "nombre":                    
                datoDevuelto = repostiroio.nombreR;                    
                break;                        
        }        
        return datoDevuelto;
    }
    /**
     * Metodo Set para asignarle nombre y autor al repositorio
     * @param autor String con el nombre del autor del repositorio
     * @param nombre String con el nombre del repositorio
     */
    public void repoSet(String autor, String nombre){  
        this.autor = autor;                        
        this.nombreR = nombre;
    }
}
