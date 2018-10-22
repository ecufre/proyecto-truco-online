package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controladores.HibernateUtil;
import entities.MazoEntity;
import excepciones.ComunicacionException;
import negocio.Carta;
import negocio.Mazo;

public class MazoDAO {
	private static MazoDAO instancia;
	
	private MazoDAO() {}
	
	public static MazoDAO getInstancia() {
		if (instancia == null) instancia = new MazoDAO();
		return instancia;
	}
	
	@SuppressWarnings("unchecked")
	public Mazo getMazo() throws ComunicacionException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		ArrayList<MazoEntity> mazocompleto = (ArrayList<MazoEntity>) session.createQuery("from MazoEntity").list();
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		for (MazoEntity me : mazocompleto) {
			cartas.add(new Carta(me.getCartaId(), me.getValor(), me.getValorEnvite(), me.getNumero(), me.getPalo()));
		}
		Mazo m = new Mazo(cartas);
		return m;
	}
	
	/** Solo utilizar cuando haya que recrear la tabla Mazo en la BD **/
	public void crearMazo() {
		ArrayList<MazoEntity> mazo = new ArrayList<MazoEntity>();
		MazoEntity  C1 = new MazoEntity(1,14,1,1,"espada");	 mazo.add(C1);
		MazoEntity  C2 = new MazoEntity(2,9,2,2,"espada");	 mazo.add(C2);
		MazoEntity  C3 = new MazoEntity(3,10,3,3,"espada");	 mazo.add(C3);
		MazoEntity  C4 = new MazoEntity(4,1,4,4,"espada");	 mazo.add(C4);
		MazoEntity  C5 = new MazoEntity(5,2,5,5,"espada");	 mazo.add(C5);
		MazoEntity  C6 = new MazoEntity(6,3,6,6,"espada");	 mazo.add(C6);
		MazoEntity  C7 = new MazoEntity(7,12,7,7,"espada");	 mazo.add(C7);
		MazoEntity  C8 = new MazoEntity(8,5,0,10,"espada");	 mazo.add(C8);
		MazoEntity  C9 = new MazoEntity(9,6,0,11,"espada");	 mazo.add(C9);
		MazoEntity  C10 = new MazoEntity(10,7,0,12,"espada");	 mazo.add(C10);
		MazoEntity  C11 = new MazoEntity(11,13,1,1,"basto");	 mazo.add(C11);
		MazoEntity  C12 = new MazoEntity(12,9,2,2,"basto");	 mazo.add(C12);
		MazoEntity  C13 = new MazoEntity(13,10,3,3,"basto");	 mazo.add(C13);
		MazoEntity  C14 = new MazoEntity(14,1,4,4,"basto");	 mazo.add(C14);
		MazoEntity  C15 = new MazoEntity(15,2,5,5,"basto");	 mazo.add(C15);
		MazoEntity  C16 = new MazoEntity(16,3,6,6,"basto");	 mazo.add(C16);
		MazoEntity  C17 = new MazoEntity(17,4,7,7,"basto");	 mazo.add(C17);
		MazoEntity  C18 = new MazoEntity(18,5,0,10,"basto");	 mazo.add(C18);
		MazoEntity  C19 = new MazoEntity(19,6,0,11,"basto");	 mazo.add(C19);
		MazoEntity  C20 = new MazoEntity(20,7,0,12,"basto");	 mazo.add(C20);
		MazoEntity  C21 = new MazoEntity(21,8,1,1,"copa");	 mazo.add(C21);
		MazoEntity  C22 = new MazoEntity(22,9,2,2,"copa");	 mazo.add(C22);
		MazoEntity  C23 = new MazoEntity(23,10,3,3,"copa");	 mazo.add(C23);
		MazoEntity  C24 = new MazoEntity(24,1,4,4,"copa");	 mazo.add(C24);
		MazoEntity  C25 = new MazoEntity(25,2,5,5,"copa");	 mazo.add(C25);
		MazoEntity  C26 = new MazoEntity(26,3,6,6,"copa");	 mazo.add(C26);
		MazoEntity  C27 = new MazoEntity(27,4,7,7,"copa");	 mazo.add(C27);
		MazoEntity  C28 = new MazoEntity(28,5,0,10,"copa");	 mazo.add(C28);
		MazoEntity  C29 = new MazoEntity(29,6,0,11,"copa");	 mazo.add(C29);
		MazoEntity  C30 = new MazoEntity(30,7,0,12,"copa");	 mazo.add(C30);
		MazoEntity  C31 = new MazoEntity(31,8,1,1,"oro");	 mazo.add(C31);
		MazoEntity  C32 = new MazoEntity(32,9,2,2,"oro");	 mazo.add(C32);
		MazoEntity  C33 = new MazoEntity(33,10,3,3,"oro");	 mazo.add(C33);
		MazoEntity  C34 = new MazoEntity(34,1,4,4,"oro");	 mazo.add(C34);
		MazoEntity  C35 = new MazoEntity(35,2,5,5,"oro");	 mazo.add(C35);
		MazoEntity  C36 = new MazoEntity(36,3,6,6,"oro");	 mazo.add(C36);
		MazoEntity  C37 = new MazoEntity(37,11,7,7,"oro");	 mazo.add(C37);
		MazoEntity  C38 = new MazoEntity(38,5,0,10,"oro");	 mazo.add(C38);
		MazoEntity  C39 = new MazoEntity(39,6,0,11,"oro");	 mazo.add(C39);
		MazoEntity  C40 = new MazoEntity(40,7,0,12,"oro");	 mazo.add(C40);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		for (MazoEntity me : mazo) {
			session.saveOrUpdate(me);
		}
		session.getTransaction().commit();
		session.close();
	}

}
