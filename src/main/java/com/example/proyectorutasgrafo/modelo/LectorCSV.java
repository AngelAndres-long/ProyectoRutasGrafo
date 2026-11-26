package com.example.proyectorutasgrafo.modelo;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

// Lee archivos CSV y construye el grafo aplicando validaciones
public class LectorCSV {

    // Carga un grafo desde un recurso interno del proyecto
    public static Grafo cargar(String ruta) {
        Grafo grafo = new Grafo();
        StringBuilder errores = new StringBuilder();
        int lineaNum = 1;
        int aristas = 0;
        Set<String> conexionesVistas = new HashSet<>();

        try {
            // Abre el flujo de lectura del archivo dentro de los recursos
            InputStream is = LectorCSV.class.getResourceAsStream(ruta);
            if (is == null) {
                System.out.println("Error: No se encontró el archivo");
                return grafo;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linea = br.readLine();
            lineaNum++;

            // Valida que la cabecera contenga las columnas esperadas
            if (linea == null || !linea.toLowerCase().contains("origen") || !linea.toLowerCase().contains("destino") || !linea.toLowerCase().contains("peso")) {
                errores.append("Error: El archivo no tiene las columnas requeridas\n");
            }

            // Recorre cada linea del archivo para extraer las conexiones
            while ((linea = br.readLine()) != null) {
                lineaNum++;
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",");

                // Verifica que la fila tenga los tres campos minimos
                if (partes.length < 3) {
                    errores.append("Línea ").append(lineaNum).append(": Registro incompleto\n");
                    continue;
                }

                String origen = partes[0].trim();
                String destino = partes[1].trim();
                String pesoStr = partes[2].trim();

                // Descarta filas con nombres de vertices vacios
                if (origen.isEmpty() || destino.isEmpty()) {
                    errores.append("Línea ").append(lineaNum).append(": Vértice vacío\n");
                    continue;
                }

                // Convierte el peso a entero y valida que no sea negativo
                int peso;
                try {
                    peso = Integer.parseInt(pesoStr);
                    if (peso < 0) {
                        errores.append("Línea ").append(lineaNum).append(": Peso negativo\n");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    errores.append("Línea ").append(lineaNum).append(": Peso no numérico\n");
                    continue;
                }

                // Evita agregar conexiones repetidas
                String clave = origen + "->" + destino;
                if (conexionesVistas.contains(clave)) {
                    errores.append("Línea ").append(lineaNum).append(": Conexión duplicada\n");
                    continue;
                }
                conexionesVistas.add(clave);

                // Agrega la conexion valida al grafo
                grafo.agregarConexion(origen, destino, peso);
                aristas++;
            }
            br.close();

            // Imprime resumen de carga y errores encontrados en consola
            System.out.println("Vértices: " + grafo.getNumeroVertices() + " | Aristas: " + aristas);
            if (errores.length() > 0) {
                System.out.println("Errores:\n" + errores);
            }
        } catch (Exception e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
        return grafo;
    }

    // Carga un grafo desde un archivo externo seleccionado por el usuario
    public static Grafo cargarDesdeArchivo(File archivo) {
        Grafo grafo = new Grafo();
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea = br.readLine();

            // Lee linea por linea omitiendo la cabecera y valida datos basicos
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",");
                if (partes.length >= 3) {
                    String origen = partes[0].trim();
                    String destino = partes[1].trim();
                    try {
                        int peso = Integer.parseInt(partes[2].trim());
                        if (peso >= 0 && !origen.isEmpty() && !destino.isEmpty()) {
                            grafo.agregarConexion(origen, destino, peso);
                        }
                    } catch (NumberFormatException e) {}
                }
            }
            br.close();
        } catch (Exception e) {}
        return grafo;
    }
}