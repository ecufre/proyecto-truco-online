package test;

import java.rmi.RemoteException;

import test.Server;

public class IniciarServidor {

	public static void main(String[] args) {
		try {
			new Server();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
