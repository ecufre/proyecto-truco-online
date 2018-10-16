package dao;

import entities.CantoEntity;
import entities.CartaEntity;
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
		
		public CantoEntity getCantoById(int id) {
			return null; //TODO
		}
		
		
		public Integer crear(Canto c) {
			
			return null; //TODO
		}
		
		public void grabar(Canto c) {
			
			//TODO
		}
		
		public void actualizar(Canto c) {

			//TODO
		}
		
		public Canto toNegocio(CantoEntity ce) {
			return null; //TODO
		}
		
		public CantoEntity toEntity(Canto c) {
			return null; //TODO
		}
}
