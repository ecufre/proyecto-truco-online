package negocio;

import enumeraciones.TipoCanto;

public class Canto {
	private static int siguienteId = 1; //TODO esto se reemplaza por la persistencia

	private int id;
	private boolean querido;
	private TipoCanto tipoCanto;
	private int cantante;
	
	private static int getSiguienteId() {
		return siguienteId++;
	}

	public Canto(int cantante) {
		this.id = Canto.getSiguienteId();
		this.cantante = cantante;
		this.querido=false;
	}
	public boolean isQuerido() {
		return querido;
	}
	public void setQuerido(boolean querido) {
		this.querido = querido;
	}
	public TipoCanto getTipoCanto() {
		return tipoCanto;
	}
	public void setTipoCanto(TipoCanto tipoCanto) {
		this.tipoCanto = tipoCanto;
	}
	public int getCantante() {
		return cantante;
	}
	public void setCantante(int cantante) {
		this.cantante = cantante;
	}

	public void grabar() {
		// TODO Grabar
	}
}
