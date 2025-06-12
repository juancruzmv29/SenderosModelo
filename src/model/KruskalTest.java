package model;

import org.junit.Test;

import grafo.Arista;
import grafo.Grafo;

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

        // Kruskal debería obtener el mismo resultado que Prim para este grafo
        assertEquals(4, agm.size());
        assertEquals(16, impactoTotal);
        assertTrue(contieneArista(agm, 0, 1, 2));
        assertTrue(contieneArista(agm, 1, 2, 3));
        assertTrue(contieneArista(agm, 1, 4, 5));
        assertTrue(contieneArista(agm, 0, 3, 6));
    }

    @Test
    public void testAGMGrafoDelParque() {
        // Grafo idéntico al de tu aplicación
        Grafo grafo = new Grafo(7);
        grafo.agregarArista(0, 1, 10);
        grafo.agregarArista(0, 2, 6);
        grafo.agregarArista(0, 3, 5);
        grafo.agregarArista(1, 3, 15);
        grafo.agregarArista(2, 3, 4);
        grafo.agregarArista(3, 4, 8);
        grafo.agregarArista(4, 5, 7);
        grafo.agregarArista(5, 6, 9);

        Kruskal kruskal = new Kruskal();
        List<Arista> agm = kruskal.calcularAGM(grafo);

        assertEquals(6, agm.size());
        assertEquals(40, kruskal.calcularImpactoTotal(agm));
        assertTrue(contieneArista(agm, 0, 3, 5));
        assertTrue(contieneArista(agm, 2, 3, 4));
        assertTrue(contieneArista(agm, 3, 4, 8));
        assertTrue(contieneArista(agm, 4, 5, 7));
        assertTrue(contieneArista(agm, 5, 6, 9));
        assertTrue(contieneArista(agm, 0, 2, 6) || contieneArista(agm, 0, 1, 10));
    }

    // Método auxiliar igual que en PrimTest
    private boolean contieneArista(List<Arista> aristas, int origen, int destino, int peso) {
        return aristas.stream().anyMatch(a -> 
            (a.getOrigen() == origen && a.getDestino() == destino && a.getPeso() == peso) ||
            (a.getOrigen() == destino && a.getDestino() == origen && a.getPeso() == peso));
    }
}