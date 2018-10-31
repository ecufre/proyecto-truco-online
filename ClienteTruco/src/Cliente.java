import java.util.Scanner;

import delegado.BusinessDelegate;
import dto.AccionDTO;
import dto.CartaDTO;
import dto.EnviteDTO;
import dto.GrupoDTO;
import dto.JugadorDTO;
import dto.PartidaDTO;
import dto.PartidaPantallaDTO;
import enumeraciones.TipoCanto;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;

public class Cliente {
	private static TipoCanto tc;
	private static EnviteDTO e ;
	public static void main(String[] args) {

		try {
			BusinessDelegate bd = BusinessDelegate.getInstance();
			JugadorDTO admin = new JugadorDTO("Ced", "fer@fer", "Pass", "SessionID");
			bd.crearJugador(admin);
			JugadorDTO jugador1 = new JugadorDTO("San", "eze@eze", "Pass", "SessionID2");
			bd.crearJugador(jugador1);			
			JugadorDTO jugador2 = new JugadorDTO("Bru", "kike@kike", "Pass", "SessionID3");
			bd.crearJugador(jugador2);			
			JugadorDTO jugador3 = new JugadorDTO("Ale", "fer2@fer", "Pass", "SessionID4");
			bd.crearJugador(jugador3);			
			JugadorDTO jugador4 = new JugadorDTO("Ger", "eze2@eze", "Pass", "SessionID5");
			bd.crearJugador(jugador4);			
			JugadorDTO jugador = new JugadorDTO("Fer", "kike2@kike", "Pass", "SessionID6");
			bd.crearJugador(jugador);	

			bd.login(admin);
			bd.crearGrupo(admin, "La Cumbancha");
			GrupoDTO g = new GrupoDTO("La Cumbancha", 1);
			bd.agregarJugadorAGrupo(admin, g, "Ced");
			bd.agregarJugadorAGrupo(admin, g, "Fer");
			bd.agregarJugadorAGrupo(admin, g, "San");
			bd.agregarJugadorAGrupo(admin, g, "Ger");
			bd.crearPareja(admin, g, admin, jugador);
			bd.crearPareja(admin, g, jugador1, jugador4);

			bd.jugarLibreIndividual(jugador1);
			bd.jugarLibreIndividual(jugador2);
			bd.jugarLibreIndividual(jugador3);
			bd.jugarLibreIndividual(jugador4);

			PartidaDTO pd = new PartidaDTO();
			pd.setId(1);
			AccionDTO ad1 = new AccionDTO(pd, jugador1, 1, false, "");
			bd.jugadorListos(ad1);
			ad1.setJugador(jugador2);
			bd.jugadorListos(ad1);
			ad1.setJugador(jugador3);
			bd.jugadorListos(ad1);
			ad1.setJugador(jugador4);
			bd.jugadorListos(ad1);


		} catch (ComunicacionException | LoggedInException e) {
			System.err.println(e.getMessage());
		}

		int val=0;
		System.out.println("Empezo la partida...");
		while (val !=-1){
			try {
				BusinessDelegate bd = BusinessDelegate.getInstance();
				Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
				String entradaTeclado;

				JugadorDTO jugador1 = new JugadorDTO("San", "mail1@mail", "Pass", "SessionID2");
				JugadorDTO jugador2 = new JugadorDTO("Bru", "mail2@mail", "Pass", "SessionID3");
				JugadorDTO jugador3 = new JugadorDTO("Ale", "mail3@mail", "Pass", "SessionID4");
				JugadorDTO jugador4 = new JugadorDTO("Ger", "mail4@mail", "Pass", "SessionID5");

				PartidaDTO pd = new PartidaDTO();
				pd.setId(1);
				JugadorDTO[] jugadores = new JugadorDTO[4];  
				jugadores[0] = jugador1;
				jugadores[1] = jugador2;
				jugadores[2] = jugador3;
				jugadores[3] = jugador4;

				AccionDTO ad;
				PartidaPantallaDTO pdto;
				System.out.print("Inserte numero de jugador (0 a 3): ");
				val = Integer.parseInt(entradaEscaner.nextLine());

				JugadorDTO jugAct = jugadores[val];
				ad = new AccionDTO(pd, jugAct,1,true,null);
				pdto = bd.mostrarPartida(ad);

				mostrarPartida(pdto);
				System.out.print("-1) Salir -2) Seleccionar otro jugador\n20) Jugar carta 21) Retirarse de la mano\n1) Envido 2) Envido Envido 3) Real Envido 4) Falta Envido\n5) Truco 6) ReTruco 7) Vale 4\n8) Quiero 9) No Queiro\n-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\nSeleccione una opcion: ");
				val = Integer.parseInt(entradaEscaner.nextLine());
				switch (val){
				case 20:
					System.out.println("ID de Carta a Jugar: ");
					entradaTeclado = entradaEscaner.nextLine();
					Integer carta = Integer.parseInt(entradaTeclado);
					CartaDTO c= new CartaDTO();
					c.setId(carta);
					ad.setJugador(jugAct);
					ad.setCarta(c);
					bd.JugarCarta(ad);
					break;
				case 1:
					tc =  TipoCanto.Envido;
					e = new EnviteDTO(tc, true, true);
					ad.setEnvite(e);
					ad.setJugador(jugAct);
					bd.cantarEnvite(ad);
					break;
				case 2:
					tc	=  TipoCanto.EnvidoEnvido;
					e = new EnviteDTO(tc, true, true);
					ad.setEnvite(e); 
					ad.setJugador(jugAct);
					bd.cantarEnvite(ad);
					break;
				case 3:
					tc 	=  TipoCanto.RealEnvido;
					e = new EnviteDTO(tc, true, true);
					ad.setEnvite(e);
					ad.setJugador(jugAct);
					bd.cantarEnvite(ad);
					break;
				case 4:
					tc=  TipoCanto.FaltaEnvido;
					e = new EnviteDTO(tc, true, true);
					ad.setEnvite(e);
					ad.setJugador(jugAct);
					bd.cantarEnvite(ad);
					break;
				case 5:
					tc 	=  TipoCanto.Truco;
					e = new EnviteDTO(tc, true, true);
					ad.setEnvite(e);
					ad.setJugador(jugAct);
					bd.cantarEnvite(ad);
					break;
				case 6:
					tc 	=  TipoCanto.ReTruco;
					e = new EnviteDTO(tc, true, true);
					ad.setEnvite(e);
					ad.setJugador(jugAct);
					bd.cantarEnvite(ad);
					break;
				case 7:
					tc 	=  TipoCanto.ValeCuatro;
					e = new EnviteDTO(tc, true, true);
					ad.setEnvite(e);
					ad.setJugador(jugAct);
					bd.cantarEnvite(ad);
					break;
				case 8:
					e = new EnviteDTO(tc, true, true);
					ad.setEnvite(e);
					ad.setJugador(jugAct);
					bd.responderEnvite(ad);
					break;	
				case 9:
					e = new EnviteDTO(tc, false, true);
					ad.setEnvite(e);
					ad.setJugador(jugAct);
					bd.responderEnvite(ad);
					break;
				case 21:
					e = new EnviteDTO(tc, true, true);
					ad.setEnvite(e);
					ad.setJugador(jugAct);
					bd.Retirarse(ad);
					break;
				}
			}
			catch (ComunicacionException | LoggedInException e) {
				System.err.println(e.getMessage());
			} 
		}
	}

