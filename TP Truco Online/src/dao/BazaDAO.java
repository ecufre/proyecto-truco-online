package dao;

import entities.BazaEntity;
import entities.ManoEntity;
import negocio.Baza;
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
		
		public BazaEntity getBazaById(int id) {
			return null; //TODO
		}
		
		
		public Integer crear(Baza b) {
			
			return null; //TODO
		}
		
		public void grabar(Baza b) {
			
			//TODO
		}
		
		public void actualizar(Baza b) {

			//TODO
		}
		
		public Baza toNegocio(BazaEntity be) {
			return null; //TODO
		}
		
		public BazaEntity toEntity(Baza b) {
			return null; //TODO
		}
}
