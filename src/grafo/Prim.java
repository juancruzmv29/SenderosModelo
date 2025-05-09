package grafo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Prim {
    public List<Arista> arbolGeneradorMinimo(Grafo grafo) {
        Set<Arista> visitados = new HashSet<>();
        PriorityQueue<Arista> cola = new PriorityQueue<>(Comparator.comparingInt(a -> a.peso));
        List<Arista> resultado = new ArrayList<>();

        Arista start = grafo.getVecinos().keySet().iterator().next();
        visitados.add(start);
        cola.addAll(grafo.getVecinos().get(start));

        while (!cola.isEmpty() && visitados.size() < grafo.getVecinos().size()) {
            Arista arista = cola.poll();
            if (visitados.contains(arista.destino)) continue;

            resultado.add(arista);
            visitados.add(arista.destino);

            for (Arista a : grafo.getVecinos().get(arista.destino)) {
                if (!visitados.contains(a.destino)) {
                    cola.add(a);
                }
            }
        }

        return resultado;
    }
}