	public static void mostrarPartida(PartidaPantallaDTO pd) throws ComunicacionException, LoggedInException{

		//		System.out.println(c3.mostrarPartida(ad));
		System.out.println("Juegos\nNosotros:"+pd.getJuegosNosotros()+" Ellos: "+pd.getJuegosEllos() );
		System.out.println("Puntos\nNosotros: "+pd.getPuntosJuegoNosotros()+" Ellos: "+pd.getPuntosJuegoEllos() );
		//		System.out.println("jugador:"			+pd.getJugador().getApodo()+" suma Envido: "+pd.getValorEnvidoJugador());
		//		System.out.println("jugador frente:"	+pd.getJugadorFrente().getApodo()+" suma Envido: "+pd.getValorEnvidoJugadorFrente());
		//		System.out.println("jugador IZQ:"		+pd.getJugadorIzquierda().getApodo()+" suma Envido: "+pd.getValorEnvidoJugadorIquierda());
		//		System.out.println("jugador DER:"		+pd.getJugadorDerecha().getApodo()+" suma Envido: "+pd.getValorEnvidoJugadorDerecha());
		for (int i = 0; i < 2; i++) System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.print("Ultimo canto: ");
		if (pd.getUltimoCanto() == null) System.out.println("No se canto nada todavía");
		else {
			System.out.print(pd.getUltimoCanto().getDescTipoCanto());
			if (pd.getUltimoCanto().isQuerido() == null) System.out.println(" - No fue respondido aun");
			else if (pd.getUltimoCanto().isQuerido()) System.out.println(" - Querido");
			else System.out.println(" - No querido");
		}
		for (int i = 0; i < 2; i++) System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Cartas Jugador Mesa: "+pd.getJugador().getApodo());
		for(CartaDTO c :pd.getCartasMesaJugador()) System.out.println(c.getNumero() + " de " + c.getPalo() + " (" + c.getId() + ")");
		for (int i = 0; i < 2; i++) System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Cartas Jugador Mesa Frente: "+pd.getJugadorFrente().getApodo());
		for(CartaDTO c :pd.getCartasMesaJugadorFrente()) System.out.println(c.getNumero() + " de " + c.getPalo() + " (" + c.getId() + ")");
		for (int i = 0; i < 2; i++) System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Cartas Jugador Mesa Izquieda: "+pd.getJugadorIzquierda().getApodo());
		for(CartaDTO c :pd.getCartasMesajugadorIzquierda()) System.out.println(c.getNumero() + " de " + c.getPalo() + " (" + c.getId() + ")");
		for (int i = 0; i < 2; i++) System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Cartas Jugador Mesa Derecha: "+pd.getJugadorDerecha().getApodo());
		for(CartaDTO c :pd.getCartasMesaJugadorDerecha()) System.out.println(c.getNumero() + " de " + c.getPalo() + " (" + c.getId() + ")");
		for (int i = 0; i < 2; i++) System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Cartas Jugador: " + pd.getJugador().getApodo());
		for(CartaDTO c :pd.getCartasJugador()) System.out.println(c.getNumero() + " de " + c.getPalo() + " (" + c.getId() + ")");
		for (int i = 0; i < 2; i++) System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("Turno de: " + pd.getTurnoJugador().getApodo());
		System.out.println("Jugando: " + pd.getJugador().getApodo());
	}

}
