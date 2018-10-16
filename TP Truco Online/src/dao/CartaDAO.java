package dao;

import entities.BazaEntity;
import entities.CartaEntity;
import negocio.Baza;
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
		
		public CartaEntity getCartaById(int id) {
			return null; //TODO
		}
		
		
		public Integer crear(Carta c) {
			
			return null; //TODO
		}
		
		public void grabar(Carta c) {
			
			//TODO
		}
		
		public void actualizar(Carta c) {

			//TODO
		}
		
		public Carta toNegocio(CartaEntity ce) {
			return null; //TODO
		}
		
		public CartaEntity toEntity(Carta c) {
			return null; //TODO
		}
}
