package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controladores.HibernateUtil;
import entities.GrupoEntity;
import entities.JugadorEntity;
import entities.ParejaEntity;
import entities.PartidaEntity;
import excepciones.ComunicacionException;
import negocio.Grupo;
import negocio.Jugador;
import negocio.Pareja;
import negocio.Partida;

public class GrupoDAO {
	private static GrupoDAO instancia;
	
	private GrupoDAO () {}
	
	public static GrupoDAO getInstancia() {
		if (instancia == null) instancia = new GrupoDAO();
		return instancia;
	}
	
	public GrupoEntity getGroupById(Integer id) throws ComunicacionException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		GrupoEntity ge = (GrupoEntity) session.createQuery("from GrupoEntity where id = ?")
				.setParameter(0, id)
				.uniqueResult();
		if (ge != null) return ge;
		else throw new ComunicacionException("El grupo solicitado no existe");
	}
	
	public Grupo toNegocio(GrupoEntity ge) {
		Jugador admin = JugadorDAO.getInstancia().toNegocio_grupo(ge.getAdministrador());
		Grupo g = new Grupo(ge.getNombre(), admin);
		g.setId(ge.getId());
		ArrayList<Jugador> miembros = new ArrayList<Jugador>();
		for (JugadorEntity je : ge.getMiembros()) miembros.add(JugadorDAO.getInstancia().toNegocio_grupo(je));
		g.setMiembros(miembros);
		ArrayList<Pareja> parejas = new ArrayList<Pareja>();
		for (ParejaEntity pe : ge.getParejas()) parejas.add(ParejaDAO.getInstancia().toNegocio(pe));
		g.setParejas(parejas);
		ArrayList<Partida> partidas = new ArrayList<Partida>();
		for (PartidaEntity pe : ge.getPartidas()) {partidas.add(PartidaDAO.getInstancia().toNegocio(pe));}
		g.setPartidas(partidas);
		return g;
	}

	public Grupo toNegocio_jugador(GrupoEntity ge) {
		Jugador admin = JugadorDAO.getInstancia().toNegocio_grupo(ge.getAdministrador());
		Grupo g = new Grupo(ge.getNombre(), admin);
		g.setId(ge.getId());
		return g;
	}

	public Integer crear(Grupo g) throws ComunicacionException {
		GrupoEntity ge = new GrupoEntity(g.getNombre());
		JugadorEntity je = new JugadorEntity(g.getAdministrador().getApodo());
		ge.setAdministrador(je);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Integer id = (Integer) session.save(ge);
		session.getTransaction().commit();
		if (id != null) return id;
		else throw new ComunicacionException("No se pudo crear el grupo");	
	}	
	
	public void grabar(Grupo g) {
		GrupoEntity ge = new GrupoEntity(g.getNombre());
		ge.setId(g.getId());
		JugadorEntity je = new JugadorEntity();
		je.setApodo(g.getAdministrador().getApodo());
		ge.setAdministrador(je);
		ArrayList<JugadorEntity> miembros = new ArrayList<JugadorEntity>();
		for (Jugador j : g.getMiembros()) miembros.add(new JugadorEntity(j.getApodo()));
		ge.setMiembros(miembros);
		ArrayList<ParejaEntity> parejas = new ArrayList<ParejaEntity>();
		for (Pareja p : g.getParejas()) {
			JugadorEntity je1 = new JugadorEntity(p.getJugador1().getApodo());
			JugadorEntity je2 = new JugadorEntity(p.getJugador2().getApodo());
			parejas.add(new ParejaEntity(p.getId(), je1, je2));
		}
		ge.setParejas(parejas);
		ArrayList<PartidaEntity> partidas = new ArrayList<PartidaEntity>();
		for (Partida p : g.getPartidas()) {
			PartidaEntity pe = new PartidaEntity(p.getId(), false, p.getEstado(), p.getGanador(), p.getFechaCreacion(), p.getFechaActualizacion());
			partidas.add(pe);
		}
		ge.setPartidas(partidas);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ge);
		session.getTransaction().commit();
	}
}
