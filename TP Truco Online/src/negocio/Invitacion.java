package negocio;

import controladores.CreadorPartida;
import dto.InvitacionDTO;

public class Invitacion {
	private Jugador remitente;
	private int id;
	
	public Jugador getRemitente() {
		return remitente;
	}

	public void setRemitente(Jugador remitente) {
		this.remitente = remitente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Invitacion(Jugador remitente, int id) {
		this.remitente = remitente;
		this.id = id;
	}

	public void aceptar(Jugador destinatario) {
		CreadorPartida.getInstancia().agregarPareja(remitente, destinatario);
	}
	
	public InvitacionDTO toDTO() {
		return new InvitacionDTO(this.remitente.toDTO_reducido(), this.id);
	}
}
