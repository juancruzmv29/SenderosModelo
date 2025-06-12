package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import grafo.Arista;
import grafo.Grafo;

import java.util.*;

public class Prim {

	public List<Arista> calcularAGM(Grafo grafo) {
		int numVertices = grafo.getNumVertices();
		boolean[] visitado = new boolean[numVertices];
		List<Arista> resultado = new ArrayList<>();
		PriorityQueue<Arista> cola = new PriorityQueue<>(Comparator.comparingInt(Arista::getPeso));

		// Comenzamos desde el vértice 0
		visitado[0] = true;
		cola.addAll(grafo.obtenerAristas(0));

		while (!cola.isEmpty() && resultado.size() < numVertices - 1) {
			Arista actual = cola.poll();
			int destino = actual.getDestino();

			if (!visitado[destino]) {
				visitado[destino] = true;
				resultado.add(actual);

				for (Arista adyacente : grafo.obtenerAristas(destino)) {
					if (!visitado[adyacente.getDestino()]) {
						cola.offer(adyacente);
					}
				}
			}
		}

		return resultado;
	}

	public int calcularImpactoTotal(List<Arista> agm) {
		int total = 0;
		for (Arista a : agm) {
			total += a.getPeso();
		}
		return total;
	}
}



