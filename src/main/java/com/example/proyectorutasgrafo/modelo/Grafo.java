package com.example.proyectorutasgrafo.modelo;

import java.util.*;

// Representa la estructura completa del grafo con sus vertices y aristas
public class Grafo {

    // Mapa que almacena las ubicaciones indexadas por su nombre
    private Map<String, Ubicacion> vertices = new HashMap<>();

    // Lista con todas las conexiones dirigidas del grafo
    private ArrayList<Conexion> conexiones = new ArrayList<>();

    // Agrega un nuevo vertice si el nombre es valido y no existe
    public void agregarUbicacion(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty() && !vertices.containsKey(nombre)) {
            vertices.put(nombre, new Ubicacion(nombre));
        }
    }

    // Crea una arista dirigida entre origen y destino si no existe previamente
    public void agregarConexion(String origen, String destino, int peso) {
        if (origen == null || destino == null || origen.equals(destino) || peso < 0) {
            return;
        }
        agregarUbicacion(origen);
        agregarUbicacion(destino);
        Ubicacion o = vertices.get(origen);
        Ubicacion d = vertices.get(destino);
        for (Conexion c : conexiones) {
            if (c.getOrigen().equals(o) && c.getDestino().equals(d)) {
                return;
            }
        }
        conexiones.add(new Conexion(o, d, peso));
    }

    // Crea dos aristas dirigidas opuestas para simular una conexion bidireccional
    public void agregarConexionNoDirigida(String origen, String destino, int peso) {
        agregarConexion(origen, destino, peso);
        agregarConexion(destino, origen, peso);
    }

    // Elimina la conexion directa entre el origen y destino indicados
    public void eliminarConexion(String origen, String destino) {
        conexiones.removeIf(c ->
                c.getOrigen().getNombre().equals(origen) &&
                        c.getDestino().getNombre().equals(destino)
        );
    }

    // Actualiza el peso de una conexion existente
    public void modificarPeso(String origen, String destino, int nuevoPeso) {
        for (Conexion c : conexiones) {
            if (c.getOrigen().getNombre().equals(origen) && c.getDestino().getNombre().equals(destino)) {
                c.setPeso(nuevoPeso);
                return;
            }
        }
    }

    // Renombra un vertice existente y actualiza sus referencias en las conexiones
    public void modificarNombre(String nombreActual, String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty() || vertices.containsKey(nuevoNombre)) {
            return;
        }
        Ubicacion u = vertices.get(nombreActual);
        if (u != null) {
            vertices.remove(nombreActual);
            vertices.put(nuevoNombre, new Ubicacion(nuevoNombre));
            for (Conexion c : conexiones) {
                if (c.getOrigen().getNombre().equals(nombreActual)) {
                    c.getOrigen().setNombre(nuevoNombre);
                }
                if (c.getDestino().getNombre().equals(nombreActual)) {
                    c.getDestino().setNombre(nuevoNombre);
                }
            }
        }
    }

    // Devuelve la coleccion de todas las ubicaciones del grafo
    public Collection<Ubicacion> getVertices() {
        return vertices.values();
    }

    // Devuelve la lista de todas las conexiones del grafo
    public ArrayList<Conexion> getConexiones() {
        return conexiones;
    }

    // Devuelve la cantidad total de vertices
    public int getNumeroVertices() {
        return vertices.size();
    }

    // Devuelve la cantidad total de aristas
    public int getNumeroAristas() {
        return conexiones.size();
    }

    // Exporta las conexiones del grafo a una cadena en formato CSV
    public String generarCSV() {
        StringBuilder sb = new StringBuilder("origen,destino,peso\n");
        for (Conexion c : conexiones) {
            sb.append(c.getOrigen().getNombre()).append(",")
                    .append(c.getDestino().getNombre()).append(",")
                    .append(c.getPeso()).append("\n");
        }
        return sb.toString();
    }

    // Construye un mapa con cada vertice y su lista de vecinos con pesos
    public Map<String, ArrayList<String>> obtenerListaAdyacencia() {
        Map<String, ArrayList<String>> lista = new HashMap<>();
        for (Ubicacion u : vertices.values()) {
            lista.put(u.getNombre(), new ArrayList<>());
        }
        for (Conexion c : conexiones) {
            lista.get(c.getOrigen().getNombre()).add(c.getDestino().getNombre() + "(" + c.getPeso() + ")");
        }
        return lista;
    }

    // Genera una tabla de texto que representa la matriz de adyacencia con pesos
    public String obtenerMatrizAdyacencia() {
        ArrayList<String> nombres = new ArrayList<>(vertices.keySet());
        Collections.sort(nombres);
        StringBuilder sb = new StringBuilder("Matriz de adyacencia:\n\n");
        sb.append(String.format("%-12s", ""));
        for (String n : nombres) {
            String corto = n.length() > 8 ? n.substring(0, 8) : n;
            sb.append(String.format("%-10s", corto));
        }
        sb.append("\n");
        for (String origen : nombres) {
            String corto = origen.length() > 10 ? origen.substring(0, 10) : origen;
            sb.append(String.format("%-12s", corto));
            for (String destino : nombres) {
                int peso = 0;
                for (Conexion c : conexiones) {
                    if (c.getOrigen().getNombre().equals(origen) && c.getDestino().getNombre().equals(destino)) {
                        peso = c.getPeso();
                        break;
                    }
                }
                sb.append(String.format("%-10d", peso));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
