package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controladores.HibernateUtil;
import entities.CategoriaEntity;
import entities.GrupoEntity;
import entities.JugadorEntity;
import excepciones.ComunicacionException;
import negocio.Categoria;
import negocio.Grupo;
import negocio.Jugador;

public class JugadorDAO {
	private static JugadorDAO instancia;

	private JugadorDAO() {}
	
	public static JugadorDAO getInstancia() {
		if (instancia == null) {
			instancia = new JugadorDAO();
		}
		return instancia;
	}
	
	public JugadorEntity getJugadorByApodo(String apodo) throws ComunicacionException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadorEntity je = (JugadorEntity) session.createQuery("from JugadorEntity where apodo = ?")
				.setParameter(0, apodo)
				.uniqueResult();
		if (je != null) return je;
		else throw new ComunicacionException("El jugador solicitado no existe");
	}

	public Jugador toNegocio(JugadorEntity je) {
		Jugador j = new Jugador(je.getApodo(), je.getEmail(), je.getPassword(), je.getLoggedSession());
		Categoria cat = new Categoria(je.getCategoria().getPartidasJugadas(), je.getCategoria().getPuntosTotales());
		j.setCategoria(cat);
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();
		for (GrupoEntity ge : je.getGrupos()) {
			grupos.add(GrupoDAO.getInstancia().toNegocio(ge));
		}
		j.setGrupos(grupos);
		return j;
	}
		
	public Jugador toNegocio_grupo(JugadorEntity je) {
		Jugador j = new Jugador(je.getApodo(), je.getEmail(), je.getPassword(), je.getLoggedSession());
		Categoria cat = new Categoria(je.getCategoria().getPartidasJugadas(), je.getCategoria().getPuntosTotales());
		j.setCategoria(cat);
		return j;
	}
	
	public void grabar(Jugador j) {
		CategoriaEntity ce = new CategoriaEntity(j.getCategoria().getPartidasJugadas(), j.getCategoria().getPuntosTotales());
		JugadorEntity je = new JugadorEntity(j.getApodo(), j.getEmail(), j.getPassword(), ce, j.getLoggedSession());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(je);
		session.getTransaction().commit();
		session.close();
	}
	
	public ArrayList<Jugador> getTopTenJugadores(int categoria) throws ComunicacionException{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<JugadorEntity> jes = (ArrayList<JugadorEntity>) session.createQuery("from JugadorEntity ORDER BY (puntosTotales / partidasJugadas) DESC").setMaxResults(10).list();
		if (jes == null) throw new ComunicacionException("No se pudo encontrar el ranking");
		ArrayList<Jugador> js = new ArrayList<Jugador>();
		for (JugadorEntity je : jes) {
			js.add(this.toNegocio_grupo(je));
		}
		return js;
	}
	
	public void actualizar(Jugador j) throws ComunicacionException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadorEntity je = (JugadorEntity) session.createQuery("from JugadorEntity where apodo = ?")
				.setParameter(0, j.getApodo())
				.uniqueResult();
		if (je != null) {
			j.setEmail(je.getEmail());
			j.setPassword(je.getPassword());
			j.setLoggedSession(je.getLoggedSession());
			Categoria cat = new Categoria(je.getCategoria().getPartidasJugadas(), je.getCategoria().getPuntosTotales());
			j.setCategoria(cat);
			ArrayList<Grupo> grupos = new ArrayList<Grupo>();
			for (GrupoEntity ge : je.getGrupos()) {
				grupos.add(GrupoDAO.getInstancia().toNegocio(ge));
			}
			j.setGrupos(grupos);
		}
		else throw new ComunicacionException("El jugador solicitado no existe");
	}
	
	public Boolean existeJugadorByApodo(String apodo) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		JugadorEntity je = (JugadorEntity) session.createQuery("from JugadorEntity where apodo = ?")
				.setParameter(0, apodo)
				.uniqueResult();
		if (je != null) return true;
		else return false;
	}
	
}
