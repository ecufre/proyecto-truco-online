package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controladores.HibernateUtil;
import entities.JuegoEntity;
import entities.JugadorEntity;
import entities.PartidaEntity;
import excepciones.ComunicacionException;
import negocio.Juego;
import negocio.Jugador;
import negocio.Partida;

public class PartidaDAO {
	
	private static PartidaDAO instancia;
	
	
private PartidaDAO() {}
	
	public static PartidaDAO getInstancia() {
		if (instancia == null) {
			instancia = new PartidaDAO();
		}
		return instancia;
	}
	
	public ArrayList<Partida> getPartidasByApodo(String apodo) {
		return null; //TODO
	}
	
	public Partida getPartidaById(int id) throws ComunicacionException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		PartidaEntity pe = (PartidaEntity) session.createQuery("from PartidaEntity where id = ?")
					.setParameter(0, id)
					.uniqueResult();
		if(pe != null){
			return PartidaDAO.getInstancia().toNegocio(pe);
		}
		else 
			throw new ComunicacionException("La Partida solicitada no existe");
	}
	
	
	public Integer crear(Partida p) {
		
		PartidaEntity pe = new PartidaEntity(p.isEsAbierta(), p.getEstado(), p.getGanador());
		ArrayList<JuegoEntity> juegos = new ArrayList<JuegoEntity>();
		for (Juego j : p.getJuegos()) {
			JuegoEntity je = new JuegoEntity();
			je.setId(j.getId());
			juegos.add(je);
		}
		pe.setJuegos(juegos);
		ArrayList<JugadorEntity> jugadores = new ArrayList<JugadorEntity>();
		for (Jugador j : p.getJugadores()) {
			JugadorEntity je = new JugadorEntity();
			je.setApodo(j.getApodo());
			jugadores.add(je);
		}
		pe.setJugadores(jugadores);
		ArrayList<JugadorEntity> jugadoresListos = new ArrayList<JugadorEntity>();
		for (Jugador j : p.getJugadoresListos()) {
			JugadorEntity je = new JugadorEntity();
			je.setApodo(j.getApodo());
			jugadoresListos.add(je);
		}
		pe.setJugadoresListos(jugadoresListos);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Integer numero = (Integer)session.save(pe);
		session.getTransaction().commit();
		session.close();
		return numero;
	}
	
	public void grabar(Partida p) {
		
		PartidaEntity pe = new PartidaEntity(p.getId(), p.isEsAbierta(), p.getEstado(), p.getGanador());
		ArrayList<JuegoEntity> juegos = new ArrayList<JuegoEntity>();
		for (Juego j : p.getJuegos()) {
			JuegoEntity je = new JuegoEntity();
			je.setId(j.getId());
			juegos.add(je);
		}
		pe.setJuegos(juegos);
		ArrayList<JugadorEntity> jugadores = new ArrayList<JugadorEntity>();
		for (Jugador j : p.getJugadores()) {
			JugadorEntity je = new JugadorEntity();
			je.setApodo(j.getApodo());
			jugadores.add(je);
		}
		pe.setJugadores(jugadores);
		ArrayList<JugadorEntity> jugadoresListos = new ArrayList<JugadorEntity>();
		for (Jugador j : p.getJugadoresListos()) {
			JugadorEntity je = new JugadorEntity();
			je.setApodo(j.getApodo());
			jugadoresListos.add(je);
		}
		pe.setJugadoresListos(jugadoresListos);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(pe);
		session.getTransaction().commit();
		session.close();
	}
	
	
	
	public Partida toNegocio(PartidaEntity pe) {
		Partida p = new Partida(pe.getId());
		p.setEsAbierta(pe.getEsAbierta());
		p.setEstado(pe.getEstado());
		p.setGanador(pe.getGanador());
		ArrayList<Juego> juegos = new ArrayList<Juego>();
		for (JuegoEntity je : pe.getJuegos()) {
			juegos.add(JuegoDAO.getInstancia().toNegocio(je));
		}
		p.setJuegos(juegos);
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		for (JugadorEntity je : pe.getJugadores()) {
			jugadores.add(JugadorDAO.getInstancia().toNegocio(je));
		}
		p.setJugadores(jugadores);
		ArrayList<Jugador> jugadoresListos = new ArrayList<Jugador>();
		for (JugadorEntity je : pe.getJugadoresListos()) {
			jugadoresListos.add(JugadorDAO.getInstancia().toNegocio(je));
		}
		p.setJugadoresListos(jugadoresListos);
		return p;
	}
	
	public PartidaEntity toEntity(Partida p) {
		return null; //TODO
	}
}
