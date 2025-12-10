package ejemplo1;

public class SimuladorTrafico {
    public static void main(String[] args) {
        // Creación de hilos para motos
        Moto moto1 = new Moto("Moto 1");
        Moto moto2 = new Moto("Moto 2");

        // Creación de hilos para coches
        Thread coche1 = new Thread(new Coche("Coche 1"));
        Thread coche2 = new Thread(new Coche("Coche 2"));
        
     // Seguimiento inicial de los estados de los hilos
        System.out.println("Estado Moto 1: " + moto1.getState());
        System.out.println("Estado Moto 2: " + moto2.getState());
        System.out.println("Estado Coche 1: " + coche1.getState());
        System.out.println("Estado Coche 2: " + coche2.getState());

        // Ejecución de los hilos
        System.out.println("Iniciando simulación de tráfico...");

        moto1.start();
        moto2.start();
        coche1.start();
        coche2.start();

        // Seguimiento de los estados de los hilos
        while (moto1.isAlive() || moto2.isAlive() || coche1.isAlive() || coche2.isAlive()) {
            System.out.println("Estado Moto 1: " + moto1.getState());
            System.out.println("Estado Moto 2: " + moto2.getState());
            System.out.println("Estado Coche 1: " + coche1.getState());
            System.out.println("Estado Coche 2: " + coche2.getState());

            try {
                Thread.sleep(200); // Pausa para evitar demasiados mensajes
            } catch (InterruptedException e) {
                System.err.println("Seguimiento de estados interrumpido.");
            }
        }

        System.out.println("Simulación finalizada.");
    }
}

