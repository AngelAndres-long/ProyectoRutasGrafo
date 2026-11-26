package com.example.proyectorutasgrafo.algoritmos;

import com.example.proyectorutasgrafo.modelo.Conexion;
import java.util.ArrayList;
import java.util.List;

/**
 * Algoritmos de ordenamiento propios.
 */
public class Ordenamientos{
    public static List<Conexion> copiar(List<Conexion> original){
        return new ArrayList<>(original);
    }

    //QuickSort por peso
    public static void quickSortPeso(List<Conexion> lista,int bajo,int alto){
        if(bajo<alto){
            int pi=particionPeso(lista,bajo,alto);
            quickSortPeso(lista,bajo,pi-1);
            quickSortPeso(lista,pi+1,alto);
        }
    }

    private static int particionPeso(List<Conexion> lista,int bajo,int alto){
        int pivot=lista.get(alto).getPeso();
        int i=bajo-1;
        for(int j=bajo;j<alto;j++){
            if(lista.get(j).getPeso()<=pivot){
                i++;
                Conexion temp=lista.get(i);
                lista.set(i,lista.get(j));
                lista.set(j,temp);
            }
        }
        Conexion temp=lista.get(i+1);
        lista.set(i+1,lista.get(alto));
        lista.set(alto,temp);
        return i+1;
    }

    //Shell Sort por peso
    public static void shellSortPeso(List<Conexion> lista){
        int n=lista.size();
        for(int gap=n/2;gap>0;gap/=2){
            for(int i=gap;i<n;i++){
                Conexion temp=lista.get(i);
                int j;
                for(j=i;j>=gap&&lista.get(j-gap).getPeso()>temp.getPeso();j-=gap){
                    lista.set(j,lista.get(j-gap));
                }
                lista.set(j,temp);
            }
        }
    }

    //QuickSort por nombre de origen
    public static void quickSortNombre(List<Conexion> lista,int bajo,int alto){
        if(bajo<alto){
            int pi=particionNombre(lista,bajo,alto);
            quickSortNombre(lista,bajo,pi-1);
            quickSortNombre(lista,pi+1,alto);
        }
    }

    private static int particionNombre(List<Conexion> lista,int bajo,int alto){
        String pivot=lista.get(alto).getOrigen().getNombre();
        int i=bajo-1;
        for(int j=bajo;j<alto;j++){
            if(lista.get(j).getOrigen().getNombre().compareTo(pivot)<=0){
                i++;
                Conexion temp=lista.get(i);
                lista.set(i,lista.get(j));
                lista.set(j,temp);
            }
        }
        Conexion temp=lista.get(i+1);
        lista.set(i+1,lista.get(alto));
        lista.set(alto,temp);
        return i+1;
    }
}
