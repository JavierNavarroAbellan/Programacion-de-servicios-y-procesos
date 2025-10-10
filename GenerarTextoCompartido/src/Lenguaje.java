/**
 * @author Javier Navarro Abellán
 * @date 07/10/2025
 * Este programa genera letras aleatorias, las almacena en un fichero y simula una colaboración concurrente en el archivo.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Clase Lenguaje:
 * Genera líneas de texto aleatorio y las escribe en un archivo compartido.
 */

public class Lenguaje  implements Runnable {

    private final String fichero;
    private final int numLineas;
    private final int numLetrasPorLinea;

    /**
     *Creamos el constructor
     */

    public Lenguaje(String fichero, int numLineas, int numLetrasPorLinea) {
        this.fichero = fichero;
        this.numLineas = numLineas;
        this.numLetrasPorLinea = numLetrasPorLinea;
    }

    /**
     *Creamos el método que genera las linas de texto aleatorio y las escribe en el archivo
     */

    @Override
    public void run() {
        //Generar aleatorio
        Random random = new Random();

        //Creamos archivo
        try(FileWriter fw = new FileWriter(fichero, true)) { //Escribir en modo append
            for(int i = 0; i < numLineas; i++) {
                StringBuilder stringBuild = new StringBuilder(); //Almacenamos la línea
                for(int j = 0; j < numLetrasPorLinea; j++) {
                    char randomChar = (char) ('a' + random.nextInt (26)); //Genero letras en vez de números
                    stringBuild.append(randomChar);
                }
                //Escribir la línea en el archivo
                fw.write(stringBuild.toString() + System.lineSeparator());

            }
            System.out.println(Thread.currentThread().getName() + " escribió " + numLineas + " lineas");

        } catch(IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}