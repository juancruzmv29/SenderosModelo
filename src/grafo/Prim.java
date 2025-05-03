package grafo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Prim<T> {
    public List<Arista<T>> arbolGeneradorMinimo(Grafo<T> grafo) {
        Set<T> visitados = new HashSet<>();
        PriorityQueue<Arista<T>> cola = new PriorityQueue<>(Comparator.comparingInt(a -> a.peso));
        List<Arista<T>> resultado = new ArrayList<>();

        T start = grafo.getAdyacencias().keySet().iterator().next();
        visitados.add(start);
        cola.addAll(grafo.getAdyacencias().get(start));

        while (!cola.isEmpty() && visitados.size() < grafo.getAdyacencias().size()) {
            Arista<T> arista = cola.poll();
            if (visitados.contains(arista.destino)) continue;

            resultado.add(arista);
            visitados.add(arista.destino);

            for (Arista<T> a : grafo.getAdyacencias().get(arista.destino)) {
                if (!visitados.contains(a.destino)) {
                    cola.add(a);
                }
            }
        }

        return resultado;
    }
}
