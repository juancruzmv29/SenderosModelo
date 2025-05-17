package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import grafo.Arista;
import grafo.Grafo;
import grafo.Prim;

public class Parque {
	
	HashMap<Integer, Parador> paradores;
	Grafo grafo;
	Parque p;
	
	
	
	public Parque() {
		paradores = new HashMap<Integer, Parador>();
		this.cargarParadores();
		this.cargarGrafo();
	}
	
	public Parque(ArrayList<Parador> listParadores) {
		paradores = new HashMap<Integer, Parador>();
		this.cargarParadores(listParadores);
	}
	
	private void cargarParadores() { //Estaciones template por propositos de testeo.
		Parador a = new Parador (-25.68278884155497, -54.44244468884053, 0, "Salto Lanusse");
		//MapMarker m1 =  new MapMarkerDot(-25.68278884155497, -54.44244468884053);
		//m1.getStyle().setBackColor(Color.GREEN);
		paradores.put(a.getIndex(), a);
		Parador b = new Parador (-25.684732026499695, -54.44313034865969, 1, "Circuito Inferior");
		//MapMarker m2 =  new MapMarkerDot(-25.684732026499695, -54.44313034865969);
		//m2.getStyle().setBackColor(Color.GREEN);
		paradores.put(b.getIndex(), b);
		Parador c = new Parador (-25.68459380937074, -54.44562037642403, 2, "Salto Chico Alférez");
		//MapMarker m3 =  new MapMarkerDot(-25.68459380937074, -54.44562037642403);
		//m3.getStyle().setBackColor(Color.GREEN);
		paradores.put(c.getIndex(), c);
		Parador d = new Parador (-25.683862068957488, -54.44565646378294, 3, "Salto Dos Hermanas");
		//MapMarker m4 =  new MapMarkerDot(-25.683862068957488, -54.44565646378294);
		//m4.getStyle().setBackColor(Color.GREEN);
		paradores.put(d.getIndex(), d);
		Parador e = new Parador (-25.68180504128241, -54.44656766958095, 4, "Hotel Viejo Cataratas");
		//MapMarker m5 =  new MapMarkerDot(-25.68180504128241, -54.44656766958095);
		//m5.getStyle().setBackColor(Color.RED);
		paradores.put(e.getIndex(), e);
		Parador f = new Parador (-25.68361002400252, -54.449039653665835, 5, "Estación Cataratas");
		//MapMarker m6 =  new MapMarkerDot(-25.68361002400252, -54.449039653665835);
		//m6.getStyle().setBackColor(Color.ORANGE);
		paradores.put(f.getIndex(), f);
		Parador g = new Parador (-25.684447461663346, -54.44800214209483, 6, "Circuito Superior");
		//MapMarker m7 =  new MapMarkerDot(-25.684447461663346, -54.44800214209483);
		//m7.getStyle().setBackColor(Color.GREEN);
		paradores.put(g.getIndex(), g);
	}
	
	public HashMap<Integer, Parador> getParadores() {
        return paradores;  
    }
	
	
	private void cargarParadores(ArrayList<Parador> stationList) {
		for (Parador s : stationList) {
			this.paradores.put(s.getIndex(), s);
		}
	}

	private void cargarGrafo() {
		
	    grafo = new Grafo(7);
	    grafo.agregarArista(0, 1, 10);
	    grafo.agregarArista(0, 2, 6);
	    grafo.agregarArista(0, 3, 5);
	    grafo.agregarArista(1, 3, 15);
	    grafo.agregarArista(2, 3, 4);
	    grafo.agregarArista(3, 4, 8);  
	    grafo.agregarArista(4, 5, 7);
	    grafo.agregarArista(5, 6, 9);
	    
	}
	public Grafo getGrafo() {
		return this.grafo;
	}
	
}
