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
    static Repo repositorio;
    
    public static void main(String[] args) throws Exception {
        
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int repoCreado = 0; 
        String opcion = "";
                
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
        System.out.println("7. Terminar simulacion");
        
        while(!"7".equals(opcion)){
        System.out.println("INTRODUZCA SU OPCIÓN: ");
        opcion = in.nextLine();
        
            switch (opcion) {
                case "0":
                    if (repoCreado == 0){
                        repoCreado = 1;
                        System.out.println("Introdusca nombre del autor:");
                        String autor = in.nextLine();
                        System.out.println("Ingrese el nombre del repositorio:");
                        String nombre = in.nextLine();
                        repositorio = new Repo(autor, nombre);
                        break;
                    }

                case "1":
                    if (repoCreado == 1){
                        repositorio.add(repositorio);
                    }
                    System.out.println("1");
                    break;

                case "2":
                    System.out.println("2");
                    break;

                case "3":
                    System.out.println("3");
                    break;

                case "4":
                    System.out.println("4");
                    break;

                case "5":
                    System.out.println("5");
                    break;

                case "6":
                    if (repoCreado == 0){
                        System.out.println("Repositorio no creado");
                        break;
                    }
                    repositorio.addWork(repositorio);
                    break;

                case "7":
                    System.out.println("Fin simulacion");
                    break;

                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}
