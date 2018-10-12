package dao;

import java.util.ArrayList;

import entities.JugadorEntity;
import negocio.Jugador;

public class JugadorDAO {
	private static JugadorDAO instancia;

	private JugadorDAO() {}
	
	public JugadorDAO getInstancia() {
		if (this.instancia == null) {
			this.instancia = new JugadorDAO();
		}
		return this.instancia;
	}
	
	public JugadorEntity getJugadorById(int id) {
		return null; //TODO
	}
	
	public Jugador toNegocio(JugadorEntity je) {
		return null; //TODO
	}
	
	public Integer crear(Jugador j) {
		//Persiste un nuevo jugador, devuelve el ID generado por la base de datos.
		return null; //TODO
	}
	
	public void grabar(Jugador j) {
		//Persiste cambios en un jugador ya existente (Incluye categoria)
		//TODO
	}
	
	public ArrayList<Jugador> getTopTenJugadores(int categoria){
		//Devuelve el top 10 de jugadores por promedio para la categoria solicitada.
		return null; //TODO
	}
	
}
