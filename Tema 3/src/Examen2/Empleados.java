package Examen2;

import java.util.concurrent.Semaphore;

public class Empleados implements Runnable{

    private Semaphore semaphore;
    private String nombre;

    public Empleados(Semaphore semaphore, String nombre) {
        this.semaphore = semaphore;
        this.nombre = nombre;
    }
    @Override
    public void run() {
        try {
            // Adquiere un permiso de semáforo
            semaphore.acquire();

            // Mensaje por consola de que el ‘Empleado X está procesando un pedido’
            System.out.println("Empleado " + nombre + " está procesando un pedido.");

            // Simula el tiempo de preparar el pedido (1,5 segundos)
            int tiempo = (int)(Math.random() * 1500);
            Thread.sleep(tiempo);
            // Mensaje por consola ‘Empleado X ha terminado de preparar su pedido’
            System.out.println("El empleado " + nombre + " ha terminado de procesar el pedido tras " + tiempo / 1000 + " segundos.");

        } catch (InterruptedException e) {

            //Mensaje por consola sobre la interrupción
            e.printStackTrace();

        } finally {

            // Libera el permiso del semáforo
            semaphore.release();
        }
    }
}