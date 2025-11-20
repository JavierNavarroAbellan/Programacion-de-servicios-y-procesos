package Examen2;

import java.util.concurrent.Semaphore;

public class Clientes implements Runnable {

    private Semaphore semaphore;
    private String nombre;

    public Clientes(Semaphore semaphore, String nombre) {
        this.semaphore = semaphore;
        this.nombre = nombre;
    }
    @Override
    public void run() {
        try {
            // Adquiere un permiso de semáforo
            semaphore.acquire();

            // Mensaje por consola de que el ‘Cliente X está realizando un pedido’
            System.out.println("Cliente " + nombre + " está realizando un pedido.");

            // Simula el tiempo de realizar el pedido (1 segundo)
            int tiempo = (int)(Math.random() * 1000);
            Thread.sleep(tiempo);
            // Mensaje por consola ‘Cliente X ha terminado su pedido’
            System.out.println("El cliente " + nombre + " ha realizado su pedido tras " + tiempo / 1000 + " segundos.");

        } catch (InterruptedException e) {

            //Mensaje por consola sobre la interrupción
            e.printStackTrace();

        } finally {

            // Libera el permiso del semáforo para otros clientes
            semaphore.release();
        }
    }
}