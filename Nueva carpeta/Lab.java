

import java.util.Scanner;

/**
 * Una clase para simular un sistema Git.
 * Lab con la funcion main simula y contiene a al repositorio
 * @version 1.0, 11/08/20
 * @author Nicolas Schnake
 */

public class Lab {

    /**
     * Atravez de la funcion main se realiza la simulacion del repositorio
     */
    
    public static void main(String[] args) throws Exception {
        
        Scanner in = new Scanner(System.in);        //Variable para pedir valor
        int repoCreado = 0;                         //Entero verificador 
        String opcion = "";                         //String para la opcion ingresada
        String autor = new String();                //String para el autor
        String nombre = new String();               //String para el nombre
        Repo repositorio = new Repo();              //Se crea un repositorio
        
        System.out.println("#########################");
        System.out.println("### SIMULACION DE GIT ###");
        
        /**
        * Se realiza la dimuacion hasta elejir la opcion de salida
        */
        while(!"8".equals(opcion)){
            System.out.println("");
            System.out.println("Escoja su opcion:");
            System.out.println("0. Crear Repositorio");
            System.out.println("1. add");
            System.out.println("2. commit");
            System.out.println("3. pull");
            System.out.println("4. push");
            System.out.println("5. status");
            System.out.println("6. Crear nuevo archivo");
            System.out.println("7. Eliminar archivo");
            System.out.println("8. Terminar simulacion");            
            System.out.println("INTRODUZCA SU OPCIoN: ");
            opcion = in.nextLine();
   
            switch (opcion) {
                case "0":
                    //Se verifica si se inicializo el repositorio 
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
                    //Se verifica si se inicializo el repositorio 
                    if (repoCreado == 0){
                        System.out.println("Repositorio no creado");
                        break;
                    }                    
                    repositorio.add(repositorio);                    
                    break;

                case "2":
                    //Se verifica si se inicializo el repositorio
                    if (repoCreado == 0){
                        System.out.println("Repositorio no creado");
                        break;
                    }                    
                    repositorio.commit(repositorio);
                    break;

                case "3":
                    //Se verifica si se inicializo el repositorio
                    if (repoCreado == 0){
                        System.out.println("Repositorio no creado");
                        break;
                    }                    
                    repositorio.pull(repositorio);
                    break;

                case "4":
                    //Se verifica si se inicializo el repositorio
                    if (repoCreado == 0){
                        System.out.println("Repositorio no creado");
                        break;
                    }                    
                    repositorio.push(repositorio);
                    break;

                case "5":
                    //Se verifica si se inicializo el repositorio
                    if (repoCreado == 0){
                        System.out.println("Repositorio no creado");
                        break;
                    }                    
                    System.out.println("#########################");
                    System.out.println("Nombre Repositorio: " + repositorio.repoGet(repositorio, "nombre"));
                    System.out.println("Nombre del autor: " + repositorio.repoGet(repositorio, "autor"));
                    System.out.println("Numero de archivos en el Workspace: " + repositorio.workspace.listaArchivos.size());
                    System.out.println("Numero de archivos en el Index: " + repositorio.index.listaArchivos.size());
                    System.out.println("Numero de commits en el Local Repository: " + repositorio.local.listaCommits.size());
                    if (repositorio.local.listaCommits.size() == repositorio.remote.listaCommits.size()){
                        System.out.println("El Remote Repository esta al dia con los cambios del Local Repository");
                    } else {
                        System.out.println("El Remote Repository no esta al dia con los cambios del Local Repository");
                    }
                    break;

                case "6":
                    //Se verifica si se inicializo el repositorio
                    if (repoCreado == 0){
                        System.out.println("Repositorio no creado");
                        break;
                    }                    
                    repositorio.addWork(repositorio);
                    break;

                case "7":
                    //Se verifica si se inicializo el repositorio
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
