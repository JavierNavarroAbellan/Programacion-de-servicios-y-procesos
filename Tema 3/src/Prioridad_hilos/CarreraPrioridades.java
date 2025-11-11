package Prioridad_hilos;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CarreraPrioridades {

    public static void main(String[] args) {
    //Lista linkeada a la que hay en la clase Corredor
    List<String> metaCompartida = Collections.synchronizedList(new LinkedList<>());

    //Creamos los hilos (corredores) con sus prioridades
    Thread corredor1 = new Thread(new Corredor("Corredor 1", metaCompartida));
    corredor1.setPriority(Thread.MIN_PRIORITY);

    Thread corredor2 = new Thread(new Corredor("Corredor 2", metaCompartida));
    corredor2.setPriority(Thread.NORM_PRIORITY);

    Thread corredor3 = new Thread(new Corredor("Corredor 3", metaCompartida));
    corredor3.setPriority(Thread.MAX_PRIORITY);

    Thread corredor4 = new Thread(new Corredor("Corredor 4", metaCompartida));
    corredor4.setPriority(Thread.NORM_PRIORITY);

    Thread corredor5 = new Thread(new Corredor("Corredor 5", metaCompartida));
    corredor5.setPriority(Thread.MIN_PRIORITY);

    //Iniciamos la ejecución
        corredor1.start();
        corredor2.start();
        corredor3.start();
        corredor4.start();
        corredor5.start();

    //Esperamos a que todos terminen

        try {
            corredor1.join();
            corredor2.join();
            corredor3.join();
            corredor4.join();
            corredor5.join();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        //Mostramos resultados
        System.out.println("\nOrden de llegada:");

        //Recorremos la lista compartida para imprimir la clasificación
        for (int i = 0; i < metaCompartida.size(); i++) {

            System.out.println((i + 1) + " " + metaCompartida.get(i));
        }

    }
}