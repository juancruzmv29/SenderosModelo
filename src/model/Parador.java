package model;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Parador {
	
	private double x;
	private double y;
	private int index;
	private String nombre;
	
	public Parador(double x, double y, int index, String nombre) {
		
		if (nombre == null || index < 0) {
			throw new NullPointerException("El nombre no puede ser null o el indice no puede ser < 0");
		}
		this.nombre = nombre;
		this.x = x;
		this.y = y;
	}



	public String getNombre() {
		return nombre;
	}


	public int getIndex() {
		return index;
	}



	public double getX() {
		return x;
	}



	public double getY() {
		return y;
	}




}
