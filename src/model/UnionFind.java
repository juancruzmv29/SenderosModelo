package model;


public class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];

        // Inicialmente, cada nodo es su propio padre (conjunto separado)
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Encuentra el representante del conjunto con compresión de caminos
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // compresión
        }
        return parent[x];
    }

    // Une dos conjuntos si son diferentes
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return; // ya están en el mismo conjunto

        // Unión por rango para balancear el árbol
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}

