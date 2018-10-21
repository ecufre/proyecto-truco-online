package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controladores.HibernateUtil;
import entities.BazaEntity;
import entities.CantoEntity;
import entities.CartaEntity;
import excepciones.ComunicacionException;
import negocio.Baza;
import negocio.Canto;
import negocio.Carta;

public class CantoDAO {


private static CantoDAO instancia;
	
	
	private CantoDAO() {}
		
		public static CantoDAO getInstancia() {
			if (instancia == null) {
				instancia = new CantoDAO();
			}
			return instancia;
		}
		
		public Canto getCantoById(int id) throws ComunicacionException {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			CantoEntity ce = (CantoEntity) session.createQuery("from CantoEntity where id = ?")
						.setParameter(0, id)
						.uniqueResult();
			if(ce != null){
				return CantoDAO.getInstancia().toNegocio(ce);
			}
			else 
				throw new ComunicacionException("El canto solicitado no existe");
		}
		
		
		public Integer crear(Canto c) {
			CantoEntity ce = new CantoEntity(c.isQuerido(), c.getTipoCanto(), c.getCantante());
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			Integer numero = (Integer)session.save(ce);
			session.getTransaction().commit();
			session.close();
			return numero;
		}
		
		public void grabar(Canto c) {
			CantoEntity ce = new CantoEntity(c.getId(),c.isQuerido(), c.getTipoCanto(), c.getCantante());
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			session.saveOrUpdate(ce);
			session.getTransaction().commit();
			session.close();
		}
		
		public Canto toNegocio(CantoEntity ce) {
			Canto c = new Canto(ce.getCantante());
			c.setQuerido(ce.getQuerido());
			c.setTipoCanto(ce.getTipoCanto());
			c.setId(ce.getId());
			return c;
		}
		
		public CantoEntity toEntity(Canto c) {
			return null; //TODO
		}
}
