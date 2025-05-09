package model;

import java.util.List;

import grafo.Arista;
import grafo.Grafo;

public class Main {
	public static void main(String[] args) {
		Grafo g = new Grafo(6);
		g.agregarArista(0, 1, 10);
		g.agregarArista(0, 2, 6);
		g.agregarArista(0, 3, 5);
		g.agregarArista(1, 3, 15);
		g.agregarArista(2, 3, 4);
	}
		
}
