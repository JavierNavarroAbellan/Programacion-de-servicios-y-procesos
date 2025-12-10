package Aplicacion_cliente_servidor_Socket_TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteChat {

    public static void main(String[] args) {

        //Dirección y puerto del servidor
        final String HOST = "localhost";
        final int PUERTO = 12345;

        //Bloque try with resources
        try (Socket socket = new Socket(HOST, PUERTO)) { //Instanciamos un objeto de la clase Socket que represente la conexion del cliente con servidor

            //Instanciamos un objeto de la clase buffered reader para leer las respuestas que envia el servidor
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Instanciamos un objeto de la clase PrintWriter para enviar mensajes al servidor
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            //Instanciamos un objeto de la clase BufferedReader para leer lo que el usuario escribe por consola
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            String mensaje, respuesta;
            System.out.println("Conectado al servidor. Escribe tus mensajes:");

            while (true) {

                //Método readline() que lee la linea completa que el usuario escribe por consola
                mensaje = teclado.readLine();

                //Si no ponemos mensaje al servidor cierra la conexión
                if (mensaje == null || mensaje.equalsIgnoreCase("salir")) break;

                //Enviamos un mensaje al servidor utilizando println
                salida.println(mensaje);

                //Leemos la respuesta del servidor
                respuesta = entrada.readLine();
                System.out.println("Servidor responde: " + respuesta);
            }

            System.out.println("Cliente desconectado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}