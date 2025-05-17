package grafo;

import java.util.*;

import model.UnionFind;

public class Kruskal {
    
    
    public List<Arista> calcularAGM(Grafo grafo) {
        List<Arista> agm = new ArrayList<>();
        List<Arista> todasAristas = obtenerTodasAristas(grafo);
        
        // Ordenar aristas por peso de menor a mayor
        Collections.sort(todasAristas);
        
        UnionFind uf = new UnionFind(grafo.getNumVertices());
        
        for (Arista arista : todasAristas) {
            int rootOrigen = uf.find(arista.getOrigen());
            int rootDestino = uf.find(arista.getDestino());
            
            if (rootOrigen != rootDestino) {
                agm.add(arista);
                uf.union(arista.getOrigen(), arista.getDestino());
                
                // Terminar si ya tenemos V-1 aristas
                if (agm.size() == grafo.getNumVertices() - 1) {
                    break;
                }
            }
        }
        
        return agm;
    }
    
    /**
     * Obtiene todas las aristas del grafo (sin duplicados)
     */
    private List<Arista> obtenerTodasAristas(Grafo grafo) {
        List<Arista> aristas = new ArrayList<>();
        // Usamos un conjunto para evitar procesar aristas duplicadas
        Set<String> aristasProcesadas = new HashSet<>();
        
        for (int i = 0; i < grafo.getNumVertices(); i++) {
            for (Arista arista : grafo.obtenerAristas(i)) {
                String clave = Math.min(arista.getOrigen(), arista.getDestino()) + "-" + 
                              Math.max(arista.getOrigen(), arista.getDestino());
                
                if (!aristasProcesadas.contains(clave)) {
                    aristas.add(arista);
                    aristasProcesadas.add(clave);
                }
            }
        }
        
        return aristas;
    }
    
    /**
     * Calcula el impacto ambiental total del AGM
     */
    public int calcularImpactoTotal(List<Arista> agm) {
        return agm.stream().mapToInt(Arista::getPeso).sum();
    }
}