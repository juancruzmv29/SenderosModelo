package model;

import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import java.awt.*;

public class MapMarkerParador extends MapMarkerDot {
    private String nombre;

    public MapMarkerParador(double lat, double lon, String nombre) {
        super(lat, lon);
        this.nombre = nombre;
        // Configuración adicional del estilo
        getStyle().setBackColor(Color.BLUE);
        getStyle().setFont(new Font("Arial", Font.BOLD, 12));
        getStyle().setColor(Color.WHITE);
    }

    @Override
    public void paint(Graphics g, Point position, int radio) {
        super.paint(g, position, radio);
        
        // Dibujar el nombre del parador
        if (nombre != null && !nombre.isEmpty()) {
            g.setColor(getStyle().getColor());
            g.setFont(getStyle().getFont());
            g.drawString(nombre, position.x + radio + 5, position.y + radio);
        }
    }
}
