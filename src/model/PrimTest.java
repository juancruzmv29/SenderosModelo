package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class PrimTest {

    @Test
    public void testAGMGrafoConectado() {
        Grafo grafo = new Grafo(5);
        grafo.agregarArista(0, 1, 2);
        grafo.agregarArista(0, 3, 6);
        grafo.agregarArista(1, 2, 3);
        grafo.agregarArista(1, 3, 8);
        grafo.agregarArista(1, 4, 5);
        grafo.agregarArista(2, 4, 7);
        grafo.agregarArista(3, 4, 9);

        // Ejecutar algoritmo
        Prim prim = new Prim();
        List<Arista> agm = prim.calcularAGM(grafo);
        int impactoTotal = prim.calcularImpactoTotal(agm);

        assertEquals("El AGM debe tener V-1 aristas", 4, agm.size());
        assertEquals("Impacto total incorrecto", 16, impactoTotal);
        assertTrue("Debe contener la arista 0-1", contieneArista(agm, 0, 1, 2));
    }

    @Test
    public void testAGMGrafoConPesosIguales() {
        Grafo grafo = new Grafo(4);
        grafo.agregarArista(0, 1, 1);
        grafo.agregarArista(0, 2, 1);
        grafo.agregarArista(1, 3, 1);
        grafo.agregarArista(2, 3, 1);

        Prim prim = new Prim();
        List<Arista> agm = prim.calcularAGM(grafo);

        assertEquals(3, agm.size());
        assertEquals(3, prim.calcularImpactoTotal(agm));
    }

    @Test
    public void testAGMGrafoConUnSoloVertice() {
        Grafo grafo = new Grafo(1);
        Prim prim = new Prim();
        List<Arista> agm = prim.calcularAGM(grafo);

        assertTrue("AGM debe estar vacío para un solo vértice", agm.isEmpty());
        assertEquals(0, prim.calcularImpactoTotal(agm));
    }

    @Test
    public void testImpactoTotal() {
        Grafo grafo = new Grafo(3);
        grafo.agregarArista(0, 1, 5);
        grafo.agregarArista(1, 2, 3);
        grafo.agregarArista(0, 2, 4);

        Prim prim = new Prim();
        List<Arista> agm = prim.calcularAGM(grafo);

        assertEquals("Debe seleccionar las aristas de menor peso", 7, prim.calcularImpactoTotal(agm));
    }


    // Método auxiliar para verificar aristas
    private boolean contieneArista(List<Arista> aristas, int origen, int destino, int peso) {
        return aristas.stream().anyMatch(a -> 
            (a.getOrigen() == origen && a.getDestino() == destino && a.getPeso() == peso) ||
            (a.getOrigen() == destino && a.getDestino() == origen && a.getPeso() == peso));
    }
}