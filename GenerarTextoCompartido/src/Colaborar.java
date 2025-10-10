/**
 * @author Javier Navarro Abellán
 * @date 07/10/2025
 * Este programa genera letras aleatorias, las almacena en un fichero y simula una colaboración concurrente en el archivo.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Colaborar:
 * Permite compartir el archivo.
 */

public class Colaborar {

    public static void main(String[] args) {
        String fichero = "miFicheroDeLenguaje.txt";

        int numProcesos = 10;
        int numLetrasPorLinea = 10;
        int incrementoLineas = 10;

        //Iniciamos una lista para almacenar los hilos
        List<Thread> hilos = new ArrayList<>();

        //Creamos y lanzamos los hilos
        for(int i = 1; i < numProcesos; i++) {
            //Número de líneas por hacer en cada instancia
            int lineasPorHacer = i*incrementoLineas;

            //Creamos el proceso (o tarea) del objeto Lenguaje
            Lenguaje miLenguaje = new Lenguaje(fichero, lineasPorHacer, numLetrasPorLinea);

            //Creamos el hilo para ejecutar la tarea
            Thread thread = new Thread(miLenguaje);
            hilos.add(thread);
            thread.start();
        }

        //Esperamos a que todos los hilos terminen
        for (Thread thread : hilos){
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Error añ esperar el hilo: " + e.getMessage());
            }
        }

        System.out.println("Se ha guardado con éxito el archivo: " + fichero);

    }
}