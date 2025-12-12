package Recuperacion;

public class TorreCaidaLibre implements Runnable {
    private String nombre;

    public TorreCaidaLibre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println(nombre + " avanzando al punto " + i);
                Thread.sleep(500); // Simula el tiempo intermedio
            }
            System.out.println(nombre + " ha completado su recorrido.");
        } catch (InterruptedException e) {
            System.err.println(nombre + " ha sido interrumpido.");
        }
    }
}