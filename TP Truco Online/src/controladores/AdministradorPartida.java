package controladores;

import java.util.ArrayList;
import java.util.Vector;

import dto.AccionDTO;
import negocio.Jugador;
import negocio.Partida;

public class AdministradorPartida {
	private ArrayList<Partida> partidas;
	
	
	public void crearPartida(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {
			Partida p = new Partida(true);
			p.agregarJugador(j1,1);
			p.agregarJugador(j1,2 );
			p.agregarJugador(j1,3 );
			p.agregarJugador(j1,4 );
			p.nuevaMano();
			this.partidas.add(p);
		
	}
	
	public Partida crearPartidaCerrada(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {
		Partida p = new Partida(false);
		p.agregarJugador(j1,1);
		p.agregarJugador(j1,2 );
		p.agregarJugador(j1,3 );
		p.agregarJugador(j1,4 );
		p.nuevaMano();
		this.partidas.add(p);
		return p;
	}
	
	public boolean jugadorListos(AccionDTO ad) {
		Partida p=this.buscarPartida(ad.getPartida());
		if(p!=null){
		return	p.jugadorListos(ad.getApodoJugador());
			
		}
		return false;
	}
	
	public void JugarCarta(AccionDTO ad){
		Partida p=this.buscarPartida(ad.getPartida());
		if(p!=null){
			p.jugarCarta(ad.getApodoJugador(),ad.getValor());
			p.actualizarPartida();
		}
	}

	public void Retirarse(AccionDTO ad){
		Partida p=this.buscarPartida(ad.getPartida());
		if(p!=null){
			p.retiraseMano(ad.getApodoJugador());
			p.actualizarPartida();
		}
	}
	
	public void cantarEnvite(AccionDTO ad){
		Partida p=this.buscarPartida(ad.getPartida());
		if(p!=null){
			p.cantarEnvite(ad.getApodoJugador(),ad.getValor());
			p.actualizarPartida();
		}
	}
	
	
	
	private Partida buscarPartida(int partida) {
		for(Partida p:this.partidas){
			if(p.getId()==partida){
				return p;
			}
		}
		return null;
	}
	
	
}
