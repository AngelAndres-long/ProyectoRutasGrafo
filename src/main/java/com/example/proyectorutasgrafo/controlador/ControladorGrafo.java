package com.example.proyectorutasgrafo.controlador;

import com.example.proyectorutasgrafo.algoritmos.*;
import com.example.proyectorutasgrafo.modelo.*;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * Controlador principal del sistema.
 */
public class ControladorGrafo{
    private Grafo grafo=new Grafo();
    private File archivoActual=null;

    public Grafo getGrafo(){
        return grafo;
    }

    public void agregarConexion(String origen,String destino,int peso){
        grafo.agregarConexion(origen,destino,peso);
    }

    public void agregarConexionNoDirigida(String origen,String destino,int peso){
        grafo.agregarConexionNoDirigida(origen,destino,peso);
    }

    public void eliminarConexion(String origen,String destino){
        grafo.eliminarConexion(origen,destino);
    }

    public void modificarPeso(String origen,String destino,int nuevoPeso){
        grafo.modificarPeso(origen,destino,nuevoPeso);
    }

    public void modificarNombre(String actual,String nuevo){
        grafo.modificarNombre(actual,nuevo);
    }

    public void cargarDesdeArchivo(File archivo){
        grafo=LectorCSV.cargarDesdeArchivo(archivo);
        archivoActual=archivo;
    }

    public void guardarCambios(){
        if(archivoActual!=null){
            try{
                FileWriter fw=new FileWriter(archivoActual);
                fw.write(grafo.generarCSV());
                fw.close();
            }catch(Exception e){}
        }
    }

    public void guardarComo(File archivo){
        try{
            FileWriter fw=new FileWriter(archivo);
            fw.write(grafo.generarCSV());
            fw.close();
            archivoActual=archivo;
        }catch(Exception e){}
    }

    public void nuevoGrafo(){
        grafo=new Grafo();
        archivoActual=null;
    }

    public String obtenerCSV(){
        return grafo.generarCSV();
    }

    public void guardarAlSalir(){
        if(archivoActual!=null){
            guardarCambios();
        }
    }

    public String obtenerListaAdyacenciaTexto(){
        StringBuilder sb=new StringBuilder("Lista de adyacencia:\n\n");
        grafo.obtenerListaAdyacencia().forEach((k,v)->
                sb.append(k).append(" → ").append(v).append("\n")
        );
        return sb.toString();
    }

    public String obtenerMatriz(){
        return grafo.obtenerMatrizAdyacencia();
    }

    public Map<Ubicacion,Integer> calcularDijkstra(String nombre){
        Ubicacion inicio=buscar(nombre);
        return inicio==null?null:Dijkstra.calcular(grafo,inicio);
    }

    public String obtenerRutaDijkstra(String origen,String destino){
        Ubicacion o=buscar(origen);
        Ubicacion d=buscar(destino);
        if(o==null||d==null){
            return "Vértice no encontrado";
        }
        Map<Ubicacion,Integer> dist=Dijkstra.calcular(grafo,o);
        Integer costo=dist.get(d);
        if(costo==null||costo==Integer.MAX_VALUE){
            return "No hay ruta";
        }
        List<Ubicacion> ruta=Dijkstra.obtenerRuta(o,d);
        StringBuilder sb=new StringBuilder();
        sb.append("Origen: ").append(origen).append("\n");
        sb.append("Destino: ").append(destino).append("\n");
        sb.append("Ruta: ");
        for(int i=0;i<ruta.size();i++){
            sb.append(ruta.get(i).getNombre());
            if(i<ruta.size()-1){
                sb.append(" → ");
            }
        }
        sb.append("\nCosto total: ").append(costo);
        return sb.toString();
    }

    public List<Ubicacion> recorrerBFS(String nombre){
        Ubicacion inicio=buscar(nombre);
        return inicio==null?null:BFS.recorrer(grafo,inicio);
    }

    public List<Ubicacion> recorrerDFS(String nombre){
        Ubicacion inicio=buscar(nombre);
        return inicio==null?null:DFS.recorrer(grafo,inicio);
    }

    public List<Conexion> ordenarPorPesoQuick(){
        List<Conexion> copia=Ordenamientos.copiar(grafo.getConexiones());
        Ordenamientos.quickSortPeso(copia,0,copia.size()-1);
        return copia;
    }

    public List<Conexion> ordenarPorPesoShell(){
        List<Conexion> copia=Ordenamientos.copiar(grafo.getConexiones());
        Ordenamientos.shellSortPeso(copia);
        return copia;
    }

    public List<Conexion> ordenarPorNombreQuick(){
        List<Conexion> copia=Ordenamientos.copiar(grafo.getConexiones());
        Ordenamientos.quickSortNombre(copia,0,copia.size()-1);
        return copia;
    }

    private Ubicacion buscar(String nombre){
        for(Ubicacion u:grafo.getVertices()){
            if(u.getNombre().equals(nombre)){
                return u;
            }
        }
        return null;
    }
}
