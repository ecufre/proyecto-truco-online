package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controladores.HibernateUtil;
import entities.BazaEntity;
import entities.CartaEntity;
import entities.ManoEntity;
import negocio.Baza;
import negocio.Carta;
import negocio.Mano;

public class BazaDAO {

private static BazaDAO instancia;
	
	
	private BazaDAO() {}
		
		public static BazaDAO getInstancia() {
			if (instancia == null) {
				instancia = new BazaDAO();
			}
			return instancia;
		}
		
		public Integer crear(Baza b) {
			BazaEntity be = new BazaEntity(b.getGanadorBaza(), b.getTurno(), b.getMano(), b.isParda());
			ArrayList<CartaEntity> cartas = new ArrayList<CartaEntity>();
			for (Carta c : b.getCartasbaza()) {
				CartaEntity ce = new CartaEntity();
				ce.setId(c.getId());
			}
			be.setCartasbaza(cartas);
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			Integer numero = (Integer)session.save(be);
			session.getTransaction().commit();
			session.close();
			return numero;
		}
		
		public void grabar(Baza b) {
			BazaEntity be = new BazaEntity(b.getId(), b.getGanadorBaza(), b.getTurno(), b.getMano(), b.isParda());
			ArrayList<CartaEntity> cartas = new ArrayList<CartaEntity>();
			for (Carta c : b.getCartasbaza()) {
				CartaEntity ce = new CartaEntity();
				ce.setId(c.getId());
			}
			be.setCartasbaza(cartas);
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			session.saveOrUpdate(be);
			session.getTransaction().commit();
			session.close();
		}
			
		public Baza toNegocio(BazaEntity be) {
			Baza b = new Baza(be.getId(), be.getTurno(), be.getMano(), be.getParda(), be.getGanadorBaza());
			ArrayList<Carta> cartas = new ArrayList<Carta>();
			for (CartaEntity ce : be.getCartasbaza()) {
				cartas.add(CartaDAO.getInstancia().toNegocio(ce));
			}
			b.setCartasbaza(cartas);
			return b;
		}
		
		public BazaEntity toEntity(Baza b) {
			return null; //TODO
		}
}
