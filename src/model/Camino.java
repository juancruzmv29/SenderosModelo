package model;


// Usamos comparable para comparar el impacto de cada camino.
public class Camino {

	private Parador origen;
    private Parador destino;
    private int impactoAmbiental;

    public Camino(Parador origen, Parador destino, int impactoAmbiental) {
        this.origen = origen;
        this.destino = destino;
        this.impactoAmbiental = impactoAmbiental;
    }

    public Parador getOrigen() { return origen; }
    public Parador getDestino() { return destino; }
    public int getImpactoAmbiental() { return impactoAmbiental; }
	
	
}
