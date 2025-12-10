package Aplicacion_cliente_servidor_Socket_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPCliente {

    //Dirección y puerto del servidor
    private static final String HOST = "localhost";
    private static final int PUERTO = 12345;

    public static void main(String[] args) {

        System.out.println("Iniciando cliente UDP...");

        //Leemos lo que introduce el usuario por teclado
        Scanner scanner = new Scanner(System.in);

        try (DatagramSocket clientSocket = new DatagramSocket()) {


            boolean keeprunning = true; //Variable para salir del bucle

            //Bucle principal del cliente
            while (keeprunning) {

                //Mostramos un menú simple
                System.out.println("\n=== MENÚ DE OPCIONES ===");
                System.out.println("1. Consultar sensor");
                System.out.println("2. Listar sensores disponibles");
                System.out.println("3. Mostrar ayuda");
                System.out.println("4. Salir");
                System.out.print("Elije una opción: ");

                //Leemos la opción que hemos elegido
                String opcion = scanner.nextLine();

                //Variable donde almacenaré el mensaje que posteriormente le mande al servidor
                String messageToSend;

                //Estructura del switch
                switch (opcion) {
                    case "1":

                        //Pedimos el nombre del sensor al usuario
                        System.out.println("Ingresa el nombre del sensor (temperatura/humedad/viento): ");
                        String sensorName = scanner.nextLine();

                        //Formato del mensaje
                        messageToSend = "@sensor#" + sensorName + "@";
                        break;

                    //Listar sensores disponibles
                    case "2":

                        System.out.println("Sensores listados: ");

                        //Formato del mensaje
                        messageToSend = "@listadoSensores@";
                        break;

                    //Solicitar ayuda
                    case "3":

                        //Formato del mensaje
                        messageToSend = "@ayuda@";
                        break;

                    //Salimos del programa
                    case "4":
                        messageToSend = "@salir@";
                        keeprunning = false;
                        break;

                    default:

                        //Si se ingresa algo no contemplado
                        System.out.println("Opción no valida, enviando comando no reconocido");
                        messageToSend = "Comando no reconocido";
                        break;
                }

                //Enviamos el mensaje al servidor
                sendMessageToServer(clientSocket, messageToSend);

                //Recibir y mostrar la respuesta del servidor

                //Recibimos la respuesta del servidor (excepto si estamos saliendo)

                if (!messageToSend.equals("@salir@")) {
                    String response = receiveMessageFromServer(clientSocket);
                    System.out.println("Respuesta del servidor: " + response);
                } else {

                    //Mensaje de despedida
                    System.out.println("Saliendo del cliente...");
                }

            }
        } catch (SocketException e) {
            System.err.println("Error al crear el socket del cliente" + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    //Método para enviar mensajes al servidor
    private static void sendMessageToServer(DatagramSocket clientSocket, String message) {
        try {
            //Convertir el mensaje a bytes
            byte[] sendBuffer = message.getBytes();

            //Obtener dirección op del servidor
            InetAddress serverIP = InetAddress.getByName(HOST);

            //Crear el datagrama de envío
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverIP, PUERTO);

            //Enviamos el datagrama
            clientSocket.send(sendPacket);

        } catch (IOException e) {
            System.err.println("Error al enviar el datagrama al servidor" + e.getMessage());
        }
    }

    private static String receiveMessageFromServer(DatagramSocket clientSocket) {
        try {

            //Preparar el buffer de recepción
            byte[] receiveBuffer = new byte[1024];

            //Preparar el datagrama de recepción
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            //Esperar la respuesta del servidor
            clientSocket.receive(receivePacket);

            //Extraer el mensaje del datagrama
            return new String(receivePacket.getData(), 0, receivePacket.getLength());


        } catch (IOException e) {
            System.err.println("Error al enviar el datagrama del servidor" + e.getMessage());
        }

        return "No se recibió respuesta del servidor";
    }
}