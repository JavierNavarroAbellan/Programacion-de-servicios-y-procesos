package Prueba;

import java.net.*;
import java.util.*;

public class ListNetworkInterfacesPartA {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (!interfaces.hasMoreElements()) {
                System.out.println("No hay interfaces de red disponibles.");
                return;
            }
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                String nombre = ni.getName();
                boolean estaActiva = ni.isUp();
                System.out.println("Nombre: " + nombre + ", Estado: " + (estaActiva ? "Activo" : "Inactivo"));
            }
        } catch (SocketException e) {
            System.err.println("Error al obtener interfaces de red: " + e.getMessage());
        }
    }
}