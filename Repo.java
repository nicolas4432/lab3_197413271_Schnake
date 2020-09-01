/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nico4
 */
public class Repo {
    //Atributos
    String autor = new String();
    String nombreR = new String();
    Workspace workspace = new Workspace();
    Index index = new Index();
    RemoteR remote = new RemoteR();
    LocalR local = new LocalR();
    
    
    //Metodos
    public void addWork(Repo repositorio){
        Scanner in = new Scanner(System.in);
        
        System.out.println("");
        System.out.println("Opcion elejida: 6");
        System.out.println("Introdusca nombre del archivo:");
        String nombre = in.nextLine();
        System.out.println("Ingrese el contenido:");
        String contenido = in.nextLine();
        
        repositorio.workspace.agregarArchivo(nombre, contenido); 

        //Para ver todos los archivos del workspace.
        //for (TextoArchivo archivo2 : repositorio.workspace.listaArchivos){             
        //System.out.println(archivo2.obtenerDato(archivo2, "contenido") + " " + archivo2.obtenerDato(archivo2, "fecha") + " " + archivo2.obtenerDato(archivo2, "nombre"));
        //}
    }
    
    public void add(Repo repositorio){
        Scanner in = new Scanner(System.in);
        String nombreArchivo = "";
//        TextoArchivo archivo;
        int largoIndex = 0;
        int estabaEnIndex;
        
        System.out.println("");
        System.out.println("Archivos actuales del Workspace:");
        
        //Para ver todos los archivos del workspace
        for (TextoArchivo archivoEnLista : repositorio.workspace.listaArchivos){             
            System.out.println("-" + archivoEnLista.datoGet(archivoEnLista, "nombre"));
        } 
        System.out.println("Ingrese nombre archivos, para salir escribir 00");
        
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
                            repositorio.index.listaArchivos.add(archivo);
                            break;
                            }
                            break;
                        }
                    }
                    if (estabaEnIndex == 0){                        
                        TextoArchivo archivo = new TextoArchivo();                    
                        archivo.datoSet(archivoEnLista.datoGet(archivoEnLista, "fecha"), archivoEnLista.datoGet(archivoEnLista, "nombre"), archivoEnLista.datoGet(archivoEnLista, "contenido"));
                        repositorio.index.listaArchivos.add(archivo);
                        break;
                    }
                }
            }        
        }
        //Para ver que archivos tiene el index
        //for (TextoArchivo archivo2 : repositorio.index.listaArchivos){             
        //System.out.println(archivo2.obtenerDato(archivo2, "contenido") + " " + archivo2.obtenerDato(archivo2, "fecha") + " " + archivo2.obtenerDato(archivo2, "nombre"));
        //}
        
     }
    //Error al verificar si un archivo esta en la lista cambiar el metodo de busqueda
    public void commit(Repo repositorio){
        Scanner in = new Scanner(System.in);
        Commit ultimoCommit;
        //TextoArchivo archivosCommit;
        int largoIndex = 0;
        
        System.out.println("");
        System.out.println("Ingrese nombre del autor:");
        String autorC = in.nextLine();        
        System.out.println("Ingrese su comentario:");
        String comentario = in.nextLine();
        
        Commit nuevoCommit = new Commit(autorC, comentario);
        
        //Copio todos los archivos del index al commit
        for (TextoArchivo archivoEnListaI : repositorio.index.listaArchivos){
            TextoArchivo archivosCommit = new TextoArchivo();                          
            archivosCommit.datoSet(archivoEnListaI.datoGet(archivoEnListaI, "fecha"), archivoEnListaI.datoGet(archivoEnListaI, "nombre"), archivoEnListaI.datoGet(archivoEnListaI, "contenido"));
            nuevoCommit.listaArchivos.add(archivosCommit);
            largoIndex = largoIndex + 1;
        }
        
        if (!repositorio.local.listaCommits.isEmpty()){
            ultimoCommit = repositorio.local.listaCommits.get(repositorio.local.listaCommits.size() - 1);
            for (TextoArchivo archivo : ultimoCommit.listaArchivos){
                //Si el archivo no esta ni nuevo commit y en la lista de diferencias se agrega
                if((nuevoCommit.listaArchivos.indexOf(archivo) == -1 && nuevoCommit.diferencias.indexOf(archivo) == -1)){
              //if(archivo.datoGet(archivo, "nombre") ==     
                    TextoArchivo archivosCommit = new TextoArchivo();                        
                    archivosCommit.datoSet(archivo.datoGet(archivo, "fecha"), archivo.datoGet(archivo, "nombre"), archivo.datoGet(archivo, "contenido"));
                    nuevoCommit.diferencias.add(archivosCommit);                    
                }
            }
            for (TextoArchivo archivo : nuevoCommit.listaArchivos){
                //Si el archivo no estaba en el commit anterior ni en la lista de diferencias
                 if((ultimoCommit.listaArchivos.indexOf(archivo) == -1 && nuevoCommit.diferencias.indexOf(archivo) == -1)){
                    TextoArchivo archivosCommit = new TextoArchivo();                           
                    archivosCommit.datoSet(archivo.datoGet(archivo, "fecha"), archivo.datoGet(archivo, "nombre"), archivo.datoGet(archivo, "contenido"));
                    nuevoCommit.diferencias.add(archivosCommit);                    
                }               
            }
        }
        
        repositorio.local.listaCommits.add(nuevoCommit);
        //Para ver que contenido tienen los commits
        //for (Commit comen : repositorio.local.listaCommits){
        //    System.out.println(comentario);
        //    for(TextoArchivo archivo2 : comen.listaArchivos){
        //    System.out.println(archivo2.obtenerDato(archivo2, "contenido") + " " + archivo2.obtenerDato(archivo2, "fecha") + " " + archivo2.obtenerDato(archivo2, "nombre"));
        //    }
        //}
        
    }
    
    public void push(Repo repositorio){
        Commit comentario;
        
        for (Commit comentarioLocal : repositorio.local.listaCommits){
            if (repositorio.remote.listaCommits.indexOf(comentarioLocal) == -1){
            comentario = comentarioLocal;
            repositorio.remote.listaCommits.add(comentario);
            }
        }
    }
    
    public void pull(Repo repositorio){ //error 
        for (Commit commitRemote : repositorio.remote.listaCommits){
            for (TextoArchivo archivoDeRemote : commitRemote.listaArchivos){
                if (repositorio.workspace.listaArchivos.indexOf(archivoDeRemote) == -1){
                    TextoArchivo archivo = new TextoArchivo();                          
                    archivo.datoSet(archivoDeRemote.datoGet(archivoDeRemote, "fecha"), archivoDeRemote.datoGet(archivoDeRemote, "nombre"), archivoDeRemote.datoGet(archivoDeRemote, "contenido"));
                    repositorio.workspace.listaArchivos.add(archivo);
                }
            }
        }       
    }
    
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
    
    public void repoSet(String autor, String nombre){  
        this.autor = autor;
        this.nombreR = nombre;
    }
}
