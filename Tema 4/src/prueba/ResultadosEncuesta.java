package prueba;

import java.util.*;

public class ResultadosEncuesta {
    // Mapa zona -> lista de respuestas
    private final Map<String, List<String>> datos = new HashMap<>();

    // Agrega una respuesta de forma sincronizada
    public synchronized void agregarRespuesta(String zona, String respuesta) {
        List<String> lista = datos.get(zona);
        if (lista == null) {
            lista = new ArrayList<>();
            datos.put(zona, lista);
        }
        lista.add(respuesta);
    }

    // Obtiene resumen de una zona (string formateado), sincronizado
    public synchronized String getResumenZona(String zona) {
        List<String> lista = datos.get(zona);
        if (lista == null || lista.isEmpty()) {
            return "No hay respuestas registradas para la zona: " + zona;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Resumen de la zona '").append(zona).append("':\n");
        int i = 1;
        for (String r : lista) {
            sb.append(i++).append(". ").append(r).append("\n");
        }
        return sb.toString();
    }

    // Obtiene resumen global (todas las zonas), sincronizado
    public synchronized String getResumenGlobal() {
        if (datos.isEmpty()) {
            return "No hay respuestas registradas aún.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Resumen global de todas las zonas:\n");
        for (Map.Entry<String, List<String>> e : datos.entrySet()) {
            sb.append("\nZona: ").append(e.getKey()).append("\n");
            int i = 1;
            for (String r : e.getValue()) {
                sb.append("  ").append(i++).append(". ").append(r).append("\n");
            }
        }
        return sb.toString();
    }
}
