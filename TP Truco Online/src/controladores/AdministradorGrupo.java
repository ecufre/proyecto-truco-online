package controladores;

import java.util.Vector;

import negocio.Grupo;

public class AdministradorGrupo {
	private Vector<Grupo> grupos;
	
	public void crearGrupo(String nombreGrupo, String apodoAdmin) {}
	
	public void agregarJugadorAGrupo(String apodo) {}
	
	public void eliminarJugadorDeGrupo(String apodo) {}
	
	public Grupo buscarGrupo(String nombreGrupo) {}
	
	public void crearPareja(String apodo1, String apodo2) {}
	
	public void crearPartida(int idPareja1, int idPareja2) {}
	
	public void listarJugadoresGrupo(String nombreGrupo) {}
	
	public void listarParejasGrupo(String nombreGrupo) {}
	
	public void listarPartidasGrupo(String nombreGrupo) {}
	
	public void calcularRankingCerrado(String nombreGrupo) {}
}
