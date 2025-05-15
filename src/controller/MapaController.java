package controller;

import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import grafo.Arista;
import model.LineaColor;
import model.Parque;

public class MapaController {
	
	private Parque parque;
	
	public void cargarParque() {
		parque = new Parque();
	}

	public Parque getParque() {
		return parque;
	}
	
	

	
	
}
