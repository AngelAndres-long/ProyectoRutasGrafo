package com.example.proyectorutasgrafo.algoritmos;

import com.example.proyectorutasgrafo.modelo.*;
import java.util.*;

/**
 * Algoritmo de Dijkstra para rutas de menor costo.
 */
public class Dijkstra{
    private static Map<Ubicacion,Ubicacion> predecesores;

    public static Map<Ubicacion,Integer> calcular(Grafo grafo,Ubicacion inicio){
        Map<Ubicacion,Integer> dist=new HashMap<>();
        predecesores=new HashMap<>();
        PriorityQueue<Ubicacion> pq=new PriorityQueue<>(Comparator.comparingInt(dist::get));
        Set<Ubicacion> vis=new HashSet<>();

        for(Ubicacion u:grafo.getVertices()){
            dist.put(u,Integer.MAX_VALUE);
        }
        dist.put(inicio,0);
        pq.add(inicio);

        while(!pq.isEmpty()){
            Ubicacion actual=pq.poll();
            if(vis.contains(actual)){
                continue;
            }
            vis.add(actual);
            for(Conexion c:grafo.getConexiones()){
                if(c.getOrigen().equals(actual)){
                    Ubicacion vecino=c.getDestino();
                    int nueva=dist.get(actual)+c.getPeso();
                    if(nueva<dist.get(vecino)){
                        dist.put(vecino,nueva);
                        predecesores.put(vecino,actual);
                        pq.add(vecino);
                    }
                }
            }
        }
        return dist;
    }

    public static List<Ubicacion> obtenerRuta(Ubicacion inicio,Ubicacion destino){
        List<Ubicacion> ruta=new ArrayList<>();
        if(predecesores==null){
            return ruta;
        }
        if(!inicio.equals(destino)&&!predecesores.containsKey(destino)){
            return ruta;
        }
        Ubicacion actual=destino;
        while(actual!=null){
            ruta.add(0,actual);
            if(actual.equals(inicio)){
                break;
            }
            actual=predecesores.get(actual);
        }
        return ruta;
    }
}