package Recuperacion;

    public class MontRusa extends Thread {
        private String nombre;

        public MontRusa(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println(nombre + " avanzando al punto " + i);
                    Thread.sleep(400); // Simula el tiempo intermedio
                }
                System.out.println(nombre + " ha completado su recorrido.");
            } catch (InterruptedException e) {
                System.err.println(nombre + " ha sido interrumpida.");
            }
        }
    }