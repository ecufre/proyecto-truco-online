package delegado;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dto.AccionDTO;
import dto.GrupoDTO;
import dto.HistoriaPartidaDTO;
import dto.InvitacionDTO;
import dto.JugadorDTO;
import dto.ParejaDTO;
import dto.PartidaDTO;
import dto.PartidaPantallaDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import interfaces.InterfaceRemota;

public class BusinessDelegate {

	private InterfaceRemota ir;
	
	private static BusinessDelegate instance;
	
	public static BusinessDelegate getInstance() throws ComunicacionException{
		
		if(instance==null){
			instance = new BusinessDelegate(); 
		}
	
		return instance;
	}
	
	private BusinessDelegate() throws ComunicacionException{
		try {
			ir = (InterfaceRemota) Naming.lookup("//127.0.0.1/truco");
		} catch (MalformedURLException e) {
			throw new ComunicacionException("La direccion especificada no es correcta");
		} catch (RemoteException e) {
		
		} catch (NotBoundException e) {
			throw new ComunicacionException("El servidor no esta disponible");		
		}
	}


	public void jugadorListos(AccionDTO ad) throws ComunicacionException, LoggedInException {
		try {
			 ir.jugadorListos(ad);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}

	
	public void JugarCarta(AccionDTO ad) throws ComunicacionException, LoggedInException {
		try {
			 ir.JugarCarta(ad);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}


	public void Retirarse(AccionDTO ad) throws ComunicacionException, LoggedInException {
		try {
			ir.Retirarse(ad);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
		
	}

	
	public void cantarEnvite(AccionDTO ad) throws ComunicacionException, LoggedInException {
		try {
			 ir.cantarEnvite(ad);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
		
		
	}

	
	public void responderEnvite(AccionDTO ad) throws ComunicacionException, LoggedInException {
		try {
			ir.responderEnvite(ad);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
		
		
	}


	public PartidaPantallaDTO mostrarPartida(AccionDTO ad) throws ComunicacionException, LoggedInException {
		try {
			return ir.mostrarPartida(ad);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
		
		
	}

/*
	public void enviarMensaje(AccionDTO ad) throws ComunicacionException {
		
			 ir.enviarMensaje(ad);
		
		
		
	}

	
	public void enviarSenia(AccionDTO ad) throws ComunicacionException {
		
			ir.enviarSenia(ad);
		
		
	}

	*/
	public PartidaDTO mostrarHistoria(AccionDTO ad)
			throws ComunicacionException, LoggedInException {
		try {
			return ir.mostrarHistoria(ad);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
		
		
	}
	
	//AdministradorJugador
		public void crearJugador(JugadorDTO jugador) throws ComunicacionException {
			try {
				ir.crearJugador(jugador);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
			
		}
		
		public void login(JugadorDTO jugador) throws ComunicacionException, LoggedInException {
			try {
				ir.login(jugador);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
			
		}
		
		public void logout(JugadorDTO jugador) throws LoggedInException, ComunicacionException {
			try {
				ir.logout(jugador);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
			
		}
		
		public Boolean isLoggedIn(JugadorDTO jugador) throws LoggedInException, ComunicacionException {
			try {
				return ir.isLoggedIn(jugador);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
			
		}

		public void jugarLibreIndividual(JugadorDTO jugador) throws LoggedInException, ComunicacionException {
			try {
				ir.jugarLibreIndividual(jugador);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
			
		}
		
		public void jugarLibrePareja(JugadorDTO remitente, String invitado) throws LoggedInException, ComunicacionException {
			try {
				ir.jugarLibrePareja(remitente, invitado);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
			
		}
		
		public ArrayList<InvitacionDTO> listarInvitacionesPendientes(JugadorDTO jugador) throws LoggedInException, ComunicacionException {
			try {
				return ir.listarInvitacionesPendientes(jugador);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
			
		}
		
		public void aceptarInvitacion(JugadorDTO jugador, InvitacionDTO invitacion) throws LoggedInException, ComunicacionException {
			try {
				ir.aceptarInvitacion(jugador, invitacion);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
			
		}
		
		public void rechazarInvitacion(JugadorDTO jugador, InvitacionDTO invitacion) throws LoggedInException, ComunicacionException{
			try {
				ir.rechazarInvitacion(jugador, invitacion);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
			
		}
		
		public ArrayList<JugadorDTO> listarRanking() throws ComunicacionException{
			try {
				return ir.listarRanking();
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
			
		}
		
		public JugadorDTO buscarJugadorDTO(String apodo) throws ComunicacionException{
			try {
				return ir.buscarJugadorDTO(apodo);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
			
		}
		
		//AdministradorGrupo
		public void crearGrupo(JugadorDTO admin, String nombre) throws LoggedInException, ComunicacionException{
			try {
				ir.crearGrupo(admin, nombre);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
			
		}
		
		public void buscarGrupoDTO(Integer id) throws ComunicacionException{
			try {
				ir.buscarGrupoDTO(id);;
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}

		}
		
		public void agregarJugadorAGrupo(JugadorDTO admin, GrupoDTO grupo, String apodo) throws LoggedInException, ComunicacionException{
			try {
				ir.agregarJugadorAGrupo(admin, grupo, apodo);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}

		}
		
		public void eliminarJugadorDeGrupo(JugadorDTO admin, GrupoDTO grupo, JugadorDTO jugador) throws LoggedInException, ComunicacionException{
			try {
				ir.eliminarJugadorDeGrupo(admin, grupo, jugador);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
		}
		
		public void crearPareja (JugadorDTO admin, GrupoDTO grupo, JugadorDTO jugador1, JugadorDTO jugador2) throws LoggedInException, ComunicacionException{
			try {
				ir.crearPareja(admin, grupo, jugador1, jugador2);
			
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}	
		}
		
		public void crearPartida(JugadorDTO admin, GrupoDTO grupo, ParejaDTO pareja1, ParejaDTO pareja2) throws LoggedInException, ComunicacionException{
			try {
				ir.crearPartida(admin, grupo, pareja1, pareja2);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
		}
  
		public ArrayList<JugadorDTO> calcularRankingCerrado(GrupoDTO grupo) throws ComunicacionException{
			try {
				return ir.calcularRankingCerrado(grupo);
			} catch (RemoteException e) {
				throw new ComunicacionException("Error en las comunicaciones");	
			}
		}
	  
}
	
	
	
