
package com.example.proyectorutasgrafo.algoritmos;

import com.example.proyectorutasgrafo.modelo.Conexion;
import java.util.List;

public class MedidorRendimiento {

    private static final int CALENTAMIENTOS = 5;
    private static final int REPETICIONES = 15;

    public static double medirQuickSort(List<Conexion> conexiones) {
        return medir(conexiones, true);
    }

    public static double medirShellSort(List<Conexion> conexiones) {
        return medir(conexiones, false);
    }

    private static double medir(List<Conexion> conexiones, boolean quick) {

        if (conexiones == null || conexiones.isEmpty()) {
            throw new IllegalArgumentException("No hay conexiones para ordenar");
        }

        double tiempoTotal = 0;

        for (int i = 0; i < CALENTAMIENTOS + REPETICIONES; i++) {

            List<Conexion> copia = Ordenamientos.copiar(conexiones);

            long inicio = System.nanoTime();

            if (quick) {
                Ordenamientos.quickSortPeso(copia, 0, copia.size() - 1);
            } else {
                Ordenamientos.shellSortPeso(copia);
            }

            long fin = System.nanoTime();

            for (int j = 1; j < copia.size(); j++) {
                if (copia.get(j - 1).getPeso() > copia.get(j).getPeso()) {
                    throw new IllegalStateException(
                            "El algoritmo no ordenó correctamente"
                    );
                }
            }

            if (i >= CALENTAMIENTOS) {
                tiempoTotal += (fin - inicio) / 1_000_000.0;
            }
        }

        return tiempoTotal / REPETICIONES;
    }
}
