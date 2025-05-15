package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

public class LineaColor extends MapPolygonImpl {
    private Color color;
    private JMapViewer mapa;

    public LineaColor(JMapViewer mapa, List<Coordinate> points, int peso) {
        super(null, null, points);
        this.mapa = mapa;
        this.color = calcularColorPorPeso(peso);
    }

    public void paint(Graphics g, List<Point> puntos) {
        if (puntos.size() < 2) return;
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(3));
        
        Point p1 = puntos.get(0);
        for (int i = 1; i < puntos.size(); i++) {
            Point p2 = puntos.get(i);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            p1 = p2;
        }
        
        mapa.repaint();
    }

    private Color calcularColorPorPeso(int peso) {
        if (peso < 3) return Color.GREEN;
        else if (peso < 7) return Color.ORANGE;
        else return Color.RED;
    }
}


