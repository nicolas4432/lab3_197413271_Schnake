/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab;

import java.util.Scanner;

/**
 *
 * @author nico4
 */

public class Lab {

    /**
     * @param args
     * @throws java.lang.Exception
     */
    
    public static void main(String[] args) throws Exception {
        
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int repoCreado = 0; 
        String opcion = "";;
        String autor = new String(); 
        String nombre = new String();
        Repo repositorio = new Repo(); 
        
        System.out.println("#########################");
        System.out.println("### SIMULACIÓN DE GIT ###");
        System.out.println("Escoja su opción:");
        System.out.println("0. Crear Repositorio");
        System.out.println("1. add");
        System.out.println("2. commit");
        System.out.println("3. pull");
        System.out.println("4. push");
        System.out.println("5. status");
        System.out.println("6. Crear nuevo archivo");
        System.out.println("7. Eliminar archivo");
        System.out.println("8. Terminar simulacion");
        
        while(!"8".equals(opcion)){
        System.out.println("INTRODUZCA SU OPCIÓN: ");
        opcion = in.nextLine();
        
        
            switch (opcion) {
                case "0":
                    if (repoCreado == 0){
                        repoCreado = 1;
                        System.out.println("Introdusca nombre del autor:");
                        autor = in.nextLine();
                        System.out.println("Ingrese el nombre del repositorio:");
                        nombre = in.nextLine();
                        repositorio.repoSet(autor, nombre);
                        break;
                    }
                    break;

                case "1":
                    if (repoCreado == 0){
                        System.out.println("Repositorio no creado");
                        break;
                    }                    
                    repositorio.add(repositorio);                    
                    break;

                case "2":
                    if (repoCreado == 0){
                        System.out.println("Repositorio no creado");
                        break;
                    }                    
                    repositorio.commit(repositorio);
                    break;

                case "3":
                    if (repoCreado == 0){
                        System.out.println("Repositorio no creado");
                        break;
                    }                    
                    repositorio.pull(repositorio);
                    break;

                case "4":
                    if (repoCreado == 0){
                        System.out.println("Repositorio no creado");
                        break;
                    }                    
                    repositorio.push(repositorio);
                    break;

                case "5":
                    if (repoCreado == 0){
                        System.out.println("Repositorio no creado");
                        break;
                    }                    
                    System.out.println("#########################");
                    System.out.println("Nombre Repositorio: " + repositorio.repoGet(repositorio, "nombre"));
                    System.out.println("Nombre del autor: " + repositorio.repoGet(repositorio, "autor"));
                    System.out.println("Número de archivos en el Workspace: " + repositorio.workspace.listaArchivos.size());
                    System.out.println("Número de archivos en el Index: " + repositorio.index.listaArchivos.size());
                    System.out.println("Número de commits en el Local Repository: " + repositorio.local.listaCommits.size());
                    if (repositorio.local.listaCommits.size() == repositorio.remote.listaCommits.size()){
                        System.out.println("El Remote Repository está al día con los cambios del Local Repository");
                    } else {
                        System.out.println("El Remote Repository no está al día con los cambios del Local Repository");
                    }
                    break;

                case "6":
                    if (repoCreado == 0){
                        System.out.println("Repositorio no creado");
                        break;
                    }                    
                    repositorio.addWork(repositorio);
                    break;

                case "7":
                    if (repoCreado == 0){
                        System.out.println("Repositorio no creado");
                        break;
                    }                    
                    repositorio.delWol(repositorio);
                    break;

                case "8":
                    System.out.println("Fin simulacion");
                    break;                    
                    
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}
