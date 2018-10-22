package test;

import java.util.ArrayList;

import controladores.AdministradorJugador;
import controladores.AdministradorPartida;
import dao.MazoDAO;
import dto.AccionDTO;
import dto.CartaDTO;
import dto.JugadorDTO;
import dto.PartidaDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;

public class Test {

	public static void main(String[] args) {
		try {
			MazoDAO.getInstancia().crearMazo();
			AdministradorJugador c1 = AdministradorJugador.getInstancia();
			//CreadorPartida cp = CreadorPartida.getInstancia();
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
			
			c1.login(admin);
			AdministradorJugador.getInstancia().jugarLibreIndividual(admin);
			AdministradorJugador.getInstancia().jugarLibreIndividual(jugador);
			AdministradorJugador.getInstancia().jugarLibreIndividual(jugador1);
			AdministradorJugador.getInstancia().jugarLibreIndividual(jugador4);
			AdministradorJugador.getInstancia().jugarLibrePareja(admin, "kike2");
			//ArrayList<InvitacionDTO> inv = AdministradorJugador.getInstancia().listarInvitacionesPendientes(jugador);
/*			AdministradorJugador.getInstancia().aceptarInvitacion(jugador, inv.get(0));
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
			c2.crearPartida(admin, g, p1, p2);*/
			PartidaDTO pdo = new PartidaDTO();
			pdo.setId(1);
			
			AccionDTO ad = new AccionDTO(pdo, admin, 1, false, "");
			c3.jugadorListo(ad);
			ad.setJugador(jugador);
			c3.jugadorListo(ad);
			ad.setJugador(jugador1);
			c3.jugadorListo(ad);
			ad.setJugador(jugador4);
			c3.jugadorListo(ad);
			ArrayList<JugadorDTO> jugadores = new ArrayList<JugadorDTO>();
			jugadores.add(admin);
			jugadores.add(jugador);
			jugadores.add(jugador1);
			jugadores.add(jugador4);
			
			ArrayList<CartaDTO> baza1 = new ArrayList<CartaDTO>();
			baza1.add(new CartaDTO(3, 3, "espada", 1));
			baza1.add(new CartaDTO(38, 10, "oro", 2));
			baza1.add(new CartaDTO(4, 4, "espada", 3));
			baza1.add(new CartaDTO(5, 5, "espada", 4));
			
			ArrayList<CartaDTO> baza2 = new ArrayList<CartaDTO>();
			baza2.add(new CartaDTO(2, 2, "espada", 1));
			baza2.add(new CartaDTO(39, 11, "oro", 1));
			baza2.add(new CartaDTO(26, 6, "copa", 1));
			baza2.add(new CartaDTO(30, 12, "copa", 1));
			
			ad.setJugador(admin);
			//mostrarPartida(c3,ad);
		System.out.println("ARRANCAMOS");

			
			for (int i = 0; i < 30; i++) {
				//Primera baza
				CartaDTO cd = baza1.get((i+0)%4);
				ad.setJugador(jugadores.get((i+0)%4));
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				//mostrarPartida(c3,ad);
				cd = baza1.get((i+1)%4);
				ad.setJugador(jugadores.get((i+1)%4));
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				//mostrarPartida(c3,ad);
				cd = baza1.get((i+2)%4);
				ad.setJugador(jugadores.get((i+2)%4));
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				//mostrarPartida(c3,ad);
				cd = baza1.get((i+3)%4);
				ad.setJugador(jugadores.get((i+3)%4));
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				//mostrarPartida(c3,ad);
				//Segunda baza
				cd = baza2.get(0);
				ad.setJugador(jugadores.get(0));
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				//mostrarPartida(c3,ad);
				cd = baza2.get(1);
				ad.setJugador(jugadores.get(1));
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				//mostrarPartida(c3,ad);
				cd = baza2.get(2);
				ad.setJugador(jugadores.get(2));
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				//mostrarPartida(c3,ad);
				cd = baza2.get(3);
				ad.setJugador(jugadores.get(3));
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
			/*
			//Primera baza
				EnviteDTO edto = new EnviteDTO(TipoCanto.Envido, true, true);
				ad.setEnvite(edto);
				
				CartaDTO cd = baza1.get(0);
				ad.setJugador(jugadores.get(0));
				c3.cantarEnvite(ad);
				ad.setJugador(jugadores.get(1));
				ad.getEnvite().setTipoCanto(TipoCanto.EnvidoEnvido);
				c3.cantarEnvite(ad);
				ad.setJugador(jugadores.get(0));
				c3.responderEnvite(ad);
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				mostrarPartida(c3,ad);
				cd = baza1.get(1);
				ad.setJugador(jugadores.get(1));
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				cd = baza1.get(2);
				ad.setJugador(jugadores.get(2));
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				cd = baza1.get(3);
				ad.setJugador(jugadores.get(3));
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				//Segunda baza
				cd = baza2.get(0);
				ad.setJugador(jugadores.get(0));
				ad.setCarta(cd);
				ad.getEnvite().setTipoCanto(TipoCanto.Truco);
				c3.cantarEnvite(ad);
				ad.setJugador(jugadores.get(1));
				c3.responderEnvite(ad);
				ad.setJugador(jugadores.get(0));
				c3.JugarCarta(ad);
				
				cd = baza2.get(1);
				ad.setJugador(jugadores.get(1));
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				cd = baza2.get(2);
				ad.setJugador(jugadores.get(2));
				c3.Retirarse(ad);
				/*
				ad.setCarta(cd);
				c3.JugarCarta(ad);
				
				cd = baza2.get(3);
				ad.setJugador(jugadores.get(3));
				ad.getEnvite().setTipoCanto(TipoCanto.ReTruco);
				c3.cantarEnvite(ad);
				ad.setJugador(jugadores.get(0));
				ad.getEnvite().setTipoCanto(TipoCanto.ValeCuatro);
				c3.cantarEnvite(ad);
				ad.setJugador(jugadores.get(3));
				ad.getEnvite().setRespuesta(false);
				c3.responderEnvite(ad);
				//ad.setCarta(cd);
				//c3.JugarCarta(ad);
				*/
				
				
			return;
		} catch (ComunicacionException ce) {
			System.err.print(ce.getMessage());
		} catch (LoggedInException le) {
			System.err.println(le.getMessage());
		}
	}
	
	public static void jugarMano(int numeroMano) {
		
	}
	/*
	public static void mostrarPartida(AdministradorPartida c3,AccionDTO ad) throws ComunicacionException, LoggedInException{
	
	PartidaDTO pd = c3.mostrarPartida(ad);
	System.out.println(c3.mostrarPartida(ad));
	System.out.println("Juegos\n Nosotros:"+pd.getJuegosNosotros()+" Ellos: "+pd.getJuegosEllos() );
	System.out.println("Puntos Juegos\n Nosotros:"+pd.getPuntosJuegoNosotros()+" Ellos: "+pd.getPuntosJuegoEllos() );
	System.out.println("jugador:"			+pd.getJugador().getApodo()+" suma Envido: "+pd.getValorEnvidoJugador());
	System.out.println("jugador frente:"	+pd.getJugadorFrente().getApodo()+" suma Envido: "+pd.getValorEnvidoJugadorFrente());
	System.out.println("jugador IZQ:"		+pd.getJugadorIzquierda().getApodo()+" suma Envido: "+pd.getValorEnvidoJugadorIquierda());
	System.out.println("jugador DER:"		+pd.getJugadorDerecha().getApodo()+" suma Envido: "+pd.getValorEnvidoJugadorDerecha());
	
	System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
			+ 		   ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
			+ 			":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	System.out.println("Cartas Jugador: "+pd.getJugador().getApodo());
	for(CartaDTO c :pd.getCartasJugador()){
		System.out.println(c.toString());
	}
	System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
			+ 		   ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
			+ 			":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	System.out.println("Cartas Jugador Mesa: "+pd.getJugador().getApodo());
	for(CartaDTO c :pd.getCartasMesaJugador()){
		System.out.println(c.toString());
	}
	System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
			+ 		   ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
			+ 			":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	System.out.println("Cartas Jugador Mesa Frente: "+pd.getJugadorFrente().getApodo());
	for(CartaDTO c :pd.getCartasMesaJugadorFrente()){
		System.out.println(c.toString());
	}
	System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
			+ 		   ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
			+ 			":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	System.out.println("Cartas Jugador Mesa Izquieda: "+pd.getJugadorIzquierda().getApodo());
	for(CartaDTO c :pd.getCartasMesajugadorIzquierda()){
		System.out.println(c.toString());
	}
	System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
			+ 		   ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
			+ 			":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	System.out.println("Cartas Jugador Mesa Derecha: "+pd.getJugadorDerecha().getApodo());
	for(CartaDTO c :pd.getCartasMesaJugadorDerecha()){
		System.out.println(c.toString());
	}
	}
	*/
}

