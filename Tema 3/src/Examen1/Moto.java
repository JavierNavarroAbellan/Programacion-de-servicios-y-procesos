package Examen1;

import java.util.concurrent.Semaphore;

public class Moto extends Thread {

    private Semaphore semaphore;
    private String nombre;
    private int fase;

    public Moto(Semaphore semaphore, String nombre, int fase) {
        this.semaphore = semaphore;
        this.nombre = nombre;
        this.fase = fase;
    }

    @Override
    public void run() {
        try {
            // Adquiere un permiso de semáforo
            semaphore.acquire();

            // Mensaje por consola de que el ‘Coche X está llegando a la fase Y’
            System.out.println("Moto " + nombre + " está llegando a la fase " +  fase);

            // Simula el tiempo de progresión (0,5 segundos)
            int tiempo = (int)(Math.random() * 500);
            Thread.sleep(tiempo);

            // Mensaje por consola ‘Coche X ha llegado a la fase Y’
            System.out.println("La moto " + nombre + " ha llegado a la fase " + fase + " tras " + tiempo / 1000 + " segundos.");

        } catch (InterruptedException e) {

            //Mensaje por consola sobre la interrupción
            e.printStackTrace();

        } finally {

            // Libera el permiso del semáforo
            semaphore.release();
        }
    }
}