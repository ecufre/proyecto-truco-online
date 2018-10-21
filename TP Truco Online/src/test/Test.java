package test;

import java.util.ArrayList;

import controladores.AdministradorGrupo;
import controladores.AdministradorJugador;
import controladores.AdministradorPartida;
import controladores.CreadorPartida;
import dto.AccionDTO;
import dto.CartaDTO;
import dto.GrupoDTO;
import dto.InvitacionDTO;
import dto.JugadorDTO;
import dto.ParejaDTO;
import dto.PartidaDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import negocio.Invitacion;
import negocio.Jugador;

public class Test {

	public static void main(String[] args) {
		try {
			AdministradorJugador c1 = AdministradorJugador.getInstancia();
			CreadorPartida cp = CreadorPartida.getInstancia();
			AdministradorPartida c3 = AdministradorPartida.getInstancia();
			JugadorDTO admin = new JugadorDTO("fer", "fer@fer", "Pass", "SessionID");
			AdministradorJugador.getInstancia().crearJugador(admin);			
			JugadorDTO jugador1 = new JugadorDTO("eze", "eze@eze", "Pass", "SessionID2");
			AdministradorJugador.getInstancia().crearJugador(jugador1);			
			JugadorDTO jugador2 = new JugadorDTO("kike", "kike@kike", "Pass", "SessionID3");
			AdministradorJugador.getInstancia().crearJugador(jugador2);			
			JugadorDTO jugador3 = new JugadorDTO("fer2", "fer2@fer", "Pass", "SessionID4");
			AdministradorJugador.getInstancia().crearJugador(jugador3);			
			JugadorDTO jugador4 = new JugadorDTO("eze2", "eze2@eze", "Pass", "SessionID5");
			AdministradorJugador.getInstancia().crearJugador(jugador4);			
			JugadorDTO jugador = new JugadorDTO("kike2", "kike2@kike", "Pass", "SessionID6");
			AdministradorJugador.getInstancia().crearJugador(jugador);			
			
			AdministradorJugador.getInstancia().login(admin);
			AdministradorJugador.getInstancia().jugarLibreIndividual(admin);
			AdministradorJugador.getInstancia().jugarLibrePareja(admin, "kike2");
			ArrayList<InvitacionDTO> inv = AdministradorJugador.getInstancia().listarInvitacionesPendientes(jugador);
			AdministradorJugador.getInstancia().aceptarInvitacion(jugador, inv.get(0));
			AdministradorGrupo c2 = AdministradorGrupo.getInstancia();
			c2.crearGrupo(admin, "La Cumbancha");
			GrupoDTO g = new GrupoDTO("La Cumbancha", 1);
			c2.agregarJugadorAGrupo(admin, g, "fer");
			c2.agregarJugadorAGrupo(admin, g, "eze");
			c2.agregarJugadorAGrupo(admin, g, "eze2");
			c2.agregarJugadorAGrupo(admin, g, "kike2");
			c2.crearPareja(admin, g, admin, jugador);
			c2.crearPareja(admin, g, jugador1, jugador4);
			ParejaDTO p1 = new ParejaDTO(1, admin, jugador);
			ParejaDTO p2 = new ParejaDTO(2, jugador1, jugador4);
			c2.crearPartida(admin, g, p1, p2);
			PartidaDTO pdo = new PartidaDTO(1);
			
			AccionDTO ad = new AccionDTO(pdo, admin, 1, false, "");
			c3.jugadorListo(ad);
			ad.setJugador(jugador);
			c3.jugadorListo(ad);
			ad.setJugador(jugador1);
			c3.jugadorListo(ad);
			ad.setJugador(jugador4);
			c3.jugadorListo(ad);
			for (int i = 0; i < 30; i++) {
				//Primera baza
				CartaDTO cd = new CartaDTO(7, 7, "espada", 1);
				ad.setJugador(admin);
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				cd = new CartaDTO(4, 4, "espada", 1);
				ad.setJugador(jugador1);
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				cd = new CartaDTO(14, 4, "basto", 1);
				ad.setJugador(jugador);
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				cd = new CartaDTO(34, 4, "oro", 1);
				ad.setJugador(jugador4);
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				//Segunda baza
				cd = new CartaDTO(2, 2, "espada", 1);
				ad.setJugador(admin);
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				cd = new CartaDTO(12, 2, "basto", 1);
				ad.setJugador(jugador1);
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				cd = new CartaDTO(17, 7, "basto", 1);
				ad.setJugador(jugador);
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				cd = new CartaDTO(19, 11, "basto", 1);
				ad.setJugador(jugador4);
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				/*
				//Tercera baza
				cd = new CartaDTO(10, 12, "espada", 1);
				ad.setJugador(jugador1);
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				cd = new CartaDTO(20, 12, "basto", 1);
				ad.setJugador(jugador);
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				cd = new CartaDTO(31, 1, "oro", 1);
				ad.setJugador(jugador4);
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				cd = new CartaDTO(5, 5, "espada", 1);
				ad.setJugador(admin);
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				*/
			}
	
			return;
		} catch (ComunicacionException ce) {
			System.err.print(ce.getMessage());
		} catch (LoggedInException le) {
			System.err.println(le.getMessage());
		}
	}

}
