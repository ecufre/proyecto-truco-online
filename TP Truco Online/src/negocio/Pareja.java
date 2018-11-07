package negocio;

import dao.ParejaDAO;
import dto.ParejaDTO;
import enumeraciones.TipoCategoria;
import excepciones.ComunicacionException;

public class Pareja {
	private int id; //Solo necesario cuando se persiste la pareja.
	private Jugador jugador1;
	private Jugador jugador2;
	
	public Pareja(Jugador jugador1, Jugador jugador2) {
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
	}

	public Pareja(int id, Jugador jugador1, Jugador jugador2) {
		this.id = id;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Jugador getJugador1() {
		return jugador1;
	}

	public void setJugador1(Jugador jugador1) {
		this.jugador1 = jugador1;
	}

	public Jugador getJugador2() {
		return jugador2;
	}

	public void setJugador2(Jugador jugador2) {
		this.jugador2 = jugador2;
	}

	public TipoCategoria calcularCategoria() {
		if (jugador1.getCategoria().calcularCategoria().getId() < jugador2.getCategoria().calcularCategoria().getId()) return jugador1.getCategoria().calcularCategoria();
		else return jugador2.getCategoria().calcularCategoria();
	}

	public ParejaDTO toDTO() {
		return new ParejaDTO(this.id, this.jugador1.toDTO_reducido(), this.jugador2.toDTO_reducido());
	}

	public void eliminar() {
		ParejaDAO.getInstancia().borrar(this);
	}

	public int crear() throws ComunicacionException {
		return ParejaDAO.getInstancia().crear(this);
	}
}
