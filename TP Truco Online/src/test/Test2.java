package test;

import controladores.AdministradorJugador;
import controladores.AdministradorPartida;
import dao.MazoDAO;
import dto.AccionDTO;
import dto.JugadorDTO;
import dto.PartidaDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;

public class Test2 {

	public static void main(String[] args) {
		
		try {
			MazoDAO.getInstancia().crearMazo();
			AdministradorJugador aj = AdministradorJugador.getInstancia();
			AdministradorPartida ap = AdministradorPartida.getInstancia();
			
			//Crear Jugadores
			JugadorDTO j1 = new JugadorDTO("Fer", "fer@fer", "Pass", "SessionID");
			JugadorDTO j2 = new JugadorDTO("San", "fer@fer", "Pass", "SessionID");
			JugadorDTO j3 = new JugadorDTO("Bru", "fer@fer", "Pass", "SessionID");
			JugadorDTO j4 = new JugadorDTO("Ger", "fer@fer", "Pass", "SessionID");
			JugadorDTO j5 = new JugadorDTO("Ale", "fer@fer", "Pass", "SessionID");
			JugadorDTO j6 = new JugadorDTO("Cel", "fer@fer", "Pass", "SessionID");
			aj.crearJugador(j1);
			aj.crearJugador(j2);
			aj.crearJugador(j3);
			aj.crearJugador(j4);
			aj.crearJugador(j5);
			aj.crearJugador(j6);
			
			//Crear partidas
			aj.jugarLibreIndividual(j1);
			aj.jugarLibreIndividual(j2);
			aj.jugarLibreIndividual(j3);
			aj.jugarLibreIndividual(j4);
			
			aj.jugarLibreIndividual(j5);
			aj.jugarLibreIndividual(j3);
			aj.jugarLibreIndividual(j1);
			aj.jugarLibreIndividual(j6);
			
			aj.jugarLibreIndividual(j2);
			aj.jugarLibreIndividual(j1);
			aj.jugarLibreIndividual(j4);
			aj.jugarLibreIndividual(j3);
			
			aj.jugarLibreIndividual(j6);
			aj.jugarLibreIndividual(j5);
			aj.jugarLibreIndividual(j4);
			aj.jugarLibreIndividual(j3);
			
			PartidaDTO pDTO = new PartidaDTO();
			pDTO.setId(2);
			AccionDTO aDTO = new AccionDTO(pDTO, j1, 1, false, "");
			
			ap.jugadorListo(aDTO);
			aDTO.setJugador(j3);
			ap.jugadorListo(aDTO);
			aDTO.setJugador(j6);
			ap.jugadorListo(aDTO);
			aDTO.setJugador(j5);
			ap.jugadorListo(aDTO);
			
			pDTO.setId(4);
			aDTO.setJugador(j4);
			ap.jugadorListo(aDTO);
			aDTO.setJugador(j3);
			ap.jugadorListo(aDTO);
			aDTO.setJugador(j6);
			ap.jugadorListo(aDTO);
			aDTO.setJugador(j5);
			ap.jugadorListo(aDTO);
			
			
			ap.buscarPartidasJugador("San");
			//ap.buscarPartidasEnCursoJugador("Bru");
			
		} catch (ComunicacionException | LoggedInException e) {
			System.err.println(e.getMessage());
		}
		

	}

}
