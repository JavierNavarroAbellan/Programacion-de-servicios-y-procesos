package Ejecucion_concurrente_de_multiples_hilos;

class MultihiloRunnableDemo implements Runnable {
    @Override
    public void run() {
        try {
            // Mostrar el ID del hilo en ejecución
            System.out.println("ID del hilo: " + Thread.currentThread().getId());
        } catch (Exception e) {
            // Lanzar excepción
            System.out.println("Error mostrando id del hilo: " + e);
        }
    }
}

public class MultihiloRunnable {
    public static void main(String[] args) {
        int n = 8; // Número de hilos
        for (int i = 0; i < n; i++) {
            // Creamos el objeto Thread
            Thread object = new Thread(new MultihiloRunnableDemo());
            // Iniciamos el hilo
            object.start();
        }
    }
}