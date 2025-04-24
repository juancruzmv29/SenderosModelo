package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Grafo {
    private Map<Parador, List<Camino>> adyacencias;

    public Grafo() {
        this.adyacencias = new HashMap<>();
    }

    public void agregarParador(Parador parador) {
        adyacencias.putIfAbsent(parador, new ArrayList<>());
    }

    public void agregarCamino(Parador origen, Parador destino, int impacto) {
        agregarParador(origen);
        agregarParador(destino);

        Camino camino = new Camino(origen, destino, impacto);
        adyacencias.get(origen).add(camino);
        adyacencias.get(destino).add(new Camino(destino, origen, impacto)); // grafo no dirigido
    }

    public List<Camino> obtenerAdyacentes(Parador parador) {
        return adyacencias.getOrDefault(parador, new ArrayList<>());
    }

    public Set<Parador> obtenerEstaciones() {
        return adyacencias.keySet();
    }
}

