class Hilo implements Runnable
 {
private final String nombre;
     Hilo(String nombre)
{
    // TODO Auto-generatedconstructor stub
    this.nombre=nombre;
}
@Override
public void run()
{
    System.out.printf("Hola, soy el hilo: %s.\n",this.nombre);
            System.out.printf("Hilo %s terminado.\n",this.nombre);

}

}
public class crearHiloRunnable
{
    public static void main(String[] args)
    {
        //Get the current thread

        //Thread thread=Thread.currentThread();

        //System.out.println(thread);

        Thread h1=new Thread(new Hilo("H1"));
        Thread h2=new Thread(new Hilo("H2"));
        h1.start();
        h2.start();
        System.out.println("Hilo principal terminado.");
    }
}
