package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controladores.HibernateUtil;
import entities.CartaEntity;
import negocio.Carta;

public class CartaDAO {


private static CartaDAO instancia;
	
	
	private CartaDAO() {}
		
		public static CartaDAO getInstancia() {
			if (instancia == null) {
				instancia = new CartaDAO();
			}
			return instancia;
		}
		
		public Integer crear(Carta c) {
			CartaEntity ce =  new CartaEntity(c.getCartaId(), c.getValor(), c.getValorEnvite(), c.getNumero(), c.getPalo(), c.getJugador(), c.isJugada());
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			Integer numero = (Integer)session.save(ce);
			session.getTransaction().commit();
			session.close();
			return numero;
		}
		
		public void grabar(Carta c) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			session.saveOrUpdate(this.toEntity(c));
			session.getTransaction().commit();
			session.close();
		}
		
		public Carta toNegocio(CartaEntity c) {
			return new Carta(c.getId(), c.getCartaId(), c.getValor(), c.getValorEnvite(), c.getNumero(), c.getPalo(), c.getUbicacionJugador(), c.getJugada());
		}
		
		public CartaEntity toEntity(Carta c) {
			return new CartaEntity(c.getId(), c.getCartaId(), c.getValor(), c.getValorEnvite(), c.getNumero(), c.getPalo(), c.getJugador(), c.isJugada());
		}
}
