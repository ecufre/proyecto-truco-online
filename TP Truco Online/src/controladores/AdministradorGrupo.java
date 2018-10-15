package controladores;

import java.util.ArrayList;
import java.util.Vector;

import dao.GrupoDAO;
import dto.GrupoDTO;
import dto.JugadorDTO;
import dto.ParejaDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import negocio.Grupo;
import negocio.Jugador;
import negocio.Pareja;

public class AdministradorGrupo {
	//private Vector<Grupo> grupos;
	
	public void crearGrupo(JugadorDTO administrador, String nombreGrupo) throws LoggedInException, ComunicacionException {
		if (AdministradorJugador.getInstancia().isLoggedIn(administrador)) {
			Jugador admin = AdministradorJugador.getInstancia().buscarJugador(administrador.getApodo());
			Grupo g = new Grupo(nombreGrupo, admin);
			Integer id = g.crear();
			if (id != null) g.setId(id);
		}
	}
	
	public void agregarJugadorAGrupo(JugadorDTO administrador, GrupoDTO grupo, String apodo) throws LoggedInException, ComunicacionException {
		if (AdministradorJugador.getInstancia().isLoggedIn(administrador)) {
			Grupo g = this.buscarGrupo(grupo.getId());
			if (g != null && g.getAdministrador().getApodo().equals(administrador.getApodo())) {
				Jugador j = AdministradorJugador.getInstancia().buscarJugador(apodo);
				if (j != null) {
					g.agregarJugador(j);
					g.grabar();
					j.agregarAGrupo(g);
					j.grabar();
				}
			}
		}
	}
	
	public void eliminarJugadorDeGrupo(JugadorDTO administrador, GrupoDTO grupo, String apodo) throws LoggedInException, ComunicacionException {
		if (AdministradorJugador.getInstancia().isLoggedIn(administrador)) {
			Grupo g = this.buscarGrupo(grupo.getId());
			if (g != null && g.getAdministrador().getApodo().equals(administrador.getApodo())) {
				Jugador j = AdministradorJugador.getInstancia().buscarJugador(apodo);
				if (j != null) {
					g.eliminarJugador(j);
					g.grabar();
				}
			}
		}
	}
	
	public Grupo buscarGrupo(Integer id) throws ComunicacionException {
		return GrupoDAO.getInstancia().toNegocio(GrupoDAO.getInstancia().getGroupById(id));
	}
	
	public void crearPareja(JugadorDTO administrador, GrupoDTO grupo, String apodo1, String apodo2) throws LoggedInException, ComunicacionException {
		if (AdministradorJugador.getInstancia().isLoggedIn(administrador)) {
			Grupo g = this.buscarGrupo(grupo.getId());
			if (g != null && g.getAdministrador().getApodo().equals(administrador.getApodo())) {
				Jugador j1 = AdministradorJugador.getInstancia().buscarJugador(apodo1);
				Jugador j2 = AdministradorJugador.getInstancia().buscarJugador(apodo2);
				if (j1 != null && j2 != null) g.crearPareja(j1, j2);
				g.grabar();
			}
		}
	}
	
	public void crearPartida(JugadorDTO administrador, GrupoDTO grupo, ParejaDTO pareja1, ParejaDTO pareja2) throws LoggedInException, ComunicacionException {
		if (AdministradorJugador.getInstancia().isLoggedIn(administrador)) {
			Grupo g = this.buscarGrupo(grupo.getId());
			if (g != null && g.getAdministrador().getApodo().equals(administrador.getApodo())) {
				Pareja p1 = g.buscarPareja(pareja1.getId());
				Pareja p2 = g.buscarPareja(pareja2.getId());
				if (p1 != null && p2 != null) g.crearPartida(p1, p2);
				g.grabar();
			}
		}
	}
	
	public ArrayList<JugadorDTO> calcularRankingCerrado(GrupoDTO grupo) throws ComunicacionException {
		return this.buscarGrupo(grupo.getId()).calcularRankingCerrado();
	}
}
