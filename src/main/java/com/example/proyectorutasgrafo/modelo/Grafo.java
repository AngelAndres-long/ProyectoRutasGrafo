package com.example.proyectorutasgrafo.modelo;

import java.util.*;

/**
 * Representa el grafo de ubicaciones.
 */
public class Grafo{

    private Map<String,Ubicacion> vertices=new HashMap<>();
    private List<Conexion> conexiones=new ArrayList<>();

    public void agregarUbicacion(String nombre){
        if(nombre!=null&&!nombre.trim().isEmpty()&&!vertices.containsKey(nombre)){
            vertices.put(nombre,new Ubicacion(nombre));
        }
    }

    public void agregarConexion(String origen,String destino,int peso){
        if(origen==null||destino==null||origen.equals(destino)||peso<0){
            return;
        }
        agregarUbicacion(origen);
        agregarUbicacion(destino);
        Ubicacion o=vertices.get(origen);
        Ubicacion d=vertices.get(destino);
        for(Conexion c:conexiones){
            if(c.getOrigen().equals(o)&&c.getDestino().equals(d)){
                return;
            }
        }
        conexiones.add(new Conexion(o,d,peso));
    }

    public void agregarConexionNoDirigida(String origen,String destino,int peso){
        agregarConexion(origen,destino,peso);
        agregarConexion(destino,origen,peso);
    }

    public void eliminarConexion(String origen,String destino){
        conexiones.removeIf(c->
                c.getOrigen().getNombre().equals(origen)&&
                        c.getDestino().getNombre().equals(destino)
        );
    }

    public void modificarPeso(String origen,String destino,int nuevoPeso){
        for(Conexion c:conexiones){
            if(c.getOrigen().getNombre().equals(origen)&&c.getDestino().getNombre().equals(destino)){
                c.setPeso(nuevoPeso);
                return;
            }
        }
    }

    public void modificarNombre(String nombreActual,String nuevoNombre){
        if(nuevoNombre==null||nuevoNombre.trim().isEmpty()||vertices.containsKey(nuevoNombre)){
            return;
        }
        Ubicacion u=vertices.get(nombreActual);
        if(u!=null){
            vertices.remove(nombreActual);
            vertices.put(nuevoNombre,new Ubicacion(nuevoNombre));
            for(Conexion c:conexiones){
                if(c.getOrigen().getNombre().equals(nombreActual)){
                    c.getOrigen().setNombre(nuevoNombre);
                }
                if(c.getDestino().getNombre().equals(nombreActual)){
                    c.getDestino().setNombre(nuevoNombre);
                }
            }
        }
    }

    public Collection<Ubicacion> getVertices(){
        return vertices.values();
    }

    public List<Conexion> getConexiones(){
        return conexiones;
    }

    public int getNumeroVertices(){
        return vertices.size();
    }

    public int getNumeroAristas(){
        return conexiones.size();
    }

    public String generarCSV(){
        StringBuilder sb=new StringBuilder("origen,destino,peso\n");
        for(Conexion c:conexiones){
            sb.append(c.getOrigen().getNombre()).append(",")
                    .append(c.getDestino().getNombre()).append(",")
                    .append(c.getPeso()).append("\n");
        }
        return sb.toString();
    }

    public Map<String,List<String>> obtenerListaAdyacencia(){
        Map<String,List<String>> lista=new HashMap<>();
        for(Ubicacion u:vertices.values()){
            lista.put(u.getNombre(),new ArrayList<>());
        }
        for(Conexion c:conexiones){
            lista.get(c.getOrigen().getNombre()).add(c.getDestino().getNombre()+"("+c.getPeso()+")");
        }
        return lista;
    }

    public String obtenerMatrizAdyacencia(){
        List<String> nombres=new ArrayList<>(vertices.keySet());
        Collections.sort(nombres);
        StringBuilder sb=new StringBuilder("Matriz de adyacencia:\n\n");
        sb.append(String.format("%-12s",""));
        for(String n:nombres){
            String corto=n.length()>8?n.substring(0,8):n;
            sb.append(String.format("%-10s",corto));
        }
        sb.append("\n");
        for(String origen:nombres){
            String corto=origen.length()>10?origen.substring(0,10):origen;
            sb.append(String.format("%-12s",corto));
            for(String destino:nombres){
                int peso=0;
                for(Conexion c:conexiones){
                    if(c.getOrigen().getNombre().equals(origen)&&c.getDestino().getNombre().equals(destino)){
                        peso=c.getPeso();
                        break;
                    }
                }
                sb.append(String.format("%-10d",peso));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
