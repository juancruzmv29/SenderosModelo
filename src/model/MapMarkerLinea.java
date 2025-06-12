package model;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;

public class MapMarkerLinea extends MapMarkerCircle {

    private Coordinate destino;
    private JMapViewer mapa;
    private Color colorLinea;

    public MapMarkerLinea(JMapViewer mapa, double lat1, double lon1, double lat2, double lon2, int peso) {
        super("Linea", new Coordinate(lat1, lon1), 0);
        this.mapa = mapa;
        this.destino = new Coordinate(lat2, lon2);

        // Color según peso (impacto ambiental)
        if (peso >= 1 && peso < 3) {
            this.colorLinea = Color.RED; // peligroso
        } else if (peso >= 3 && peso <= 6) {
            this.colorLinea = Color.ORANGE; // moderado
        } else if (peso >= 7 && peso <= 10) {
            this.colorLinea = Color.GREEN; // bueno
        } else {
            this.colorLinea = Color.GRAY; // por si hay algún valor fuera de rango
        }

        setBackColor(new Color(0, 0, 255, 0)); // punto invisible
    }

    @Override
    public void paint(Graphics g, Point position, int radius) {
        if (mapa == null || destino == null)
            return;

        Point puntoDestino = mapa.getMapPosition(destino, false);
        if (puntoDestino != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(colorLinea);
            g2.setStroke(new BasicStroke(2));
            g2.drawLine(position.x, position.y, puntoDestino.x, puntoDestino.y);
        }
    }
}
