package negocio;

import java.util.ArrayList;

import controladores.AdministradorPartida;
import dao.GrupoDAO;
import dto.CategoriaDTO;
import dto.GrupoDTO;
import dto.JugadorDTO;
import dto.ParejaDTO;
import dto.PartidaDTO;
import enumeraciones.EstadoPartida;
import excepciones.ComunicacionException;

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
				break;
			}
		}
		for (Pareja p : parejas) {
			if (p.getJugador1().getApodo().equals(j.getApodo()) || p.getJugador2().getApodo().equals(j.getApodo())) {
				parejas.remove(p);
				this.grabar();
				p.eliminar();
				break;
			}
		}
	}
	
	public void crearPareja(Jugador j1, Jugador j2) throws ComunicacionException {
		Pareja p = new Pareja(j1, j2);
		p.setId(p.crear());
		this.parejas.add(p);
	}
		
	public void crearPartida(Pareja p1, Pareja p2) {
		Partida p = AdministradorPartida.getInstancia().crearPartidaCerrada(p1.getJugador1(), p2.getJugador1(), p1.getJugador2(), p2.getJugador2());
		this.partidas.add(p);
		this.grabar();
	}
	
	public void agregarPartida(Partida p) {
		for (Partida partida : partidas) {
			if (p.getId() == partida.getId()) {
				return;
			}
		}
		this.partidas.add(p);
	}
	
	public ArrayList<ParejaDTO> listarParejas() {
		ArrayList<ParejaDTO> parejasDTO = new ArrayList<ParejaDTO>();
		for (Pareja p : this.parejas) {
			parejasDTO.add(p.toDTO());
		}
		return parejasDTO;
	}
	
	public ArrayList<JugadorDTO> listarMiembros() {
		ArrayList<JugadorDTO> miembrosDTO = new ArrayList<JugadorDTO>();
		for (Jugador j : this.miembros) {
			miembrosDTO.add(j.toDTO());
		}
		return miembrosDTO;
	}

	public ArrayList<PartidaDTO> listarPartidas() {
		ArrayList<PartidaDTO> partidasDTO = new ArrayList<PartidaDTO>();
		for (Partida p : partidas) {
			partidasDTO.add(p.toDTO_reducido());
		}
		return partidasDTO;
	}
	
	public ArrayList<JugadorDTO> calcularRankingCerrado() {
		ArrayList<JugadorDTO> ranking = new ArrayList<JugadorDTO>();
		for (Partida p : partidas) {
			if (p.getEstado().equals(EstadoPartida.Finalizada)) { //TODO ni idea que numero va a ser "Terminada"
				ArrayList<Jugador> jugadores = p.getJugadores();
				for (int i = 0; i < jugadores.size(); i++) {
					Jugador j = jugadores.get(i);
					int puntosPar;
					int puntosImpar;
					if (p.getGanador() == 1) {
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
	
	public GrupoDTO toDTO() {
		JugadorDTO admin = administrador.toDTO_reducido();
		ArrayList<JugadorDTO> miembros = new ArrayList<JugadorDTO>();
		for (Jugador j : this.miembros) {
			miembros.add(j.toDTO_reducido());
		}
		ArrayList<PartidaDTO> partidas = new ArrayList<PartidaDTO>();
		for (Partida p : this.partidas) {
			partidas.add(p.toDTO_reducido());
		}
		ArrayList<ParejaDTO> parejas = new ArrayList<ParejaDTO>();
		for (Pareja p : this.parejas) {
			parejas.add(p.toDTO());
		}
		GrupoDTO g = new GrupoDTO(this.nombre, this.id);
		g.setAdministrador(admin);
		g.setMiembros(miembros);
		g.setPartidas(partidas);
		g.setParejas(parejas);
		return g;
	}
	
	public GrupoDTO toDTO_reducido() {
		return new GrupoDTO(this.nombre, this.id);
	}
	
	public void grabar() {
		GrupoDAO.getInstancia().grabar(this);
	}
	
	public Integer crear() throws ComunicacionException {
		return GrupoDAO.getInstancia().crear(this);
	}

	public Grupo(String nombre, Jugador administrador) {
		this.nombre = nombre;
		this.administrador = administrador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Jugador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Jugador administrador) {
		this.administrador = administrador;
	}

	public ArrayList<Jugador> getMiembros() {
		return miembros;
	}

	public void setMiembros(ArrayList<Jugador> miembros) {
		this.miembros = miembros;
	}

	public ArrayList<Pareja> getParejas() {
		return parejas;
	}

	public void setParejas(ArrayList<Pareja> parejas) {
		this.parejas = parejas;
	}

	public ArrayList<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(ArrayList<Partida> partidas) {
		this.partidas = partidas;
	}

	public Pareja buscarPareja(Integer id) {
		for (Pareja p : this.parejas) if (p.getId() == id) return p;
		return null;
	}
	
	public Boolean esMiembro(Jugador jugador) {
		for (Jugador j : this.miembros) {
			if (jugador.getApodo().equals(j.getApodo())) return true;
		}
		return false;
	}
}
