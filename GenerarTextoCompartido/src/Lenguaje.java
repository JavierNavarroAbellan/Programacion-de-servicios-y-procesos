/**
 * @author Javier Navarro Abellán
 * @date 04/10/2025
 * Este programa genera letras aleatorias, las almacena en un fichero y simula una colaboración concurrente en el archivo.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Clase Lenguaje:
 * Genera líneas de texto aleatorio y las escribe en un archivo compartido.
 */

public class Lenguaje {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java Lenguaje <numLineas> <numLetrasPorLinea>");
            return;
        }

        int numLineas = Integer.parseInt(args[0]);
        int numLetras = Integer.parseInt(args[1]);
        escribirLineasAleatorias(numLineas, numLetras);
    }

    private static void escribirLineasAleatorias(int numLineas, int numLetras) {
        Random random = new Random();
        String nombreArchivo = "miFicheroDeLenguaje.txt";

        // Bloque sincronizado

        synchronized (Lenguaje.class) {
            try (FileWriter writer = new FileWriter(nombreArchivo, true)) {
                for (int i = 0; i < numLineas; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < numLetras; j++) {
                        char letra = (char) ('a' + random.nextInt(26));
                        sb.append(letra);
                    }
                    writer.write(sb.toString() + System.lineSeparator());
                }
                writer.flush();
                System.out.println("Proceso " + ProcessHandle.current().pid() +
                        " escribio " + numLineas + " lineas.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}