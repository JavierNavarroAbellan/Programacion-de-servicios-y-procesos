public class ejemploCrearProceso {
    public static void main(String[] args) {
        try {
            //Ejecutar bloc de notas en windows
            Process proceso = Runtime.getRuntime().exec("notepad.exe");

            //Esperar que el usuario cierre el bloc de notas
            proceso.waitFor();
            System.out.println("El bloc de notas se cerró, Código de salida: " + proceso.exitValue());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}