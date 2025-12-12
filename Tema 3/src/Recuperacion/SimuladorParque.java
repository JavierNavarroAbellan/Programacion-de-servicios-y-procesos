package Recuperacion;

public class SimuladorParque {
    public static void main(String[] args) {

        // Creación de hilos para montañas rusas
        MontRusa montRusa1 = new MontRusa("Montaña rusa 1");
        MontRusa montRusa2 = new MontRusa("Montaña rusa 2");

        // Creación de hilos para Torres de caida libre
        Thread caidaLibre1 = new Thread(new TorreCaidaLibre("Caída libre 1"));
        Thread caidaLibre2 = new Thread(new TorreCaidaLibre("Caída libre 2"));

        // Seguimiento inicial de los estados de los hilos
        System.out.println("Estado Montaña rusa 1: " + montRusa1.getState());
        System.out.println("Estado Montaña rusa 2: " + montRusa2.getState());
        System.out.println("Estado Caída libre 1: " + caidaLibre1.getState());
        System.out.println("Estado Caída libre 2: " + caidaLibre2.getState());

        // Ejecución de los hilos
        System.out.println("Iniciando simulación del parque de atracciones");

        montRusa1.start();
        montRusa2.start();
        caidaLibre1.start();
        caidaLibre2.start();

        // Seguimiento de los estados de los hilos
        while (montRusa1.isAlive() || montRusa2.isAlive() || caidaLibre1.isAlive() || caidaLibre2.isAlive()) {
            System.out.println("Estado Montaña rusa 1: " + montRusa1.getState());
            System.out.println("Estado Montaña rusa 2: " + montRusa2.getState());
            System.out.println("Estado Caída libre 1: " + caidaLibre1.getState());
            System.out.println("Estado Caída libre 2: " + caidaLibre2.getState());

            try {
                Thread.sleep(200); // Pausa para evitar demasiados mensajes
            } catch (InterruptedException e) {
                System.err.println("Seguimiento de estados interrumpido");
            }
        }

        System.out.println("Simulación del parque terminada");
    }
}