package Recuperacion2;

import java.util.concurrent.Semaphore;

public class ClientesReservas implements Runnable {
    private Semaphore semaphore;
    private String nombre;

    public ClientesReservas(Semaphore semaphore, String nombre) {
        this.semaphore = semaphore;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire(); // Adquiere un permiso de semáforo

            System.out.println(nombre + " está realizando una reserva..."); // Mensaje por consola de que el ‘Cliente X está realizando una reserva’


            Thread.sleep(1000); // Simula el tiempo de realizar la reserva (1 segundo)

            System.out.println(nombre + " ha terminado su pedido."); // Mensaje por consola ‘Cliente X ha terminado su reserva’

        } catch (InterruptedException e) {

            System.err.println(nombre + " fue interrumpido."); // Mensaje por consola sobre la interrupción

        } finally {

            semaphore.release(); // Libera el permiso del semáforo para otros clientes
        }
    }
}