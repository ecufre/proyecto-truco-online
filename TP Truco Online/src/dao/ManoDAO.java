package dao;

import java.util.ArrayList;

import entities.CartaEntity;
import entities.JuegoEntity;
import entities.ManoEntity;
import negocio.Baza;
import negocio.Carta;
import negocio.Juego;
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
		
		public ManoEntity getManoById(int id) {
			return null; //TODO
		}
		
		
		public Integer crear(Mano m) {
			
			return null; //TODO
		}
		
		public void grabar(Mano m) {
			
			//TODO
		}
		
		public void actualizar(Mano m) {

			//TODO
		}
		
		public Mano toNegocio(ManoEntity me) {
		return null;
		}
		
		public ManoEntity toEntity(Mano m) {
			return null; //TODO
		}
}
