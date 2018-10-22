package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class JugadorDTO implements Serializable {
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

	public JugadorDTO(String apodo, String email, String password, String loggedSession) {
		this.apodo = apodo;
		this.email = email;
		this.password = password;
		this.loggedSession = loggedSession;
	}

	public String getApodo() {
		return apodo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<InvitacionDTO> getInvitacionesPendientes() {
		return invitacionesPendientes;
	}

	public void setInvitacionesPendientes(ArrayList<InvitacionDTO> invitacionesPendientes) {
		this.invitacionesPendientes = invitacionesPendientes;
		this.categoria = categoria;
	}

	public String getLoggedSession() {
		return loggedSession;
	}

	public void setLoggedSession(String loggedSession) {
		this.loggedSession = loggedSession;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}
}