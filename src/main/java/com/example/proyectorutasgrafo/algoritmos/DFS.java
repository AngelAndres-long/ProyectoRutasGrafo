package com.example.proyectorutasgrafo.algoritmos;

import com.example.proyectorutasgrafo.modelo.*;
import java.util.*;

/**
 * Recorrido en profundidad (DFS).
 */
public class DFS{
    public static List<Ubicacion> recorrer(Grafo grafo, Ubicacion inicio){
        List<Ubicacion> res=new ArrayList<>();
        Set<Ubicacion> vis=new HashSet<>();
        dfs(grafo,inicio,vis,res);
        return res;
    }

    private static void dfs(Grafo grafo,Ubicacion actual,Set<Ubicacion> vis,List<Ubicacion> res){
        vis.add(actual);
        res.add(actual);
        for(Conexion c:grafo.getConexiones()){
            if(c.getOrigen().equals(actual)&&!vis.contains(c.getDestino())){
                dfs(grafo,c.getDestino(),vis,res);
            }
        }
    }
}
