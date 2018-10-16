package interfaces;

import java.rmi.Remote;
import java.util.ArrayList;

import dto.GrupoDTO;
import dto.InvitacionDTO;
import dto.JugadorDTO;
import dto.ParejaDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;

public interface InterfaceRemota extends Remote {
	
	//AdministradorJugador
	public void crearJugador(JugadorDTO jugador) throws ComunicacionException;
	
	public void login(JugadorDTO jugador) throws ComunicacionException;
	
	public void logout(JugadorDTO jugador) throws LoggedInException, ComunicacionException;
	
	public void jugarLibreIndividual(JugadorDTO jugador) throws LoggedInException, ComunicacionException;
	
	public void jugarLibrePareja(JugadorDTO remitente, String invitado) throws LoggedInException, ComunicacionException;
	
	public ArrayList<InvitacionDTO> listarInvitacionesPendientes(JugadorDTO jugador) throws LoggedInException, ComunicacionException;
	
	public void aceptarInvitacion(JugadorDTO jugador, InvitacionDTO invitacion) throws LoggedInException, ComunicacionException;
	
	public void rechazarInvitacion(JugadorDTO jugador, InvitacionDTO invitacion) throws LoggedInException, ComunicacionException;
	
	public ArrayList<JugadorDTO> listarTopTen(Integer categoria) throws ComunicacionException;
	
	public JugadorDTO buscarJugadorDTO(String apodo) throws ComunicacionException;
	
	//AdministradorGrupo
	public void crearGrupo(JugadorDTO admin, String nombre) throws LoggedInException, ComunicacionException;
	
	public void buscarGrupoDTO(Integer id) throws ComunicacionException;
	
	public void agregarJugadorAGrupo(JugadorDTO admin, GrupoDTO grupo, String apodo) throws LoggedInException, ComunicacionException;
	
	public void eliminarJugadorDeGrupo(JugadorDTO admin, GrupoDTO grupo, JugadorDTO jugador) throws LoggedInException, ComunicacionException;
	
	public void crearPareja (JugadorDTO admin, GrupoDTO grupo, JugadorDTO jugador1, JugadorDTO jugador2) throws LoggedInException, ComunicacionException;
	
	public void crearPartida(JugadorDTO admin, GrupoDTO grupo, ParejaDTO pareja1, ParejaDTO pareja2) throws LoggedInException, ComunicacionException;
	
	public ArrayList<JugadorDTO> calcularRankingCerrado(GrupoDTO grupo) throws ComunicacionException;
}
