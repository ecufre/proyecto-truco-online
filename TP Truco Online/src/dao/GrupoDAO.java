package dao;

import entities.GrupoEntity;
import negocio.Grupo;

public class GrupoDAO {
	private static GrupoDAO instancia;
	
	private GrupoDAO () {}
	
	public static GrupoDAO getInstsancia() {
		if (instancia == null) instancia = new GrupoDAO();
		return instancia;
	}
	
	public GrupoEntity getGroupByName(String name) {
		return null; //TODO
	}
	
	public Grupo toNegocio(GrupoEntity ge) {
		return null; //TODO
	}
	
	public Integer crear() {
		return null; //TODO
	}
	
	public void grabar() {
		//TODO
	}
}
