package model;

import org.junit.Test;

import grafo.Arista;
import grafo.Grafo;
import grafo.Prim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

public class PrimTest {

    @Test
    public void testMSTGrafoConectado() {
        // Configuración
        Grafo grafo = new Grafo(5);
        grafo.agregarArista(0, 1, 2);
        grafo.agregarArista(0, 3, 6);
        grafo.agregarArista(1, 2, 3);
        grafo.agregarArista(1, 3, 8);
        grafo.agregarArista(1, 4, 5);
        grafo.agregarArista(2, 4, 7);
        grafo.agregarArista(3, 4, 9);

        // Ejecución
        Prim prim = new Prim();
        List<Arista> mst = prim.arbolGeneradorMinimo(grafo);

        // Verificaciones
        assertEquals(4, mst.size()); // Un MST para 5 nodos debe tener 4 aristas
        
        // Verificar que todas las aristas esperadas están presentes
        assertTrue(contieneArista(mst, 0, 1, 2));
        assertTrue(contieneArista(mst, 1, 2, 3));
        assertTrue(contieneArista(mst, 1, 4, 5));
        assertTrue(contieneArista(mst, 0, 3, 6));
        
        // Verificar peso total del MST
        assertEquals(16, calcularPesoTotal(mst));
    }

    @Test
    public void testMSTGrafoDesconectado() {
        // Configuración (grafo con dos componentes conexas)
        Grafo grafo = new Grafo(4);
        grafo.agregarArista(0, 1, 1);
        grafo.agregarArista(2, 3, 1);

        // Ejecución
        Prim prim = new Prim();
        List<Arista> mst = prim.arbolGeneradorMinimo(grafo);

        // Verificación (debería retornar solo el MST del componente conexo accesible desde 0)
        assertEquals(1, mst.size());
        assertTrue(contieneArista(mst, 0, 1, 1));
    }

    @Test
    public void testMSTGrafoConUnSoloNodo() {
        Grafo grafo = new Grafo(1);
        Prim prim = new Prim();
        List<Arista> mst = prim.arbolGeneradorMinimo(grafo);
        
        assertTrue(mst.isEmpty());
    }

    @Test
    public void testMSTGrafoDelParque() {
        // Configuración usando el mismo grafo que en la clase Parque
        Parque parque = new Parque();
        Grafo grafo = parque.getGrafo();
        
        Prim prim = new Prim();
        List<Arista> mst = prim.arbolGeneradorMinimo(grafo);

        // Verificaciones específicas para el grafo del parque
        assertEquals(6, mst.size()); // 7 nodos => 6 aristas en el MST
        
        // Verificar que contiene las aristas esperadas con los pesos correctos
        assertTrue(contieneArista(mst, 0, 1, 10));
        assertTrue(contieneArista(mst, 0, 2, 6));
        assertTrue(contieneArista(mst, 0, 3, 5));
        assertTrue(contieneArista(mst, 2, 3, 4));
        assertTrue(contieneArista(mst, 3, 4, 8));
        assertTrue(contieneArista(mst, 4, 5, 7));
        
        // Verificar peso total (debería ser 40 según el grafo del parque)
        assertEquals(40, calcularPesoTotal(mst));
    }

    // Métodos auxiliares para las pruebas
    private boolean contieneArista(List<Arista> aristas, int origen, int destino, int peso) {
        for (Arista a : aristas) {
            if ((a.getOrigen() == origen && a.getDestino() == destino && a.getPeso() == peso) ||
                (a.getOrigen() == destino && a.getDestino() == origen && a.getPeso() == peso)) {
                return true;
            }
        }
        return false;
    }

    private int calcularPesoTotal(List<Arista> aristas) {
        return aristas.stream().mapToInt(Arista::getPeso).sum();
    }
}
