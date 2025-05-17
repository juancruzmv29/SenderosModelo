package grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Parador;


import java.util.*;

public class Grafo {
    private List<List<Arista>> adyacencias;
    private int numVertices;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.adyacencias = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adyacencias.add(new ArrayList<>());
        }
    }

    public void agregarArista(int origen, int destino, int peso) {
        verificarVertice(origen);
        verificarVertice(destino);
        adyacencias.get(origen).add(new Arista(origen, destino, peso));
        adyacencias.get(destino).add(new Arista(destino, origen, peso)); // Grafo no dirigido
    }

    public List<Arista> obtenerAristas(int vertice) {
        verificarVertice(vertice);
        return Collections.unmodifiableList(adyacencias.get(vertice));
    }

    public int getNumVertices() {
        return numVertices;
    }

    private void verificarVertice(int vertice) {
        if (vertice < 0 || vertice >= numVertices) {
            throw new IllegalArgumentException("Vértice inválido: " + vertice);
        }
    }
}


