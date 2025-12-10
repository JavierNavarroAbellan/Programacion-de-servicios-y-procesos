package ejemplo2;

public class Empleados implements Runnable {
    private String nombre;

    public Empleados(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            System.out.println(nombre + " está procesando un pedido...");
            Thread.sleep(1500 / Thread.currentThread().getPriority()); // Procesa más rápido según la prioridad
            System.out.println(nombre + " ha terminado de procesar un pedido.");
        } catch (InterruptedException e) {
            System.err.println(nombre + " fue interrumpido.");
        }
    }
}
