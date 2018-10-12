package negocio;

import java.util.ArrayList;

import dao.JugadorDAO;
import dto.CategoriaDTO;
import dto.InvitacionDTO;
import dto.JugadorDTO;

public class Jugador {
	private String apodo;
	private String email;
	private String password;
	private int id;
	private ArrayList<Invitacion> invitacionesPendientes;
	private Categoria categoria;
	
	public Jugador(String apodo, String email, String password) {
		//Constructor para nuevos jugadores.
		this.apodo = apodo;
		this.email = email;
		this.password = password;
		this.invitacionesPendientes = new ArrayList<Invitacion>();
		this.categoria = new Categoria(0,0);
	}

	public Jugador(String apodo, String email, String password, int id) {
		//Constructor para jugadores existentes (Persistidos)
		this.apodo = apodo;
		this.email = email;
		this.password = password;
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean passwordCorrecta(String password) {
		return (this.password.equals(password));
	}
	
	public void actualizarPuntaje(int puntosASumar) {
		this.categoria.sumarPuntos(puntosASumar);
	}
	
	public void crearInvitacion(Jugador remitente) {
		Invitacion i = new Invitacion(remitente, this.invitacionesPendientes.size());
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
		return new JugadorDTO(this.apodo, this.id, this.categoria.toDTO());
	}
	
	public JugadorDTO toDTO() {
		ArrayList<InvitacionDTO> invitaciones = new ArrayList<InvitacionDTO>();
		for (Invitacion i : this.invitacionesPendientes) {
			invitaciones.add(i.toDTO());
		}
		CategoriaDTO c = this.categoria.toDTO();
		return new JugadorDTO(this.apodo, this.email, this.id, invitaciones, c);
	}
	
	public Integer crear() {
		return JugadorDAO.getInstancia().crear(this);
	}
	
	public void grabar() {
		JugadorDAO.getInstancia().grabar(this);
	}
	
	public void actualizar() {
		JugadorDAO.getInstancia().actualizar(this);
	}
}
