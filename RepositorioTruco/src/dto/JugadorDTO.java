package dto;

import java.util.ArrayList;
import java.util.Vector;

public class JugadorDTO {
	private String apodo;
	private String email;
	private String password;
	//private Integer id;
	private ArrayList<InvitacionDTO> invitacionesPendientes;
	private CategoriaDTO categoria;
	private ArrayList<GrupoDTO> grupos;
	private String loggedSession;
	
	public ArrayList<GrupoDTO> getGrupos() {
		return grupos;
	}

	public void setGrupos(ArrayList<GrupoDTO> grupos) {
		this.grupos = grupos;
	}

	public JugadorDTO(String apodo, CategoriaDTO categoria) {
		this.apodo = apodo;
		//this.id = id;
		this.categoria = categoria;
	}

	public JugadorDTO(String apodo, String email, ArrayList<InvitacionDTO> invitacionesPendientes, CategoriaDTO categoria) {
		this.apodo = apodo;
		this.email = email;
		//this.id = id;
		this.invitacionesPendientes = invitacionesPendientes;
		this.categoria = categoria;
}
}