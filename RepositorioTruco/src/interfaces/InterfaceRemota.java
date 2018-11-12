package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dto.HistoriaPartidaDTO;
import dto.PartidaDTO;
import dto.AccionDTO;
import dto.GrupoDTO;
import dto.InvitacionDTO;
import dto.JugadorDTO;
import dto.ParejaDTO;
import dto.PartidaPantallaDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;

public interface InterfaceRemota extends Remote {

	//AdministradorJugador
	public void crearJugador(JugadorDTO jugador) throws RemoteException,  ComunicacionException;

	public void login(JugadorDTO jugador) throws RemoteException, LoggedInException;

	public void logout(JugadorDTO jugador) throws RemoteException,  LoggedInException, ComunicacionException;

	public Boolean isLoggedIn(JugadorDTO jugador) throws RemoteException, LoggedInException, ComunicacionException;

	public void jugarLibreIndividual(JugadorDTO jugador) throws RemoteException,  LoggedInException, ComunicacionException;

	public void jugarLibrePareja(JugadorDTO remitente, String invitado) throws RemoteException,  LoggedInException, ComunicacionException;

	public ArrayList<InvitacionDTO> listarInvitacionesPendientes(JugadorDTO jugador) throws RemoteException,  LoggedInException, ComunicacionException;

	public void aceptarInvitacion(JugadorDTO jugador, InvitacionDTO invitacion) throws RemoteException,  LoggedInException, ComunicacionException;

	public void rechazarInvitacion(JugadorDTO jugador, InvitacionDTO invitacion) throws RemoteException,  LoggedInException, ComunicacionException;

	public ArrayList<JugadorDTO> listarRanking() throws RemoteException,  ComunicacionException;

	public JugadorDTO buscarJugadorDTO(String apodo) throws RemoteException,  ComunicacionException;

	//AdministradorGrupo
	public void crearGrupo(JugadorDTO admin, String nombre) throws RemoteException,  LoggedInException, ComunicacionException;

	public GrupoDTO buscarGrupoDTO(Integer id) throws RemoteException,  ComunicacionException;

	public void agregarJugadorAGrupo(JugadorDTO admin, GrupoDTO grupo, String apodo) throws RemoteException,  LoggedInException, ComunicacionException;

	public void eliminarJugadorDeGrupo(JugadorDTO admin, GrupoDTO grupo, JugadorDTO jugador) throws RemoteException,  LoggedInException, ComunicacionException;

	public void crearPareja (JugadorDTO admin, GrupoDTO grupo, JugadorDTO jugador1, JugadorDTO jugador2) throws RemoteException,  LoggedInException, ComunicacionException;

	public void crearPartida(JugadorDTO admin, GrupoDTO grupo, ParejaDTO pareja1, ParejaDTO pareja2) throws RemoteException,  LoggedInException, ComunicacionException;

	public ArrayList<JugadorDTO> calcularRankingCerrado(GrupoDTO grupo) throws RemoteException,  ComunicacionException;

	//AdministradorPartidas
	public void jugadorListos(AccionDTO ad) throws RemoteException,  LoggedInException, ComunicacionException;

	public void JugarCarta(AccionDTO ad) throws RemoteException,  LoggedInException, ComunicacionException;

	public void Retirarse(AccionDTO ad) throws RemoteException,  LoggedInException, ComunicacionException;

	public void cantarEnvite(AccionDTO ad) throws RemoteException,  LoggedInException, ComunicacionException;

	public void responderEnvite(AccionDTO ad) throws RemoteException,  LoggedInException, ComunicacionException;

	public PartidaPantallaDTO mostrarPartida(AccionDTO ad) throws RemoteException,  ComunicacionException, LoggedInException;

	//public void enviarMensaje(AccionDTO ad) throws RemoteException,  RemoteException;

	//public void enviarSenia(AccionDTO ad) throws RemoteException,  RemoteException;

	public PartidaDTO mostrarHistoria(AccionDTO ad) throws RemoteException,  ComunicacionException, LoggedInException;
	
	public ArrayList<PartidaDTO> listarMisPartidas(JugadorDTO jugador) throws RemoteException, ComunicacionException, LoggedInException;
}