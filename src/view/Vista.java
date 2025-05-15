package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import controller.MapaController;
import grafo.Arista;
import grafo.Grafo;
import grafo.Prim;
import model.ImpactoAmbiental;
import model.LineaColor;
import model.Parador;

public class Vista {

	private JFrame frame;
	private JMapViewer mapa;
	private JPanel panelMapa;
	private JPanel panelControles;
	private MapaController mapaController = new MapaController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista window = new Vista();
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
	
	public Vista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		mapaController.cargarParque();

		// Configuración del frame
		frame = new JFrame("SenderosModelo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLayout(new BorderLayout()); // Usamos BorderLayout general

		// Creamos el mapa
		mapa = new JMapViewer();
		mapa.setDisplayPosition(new Coordinate(-25.6836, -54.4448), 15);
		mapa.setZoomControlsVisible(true);
		mapa.setFocusable(true);
		mapa.requestFocusInWindow();

		// Panel del mapa con BorderLayout para que el mapa se expanda
		panelMapa = new JPanel(new BorderLayout());
		panelMapa.add(mapa, BorderLayout.CENTER);

		// Panel de controles (lado derecho)
		panelControles = new JPanel(null);
		panelControles.setPreferredSize(new java.awt.Dimension(180, 600)); // tamaño fijo a la derecha

		// Agregamos los paneles al frame
		frame.add(panelMapa, BorderLayout.CENTER);
		frame.add(panelControles, BorderLayout.EAST);

		// Mostrar ventana
		frame.setVisible(true);
		agregarMarcadores(mapaController.getParque().getParadores());
		agregarMarcadores(mapaController.getParque().getParadores());
		
		Prim prim = new Prim();
		List<Arista> mst = prim.arbolGeneradorMinimo(mapaController.getParque().getGrafo());
		dibujarSenderos(mst, mapaController.getParque().getParadores());


		System.out.println("Aristas del AGM:");
		for (Arista a : mst) {
		    System.out.println(a.getOrigen() + " - " + a.getDestino() + " peso: " + a.getPeso());
		}

		
	}
	
	 private void agregarMarcadores(HashMap<Integer, Parador> paradores) {
		 int cont = 0;
	        for (Parador p : paradores.values()) {
	            MapMarker marcador = new MapMarkerDot(p.getX(), p.getY());
	            if (cont < 3) {
	                marcador.getStyle().setBackColor(Color.GREEN);
	            } else if (cont < 5) {
	                marcador.getStyle().setBackColor(Color.YELLOW);
	            } else {
	                marcador.getStyle().setBackColor(Color.RED);
	            }
	            mapa.addMapMarker(marcador);
	            cont++;
	        }
	    }
	 
	 private void dibujarSenderos(List<Arista> aristas, HashMap<Integer, Parador> paradores) {
		    for (Arista a : aristas) {
		        Parador origen = paradores.get(a.getOrigen());
		        Parador destino = paradores.get(a.getDestino());

		        if (origen != null && destino != null) {
		            List<Coordinate> coords = new ArrayList<>();
		            coords.add(new Coordinate(origen.getX(), origen.getY()));
		            coords.add(new Coordinate(destino.getX(), destino.getY()));

		            LineaColor linea = new LineaColor(coords, a.getPeso());
		            mapa.addMapPolygon(linea);
		        }
		    }
		}



	
}
