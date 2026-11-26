package com.example.proyectorutasgrafo.algoritmos;

import com.example.proyectorutasgrafo.modelo.*;
import java.util.*;

/**
 * Recorrido en anchura (BFS).
 */
public class BFS{
    public static List<Ubicacion> recorrer(Grafo grafo, Ubicacion inicio){
        List<Ubicacion> res=new ArrayList<>();
        Set<Ubicacion> vis=new HashSet<>();
        Queue<Ubicacion> q=new LinkedList<>();
        q.add(inicio);
        vis.add(inicio);
        while(!q.isEmpty()){
            Ubicacion a=q.poll();
            res.add(a);
            for(Conexion c:grafo.getConexiones()){
                if(c.getOrigen().equals(a)&&!vis.contains(c.getDestino())){
                    vis.add(c.getDestino());
                    q.add(c.getDestino());
                }
            }
        }
        return res;
    }
}
