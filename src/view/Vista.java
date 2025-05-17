package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;


import controller.MapaController;
import grafo.Arista;
import grafo.Grafo;
import grafo.Kruskal;
import grafo.Prim;
import model.LineaColor;
import model.MapMarkerLinea;
import model.MapMarkerParador;
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
		frame.setVisible(true);
		frame.setLayout(new BorderLayout()); // Usamos BorderLayout general

		// Creamos el mapa
		mapa = new JMapViewer();
		mapa.setDisplayPosition(new Coordinate(-25.6836, -54.4448), 18);
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
		
		Grafo grafo = mapaController.getParque().getGrafo();
		Prim prim = new Prim();
		List<Arista> agm = prim.calcularAGM(grafo);

		int impactoTotal = prim.calcularImpactoTotal(agm);
		dibujarSenderos(agm, mapaController.getParque().getParadores());

		System.out.println("=== Senderos a construir con menos impacto ambiental ===");
		for (Arista arista : agm) {
		    System.out.println(arista);
		}
		System.out.println("Impacto ambiental total: " + impactoTotal);
		mapa.repaint();
		
		visualizarAGMKruskal();
		
	}
	
	 
	 
	 private void agregarMarcadores(HashMap<Integer, Parador> paradores) {
		    int cont = 0;
		    for (Parador p : paradores.values()) {
		        // Crear marcador personalizado con nombre
		        MapMarkerParador marcador = new MapMarkerParador(p.getY(), p.getX(), p.getNombre());
		        
		        // Asignar colores según tu lógica original
		        if (cont < 3) {
		            marcador.getStyle().setBackColor(Color.GREEN);
		        } else if (cont < 5) {
		            marcador.getStyle().setBackColor(Color.YELLOW);
		            marcador.getStyle().setColor(Color.BLACK); // Mejor contraste
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
		            MapMarkerLinea sendero = new MapMarkerLinea(
		                mapa,  // Pasar la referencia al mapa
		                origen.getY(), origen.getX(),
		                destino.getY(), destino.getX(),
		                a.getPeso()
		            );
		            mapa.addMapMarker(sendero);
		        }
		    }
		    mapa.repaint();
		}
	 
	 private void visualizarAGMKruskal() {
		    // Obtener el grafo y calcular AGM con Kruskal
		    Grafo grafo = mapaController.getParque().getGrafo();
		    Kruskal kruskal = new Kruskal();
		    List<Arista> agm = kruskal.calcularAGM(grafo);
		    int impactoTotal = kruskal.calcularImpactoTotal(agm);

		    // Mostrar resultados en consola
		    System.out.println("=== Senderos a construir (Kruskal) ===");
		    HashMap<Integer, Parador> paradores = mapaController.getParque().getParadores();
		    for (Arista a : agm) {
		        Parador origen = paradores.get(a.getOrigen());
		        Parador destino = paradores.get(a.getDestino());
		        System.out.println(origen.getNombre() + " ↔ " + destino.getNombre() + 
		                         " - Impacto: " + a.getPeso());
		    }
		    System.out.println("Impacto ambiental total (Kruskal): " + impactoTotal);

		    // Visualizar en el mapa
		    dibujarSenderos(agm, paradores);
		    
		    // Mostrar información en la interfaz
		    mostrarInfoAGM(agm, impactoTotal, paradores);
		}
	 
	 private void mostrarInfoAGM(List<Arista> agm, int impactoTotal, HashMap<Integer, Parador> paradores) {
	     // Limpiar el panel de controles antes de agregar nuevos componentes
	     panelControles.removeAll();
	     panelControles.setLayout(new BoxLayout(panelControles, BoxLayout.Y_AXIS));
	     
	     // 1. Título
	     JLabel titulo = new JLabel("Senderos a Construir (AGM)");
	     titulo.setFont(new Font("Arial", Font.BOLD, 16));
	     titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
	     panelControles.add(titulo);
	     
	     panelControles.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio
	     
	     // 2. Lista de senderos
	     JPanel panelSenderos = new JPanel();
	     panelSenderos.setLayout(new BoxLayout(panelSenderos, BoxLayout.Y_AXIS));
	     panelSenderos.setBorder(BorderFactory.createTitledBorder("Detalle de Senderos"));
	     
	     for (Arista arista : agm) {
	         Parador origen = paradores.get(arista.getOrigen());
	         Parador destino = paradores.get(arista.getDestino());
	         
	         JLabel labelSendero = new JLabel(
	             String.format("• %s ↔ %s (Impacto: %d)",
	                 origen.getNombre(),
	                 destino.getNombre(),
	                 arista.getPeso()));
	         
	         // Color según impacto
	         if (arista.getPeso() < 3) {
	             labelSendero.setForeground(new Color(0, 100, 0)); // Verde oscuro
	         } else if (arista.getPeso() < 7) {
	             labelSendero.setForeground(Color.ORANGE);
	         } else {
	             labelSendero.setForeground(Color.RED);
	         }
	         
	         panelSenderos.add(labelSendero);
	         panelSenderos.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio
	     }
	     
	     JScrollPane scrollSenderos = new JScrollPane(panelSenderos);
	     scrollSenderos.setPreferredSize(new Dimension(280, 300));
	     panelControles.add(scrollSenderos);
	     
	     panelControles.add(Box.createRigidArea(new Dimension(0, 15))); // Espacio
	     
	     // 3. Impacto total
	     JPanel panelImpacto = new JPanel();
	     panelImpacto.setBorder(BorderFactory.createTitledBorder("Impacto Ambiental Total"));
	     panelImpacto.setLayout(new BoxLayout(panelImpacto, BoxLayout.Y_AXIS));
	     
	     JLabel labelImpacto = new JLabel(String.valueOf(impactoTotal));
	     labelImpacto.setFont(new Font("Arial", Font.BOLD, 24));
	     labelImpacto.setForeground(new Color(0, 70, 0)); // Verde oscuro
	     labelImpacto.setAlignmentX(Component.CENTER_ALIGNMENT);
	     
	     panelImpacto.add(labelImpacto);
	     panelControles.add(panelImpacto);
	     
	     // 4. Botón de regeneración (opcional)
	     JButton btnReCalcular = new JButton("Recalcular AGM");
	     btnReCalcular.setAlignmentX(Component.CENTER_ALIGNMENT);
	     btnReCalcular.addActionListener(e -> visualizarAGMKruskal());
	     panelControles.add(Box.createRigidArea(new Dimension(0, 10)));
	     panelControles.add(btnReCalcular);
	     
	     // Actualizar la interfaz
	     panelControles.revalidate();
	     panelControles.repaint();
	 }

}
