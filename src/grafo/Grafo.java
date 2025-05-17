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

/*
public class Grafo {
    
	private ArrayList<HashSet<Arista>> vecinos;
    
    public Grafo(int vertice)
	{
		 vecinos = new ArrayList<>();
	        for (int i = 0; i < vertice; i++) {
	            vecinos.add(new HashSet<>());
		}
	}
    
    

    public void agregarArista(int desde, int hacia, int peso)
	{
		verificarArista(desde);
		verificarArista(hacia);
		
		Arista edge = new Arista(desde, hacia, peso);
		Arista reverse = new Arista(desde, hacia, peso);

		vecinos.get(desde).add(edge);
	    vecinos.get(hacia).add(reverse); 
		
	}
    
    public boolean aristaExiste(int desde, int hacia) {
		verificarArista(desde);
		verificarArista(hacia);

		for(Arista arista : vecinos.get(desde)) {
			if(arista.getDestino() == hacia) {
				return true;
			}
		}
		return false;
	}
    
    public Set<Arista> getAristasDesde(int nodo) {
        verificarArista(nodo);
        return vecinos.get(nodo);
    }
    
    private void verificarArista(int i)
	{
		if( i < 0 )
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
		
		if( i >= tamano() )
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}
    
    public int tamano()
	{
		return vecinos.size();
	}
	
	public Set<Integer> getVecinos(int i) {
        verificarArista(i);
        
        Set<Integer> ret = new HashSet<>();
        for(Arista arista : vecinos.get(i)) {
        	ret.add((Integer) arista.getDestino());
        }
        return ret;
	}
    
}*/

