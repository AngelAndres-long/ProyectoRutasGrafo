package com.example.proyectorutasgrafo.modelo;

/**
 * Representa una conexión entre dos ubicaciones.
 */
public class Conexion{

    // Punto de partida de la conexion
    private Ubicacion origen;

    // Punto de llegada de la conexion
    private Ubicacion destino;

    // Costo, distancia o tiempo asociado al recorrido
    private int peso;

    // Constructor que inicializa origen, destino y peso de la ruta
    public Conexion(Ubicacion origen, Ubicacion destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    // Devuelve la ubicacion de origen
    public Ubicacion getOrigen() {
        return origen;
    }

    // Devuelve la ubicacion de destino
    public Ubicacion getDestino() {
        return destino;
    }

    // Devuelve el peso de la conexion
    public int getPeso() {
        return peso;
    }

    // Modifica el peso de la conexion
    public void setPeso(int peso) {
        this.peso = peso;
    }
}