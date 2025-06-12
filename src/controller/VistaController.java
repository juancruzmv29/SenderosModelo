package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import model.Arista;
import model.Grafo;
import model.Kruskal;
import model.LineaColor;
import model.MapMarkerLinea;
import model.MapMarkerParador;
import model.Parador;
import model.Parque;
import model.Prim;
import view.Vista;

public class VistaController {
	
	private Parque parque;
	private Vista vista;
	private JMapViewer mapa;
	private JPanel panelControles;
	
	public VistaController(Vista vista) {
		this.mapa = vista.getMapa(); // Debe haber un getMapa() en Vista
		this.panelControles = vista.getPanelControles(); // Lo mismo
		this.parque = new Parque();
		this.vista = vista;
		inicializar();
	}
	
	
	public void inicializar() {
		vista.getBtnCargarParadores().addActionListener(e -> agregarMarcadores(parque.getParadores()));
		vista.getBtnCargarAristas().addActionListener(e -> dibujarSenderos(parque.getGrafo().getAristas(), parque.getParadores()));
		vista.getBtnKruskal().addActionListener(e -> visualizarAGMKruskal());
		vista.getBtnPrim().addActionListener(e -> visualizarAGMPrim());
	}
	
	public void cargarParque() {
		mapa.removeAllMapMarkers(); // Limpia mapa si ya hay
	    agregarMarcadores(parque.getParadores());
	}
	
	private void agregarMarcadores(HashMap<Integer, Parador> paradores) {
		int cont = 0;
		for (Parador p : paradores.values()) {
			MapMarkerParador marcador = new MapMarkerParador(p.getY(), p.getX(), p.getNombre());
			if (cont < 3) {
				marcador.getStyle().setBackColor(Color.GREEN);
			} else if (cont < 5) {
				marcador.getStyle().setBackColor(Color.YELLOW);
				marcador.getStyle().setColor(Color.BLACK);
			} else {
				marcador.getStyle().setBackColor(Color.RED);
			}
			mapa.addMapMarker(marcador);
			cont++;
		}
		vista.mapa.repaint();
	}

	private void dibujarSenderos(List<Arista> aristas, HashMap<Integer, Parador> paradores) {
		mapa.removeAllMapMarkers();
		agregarMarcadores(paradores);
		for (Arista a : aristas) {
			Parador origen = paradores.get(a.getOrigen());
			Parador destino = paradores.get(a.getDestino());
			if (origen != null && destino != null) {
				MapMarkerLinea sendero = new MapMarkerLinea(mapa, origen.getY(), origen.getX(), destino.getY(), destino.getX(), a.getPeso());
				mapa.addMapMarker(sendero);
			}
		}
		vista.mapa.repaint();
	}

	private void visualizarAGMKruskal() {
		Grafo grafo = parque.getGrafo();
		Kruskal kruskal = new Kruskal();
		List<Arista> agm = kruskal.calcularAGM(grafo);
		int impactoTotal = kruskal.calcularImpactoTotal(agm);
		HashMap<Integer, Parador> paradores = parque.getParadores();
		dibujarSenderos(agm, paradores);
		vista.mostrarResultado("Senderos a Construir (Kruskal) \n" + agm.toString() + "\n " + "Total impacto: " + impactoTotal);
		vista.getAreaResultados();
	}

	private void visualizarAGMPrim() {
		Grafo grafo = parque.getGrafo();
		Prim prim = new Prim();
		List<Arista> agm = prim.calcularAGM(grafo);
		int impactoTotal = prim.calcularImpactoTotal(agm);
		HashMap<Integer, Parador> paradores = parque.getParadores();
		dibujarSenderos(agm, paradores);
		vista.mostrarResultado("Senderos a Construir (Prim) \n" + agm.toString() + "\n " + "Total impacto: " + impactoTotal);
		vista.getAreaResultados();
	}

 }
	
	
