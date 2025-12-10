package ejemplo1;

public class Coche implements Runnable {
    private String nombre;

    public Coche(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println(nombre + " avanzando al punto " + i);
                Thread.sleep(450); // Simula el tiempo intermedio
            }
            System.out.println(nombre + " ha completado su recorrido.");
        } catch (InterruptedException e) {
            System.err.println(nombre + " ha sido interrumpido.");
        }
    }
}
