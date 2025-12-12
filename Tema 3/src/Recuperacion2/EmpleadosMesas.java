package Recuperacion2;

public class EmpleadosMesas implements Runnable {
    private String nombre;

    public EmpleadosMesas(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            System.out.println(nombre + " está preparando una mesa para las reservas...");

            Thread.sleep(1500 / Thread.currentThread().getPriority()); // Los empleados tardan el mismo tiempo en preparar una mesa (1,5 segundos)

            System.out.println(nombre + " ha terminado de preparar la mesa.");

        } catch (InterruptedException e) {

            System.err.println(nombre + " fue interrumpido.");
        }
    }
}
