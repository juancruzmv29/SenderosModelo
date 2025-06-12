package model;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.List;

public class KruskalTest {

    @Test
    public void testAGMGrafoConectado() {
        // Configurar el mismo grafo que usamos para probar Prim
        Grafo grafo = new Grafo(5);
        grafo.agregarArista(0, 1, 2);
        grafo.agregarArista(0, 3, 6);
        grafo.agregarArista(1, 2, 3);
        grafo.agregarArista(1, 3, 8);
        grafo.agregarArista(1, 4, 5);
        grafo.agregarArista(2, 4, 7);
        grafo.agregarArista(3, 4, 9);

        Kruskal kruskal = new Kruskal();
        List<Arista> agm = kruskal.calcularAGM(grafo);
        int impactoTotal = kruskal.calcularImpactoTotal(agm);

        assertEquals(4, agm.size());
        assertEquals(16, impactoTotal);
        assertTrue(contieneArista(agm, 0, 1, 2));
    }

    private boolean contieneArista(List<Arista> aristas, int origen, int destino, int peso) {
        return aristas.stream().anyMatch(a -> 
            (a.getOrigen() == origen && a.getDestino() == destino && a.getPeso() == peso) ||
            (a.getOrigen() == destino && a.getDestino() == origen && a.getPeso() == peso));
    }
}