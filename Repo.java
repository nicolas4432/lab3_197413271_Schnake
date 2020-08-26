/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab;

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
    
    //Contructor
    public Repo(String autor, String nombreRepositorio){
        this.autor = autor;
        this.nombreR = nombreRepositorio;
    }
    
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
        TextoArchivo archivo;
        int largoIndex = 0;
        int estabaEnIndex;
        
        System.out.println("");
        System.out.println("Archivos actuales del Workspace:");
        
        //Para ver todos los archivos del workspace
        for (TextoArchivo archivoEnLista : repositorio.workspace.listaArchivos){             
            System.out.println("-" + archivoEnLista.obtenerDato(archivoEnLista, "nombre"));
        } 
        System.out.println("Ingrese nombre archivos, para salir escribir 00");
        
        while(!"00".equals(nombreArchivo)){
            nombreArchivo = in.nextLine();
            //Recoror cada archivo en la lista de index
            for (TextoArchivo archivoEnLista : repositorio.workspace.listaArchivos){
                estabaEnIndex = 0;
                //Si el archivo ingresado esta
                if(nombreArchivo.equals(archivoEnLista.obtenerDato(archivoEnLista, "nombre"))){
                    //Recorro los archivos del index 
                    for (TextoArchivo archivoEnListaI : repositorio.index.listaArchivos){
                       largoIndex = largoIndex + 1;  
                        //Si el archivo esta en index
                        if(nombreArchivo.equals(archivoEnListaI.obtenerDato(archivoEnListaI, "nombre"))){
                            estabaEnIndex = 1;
                            //y el contenido es distinto
                            if(!(archivoEnLista.obtenerDato(archivoEnLista, "contenido")).equals(archivoEnListaI.obtenerDato(archivoEnListaI, "contenido"))){
                            repositorio.index.listaArchivos.remove(archivoEnListaI);
                            archivo = archivoEnLista;
                            repositorio.index.listaArchivos.add(archivo);
                            break;
                            }
                            break;
                        }
                    }
                    if (estabaEnIndex == 0){
                        archivo = archivoEnLista;
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
    
    public void commit(Repo repositorio){
        Scanner in = new Scanner(System.in);
        Commit ultimoCommit;
        TextoArchivo archivosCommit;
        int largoIndex = 0;
        
        System.out.println("");
        System.out.println("Ingrese nombre del autor:");
        String autorC = in.nextLine();        
        System.out.println("Ingrese su comentario:");
        String comentario = in.nextLine();
        
        Commit nuevoCommit = new Commit(autorC, comentario);
        
        for (TextoArchivo archivoEnListaI : repositorio.index.listaArchivos){
            archivosCommit = archivoEnListaI;
            nuevoCommit.listaArchivos.add(archivosCommit);
            largoIndex = largoIndex + 1;
        }
        
        if (!repositorio.local.listaCommits.isEmpty()){
            ultimoCommit = repositorio.local.listaCommits.get(repositorio.local.listaCommits.size() - 1);
            for (TextoArchivo archivo : ultimoCommit.listaArchivos){
                //Si el archivo no esta ni nuevo commit y en la lista de diferencias se agrega
                if((nuevoCommit.listaArchivos.indexOf(archivo) == -1 && nuevoCommit.diferencias.indexOf(archivo) == -1)){
                    archivosCommit = archivo;
                    nuevoCommit.diferencias.add(archivosCommit);                    
                }
            }
            for (TextoArchivo archivo : nuevoCommit.listaArchivos){
                 if((ultimoCommit.listaArchivos.indexOf(archivo) == -1 && nuevoCommit.diferencias.indexOf(archivo) == -1)){
                    archivosCommit = archivo;
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
    
    public void pull(Repo repositorio){
        Commit comentario;
        
        for (Commit comentarioLocal : repositorio.local.listaCommits){
            if (repositorio.remote.listaCommits.indexOf(comentarioLocal) == -1){
            comentario = comentarioLocal;
            repositorio.remote.listaCommits.add(comentario);
            }
        }
    }
    
    public void push(Repo repositorio){
        TextoArchivo archivo;
        
        for (Commit commitRocal : repositorio.local.listaCommits){
            for (TextoArchivo archivoDeRemote : commitRocal.listaArchivos){
                if (repositorio.workspace.listaArchivos.indexOf(archivoDeRemote) == -1){
                archivo = archivoDeRemote;
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
}
