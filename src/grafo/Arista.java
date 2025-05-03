package grafo;

public class Arista<T> {
    public T origen;
    public T destino;
    public int peso;

    public Arista(T origen, T destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

	public T getOrigen() {
		return origen;
	}

	public void setOrigen(T origen) {
		this.origen = origen;
	}

	public T getDestino() {
		return destino;
	}

	public void setDestino(T destino) {
		this.destino = destino;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
    
    
}
