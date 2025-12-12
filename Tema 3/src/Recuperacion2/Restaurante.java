package Recuperacion2;

import java.util.concurrent.Semaphore;

public class Restaurante {
    public static void main(String[] args) {

        // Crear un objeto Semaphore para limitar a 2 clientes concurrentes
        Semaphore semaphore = new Semaphore(2);

        // Crear y ejecutar los hilos de clientes
        Thread[] clientes = new Thread[4];

        for (int i = 0; i < clientes.length; i++) {

            clientes[i] = new Thread(new ClientesReservas(semaphore, "Cliente " + (i + 1)));
            clientes[i].start();
        }

        // Sincronizar los hilos de clientes (esperar a que todos los clientes preparen las mesas)
        for (Thread cliente : clientes) {

            try {

                cliente.join(); // Esperar a que todos los clientes hayan realizado sus reservas

            } catch (InterruptedException e) {

                System.err.println("Sincronización interrumpida para clientes.");
            }
        }

        // Crear y configurar los hilos de empleados
        Thread[] empleados = new Thread[4];
        empleados[0] = new Thread(new EmpleadosMesas("Empleado 1"));
        empleados[0].setPriority(Thread.MAX_PRIORITY); // Maxima prioridad
        empleados[1] = new Thread(new EmpleadosMesas("Empleado 2"));
        empleados[1].setPriority(Thread.MIN_PRIORITY); // Minima prioridad
        empleados[2] = new Thread(new EmpleadosMesas("Empleado 3"));
        empleados[2].setPriority(Thread.NORM_PRIORITY); // Prioridad normal
        empleados[3] = new Thread(new EmpleadosMesas("Empleado 4"));
        empleados[3].setPriority(Thread.NORM_PRIORITY); // Prioridad normal

        // Iniciar los hilos de empleados
        for (Thread empleado : empleados) {
            empleado.start();
        }

        // Sincronizar los hilos de empleados (esperar a que todos los empleados gestionen sus pedidos)
        for (Thread empleado : empleados) {
            try {
                empleado.join(); // Esperar a que los empleados terminen
            } catch (InterruptedException e) {
                System.err.println("Sincronización interrumpida para empleados.");
            }
        }

        System.out.println("Todos los pedidos han sido procesados.");
    }
}