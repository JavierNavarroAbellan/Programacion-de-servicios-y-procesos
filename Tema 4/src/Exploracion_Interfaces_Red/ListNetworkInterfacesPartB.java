package Exploracion_Interfaces_Red;

import java.net.*;
import java.util.*;

public class ListNetworkInterfacesPartB {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces == null) {
                System.out.println("No hay interfaces de red disponibles.");
                return;
            }
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                System.out.println("Interfaz: " + ni.getName() + " (" + (ni.isUp() ? "Activo" : "Inactivo") + ")");

                List<InterfaceAddress> dirs = ni.getInterfaceAddresses();
                if (dirs.isEmpty()) {
                    System.out.println("  No tiene direcciones asignadas.");
                }
                for (InterfaceAddress ia : dirs) {
                    InetAddress direccion = ia.getAddress();
                    if (direccion instanceof Inet4Address) {
                        System.out.println("  Dirección IPv4: " + direccion.getHostAddress());
                        System.out.println("    Máscara de red (prefijo): /" + ia.getNetworkPrefixLength());
                        InetAddress broadcast = ia.getBroadcast();
                        if (broadcast != null)
                            System.out.println("    Dirección de broadcast: " + broadcast.getHostAddress());
                    } else if (direccion instanceof Inet6Address) {
                        System.out.println("  Dirección IPv6: " + direccion.getHostAddress());
                        System.out.println("    Máscara de red (prefijo): /" + ia.getNetworkPrefixLength());
                    }
                }
                System.out.println();
            }
        } catch (SocketException e) {
            System.err.println("Error al obtener interfaces de red: " + e.getMessage());
        }
    }
}
