package com.example.proyectorutasgrafo.modelo;

/**
 * Representa una conexión entre dos ubicaciones.
 */
public class Conexion{

    private Ubicacion origen;
    private Ubicacion destino;
    private int peso;

    public Conexion(Ubicacion origen,Ubicacion destino,int peso){
        this.origen=origen;
        this.destino=destino;
        this.peso=peso;
    }

    public Ubicacion getOrigen(){
        return origen;
    }

    public Ubicacion getDestino(){
        return destino;
    }

    public int getPeso(){
        return peso;
    }

    public void setPeso(int peso){
        this.peso=peso;
    }
}
