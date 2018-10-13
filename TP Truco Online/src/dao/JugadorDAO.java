package dao;

import java.util.ArrayList;

import entities.JugadorEntity;
import negocio.Jugador;

public class JugadorDAO {
	private static JugadorDAO instancia;

	private JugadorDAO() {}
	
	public static JugadorDAO getInstancia() {
		if (instancia == null) {
			instancia = new JugadorDAO();
		}
		return instancia;
	}
	
	public JugadorEntity getJugadorById(int id) {
		return null; //TODO
	}
	
	public JugadorEntity getJugadorByApodo(String apodo) {
		return null; //TODO
	}

	public Jugador toNegocio(JugadorEntity je) {
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
	
	public void actualizar(Jugador j) {
		//Actualiza la informacion en memoria de un jugador ya existente.
		//TODO
	}
	
}
