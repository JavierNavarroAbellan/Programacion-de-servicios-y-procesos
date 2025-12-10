package ejemplo2;

import java.util.concurrent.Semaphore;

public class Clientes implements Runnable {
    private Semaphore semaphore;
    private String nombre;

    public Clientes(Semaphore semaphore, String nombre) {
        this.semaphore = semaphore;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire(); // Limita el acceso a un máximo de 2 clientes al mismo tiempo
            System.out.println(nombre + " está realizando un pedido...");
            Thread.sleep(1000); // Simula el tiempo necesario para realizar el pedido
            System.out.println(nombre + " ha terminado su pedido.");
        } catch (InterruptedException e) {
            System.err.println(nombre + " fue interrumpido.");
        } finally {
            semaphore.release(); // Libera el permiso para otros clientes
        }
    }
}
