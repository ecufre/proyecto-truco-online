package remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import controladores.AdministradorGrupo;
import controladores.AdministradorJugador;
import controladores.AdministradorPartida;
import dto.AccionDTO;
import dto.GrupoDTO;
import dto.InvitacionDTO;
import dto.JugadorDTO;
import dto.ParejaDTO;
import dto.PartidaDTO;
import dto.PartidaPantallaDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import interfaces.InterfaceRemota;

public class ObjetoRemoto extends UnicastRemoteObject implements InterfaceRemota {

	private static final long serialVersionUID = 8384110999673649178L;

	public ObjetoRemoto() throws RemoteException {}

	@Override
	public void crearJugador(JugadorDTO jugador) throws RemoteException, ComunicacionException {
		AdministradorJugador.getInstancia().crearJugador(jugador);
	}

	@Override
	public void login(JugadorDTO jugador) throws RemoteException, LoggedInException {
		AdministradorJugador.getInstancia().login(jugador);
	}

	@Override
	public void logout(JugadorDTO jugador) throws RemoteException, LoggedInException, ComunicacionException {
		AdministradorJugador.getInstancia().logout(jugador);
	}
	
	@Override
	public Boolean isLoggedIn(JugadorDTO jugador) throws RemoteException, LoggedInException, ComunicacionException {
		return AdministradorJugador.getInstancia().isLoggedIn(jugador);
	}
	
	@Override
	public void jugarLibreIndividual(JugadorDTO jugador) throws RemoteException, LoggedInException, ComunicacionException {
		AdministradorJugador.getInstancia().jugarLibreIndividual(jugador);
	}

	@Override
	public void jugarLibrePareja(JugadorDTO remitente, String invitado) throws RemoteException, LoggedInException, ComunicacionException {
		AdministradorJugador.getInstancia().jugarLibrePareja(remitente, invitado);
	}

	@Override
	public ArrayList<InvitacionDTO> listarInvitacionesPendientes(JugadorDTO jugador) throws RemoteException, LoggedInException, ComunicacionException {
		return AdministradorJugador.getInstancia().listarInvitacionesPendientes(jugador);
	}

	@Override
	public void aceptarInvitacion(JugadorDTO jugador, InvitacionDTO invitacion) throws RemoteException, LoggedInException, ComunicacionException {
		AdministradorJugador.getInstancia().aceptarInvitacion(jugador, invitacion);
	}

	@Override
	public void rechazarInvitacion(JugadorDTO jugador, InvitacionDTO invitacion) throws RemoteException, LoggedInException, ComunicacionException {
		AdministradorJugador.getInstancia().rechazarInvitacion(jugador, invitacion);
	}

	@Override
	public ArrayList<JugadorDTO> listarRanking() throws RemoteException, ComunicacionException {
		return AdministradorJugador.getInstancia().listarRanking();
	}

	@Override
	public JugadorDTO buscarJugadorDTO(String apodo) throws RemoteException, ComunicacionException {
		return AdministradorJugador.getInstancia().buscarJugadorDTO(apodo);
	}

	@Override
	public void crearGrupo(JugadorDTO admin, String nombre) throws RemoteException, LoggedInException, ComunicacionException {
		AdministradorGrupo.getInstancia().crearGrupo(admin, nombre);
	}

	@Override
	public GrupoDTO buscarGrupoDTO(Integer id) throws RemoteException, ComunicacionException {
		return AdministradorGrupo.getInstancia().buscarGrupoDTO(id);
	}

	@Override
	public void agregarJugadorAGrupo(JugadorDTO admin, GrupoDTO grupo, String apodo) throws RemoteException, LoggedInException, ComunicacionException {
		AdministradorGrupo.getInstancia().agregarJugadorAGrupo(admin, grupo, apodo);
	}

	@Override
	public void eliminarJugadorDeGrupo(JugadorDTO admin, GrupoDTO grupo, JugadorDTO jugador) throws RemoteException, LoggedInException, ComunicacionException {
		AdministradorGrupo.getInstancia().eliminarJugadorDeGrupo(admin, grupo, jugador);
	}

	@Override
	public void crearPareja(JugadorDTO admin, GrupoDTO grupo, JugadorDTO jugador1, JugadorDTO jugador2) throws RemoteException, LoggedInException, ComunicacionException {
		AdministradorGrupo.getInstancia().crearPareja(admin, grupo, jugador1, jugador2);
	}

	@Override
	public void crearPartida(JugadorDTO admin, GrupoDTO grupo, ParejaDTO pareja1, ParejaDTO pareja2) throws RemoteException, LoggedInException, ComunicacionException {
		AdministradorGrupo.getInstancia().crearPartida(admin, grupo, pareja1, pareja2);
	}

	@Override
	public ArrayList<JugadorDTO> calcularRankingCerrado(GrupoDTO grupo) throws RemoteException, ComunicacionException {
		return AdministradorGrupo.getInstancia().calcularRankingCerrado(grupo);
	}

	@Override
	public void jugadorListos(AccionDTO ad) throws RemoteException, LoggedInException, ComunicacionException {
		
		AdministradorPartida.getInstancia().jugadorListo(ad);
	}

	@Override
	public void JugarCarta(AccionDTO ad) throws RemoteException, LoggedInException, ComunicacionException {
			AdministradorPartida.getInstancia().JugarCarta(ad);		
	}

	@Override
	public void Retirarse(AccionDTO ad) throws RemoteException, LoggedInException, ComunicacionException {
		AdministradorPartida.getInstancia().Retirarse(ad);
		
	}

	@Override
	public void cantarEnvite(AccionDTO ad) throws RemoteException, LoggedInException, ComunicacionException {
		AdministradorPartida.getInstancia().cantarEnvite(ad);
		
	}

	@Override
	public void responderEnvite(AccionDTO ad) throws RemoteException, LoggedInException, ComunicacionException {
		AdministradorPartida.getInstancia().responderEnvite(ad);
		
	}

	@Override
	public PartidaPantallaDTO mostrarPartida(AccionDTO ad) throws RemoteException, ComunicacionException, LoggedInException {
		return AdministradorPartida.getInstancia().mostrarPartida(ad);
	}
	/*
	@Override
	public void enviarMensaje(AccionDTO ad) throws RemoteException {
		// TODO proxima entrega
		
	}

	@Override
	public void enviarSenia(AccionDTO ad) throws RemoteException {
		// TODO proxima entrega
		
	}
*/
	@Override
	public PartidaDTO mostrarHistoria(AccionDTO ad)
			throws RemoteException, ComunicacionException, LoggedInException {
		
		return AdministradorPartida.getInstancia().mostarPartidaCompleta(ad);
	}
}
