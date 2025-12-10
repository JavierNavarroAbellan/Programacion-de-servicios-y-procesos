package prueba;

import java.net.*;
import java.io.*;

public class UDPClientEncuesta {

    private static final int SERVER_PORT = 12345;
    private static final String SERVER_HOST = "localhost";
    private static final int BUFSIZE = 4096;
    private static final int TIMEOUT_MS = 5000; // espera por respuesta

    private DatagramSocket socket;
    private InetAddress serverAddress;

    public UDPClientEncuesta() throws UnknownHostException, SocketException {
        serverAddress = InetAddress.getByName(SERVER_HOST);
        socket = new DatagramSocket();
        socket.setSoTimeout(TIMEOUT_MS);
    }

    // Envía mensaje y espera respuesta (imprime envío y respuesta)
    public String sendAndReceive(String msg) throws IOException {
        byte[] sendData = msg.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
        System.out.println("ENVIANDO: " + msg);
        socket.send(sendPacket);

        byte[] recvBuf = new byte[BUFSIZE];
        DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
        try {
            socket.receive(recvPacket); // espera respuesta
            String resp = new String(recvPacket.getData(), 0, recvPacket.getLength());
            System.out.println("RECIBIDO: " + resp);
            return resp;
        } catch (SocketTimeoutException e) {
            String err = "ERROR: tiempo de espera (" + TIMEOUT_MS + " ms) sin respuesta del servidor.";
            System.err.println(err);
            throw e;
        }
    }

    public void close() {
        if (socket != null && !socket.isClosed()) socket.close();
    }

    public static void main(String[] args) {
        try {
            UDPClientEncuesta client = new UDPClientEncuesta();

            // Simulación: 2 zonas, mínimo 2 respuestas por zona
            // Zona 1
            client.sendAndReceive("@resp#zona1#Me encanta el servicio@");
            client.sendAndReceive("@resp#zona1#Podría mejorar el tiempo de espera@");
            client.sendAndReceive("@fin#zona1@"); // pedimos resumen de zona1

            // Zona 2
            client.sendAndReceive("@resp#zona2#Muy buena atención@");
            client.sendAndReceive("@resp#zona2#Precios adecuados@");
            client.sendAndReceive("@fin#zona2@"); // pedimos resumen de zona2

            // Pedimos resumen global
            client.sendAndReceive("@resultados@");

            client.close();
        } catch (Exception e) {
            System.err.println("Cliente: error -> " + e.getMessage());
            e.printStackTrace();
        }
    }
}
