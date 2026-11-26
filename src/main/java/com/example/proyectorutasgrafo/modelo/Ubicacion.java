package com.example.proyectorutasgrafo.modelo;

// Representa un nodo o punto dentro del grafo
public class Ubicacion {

    // Nombre identificador de la ubicacion
    private String nombre;

    // Constructor que inicializa la ubicacion con su nombre
    public Ubicacion(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve el nombre de la ubicacion
    public String getNombre() {
        return nombre;
    }

    // Modifica el nombre de la ubicacion
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Compara dos ubicaciones segun su nombre
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        return nombre.equals(((Ubicacion) o).nombre);
    }

    // Genera el codigo hash basado en el nombre para estructuras como HashMap
    @Override
    public int hashCode() {
        return nombre.hashCode();
    }

    // Devuelve el nombre al imprimir el objeto
    @Override
    public String toString() {
        return nombre;
    }
}