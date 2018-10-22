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
  
  //AdministradorPartidas
  public void jugadorListos(AccionDTO ad) throws RemoteException, LoggedInException, ComunicacionException;
  
	public void JugarCarta(AccionDTO ad) throws RemoteException, LoggedInException, ComunicacionException;
	
  public void Retirarse(AccionDTO ad) throws RemoteException, LoggedInException, ComunicacionException;
	
  public void cantarEnvite(AccionDTO ad) throws RemoteException, LoggedInException, ComunicacionException;
	
  public void responderEnvite(AccionDTO ad) throws RemoteException, LoggedInException, ComunicacionException;
	
  public PartidaPantallaDTO mostrarPartida(AccionDTO ad) throws RemoteException, ComunicacionException, LoggedInException;
	
  public void enviarMensaje(AccionDTO ad) throws RemoteException;
	
  public void enviarSenia(AccionDTO ad) throws RemoteException;
	
	public PartidaDTO mostrarHistoria(AccionDTO ad) throws RemoteException, ComunicacionException, LoggedInException;
}