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

        //Para ver todos los archivos del workspace
        //for (TextoArchivo archivo2 : repositorio.workspace.listaArchivos){             
        //System.out.println(archivo2.obtenerDato(archivo2, "contenido") + " " + archivo2.obtenerDato(archivo2, "fecha") + " " + archivo2.obtenerDato(archivo2, "nombre"));
        //}
    }
    
    public void add(Repo repositorio){
        Scanner in = new Scanner(System.in);
        String nombreArchivo = "";
        TextoArchivo archivo;
        
        System.out.println("");
        System.out.println("Archivos actuales del Workspace:");
        
        //Para ver todos los archivos del workspace
        for (TextoArchivo archivoEnLista : repositorio.workspace.listaArchivos){             
            System.out.println("-" + archivoEnLista.obtenerDato(archivoEnLista, "nombre"));
        } 
        System.out.println("Ingrese nombre archivos, para salir escribir 00");
        while(!"00".equals(nombreArchivo)){
            nombreArchivo = in.nextLine();
            for (TextoArchivo archivoEnLista : repositorio.workspace.listaArchivos){             
                if(nombreArchivo.equals(archivoEnLista.obtenerDato(archivoEnLista, "nombre"))){
                    //Condicion si el archivo ya esta en index no agregar denuevo, almenos que tenga otro contenido
                    System.out.println("El archivo esta en el Workspace");
                    archivo = archivoEnLista;
                    repositorio.index.agregarArchivoI(archivo); 
                }
            }        
        }
        //Para ver que archivos tiene el index
        //for (TextoArchivo archivo2 : repositorio.index.listaArchivos){             
        //System.out.println(archivo2.obtenerDato(archivo2, "contenido") + " " + archivo2.obtenerDato(archivo2, "fecha") + " " + archivo2.obtenerDato(archivo2, "nombre"));
        //}
        
     }
    
    
}
