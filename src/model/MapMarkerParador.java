package model;

import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class MapMarkerParador extends MapMarkerDot {
    
    private String nombreParador;

    public MapMarkerParador(double latitud, double longitud, String nombreParador) {
        super(latitud, longitud);
        this.nombreParador = nombreParador;
        setName(nombreParador); 
    }

    public String getNombreParador() {
        return nombreParador;
    }

    public void setNombreParador(String nombreParador) {
        this.nombreParador = nombreParador;
        setName(nombreParador);
    }
}

