package Ejecucion_concurrente_de_multiples_hilos;

class MultihiloThreadDemo extends Thread {
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

public class MultihiloThread {
    public static void main(String[] args) {
        int n = 8; // Número de hilos
        for (int i = 0; i < n; i++) {
            MultihiloThreadDemo object = new MultihiloThreadDemo();
            // Iniciamos el hilo
            object.start();
        }
    }
}