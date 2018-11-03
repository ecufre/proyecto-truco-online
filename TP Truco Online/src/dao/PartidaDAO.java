package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDateTime;

import controladores.HibernateUtil;
import entities.JuegoEntity;
import entities.JugadorEntity;
import entities.PartidaEntity;
import enumeraciones.EstadoPartida;
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
	
	public ArrayList<Partida> getPartidasByApodo(String apodo) throws ComunicacionException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<PartidaEntity> pes = (ArrayList<PartidaEntity>) session.createQuery("from PartidaEntity where jugador1.apodo = ? or jugador2.apodo = ? or jugador3.apodo = ? or jugador4.apodo = ?")
			.setParameter(0, apodo)
			.setParameter(1, apodo)
			.setParameter(2, apodo)
			.setParameter(3, apodo)
			.list();
		if (pes == null) throw new ComunicacionException("No se encontraron partidas");
		ArrayList<Partida> ps = new ArrayList<Partida>();
		for (PartidaEntity pe : pes) ps.add(PartidaDAO.getInstancia().toNegocio(pe));
		return ps;
	}
	
	public ArrayList<Partida> getPartidasEnCursoByApodo(String apodo) throws ComunicacionException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<PartidaEntity> pes = (ArrayList<PartidaEntity>) session.createQuery("from PartidaEntity where (jugador1.apodo = ? or jugador2.apodo = ? or jugador3.apodo = ? or jugador4.apodo = ?) and estado = ?")
				.setParameter(0, apodo)
				.setParameter(1, apodo)
				.setParameter(2, apodo)
				.setParameter(3, apodo)
				.setParameter(4, EstadoPartida.EnCurso)
				.list();
		if (pes == null) throw new ComunicacionException("No se encontraron partidas");
		ArrayList<Partida> ps = new ArrayList<Partida>();
		for (PartidaEntity pe : pes) ps.add(PartidaDAO.getInstancia().toNegocio(pe));
		return ps;
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
		PartidaEntity pe = new PartidaEntity(p.isEsAbierta(), p.getEstado(), p.getGanador(), p.getFechaCreacion(), LocalDateTime.now());
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
		PartidaEntity pe = new PartidaEntity(p.getId(), p.isEsAbierta(), p.getEstado(), p.getGanador(), p.getFechaCreacion(), LocalDateTime.now());
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
		p.setFechaCreacion(pe.getFechaCreacion());
		p.setFechaActualizacion(pe.getFechaActualizacion());
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
