import java.io.IOException;

/**
 * Clase Colaborar:
 * Permite compartir el archivo.
 */

public class Colaborar {

    public static void main(String[] args) {
        int numProcesos = 10;
        int numLetrasPorLinea = 8;

        // Usa el mismo classpath que el proceso actual
        String classpath = System.getProperty("java.class.path");

        for (int i = 1; i <= numProcesos; i++) {
            int numLineas = i * 10;

            try {
                ProcessBuilder pb = new ProcessBuilder(
                        "java", "-cp", classpath, "Lenguaje",
                        String.valueOf(numLineas),
                        String.valueOf(numLetrasPorLinea)
                );

                pb.inheritIO();
                pb.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}