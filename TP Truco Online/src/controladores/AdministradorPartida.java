package controladores;

import java.util.ArrayList;
import java.util.Vector;

import dto.AccionDTO;
import dto.HistoriaPartidaDTO;
import dto.PartidaDTO;
import negocio.EstadoPartida;
import negocio.Jugador;
import negocio.Partida;

public class AdministradorPartida {
	private ArrayList<Partida> partidas;
	
	
	public void crearPartida(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {
			Partida p = new Partida(true);
			p.agregarJugador(j1,1);
			p.agregarJugador(j2,2);
			p.agregarJugador(j3,3);
			p.agregarJugador(j4,4);
			p.nuevaMano();
			this.partidas.add(p);
		
	}
	
	public Partida crearPartidaCerrada(Jugador j1, Jugador j2, Jugador j3, Jugador j4) {
		Partida p = new Partida(false);
		p.agregarJugador(j1,1);
		p.agregarJugador(j2,2 );
		p.agregarJugador(j3,3 );
		p.agregarJugador(j4,4 );
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
		if(p.getEstado()==EstadoPartida.ENCURSO){
			p.jugarCarta(ad.getApodoJugador(),ad.getValor());
			p.actualizarPartida();
		}
		}
	}

	public void Retirarse(AccionDTO ad){
		Partida p=this.buscarPartida(ad.getPartida());
		if(p!=null){
		if(p.getEstado()==EstadoPartida.ENCURSO){
			p.retiraseMano(ad.getApodoJugador());
			p.actualizarPartida();
		}
		}
	}
	
	public void cantarEnvite(AccionDTO ad){
		Partida p=this.buscarPartida(ad.getPartida());
		if(p!=null){
		if(p.getEstado()==EstadoPartida.ENCURSO){
			p.cantarEnvite(ad.getApodoJugador(),ad.getValor());
			p.actualizarPartida();
		}
		}
	}
	
	public void responderEnvite(AccionDTO ad){
		Partida p=this.buscarPartida(ad.getPartida());
		if(p!=null){
		if(p.getEstado()==EstadoPartida.ENCURSO){
        	p.responderEnvite(ad.getApodoJugador(), ad.getRespuesta());
        	}
			}
			}
	
	public PartidaDTO mostrarPartida(AccionDTO ad){
		
		
		Partida p=this.buscarPartida(ad.getPartida());
		if(p!=null){
		if(p.getEstado()==EstadoPartida.ENCURSO){
        return 	p.toDTO(ad.getPartida(), ad.getApodoJugador() ,ad.getMostrarValoresEnvido());
        	}
			}
			
		
		
		return null;
		
	}

	public void enviarMensaje(AccionDTO ad){
		Partida p=this.buscarPartida(ad.getPartida());
		if(p!=null){
			p.nuevoMesnaje(ad.getMensaje());
		}
	}

	public void enviarSenia(AccionDTO ad){
		Partida p=this.buscarPartida(ad.getPartida());
		if(p!=null){
		if(p.getEstado()==EstadoPartida.ENCURSO){
        	p.senia(ad.getApodoJugador(),ad.getValor());
        	}
			}
			
	}
	
	public HistoriaPartidaDTO mostrarHistoria(AccionDTO ad){
		
		Partida p = new Partida(false); //completar con el GetPartidaById del DAO el adi viene en el AccionDAO
		if(p!=null){
		if(p.getEstado()==EstadoPartida.FINALIZADA){
			
		return p.mostrarHistoria();
		}
		}
		return null;
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
