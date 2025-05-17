package grafo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import java.util.*;

public class Prim {
    
    public List<Arista> calcularAGM(Grafo grafo) {
        // Lista para almacenar las aristas del AGM resultante
        List<Arista> agm = new ArrayList<>();
        
        // Conjunto de vértices ya incluidos en el AGM
        boolean[] incluidos = new boolean[grafo.getNumVertices()];
        
        // Cola de prioridad para seleccionar siempre la arista de menor peso
        PriorityQueue<Arista> colaPrioridad = new PriorityQueue<>();
        
        // Empezar desde el primer vértice (puede ser cualquier vértice)
        incluidos[0] = true;
        
        // Agregar todas las aristas del vértice inicial a la cola de prioridad
        colaPrioridad.addAll(grafo.obtenerAristas(0));
        
        // Mientras no hayamos incluido todos los vértices
        while (!colaPrioridad.isEmpty() && agm.size() < grafo.getNumVertices() - 1) {
            // Extraer la arista con menor peso
            Arista aristaMinima = colaPrioridad.poll();
            
            int verticeDestino = aristaMinima.getDestino();
            
            if (!incluidos[verticeDestino]) {
                agm.add(aristaMinima);
                incluidos[verticeDestino] = true;
                
                for (Arista arista : grafo.obtenerAristas(verticeDestino)) {
                    if (!incluidos[arista.getDestino()]) {
                        colaPrioridad.add(arista);
                    }
                }
            }
        }
        
        return agm;
    }
    
    public int calcularImpactoTotal(List<Arista> agm) {
        int impactoTotal = 0;
        for (Arista arista : agm) {
            impactoTotal += arista.getPeso();
        }
        return impactoTotal;
    }
}



