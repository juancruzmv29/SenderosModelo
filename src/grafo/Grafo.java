package grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Camino;
import model.Parador;

public class Grafo<Parador> {
    private Map<Parador, List<Arista<Parador>>> adyacencias = new HashMap<>();

    public void agregarParador(Parador p) {
        adyacencias.putIfAbsent(p, new ArrayList<>());
    }

    public void agregarArista(Parador origen, Parador destino, int peso) {
        adyacencias.get(origen).add(new Arista<>(origen, destino, peso));
        adyacencias.get(destino).add(new Arista<>(destino, origen, peso)); // grafo no dirigido
    }

    public Map<Parador, List<Arista<Parador>>> getAdyacencias() {
        return adyacencias;
    }
}

