package Comunicacion_Sincronizacion_Hilos;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class IntercambioHilosDemoCola {

    private static class Productor implements Runnable {
        private BlockingQueue<Integer> cola;

        public Productor(BlockingQueue<Integer> cola) {
            this.cola = cola;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= 5; i++) {
                    int numero = (int) (Math.random() * 100) + 1;
                    cola.put(numero);
                    System.out.println("Productor: generé dato " + numero);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static class Consumidor implements Runnable {
        private BlockingQueue<Integer> cola;

        public Consumidor(BlockingQueue<Integer> cola) {
            this.cola = cola;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= 5; i++) {
                    int numero = cola.take();
                    System.out.println("Consumidor: procesé dato " + numero);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> cola = new ArrayBlockingQueue<>(1);
        Thread productor = new Thread(new Productor(cola));
        Thread consumidor = new Thread(new Consumidor(cola));

        productor.start();
        consumidor.start();
    }
}