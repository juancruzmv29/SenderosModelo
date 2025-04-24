package algoritmos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import model.Camino;
import model.Grafo;
import model.Parador;

public class AlgoritmoPrim {

    public static List<Camino> generarAGM(Grafo grafo) {
        Set<Parador> visitados = new HashSet<>();
        PriorityQueue<Camino> cola = new PriorityQueue<>();
        List<Camino> resultado = new ArrayList<>();

        Parador inicio = grafo.obtenerEstaciones().iterator().next();
        visitados.add(inicio);
        cola.addAll(grafo.obtenerAdyacentes(inicio));

        while (!cola.isEmpty() && visitados.size() < grafo.obtenerEstaciones().size()) {
            Camino actual = cola.poll();
            if (visitados.contains(actual.getDestino())) continue;

            resultado.add(actual);
            Parador nueva = actual.getDestino();
            visitados.add(nueva);
            for (Camino s : grafo.obtenerAdyacentes(nueva)) {
                if (!visitados.contains(s.getDestino())) {
                    cola.add(s);
                }
            }
        }

        return resultado;
    }
}
