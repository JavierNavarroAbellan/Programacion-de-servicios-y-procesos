package Examen2;

public class TiendaOnline {

    public static void main(String[] args) {

        Thread cliente1 = new Thread(new Clientes(,"Cliente 1"));
        cliente1.setPriority(Thread.MIN_PRIORITY);

        Thread cliente2 = new Thread(new Clientes(,"Cliente 2"));
        cliente2.setPriority(Thread.NORM_PRIORITY);

        Thread cliente3 = new Thread(new Clientes(,"Cliente 3"));
        cliente3.setPriority(Thread.MAX_PRIORITY);

        Thread cliente4 = new Thread(new Clientes(,"Cliente 4"));
        cliente4.setPriority(Thread.NORM_PRIORITY);

        Thread cliente5 = new Thread(new Clientes(,"Cliente 5"));
        cliente5.setPriority(Thread.MIN_PRIORITY);

        //Iniciamos la ejecución
        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();

        //Esperamos a que todos terminen
        try {
            cliente1.join();
            cliente2.join();
            cliente3.join();
            cliente4.join();

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        Thread empleado1 = new Thread(new Clientes(,"Empleado 1"));
        empleado1.setPriority(Thread.MIN_PRIORITY);

        Thread empleado2 = new Thread(new Clientes(,"Empleado 2"));
        empleado2.setPriority(Thread.NORM_PRIORITY);

        Thread empleado3 = new Thread(new Clientes(,"Empleado 3"));
        empleado3.setPriority(Thread.MAX_PRIORITY);

        Thread empleado4 = new Thread(new Clientes(,"Empleado 4"));
        empleado4.setPriority(Thread.NORM_PRIORITY);

        Thread empleado5 = new Thread(new Clientes(,"Empleado 5"));
        empleado5.setPriority(Thread.MIN_PRIORITY);


        //Iniciamos la ejecución
        empleado1.start();
        empleado2.start();
        empleado3.start();
        empleado4.start();

        //Esperamos a que todos terminen
        try {
            empleado1.join();
            empleado2.join();
            empleado3.join();
            empleado4.join();

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}