package Sincronizar_hilos_semaforo;

import java.util.concurrent.Semaphore;

public class SalaVideojuegos {

    // Semaphore que permite máximo 4 jugadores simultáneos
    private static final Semaphore salaDisponible = new Semaphore(4);

    // Clase interna Jugador que implementa Runnable
    static class Jugador implements Runnable {
        private final int id;

        public Jugador(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.println("Jugador " + id + " intentando entrar en la sala.");
                salaDisponible.acquire(); // intentar entrar (adquirir permiso)
                System.out.println("Jugador " + id + " ha entrado en la sala.");

                // Simular tiempo en la sala entre 2 y 5 segundos
                int tiempo = (int)(Math.random() * 3000) + 2000;
                Thread.sleep(tiempo);
                System.out.println("Jugador " + id + " ha salido de la sala tras " + tiempo / 1000 + " segundos.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                salaDisponible.release(); // liberar permiso
            }
        }
    }

    public static void main(String[] args) {
        Thread[] jugadores = new Thread[10];

        // Crear 10 jugadores (hilos)
        for (int i = 0; i < 10; i++) {
            jugadores[i] = new Thread(new Jugador(i+1));
        }

        // Lanzar todos los hilos
        for (Thread jugador : jugadores) {
            jugador.start();
        }

        // Esperar a que terminen todos
        for (Thread jugador : jugadores) {
            try {
                jugador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Simulación finalizada.");
    }
}