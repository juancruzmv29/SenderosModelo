package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class MapMarkerLinea extends MapMarkerDot {
    private final Coordinate coordFin;
    private final Color color;
    private final JMapViewer mapa;  // Campo para almacenar la referencia

    public MapMarkerLinea(JMapViewer mapa, double latInicio, double lonInicio, 
                        double latFin, double lonFin, int peso) {
        super(latInicio, lonInicio);
        this.mapa = mapa;  // Guardar la referencia al mapa
        this.coordFin = new Coordinate(latFin, lonFin);
        this.color = calcularColor(peso);
        
        getStyle().setBackColor(new Color(0, 0, 0, 0)); // Fondo transparente
    }

    @Override
    public void paint(Graphics g, Point posInicio, int radio) {
        Point posFin = mapa.getMapPosition(coordFin.getLat(), coordFin.getLon());
        
        if (posFin != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(posInicio.x, posInicio.y, posFin.x, posFin.y);
        }
    }
    private Color calcularColor(int peso) {
        if (peso < 3) return Color.GREEN;
        else if (peso < 7) return Color.ORANGE;
        else return Color.RED;
    }
}


