package Aplicacion_cliente_servidor_Socket_TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorChat {
    public static void main(String[] args) {

        // Definimos el puerto en el que el servidor estará escuchando conexiones
        final int PUERTO = 12345;

        // Bloque try-with-resources, para crear el ServerSocket como recurso
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            // Bucle infinito: el servidor siempre escucha
            while (true) {
                System.out.println("Esperando cliente...");

                // Bloquear hasta que un cliente se conecta
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado desde : " + cliente.getInetAddress().getHostAddress());

                // try-with-resources para los streams de entrada/salida
                try (
                        BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                        PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)
                ) {

                    // Bucle para leer mensajes del cliente mientras esté conectado
                    String mensaje;
                    while ((mensaje = entrada.readLine()) != null) {

                        // 1. Mostrar mensaje recibido
                        System.out.println("Cliente dice: " + mensaje);

                        // 2. Enviar respuesta al cliente
                        salida.println("Mensaje recibido: " + mensaje);
                    }
                } catch (IOException ex) {
                    System.out.println("Error de comunicación con el cliente: " + ex.getMessage());
                } finally {

                    // Cerramos el socket del cliente
                    try {
                        cliente.close();
                    } catch (IOException ex) {
                        System.out.println("Error al cerrar el cliente: " + ex.getMessage());
                    }
                }
            }
        } catch (IOException ex) {

            // Error al iniciar el servidor
            System.out.println("Error al iniciar el servidor: " + ex.getMessage());
        }
    }
}
