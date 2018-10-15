package negocio;

import java.util.ArrayList;

import dao.JugadorDAO;
import dto.CategoriaDTO;
import dto.GrupoDTO;
import dto.InvitacionDTO;
import dto.JugadorDTO;

public class Jugador {
	private String apodo;
	private String email;
	private String password;
	//private int id;
	private ArrayList<Invitacion> invitacionesPendientes;
	private Categoria categoria;
	private ArrayList<Grupo> grupos;
	private String loggedSession;
	
	public ArrayList<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(ArrayList<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Jugador(String apodo, String email, String password, String loggedSession) {
		this.apodo = apodo;
		this.email = email;
		this.password = password;
		this.loggedSession = loggedSession;
		this.invitacionesPendientes = new ArrayList<Invitacion>();
		this.grupos = new ArrayList<Grupo>();
		this.categoria = new Categoria(0,0);
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Invitacion> getInvitacionesPendientes() {
		return invitacionesPendientes;
	}

	public void setInvitacionesPendientes(ArrayList<Invitacion> invitacionesPendientes) {
		this.invitacionesPendientes = invitacionesPendientes;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getLoggedSession() {
		return loggedSession;
	}

	public void setLoggedSession(String loggedSession) {
		this.loggedSession = loggedSession;
	}

	public boolean passwordCorrecta(String password) {
		return (this.password.equals(password));
	}
	
	public void actualizarPuntaje(int puntosASumar) {
		this.categoria.sumarPuntos(puntosASumar);
	}
	
	public void crearInvitacion(Jugador remitente) {
		int proximoID = 1;
		for (Invitacion i : this.invitacionesPendientes) {
			if (i.getRemitente().getApodo().equals(remitente.getApodo())) return; //Ya tiene una invitacion del jugador
			if (i.getId() >= proximoID) proximoID = i.getId() + 1;
		}
		Invitacion i = new Invitacion(remitente, this.invitacionesPendientes.size());
		this.invitacionesPendientes.add(i);
	}
	
	//Metodo no necesario, reemplazable por el getter.
	//public void listarInvitacionesPendientes() {}
	
	public void aceptarInvitacion(int idInvitacion) {
		Invitacion i = this.buscarInvitacion(idInvitacion);
		//TODO Agregar error si no existe?
		if (i != null) {
			i.aceptar(this);
			this.invitacionesPendientes.remove(i);
		}
	}
	
	public void rechazarInvitacion(int idInvitacion) {
		this.invitacionesPendientes.remove(this.buscarInvitacion(idInvitacion));
	}
	
	private Invitacion buscarInvitacion(int idInvitacion) {
		for (Invitacion i : this.invitacionesPendientes) {
			if (i.getId() == idInvitacion) {
				return i;
			}
		}
		return null;
	}
	
	public JugadorDTO toDTO_reducido() {
		return new JugadorDTO(this.apodo, this.categoria.toDTO());
	}
	
	public JugadorDTO toDTO() {
		ArrayList<InvitacionDTO> invitaciones = new ArrayList<InvitacionDTO>();
		for (Invitacion i : this.invitacionesPendientes) {
			invitaciones.add(i.toDTO());
		}
		CategoriaDTO c = this.categoria.toDTO();
		ArrayList<GrupoDTO> grupos = new ArrayList<GrupoDTO>();
		for (Grupo g : this.grupos) {
			grupos.add(g.toDTO_reducido());
		}
		JugadorDTO j = new JugadorDTO(this.apodo, this.email, invitaciones, c);
		j.setGrupos(grupos);
		return j;
	}
			
	public void grabar() {
		JugadorDAO.getInstancia().grabar(this);
	}
	
	public void actualizar() {
		JugadorDAO.getInstancia().actualizar(this);
	}
	
	public ArrayList<JugadorDTO> buscarTop10() {
		return null; //TODO
	}

	public void agregarAGrupo(Grupo grupo) {
		for (Grupo g : this.grupos) {
			if (grupo.getId() == g.getId()) return;
		}
		this.grupos.add(grupo);
	}
}
