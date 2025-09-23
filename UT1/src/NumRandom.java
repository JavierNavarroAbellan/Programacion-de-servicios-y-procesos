import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumRandom {
    public static void main(String[] args) {

        //1. Creamos una instancia para usar los métodos de esta clase

        NumRandom numRandom = new NumRandom();

        //2. Generamos 35 números aleatorios

        List<Integer> numeros = numRandom.generarNumeros();

        //3. Mostramos los números por la salida estándar

        numRandom.mostrarNumeros(numeros);

    }

    //Definimos los métodos de la clase

    public List<Integer> generarNumeros() {

        //Instanciamos un generador de números aleatorios

        Random random = new Random();

        // Generamos la lista donde queremos almacenar los números aleatorios

        List<Integer> numeros = new ArrayList<>();

        //Bucle para generar los 35 números aleatorios

        for (int i = 0; i < 35; i++) {
            int resultado = random.nextInt(101);
            numeros.add(resultado);
        }

        //Devolvemos los números generados

        return numeros;
    }

    //Método que muestra los números por consola

    public void mostrarNumeros(List<Integer> numeros) {
        for (int numero:numeros) {
            System.out.println(numero + " ");
        }
        System.out.println();
    }
}
