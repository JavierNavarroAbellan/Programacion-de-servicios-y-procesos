package Examen1;

import java.util.concurrent.Semaphore;

public class Coche implements Runnable {

    private Semaphore semaphore;
    private String nombre;
    private int fase;

    public Coche(Semaphore semaphore, String nombre, int fase) {
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
            System.out.println("Coche " + nombre + " está llegando a la fase " +  fase);

            // Simula el tiempo de progresión (0,45 segundos)
            int tiempo = (int)(Math.random() * 450);
            Thread.sleep(tiempo);

            // Mensaje por consola ‘Coche X ha llegado a la fase Y’
            System.out.println("El coche " + nombre + " ha llegado a la fase " + fase + " tras " + tiempo / 1000 + " segundos.");

        } catch (InterruptedException e) {

            //Mensaje por consola sobre la interrupción
            e.printStackTrace();

        } finally {

            // Libera el permiso del semáforo
            semaphore.release();
        }
    }
}