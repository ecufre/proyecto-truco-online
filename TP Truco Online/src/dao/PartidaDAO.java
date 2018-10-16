package dao;

import entities.JugadorEntity;
import entities.PartidaEntity;
import negocio.Jugador;
import negocio.Partida;

public class PartidaDAO {
	
	private static PartidaDAO instancia;
	
	
private PartidaDAO() {}
	
	public static PartidaDAO getInstancia() {
		if (instancia == null) {
			instancia = new PartidaDAO();
		}
		return instancia;
	}
	
	public PartidaEntity getPartidaById(int id) {
		return null; //TODO
	}
	
	
	public Integer crear(Partida p) {
		
		return null; //TODO
	}
	
	public void grabar(Partida p) {
		
		//TODO
	}
	
	public void actualizar(Partida p) {

		//TODO
	}
	
	public Partida toNegocio(PartidaEntity pe) {
		return null; //TODO
	}
	
	public PartidaEntity toEntity(Partida p) {
		return null; //TODO
	}
}
