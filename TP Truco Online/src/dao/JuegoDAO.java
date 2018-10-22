package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controladores.HibernateUtil;
import entities.BazaEntity;
import entities.CartaEntity;
import entities.JuegoEntity;
import entities.ManoEntity;
import entities.PartidaEntity;
import negocio.Baza;
import negocio.Carta;
import negocio.Juego;
import negocio.Mano;
import negocio.Partida;

public class JuegoDAO {
	private static JuegoDAO instancia;
	
	
	private JuegoDAO() {}
		
		public static JuegoDAO getInstancia() {
			if (instancia == null) {
				instancia = new JuegoDAO();
			}
			return instancia;
		}
		
	
		
		public Integer crear(Juego j) {
			
			JuegoEntity je = new JuegoEntity(j.getPuntajePar(), j.getPuntajeImpar(), j.isFinalizado());
			ArrayList<ManoEntity> manos = new ArrayList<ManoEntity>();
			for (Mano m : j.getManos()) {
				ManoEntity me = new ManoEntity();
				me.setId(m.getId());
			}
			je.setManos(manos);
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			Integer numero = (Integer)session.save(je);
			session.getTransaction().commit();
			session.close();
			return numero;
		}
		
		public void grabar(Juego j) {
			JuegoEntity je = new JuegoEntity(j.getId(), j.getPuntajePar(), j.getPuntajeImpar(), j.isFinalizado());
			ArrayList<ManoEntity> manos = new ArrayList<ManoEntity>();
			for (Mano m : j.getManos()) {
				ManoEntity me = new ManoEntity();
				me.setId(m.getId());
			}
			je.setManos(manos);
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			session.saveOrUpdate(je);
			session.getTransaction().commit();
			session.close();
		}
		
	
		
		public Juego toNegocio(JuegoEntity je) {
			Juego j = new Juego();
			j.setId(je.getId());
			j.setFinalizado(je.getFinalizado());
			j.setPuntajeImpar(je.getPuntajeImpar());
			j.setPuntajePar(je.getPuntajePar());
			ArrayList<Mano> manos = new ArrayList<Mano>();
			for (ManoEntity me : je.getManos()) {
				manos.add(ManoDAO.getInstancia().toNegocio(me));
			}
			j.setManos(manos);
			return j;
		}
		
		public JuegoEntity toEntity(Juego j) {
			return null; //TODO
		}
}
