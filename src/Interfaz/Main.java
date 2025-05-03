package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import grafo.Arista;
import grafo.Grafo;
import grafo.Prim;
import model.Camino;
import model.ImpactoAmbiental;
import model.Parador;

public class Main {

	private JFrame frame;
	private JMapViewer mapa;
	private JPanel panelMapa;
	private JPanel panelControles;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	/*
	public Main() {
		initialize();
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setTitle("SenderosModelo");
		
		panelMapa = new JPanel();
		panelMapa.setBounds(12, 12, 304, 200);
		frame.getContentPane().add(panelMapa);
		
		panelControles = new JPanel();
		panelControles.setBounds(457, 11, 242, 446);
		panelControles.setLayout(null);
		frame.getContentPane().setLayout(null); // nos permite manejar nosotros el formulario, osea la ventana
		
		mapa = new JMapViewer();
		
		// Agregamos coordenadas
		Coordinate coord1 = new Coordinate(-25.68278884155497, -54.44244468884053);
		Coordinate coord2 = new Coordinate(-25.684732026499695, -54.44313034865969);
		Coordinate coord3 = new Coordinate(-25.68459380937074, -54.44562037642403);
		Coordinate coord4 = new Coordinate(-25.683862068957488, -54.44565646378294);
		Coordinate coord5 = new Coordinate(-25.68180504128241, -54.44656766958095);
		Coordinate coord6 = new Coordinate(-25.68361002400252, -54.449039653665835);
		Coordinate coord7 = new Coordinate(-25.684447461663346, -54.44800214209483);
		
		// Agregar paradores (Parador)
		Parador parador1 = new Parador("Salto Lanusse", coord1);
		Parador parador2 = new Parador("Circuito Inferior", coord2);
		Parador parador3 = new Parador("Salto Chico Alférez", coord3);
		Parador parador4 = new Parador("Salto Dos Hermanas", coord4);
		Parador parador5 = new Parador("Hotel Viejo Cataratas", coord5);
		Parador parador6 = new Parador("Estación Cataratas", coord6);
		Parador parador7 = new Parador("Circuito Superior", coord7);
		
		
		// Agregar marcadores
		MapMarker marcador1 = new MapMarkerDot("Salto Lanusse", coord1);
		marcador1.getStyle().setBackColor(Color.GREEN);
		MapMarker marcador2 = new MapMarkerDot("Circuito Inferior", coord2);
		marcador2.getStyle().setBackColor(Color.GREEN);
		MapMarker marcador3 = new MapMarkerDot("Salto Chico Alférez", coord3);
		marcador3.getStyle().setBackColor(Color.GREEN);
		MapMarker marcador4 = new MapMarkerDot("Salto Dos Hermanas", coord4);
		marcador4.getStyle().setBackColor(Color.GREEN);
		MapMarker marcador5 = new MapMarkerDot("Hotel Viejo Cataratas", coord5);
		marcador5.getStyle().setBackColor(Color.RED);
		MapMarker marcador6 = new MapMarkerDot("Estación Cataratas", coord6);
		marcador6.getStyle().setBackColor(Color.ORANGE);
		MapMarker marcador7 = new MapMarkerDot("Circuito Superior", coord7);
		marcador7.getStyle().setBackColor(Color.GREEN);
		
		
		// Lista de paradores
		/*
		Camino camino1 = new Camino(parador1, parador2, 3);
		Camino camino2 = new Camino(parador2, parador5, 5);
		Camino camino3 = new Camino(parador5, parador6, 7);
		Camino camino4 = new Camino(parador5, parador4, 9);
		Camino camino5 = new Camino(parador5, parador6, 4);
		Camino camino6 = new Camino(parador6, parador7, 5);
		Camino camino7 = new Camino(parador7, parador4, 2);
		Camino camino8 = new Camino(parador7, parador3, 1);
		Camino camino9 = new Camino(parador3, parador2, 7);
		Camino camino10 = new Camino(parador1, parador5, 7);*/
		
		
		// Definimos el grafo y sus paradores
		Grafo<Parador> grafoSenderos = new Grafo<>();
		grafoSenderos.agregarParador(parador1);
		grafoSenderos.agregarParador(parador2);
		grafoSenderos.agregarParador(parador3);
		grafoSenderos.agregarParador(parador4);
		grafoSenderos.agregarParador(parador5);
		grafoSenderos.agregarParador(parador6);
		grafoSenderos.agregarParador(parador7);
		
		// Agregamos los caminos
		grafoSenderos.agregarArista(parador1, parador2, 3);
		grafoSenderos.agregarArista(parador2, parador5, 5);
		grafoSenderos.agregarArista(parador5, parador6, 7);
		grafoSenderos.agregarArista(parador5, parador4, 9);
		grafoSenderos.agregarArista(parador5, parador6, 4);
		grafoSenderos.agregarArista(parador6, parador7, 5);
		grafoSenderos.agregarArista(parador7, parador4, 2);
		grafoSenderos.agregarArista(parador7, parador3, 1);
		grafoSenderos.agregarArista(parador3, parador2, 7);
		grafoSenderos.agregarArista(parador1, parador5, 7);
		
		
		// Aplicamos el algoritmo de Prim
		Prim<Parador> prim = new Prim<>();
		List<Arista<Parador>> mst = prim.arbolGeneradorMinimo(grafoSenderos);

		// Mostrar en el mapa
		for (Arista<Parador> arista : mst) {
		    Coordinate c1 = arista.origen.getCoord();
		    Coordinate c2 = arista.destino.getCoord();
		    Color color = ImpactoAmbiental.getColorPorImpacto(arista.peso);

		    MapPolygonImpl linea = new MapPolygonImpl(Arrays.asList(c1, c2));
		    mapa.addMapPolygon(linea);
		}
		
		
		
		//frame.getContentPane().add(mapa);
		panelMapa.add(mapa);
		

		
	}

}
