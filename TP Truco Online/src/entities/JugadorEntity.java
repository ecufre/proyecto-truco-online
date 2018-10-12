package entities;

import java.util.ArrayList;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import negocio.Categoria;
import negocio.Invitacion;

@Entity
@Table(name="jugadores")
public class JugadorEntity {
	private String apodo;
	private String email;
	private String password;
	@Id
	@GeneratedValue
	private Integer id;
	@Embedded
	private CategoriaEntity categoria;
	public JugadorEntity(String apodo, String email, String password, Integer id, CategoriaEntity categoria) {
		this.apodo = apodo;
		this.email = email;
		this.password = password;
		this.id = id;
		this.categoria = categoria;
	}
	public JugadorEntity() {
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CategoriaEntity getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}
}
