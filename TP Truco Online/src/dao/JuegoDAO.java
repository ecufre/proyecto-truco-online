package dao;

import entities.JuegoEntity;
import entities.PartidaEntity;
import negocio.Juego;
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
		
		public JuegoEntity getJuegoById(int id) {
			return null; //TODO
		}
		
		
		public Integer crear(Juego j) {
			
			return null; //TODO
		}
		
		public void grabar(Juego j) {
			
			//TODO
		}
		
		public void actualizar(Juego j) {

			//TODO
		}
		
		public Juego toNegocio(JuegoEntity je) {
			return null; //TODO
		}
		
		public JuegoEntity toEntity(Juego j) {
			return null; //TODO
		}
}
