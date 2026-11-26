
package com.example.proyectorutasgrafo.modelo;

public class ResultadosPrueba {

    private String algoritmo;
    private String criterio;
    private double tiempo;
    private int cantidadConexiones;

    public ResultadosPrueba(String algoritmo, String criterio, double tiempo, int cantidadConexiones) {
        this.algoritmo = algoritmo;
        this.criterio = criterio;
        this.tiempo = tiempo;
        this.cantidadConexiones = cantidadConexiones;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public String getCriterio() {
        return criterio;
    }

    public double getTiempo() {
        return tiempo;
    }

    public int getCantidadConexiones() {
        return cantidadConexiones;
    }
}
