package prueba;

import java.net.*;
import java.util.regex.*;
import java.io.*;

public class UDPServerMultihilo {

    private static final int PORT = 12345;
    private static final int BUFSIZE = 1024;

    private final ResultadosEncuesta resultados = new ResultadosEncuesta();

    // Patrones para validar/parsear mensajes
    private static final Pattern P_RESP = Pattern.compile("^@resp#([^#@]+)#([^@]+)@$");
    private static final Pattern P_FIN  = Pattern.compile("^@fin#([^#@]+)@$");
    private static final Pattern P_RES  = Pattern.compile("^@resultados@$");

    public void start() {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Servidor UDP multihilo escuchando en el puerto " + PORT);
            byte[] buffer = new byte[BUFSIZE];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // bloqueo hasta recibir
                // Copiamos los bytes a un nuevo array para evitar sobrescritura
                byte[] dataCopy = new byte[packet.getLength()];
                System.arraycopy(packet.getData(), packet.getOffset(), dataCopy, 0, packet.getLength());
                DatagramPacket packetCopy = new DatagramPacket(dataCopy, dataCopy.length, packet.getAddress(), packet.getPort());

                // Lanzar hilo para procesar este datagrama
                new Thread(new Handler(packetCopy, socket)).start();
            }
        } catch (IOException e) {
            System.err.println("Error en servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private class Handler implements Runnable {
        private final DatagramPacket packet;
        private final DatagramSocket socket;

        Handler(DatagramPacket packet, DatagramSocket socket) {
            this.packet = packet;
            this.socket = socket;
        }

        @Override
        public void run() {
            String mensaje = new String(packet.getData(), 0, packet.getLength()).trim();
            InetAddress addr = packet.getAddress();
            int port = packet.getPort();

            // Mostrar en consola el mensaje recibido y desde dónde
            System.out.printf("Recibido desde %s:%d -> %s%n", addr.getHostAddress(), port, mensaje);

            try {
                // Comprobar formatos
                Matcher mResp = P_RESP.matcher(mensaje);
                Matcher mFin  = P_FIN.matcher(mensaje);
                Matcher mRes  = P_RES.matcher(mensaje);

                if (mResp.matches()) {
                    String zona = mResp.group(1).trim();
                    String respuesta = mResp.group(2).trim();

                    // Almacenar respuesta (objeto sincronizado)
                    resultados.agregarRespuesta(zona, respuesta);

                    // Confirmación al cliente
                    String conf = String.format("OK: respuesta recibida. Zona='%s', Respuesta='%s'", zona, respuesta);
                    enviarRespuesta(conf, addr, port);
                } else if (mFin.matches()) {
                    String zona = mFin.group(1).trim();
                    String resumen = resultados.getResumenZona(zona);
                    enviarRespuesta(resumen, addr, port);
                } else if (mRes.matches()) {
                    String resumenGlobal = resultados.getResumenGlobal();
                    enviarRespuesta(resumenGlobal, addr, port);
                } else {
                    // Mensaje con formato inválido
                    String error = "ERROR: Formato inválido. Formatos válidos:\n"
                            + "@resp#zona#respuesta@\n"
                            + "@fin#zona@\n"
                            + "@resultados@";
                    enviarRespuesta(error, addr, port);
                }
            } catch (Exception e) {
                String err = "ERROR procesando la petición: " + e.getMessage();
                try {
                    enviarRespuesta(err, addr, port);
                } catch (IOException ioEx) {
                    System.err.println("No se pudo enviar mensaje de error al cliente: " + ioEx.getMessage());
                }
            }
        }

        private void enviarRespuesta(String texto, InetAddress addr, int port) throws IOException {
            byte[] respBytes = texto.getBytes();
            DatagramPacket respPacket = new DatagramPacket(respBytes, respBytes.length, addr, port);
            socket.send(respPacket);
            // Mostrar en consola lo que enviamos de vuelta
            System.out.printf("Enviado a %s:%d -> %s%n", addr.getHostAddress(), port, texto.replaceAll("\n", " | "));
        }
    }

    // Main
    public static void main(String[] args) {
        new UDPServerMultihilo().start();
    }
}
