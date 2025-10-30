package Prioridad_hilos;

import java.util.List;

public class Corredor implements Runnable {

    // Atributos
    private final String nombre;
    // Lista compartida para registrar el orden de llegada
    private final List<String> metaCompartida;

    // Constructor para inicializar el nombre de la lista compartida
    public Corredor(String nombre, List<String> metaCompartida) {
        this.nombre = nombre;
        this.metaCompartida = metaCompartida;
    }

    @Override
    public void run() {
        // Determinar el tiempo de espera entre etapas en función de la prioridad del hilo
        int sleepTime;
        switch (Thread.currentThread().getPriority()) {
            case Thread.MIN_PRIORITY:
                sleepTime = 1200; //Lento
                break;
            case Thread.NORM_PRIORITY:
                sleepTime = 1000; //Normal
                break;
            case Thread.MAX_PRIORITY:
                sleepTime = 700;  //Rápido
                break;
            default:
                sleepTime = 1000; //Por defecto
        }

        // Simular el avance en 10 etapas intermedias de la carrera
        for (int i = 1; i <= 10; i++) {
            System.out.println(nombre + " ha avanzado al punto " + i);

            try {
                // Simula el tiempo de carrera, modificando velocidad según prioridad
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Registrar la llegada al final de la carrera en la lista sincronizada
        synchronized (metaCompartida) {
            metaCompartida.add(nombre);
        }

        System.out.println(nombre + " ha llegado a la meta.");
    }
}