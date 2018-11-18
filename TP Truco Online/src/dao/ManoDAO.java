package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controladores.HibernateUtil;
import entities.BazaEntity;
import entities.CantoEntity;
import entities.CartaEntity;
import entities.ManoEntity;
import negocio.Baza;
import negocio.Canto;
import negocio.Carta;
import negocio.Mano;

public class ManoDAO {
	
private static ManoDAO instancia;
	
	
	private ManoDAO() {}
		
		public static ManoDAO getInstancia() {
			if (instancia == null) {
				instancia = new ManoDAO();
			}
			return instancia;
		}
		
		public Integer crear(Mano m) {
			ManoEntity me = new ManoEntity(m.getNumeroMano());
			ArrayList<CartaEntity> cartas = new ArrayList<CartaEntity>();
			ArrayList<CantoEntity> cantos = new ArrayList<CantoEntity>();
			ArrayList<BazaEntity> bazas = new ArrayList<BazaEntity>();
			for (Carta c : m.getCartas()) {
				CartaEntity ce = new CartaEntity();
				ce.setId(c.getId());
				cartas.add(ce);
			}
			me.setCartas(cartas);
			for (Canto c : m.getCantos()) {
				CantoEntity ce = new CantoEntity();
				ce.setId(c.getId());
				cantos.add(ce);
			}
			me.setCantos(cantos);
			for (Baza b : m.getBazas()) {
				BazaEntity be = new BazaEntity();
				be.setId(b.getId());
				bazas.add(be);
			}
			me.setBazas(bazas);
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			Integer numero = (Integer)session.save(me);
			session.getTransaction().commit();
			session.close();
			return numero;
		}
		
		public void grabar(Mano m) {
			ManoEntity me = new ManoEntity(m.getId(),m.getNumeroMano(), m.getMostrarPuntos());
			ArrayList<CartaEntity> cartas = new ArrayList<CartaEntity>();
			ArrayList<CantoEntity> cantos = new ArrayList<CantoEntity>();
			ArrayList<BazaEntity> bazas = new ArrayList<BazaEntity>();
			for (Carta c : m.getCartas()) {
				CartaEntity ce = new CartaEntity();
				ce.setId(c.getId());
				cartas.add(ce);
			}
			me.setCartas(cartas);
			for (Canto c : m.getCantos()) {
				CantoEntity ce = new CantoEntity();
				ce.setId(c.getId());
				cantos.add(ce);
			}
			me.setCantos(cantos);
			for (Baza b : m.getBazas()) {
				BazaEntity be = new BazaEntity();
				be.setId(b.getId());
				bazas.add(be);
			}
			me.setBazas(bazas);
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			session.saveOrUpdate(me);
			session.getTransaction().commit();
			session.close();
		}
		
		public Mano toNegocio(ManoEntity me) {
			Mano m = new Mano(me.getId(), me.getNumeroMano(), me.getMostrarPuntos());
			ArrayList<Carta> cartas = new ArrayList<Carta>();
			ArrayList<Canto> cantos = new ArrayList<Canto>();
			ArrayList<Baza> bazas = new ArrayList<Baza>();
			for (CartaEntity ce : me.getCartas()) {
				cartas.add(CartaDAO.getInstancia().toNegocio(ce));
			}
			m.setCartas(cartas);
			for (CantoEntity ce : me.getCantos()) {
				cantos.add(CantoDAO.getInstancia().toNegocio(ce));
			}
			m.setCantos(cantos);
			for (BazaEntity be : me.getBazas()) {
				bazas.add(BazaDAO.getInstancia().toNegocio(be));
			}
			m.setBazas(bazas);
			m.calcularEnvidos();
			return m;
		}
		
		public ManoEntity toEntity(Mano m) {
			return null; //TODO
		}
}
