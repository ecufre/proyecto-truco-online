package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controladores.HibernateUtil;
import entities.JugadorEntity;
import entities.ParejaEntity;
import excepciones.ComunicacionException;
import negocio.Pareja;

public class ParejaDAO {
	private static ParejaDAO instancia;
	
	private ParejaDAO() {}
	
	public static ParejaDAO getInstancia() {
		if (instancia == null) instancia = new ParejaDAO();
		return instancia;
	}
	
	public Integer crear(Pareja p) throws ComunicacionException {
		JugadorEntity j1 = new JugadorEntity();
		j1.setApodo(p.getJugador1().getApodo());
		JugadorEntity j2 = new JugadorEntity();
		j2.setApodo(p.getJugador2().getApodo());
		ParejaEntity pe = new ParejaEntity(p.getId(), j1, j2);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Integer id = (Integer) session.save(pe);
		session.getTransaction().commit();
		if (id != null) return id;
		else throw new ComunicacionException("No se pudo crear la pareja");
	}

	public void borrar(Pareja p) {
		JugadorEntity j1 = new JugadorEntity();
		j1.setApodo(p.getJugador1().getApodo());
		JugadorEntity j2 = new JugadorEntity();
		j2.setApodo(p.getJugador2().getApodo());
		ParejaEntity pe = new ParejaEntity(p.getId(), j1, j2);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(pe);
		session.getTransaction().commit();
	}
}
