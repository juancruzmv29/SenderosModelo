package model;


// Usamos comparable para comparar el impacto de cada camino.
public class Camino implements Comparable<Camino>{

	
	private Parador origen;
	private Parador destino;
	private int impactoAmbiental;
	
	
	public Camino(Parador origen, Parador destino, int impactoAmbiental) {
		this.origen = origen;
		this.destino = destino;
		this.impactoAmbiental = impactoAmbiental;
	}


	@Override
	public int compareTo(Camino o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.impactoAmbiental, o.impactoAmbiental);
	}


	public Parador getOrigen() {
		return origen;
	}


	public void setOrigen(Parador origen) {
		this.origen = origen;
	}


	public Parador getDestino() {
		return destino;
	}


	public void setDestino(Parador destino) {
		this.destino = destino;
	}


	public int getImpactoAmbiental() {
		return impactoAmbiental;
	}


	public void setImpactoAmbiental(int impactoAmbiental) {
		this.impactoAmbiental = impactoAmbiental;
	}
	
	
}
