package delegado;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dto.AccionDTO;
import dto.HistoriaPartidaDTO;
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
	
	public BusinessDelegate() throws ComunicacionException{
		try {
			ir = (InterfaceRemota) Naming.lookup("//127.0.0.1/truco");
		} catch (MalformedURLException e) {
			throw new ComunicacionException("La direccion especificada no es correcta");
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
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


	public void enviarMensaje(AccionDTO ad) throws ComunicacionException {
		try {
			 ir.enviarMensaje(ad);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
		
	}

	
	public void enviarSenia(AccionDTO ad) throws ComunicacionException {
		try {
			ir.enviarSenia(ad);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
		
	}

	
	public PartidaDTO mostrarHistoria(AccionDTO ad)
			throws ComunicacionException, LoggedInException {
		try {
			return ir.mostrarHistoria(ad);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
	}
}
	
	
	
