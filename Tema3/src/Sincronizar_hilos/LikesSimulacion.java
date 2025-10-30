package Sincronizar_hilos;

import java.util.concurrent.atomic.AtomicInteger;

public class LikesSimulacion {

    // Declaración del contador atómico de likes
    private static AtomicInteger contadorLikes = new AtomicInteger(0);

    // Clase Usuario
    static class Usuario extends Thread {
        private int numLikes; // Número de likes

        public Usuario(int numLikes) {
            this.numLikes = numLikes;
        }

        @Override
        public void run() {
            for (int i = 0; i < numLikes; i++) {
                // Incrementa el contador atómicamente
                int nuevoTotal = contadorLikes.incrementAndGet();
                // Muestra como van dando like
                System.out.println(getName() + " dio un like. Likes totales: " + nuevoTotal);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Número de usuarios y likes por usuario
        int numUsuarios = 3;
        int likesPorUsuario = 100;

        Thread[] usuarios = new Thread[numUsuarios];

        // Crear e iniciar los hilos
        for (int i = 0; i < numUsuarios; i++) {
            usuarios[i] = new Usuario(likesPorUsuario);
            usuarios[i].setName("Usuario " + (i+1));
            usuarios[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (int i = 0; i < numUsuarios; i++) {
            usuarios[i].join();
        }

        // Muestra el resultado final
        System.out.println("Número total de likes: " + contadorLikes.get());
    }
}