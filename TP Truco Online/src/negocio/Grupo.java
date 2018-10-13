package negocio;

import java.util.ArrayList;
import java.util.Vector;

import controladores.AdministradorPartida;
import dto.CategoriaDTO;
import dto.JugadorDTO;
import dto.ParejaDTO;

public class Grupo {
	private String nombre;
	private int id;
	private Jugador administrador;
	private ArrayList<Jugador> miembros;
	private ArrayList<Pareja> parejas;
	private ArrayList<Partida> partidas;
	
	public void agregarJugador(Jugador j) {
		for (Jugador m : miembros) {
			if (j.getApodo().equals(m.getApodo())) {
				return;
			}
		}
		miembros.add(j);
	}
	
	public void eliminarJugador(Jugador j) {
		for (Jugador m : miembros) {
			if (j.getApodo().equals(m.getApodo())) {
				miembros.remove(m);
			}
		}
	}
	
	public void crearPareja(Jugador j1, Jugador j2) {
		ArrayList<Jugador> pareja = new ArrayList<Jugador>();
		pareja.add(j1);
		pareja.add(j2);
		Pareja p = new Pareja(pareja);
		//p.setId(p.crear());
		this.parejas.add(p);
	}
	
	public ArrayList<ParejaDTO> listarParejas() {
		ArrayList<ParejaDTO> parejasDTO = new ArrayList<ParejaDTO>();
		for (Pareja p : this.parejas) {
			parejasDTO.add(p.toDTO());
		}
		return parejasDTO;
	}
	
	public ArrayList<Jugador> listarMiembros() {
		ArrayList<JugadorDTO> miembrosDTO = new ArrayList<JugadorDTO>();
		for (Jugador j : this.miembros) {
			miembrosDTO.add(j.toDTO());
		}
		return miembrosDTO;
	}
	
	public void crearPartida(Pareja p1, Pareja p2) {
		AdministradorPartida.getInstancia().crearPartidaPrivada(this, p1.getJugadores().get(0), p2.getJugadores().get(0), p1.getJugadores().get(1), p2.getJugadores().get(1));
	}
	
	public void agregarPartida(Partida p) {
		for (Partida partidas : partidas) {
			if (p.getIda == partida.getId()) {
				return;
			}
		}
		this.partidas.add(p);
	}
	
	public ArrayList<PartidaDTO> listarPartidas() {
		ArrayList<PartidaDTO> partidasDTO = new ArrayList<PartidaDTO>();
		for (Partida p : partidas) {
			partidasDTO.add(p.toDTO());
		}
		return partidasDTO;
	}
	
	public ArrayList<JugadorDTO> calcularRankingCerrado() {
		ArrayList<JugadorDTO> ranking = new ArrayList<JugadorDTO>();
		for (Partida p : partidas) {
			if (p.getEstado().equals("Terminada")) {
				ArrayList<Jugador> jugadores = p.getJugadores();
				for (int i = 0; i < jugadores.size(); i++) {
					Jugador j = jugadores.get(i);
					int puntosPar;
					int puntosImpar;
					if (p.getGanador = 1) {
						puntosPar = 0;
						puntosImpar = 5;
					} else {
						puntosPar = 5;
						puntosImpar = 0;
					}
					JugadorDTO jDTO = null;
					for (JugadorDTO jugDTO : ranking) {
						if (jugDTO.getApodo().equals(j.getApodo())) {
							jDTO = jugDTO;
						}
					}
					int pts = 0;
					if (jDTO == null) {
						jDTO = j.toDTO();
						if (i % 2 == 0) pts = puntosImpar; 
						else pts = puntosPar;
						jDTO.setCategoria(new CategoriaDTO(1, pts, (float)pts));
						ranking.add(jDTO);
					} else {
						int pj = j.getCategoria().getPartidasJugadas() + 1;
						pts = j.getCategoria().getPuntosTotales();
						if (i % 2 == 0) pts = pts + puntosImpar; 
						else pts = pts + puntosPar;
						float prom = pts / pj;
						CategoriaDTO cat = new CategoriaDTO(pj, pts, prom);
						jDTO.setCategoria(cat);						
					}
				}
			}
		}
		return ranking;
	}
	
	
}
