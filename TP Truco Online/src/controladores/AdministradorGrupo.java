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
	private static AdministradorGrupo instancia;
	
	private AdministradorGrupo() {}
	
	public static AdministradorGrupo getInstancia() {
		if (instancia == null) instancia = new AdministradorGrupo();
		return instancia;
	}
	//private Vector<Grupo> grupos;
	
	public void crearGrupo(JugadorDTO administrador, String nombreGrupo) throws LoggedInException, ComunicacionException {
		if (AdministradorJugador.getInstancia().isLoggedIn(administrador)) {
			Jugador admin = AdministradorJugador.getInstancia().buscarJugador(administrador.getApodo());
			Grupo g = new Grupo(nombreGrupo, admin);
			Integer id = g.crear();
			if (id != null) {
				g.setId(id);
				System.out.println("El jugador " + administrador.getApodo() + " creo el grupo: " + nombreGrupo + "(" + String.valueOf(id) + ")");
			}
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
					System.out.println("El jugador " + administrador.getApodo() + " agrego un jugador (" + apodo + ") al grupo: " + grupo.getNombre() + "(" + String.valueOf(grupo.getId()) + ")");
				}
			}
		}
	}
	
	public void eliminarJugadorDeGrupo(JugadorDTO administrador, GrupoDTO grupo, JugadorDTO jugador) throws LoggedInException, ComunicacionException {
		if (AdministradorJugador.getInstancia().isLoggedIn(administrador)) {
			Grupo g = this.buscarGrupo(grupo.getId());
			if (g != null && g.getAdministrador().getApodo().equals(administrador.getApodo())) {
				Jugador j = AdministradorJugador.getInstancia().buscarJugador(jugador.getApodo());
				if (j != null && g.esMiembro(j)) {
					g.eliminarJugador(j);
					g.grabar();
					System.out.println("El jugador " + administrador.getApodo() + " elimino un jugador (" + jugador.getApodo() + ") al grupo: " + grupo.getNombre() + "(" + String.valueOf(grupo.getId()) + ")");
				} else throw new ComunicacionException("El jugador no forma parte del grupo");
			}
		}
	}
	
	public Grupo buscarGrupo(Integer id) throws ComunicacionException {
		return GrupoDAO.getInstancia().toNegocio(GrupoDAO.getInstancia().getGroupById(id));
	}
	
	public GrupoDTO buscarGrupoDTO(Integer id) throws ComunicacionException {
		return GrupoDAO.getInstancia().toNegocio(GrupoDAO.getInstancia().getGroupById(id)).toDTO();
	}
	
	public void crearPareja(JugadorDTO administrador, GrupoDTO grupo, JugadorDTO jugador1, JugadorDTO jugador2) throws LoggedInException, ComunicacionException {
		if (AdministradorJugador.getInstancia().isLoggedIn(administrador)) {
			Grupo g = this.buscarGrupo(grupo.getId());
			if (g != null && g.getAdministrador().getApodo().equals(administrador.getApodo())) {
				Jugador j1 = AdministradorJugador.getInstancia().buscarJugador(jugador1.getApodo());
				Jugador j2 = AdministradorJugador.getInstancia().buscarJugador(jugador2.getApodo());
				if (j1 != null && j2 != null && j1.getApodo().compareTo(j2.getApodo()) != 0 && g.esMiembro(j1) && g.esMiembro(j2)) g.crearPareja(j1, j2);
				else throw new ComunicacionException("Creacion de pareja invalida");
				g.grabar();
				System.out.println("El jugador " + administrador.getApodo() + " creo una pareja (" + jugador1.getApodo() + " y " + jugador2.getApodo() + ") en el grupo: " + grupo.getNombre() + "(" + String.valueOf(grupo.getId()) + ")");

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
				else throw new ComunicacionException("Fallo la creacion de partida");
				System.out.println("El jugador " + administrador.getApodo() + " creo una partida en el grupo: " + grupo.getNombre() + "(" + String.valueOf(grupo.getId()) + ")");
				//g.grabar(); TODO sacar quoteo
			}
		}
	}
	
	public ArrayList<JugadorDTO> calcularRankingCerrado(GrupoDTO grupo) throws ComunicacionException {
		return this.buscarGrupo(grupo.getId()).calcularRankingCerrado();
	}
}
