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
import java.awt.GridLayout;
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
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import model.Arista;
import model.Grafo;
import model.Kruskal;
import model.LineaColor;
import model.MapMarkerLinea;
import model.MapMarkerParador;
import model.Parador;
import model.Prim;

public class Vista extends JFrame {

    private JPanel panelControles;
    private JButton btnCargarMapa;
    private JButton btnKruskal;
    private JButton btnPrim;
    public JMapViewer mapa;
    private JButton btnCargarParadores;
    private JButton btnCargarAristas;
    private JTextArea areaResultados;

    public Vista() {
        setTitle("TP Senderos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Mapa
        mapa = new JMapViewer();
        mapa.setDisplayPosition(new Coordinate(-25.6830, -54.4450), 17);
        mapa.setZoomControlsVisible(false);
        mapa.setFocusable(true);
        mapa.requestFocusInWindow();
        add(mapa, BorderLayout.CENTER);

        // Panel inferior con controles y resultados
        JPanel panelInferior = new JPanel(new BorderLayout());

        // Panel de botones a la izquierda
        panelControles = new JPanel(new GridLayout(0, 1, 5, 5));
        btnKruskal = new JButton("Ejecutar algoritmo de Kruskal");
        btnPrim = new JButton("Ejecutar algoritmo de Prim");
        btnCargarAristas = new JButton("Cargar caminos");
        btnCargarParadores = new JButton("Cargar paradores");

        panelControles.add(btnCargarParadores);
        panelControles.add(btnCargarAristas);
        panelControles.add(btnPrim);
        panelControles.add(btnKruskal);

        // Área de resultados a la derecha
        areaResultados = new JTextArea(8, 30);
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaResultados.setBorder(BorderFactory.createTitledBorder("Resultado del Algoritmo"));
        JScrollPane scroll = new JScrollPane(areaResultados);

        // Agregamos ambos al panel inferior
        panelInferior.add(panelControles, BorderLayout.WEST);
        panelInferior.add(scroll, BorderLayout.CENTER);

        // Finalmente, el panel inferior va al SUR
        add(panelInferior, BorderLayout.SOUTH);
    }


    // Getters para botones si los necesitás en el controlador...



	public JPanel getPanelControles() {
		return panelControles;
	}


	public void setPanelControles(JPanel panelControles) {
		this.panelControles = panelControles;
	}


	public JButton getBtnCargarMapa() {
		return btnCargarMapa;
	}


	public void setBtnCargarMapa(JButton btnCargarMapa) {
		this.btnCargarMapa = btnCargarMapa;
	}


	public JButton getBtnKruskal() {
		return btnKruskal;
	}


	public void setBtnKruskal(JButton btnKruskal) {
		this.btnKruskal = btnKruskal;
	}


	public JButton getBtnPrim() {
		return btnPrim;
	}


	public void setBtnPrim(JButton btnPrim) {
		this.btnPrim = btnPrim;
	}


	public JMapViewer getMapViewer() {
		return mapa;
	}


	public void setMapViewer(JMapViewer mapa) {
		this.mapa = mapa;
	}


	public JButton getBtnCargarParadores() {
		return btnCargarParadores;
	}


	public void setBtnCargarParadores(JButton btnCargarParadores) {
		this.btnCargarParadores = btnCargarParadores;
	}


	public JButton getBtnCargarAristas() {
		return btnCargarAristas;
	}


	public void setBtnCargarAristas(JButton btnCargarAristas) {
		this.btnCargarAristas = btnCargarAristas;
	}
	
	public void mostrarResultado(String texto) {
        areaResultados.setText(texto);
    }

	public JTextArea getAreaResultados() {
		return areaResultados;
	}


	public void setAreaResultados(JTextArea areaResultados) {
		this.areaResultados = areaResultados;
	}


	public JMapViewer getMapa() {
		return mapa;
	}


	public void setMapa(JMapViewer mapa) {
		this.mapa = mapa;
	}	
	 
	
	 

}
