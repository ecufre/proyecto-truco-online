package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import dto.AccionDTO;

import dto.HistoriaPartidaDTO;
import dto.PartidaDTO;

public interface InterfaceRemota extends Remote {

	
	
	public boolean jugadorListos(AccionDTO ad) throws RemoteException;
	public void JugarCarta(AccionDTO ad) throws RemoteException;
	public void Retirarse(AccionDTO ad) throws RemoteException;
	public void cantarEnvite(AccionDTO ad) throws RemoteException;
	public void responderEnvite(AccionDTO ad) throws RemoteException;
	public PartidaDTO mostrarPartida(AccionDTO ad) throws RemoteException;
	public void enviarMensaje(AccionDTO ad) throws RemoteException;
	public void enviarSenia(AccionDTO ad) throws RemoteException;
	
	public HistoriaPartidaDTO mostrarHistoria(AccionDTO ad) throws RemoteException;
}
