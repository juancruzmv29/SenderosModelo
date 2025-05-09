package controller;

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
