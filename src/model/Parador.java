package model;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Parador {
	
	private double x;
	private double y;
	private int index;
	private String nombre;
	
	public Parador(double y, double x, int index, String nombre) {
	    if (nombre == null || index < 0) {
	        throw new IllegalArgumentException("El nombre no puede ser null o el indice no puede ser < 0");
	    }
	    this.nombre = nombre;
	    this.x = x; // Longitud
	    this.y = y; // Latitud
	    this.index = index; // Faltaba esta línea
	}


	public String getNombre() {
		return nombre;
	}


	public int getIndex() {
		return index;
	}



	public double getY() {
		return y;
	}



	public double getX() {
		return x;
	}




}
