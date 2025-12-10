package Aplicacion_cliente_servidor_Socket_UDP;

import java.io.IOException;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {

        // Definimos el puerto en el que el servidor estará escuchando conexiones
        final int SERVER_PORT = 12345;

        // Bloque try-with-resources, para crear el DatagramSocket como recurso
        try (DatagramSocket socket = new DatagramSocket(SERVER_PORT)) {
            System.out.println("Servidor UDP iniciado en el puerto " + SERVER_PORT);

            // Bucle infinito: el servidor siempre escucha
            while (true) {
                System.out.println("Esperando cliente...");

                //Preparamos un buffer para recibir el mensaje del cliente
                byte[] receivebuffer = new byte[1024];


                //Creamos un DatagramPacket para almacenar el datagrama de recepción
                DatagramPacket receivePacket = new DatagramPacket(receivebuffer, receivebuffer.length);

                //Esperar hasta recibir petición del cliente
                System.out.println("Iniciando cliente...");
                socket.receive(receivePacket);

                //Extremos el mensaje en formato string del paquete recibido
                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Mensaje recibido desde el cliente" + clientMessage);

                //Procesamos o analizamos el mensaje para identificar la respuesta adecuada
                String serverResponse = processMessage(clientMessage);

                //Preparamos la respuesta del servidor convirtiéndola en bytes para su envío
                byte[] sendbuffer = serverResponse.getBytes();

                //Enviar respuesta (sendBuffer, la longitud de los datos, ip del cliente, puerto del destinatario
                DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, receivePacket.getAddress(), receivePacket.getPort());

                socket.send(sendPacket);
                System.out.println("Enviando al cliente... " + serverResponse);
            }

        } catch (SocketException e) {

            System.out.println("Error al crear el socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }

    private static String processMessage(String message) {

        //Convertimos todo el mensaje a minúsculas para procesarlo mejor
        String lowerCaseMessage = message.toLowerCase();

        //Manejamos los distintos comandos
        if (lowerCaseMessage.contains("@sensor#")) {

            //Extraemos el nombre del sensor
            return handleSensorMessage(message);

        } else if (lowerCaseMessage.equals("@listadosensores@")) {
            return "Sensores disponibles: temperatura, humedad y viento";

        } else if (lowerCaseMessage.equals("@ayuda@")) {
            return getHelpMessage();

        } else if (lowerCaseMessage.equals("@salir@")) {
            return "Saliendo";

        } else {
            return "Comando no reconocido, escribe @ayuda@ para más información.";
        }
    }

    //Procesamiento de solicitudes
    private static String handleSensorMessage(String fullMessage) {

        String[] parts = fullMessage.split("#");

        if (parts.length < 2) {
            return "Formato de solicitud de sendor invalido, usa @sensor#nombreSensor@";
        }

        //Extraemos el nombre del sensor
        String sensorPart = parts[1];

        //A veces viene con el @ al final, lo quitamos
        String sensorName = sensorPart.replace("@", "").toLowerCase();

        switch(sensorName) {

            case "temperatura":
                //Temperatura entre 20 y 35
                int temp = 20 + (int) (Math.random() * 16);
                return "Temperatura: " + temp + "ºC";

            case "humedad":

                //Humedad de 0 a 100%
                int hum = (int) (Math.random() * 101);
                return "Humedad: " + hum + "%";

            case "viento":

                //Velocidad del viento en Km/h
                int viento = (int) (Math.random() * 100);
                return "Velocidad del viento: " + viento + "Km/h";

            default:
                return "Sensor " + sensorName + " no reconocido";
        }
    }

    private static String getHelpMessage() {
        return "Consulta en la guia de ayuda para mas información";
    }
}