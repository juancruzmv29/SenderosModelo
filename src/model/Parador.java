package model;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Parador {
	
	private String nombre;
	private Coordinate coord;
	
	public Parador(String nombre, Coordinate coord) {
		this.nombre = nombre;
		this.coord = coord;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Coordinate getCoord() {
		return coord;
	}



	public void setCoord(Coordinate coord) {
		this.coord = coord;
	}
	
	
	
	

}
