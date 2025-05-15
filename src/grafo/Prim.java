package grafo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Prim {

    public List<Arista> arbolGeneradorMinimo(Grafo grafo) {
        List<Arista> resultado = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();
        PriorityQueue<Arista> cola = new PriorityQueue<>(Comparator.comparingInt(Arista::getPeso));

        visitados.add(0); // comenzamos desde el nodo 0
        cola.addAll(grafo.getAristasDesde(0));

        while (!cola.isEmpty() && visitados.size() < grafo.tamano()) {
            Arista actual = cola.poll();
            int destino = actual.getDestino();

            if (visitados.contains(destino)) continue;

            visitados.add(destino);
            resultado.add(actual);

            for (Arista a : grafo.getAristasDesde(destino)) {
                if (!visitados.contains(a.getDestino())) {
                    cola.add(a);
                }
            }
        }

        return resultado;
    }
}

