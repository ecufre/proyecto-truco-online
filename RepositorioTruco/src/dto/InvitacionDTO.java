package dto;

public class InvitacionDTO {
	private JugadorDTO remitente;
	private Integer id;
	
	public InvitacionDTO(JugadorDTO remitente, Integer id) {
		this.remitente = remitente;
		this.id = id;
	}

	public JugadorDTO getRemitente() {
		return remitente;
	}

	public void setRemitente(JugadorDTO remitente) {
		this.remitente = remitente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
