package test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dao.MazoDAO;
import interfaces.InterfaceRemota;
import remoto.ObjetoRemoto;

public class Server {

	public Server() throws RemoteException{
		inicializar();
	}
	
	public static void main(String[] args) {
		try {
			new Server();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void inicializar() throws RemoteException {
		
		InterfaceRemota or = new ObjetoRemoto();
		
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind("//127.0.0.1/truco", or);
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			System.out.println(dtf.format(now) + " - Server started successfully.");
			MazoDAO.getInstancia().crearMazo();
			System.out.println(dtf.format(now) + " - Se creo el Mazo.");
			

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
