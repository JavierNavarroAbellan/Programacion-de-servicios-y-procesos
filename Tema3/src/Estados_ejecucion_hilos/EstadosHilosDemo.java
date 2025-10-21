package Estados_ejecucion_hilos;

// Clase que simula una tarea personalizada
class MiTarea implements Runnable {
    private String nombre;

    public MiTarea(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(nombre + " ejecutando paso " + i);
            try {
                Thread.sleep(500); // pausa de medio segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nombre + " ha finalizado su ejecución.");
    }
}

// Clase principal que observa los estados de los hilos
public class EstadosHilosDemo {
    public static void main(String[] args) {
        // Creamos las tareas y los hilos
        Thread hilo1 = new Thread(new MiTarea("Hilo Números"));
        Thread hilo2 = new Thread(new MiTarea("Hilo Letras"));

        // Estado inicial
        System.out.println("Estado inicial de hilo1: " + hilo1.getState());
        System.out.println("Estado inicial de hilo2: " + hilo2.getState());

        // Iniciamos los hilos
        hilo1.start();
        hilo2.start();

        // Estado después de start(): RUNNABLE
        System.out.println("\nDespués de start():");
        System.out.println("Estado de hilo1: " + hilo1.getState());
        System.out.println("Estado de hilo2: " + hilo2.getState());

        // Monitoreo mientras los hilos están ejecutándose
        while (hilo1.isAlive() || hilo2.isAlive()) {
            System.out.println("\nEstado actual:");
            System.out.println("hilo1 -> " + hilo1.getState());
            System.out.println("hilo2 -> " + hilo2.getState());
            try {
                Thread.sleep(300); // pausa para observar cambios
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Estados finales
        System.out.println("\nDespués de la ejecución completa:");
        System.out.println("Estado final de hilo1: " + hilo1.getState());
        System.out.println("Estado final de hilo2: " + hilo2.getState());
    }
}
