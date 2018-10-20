package dto;

import java.io.Serializable;

import enumeraciones.TipoCanto;

public class EnviteDTO implements Serializable{
	
	static final long serialVersionUID = 6880253755519391398L;
	private TipoCanto tipoCanto;
	private boolean respuesta;
	private boolean mostrarValoresEnvido;
	
	public EnviteDTO(TipoCanto tipoCanto, boolean respuesta, boolean mostrarValoresEnvido) {
		this.tipoCanto = tipoCanto;
		this.respuesta = respuesta;
		this.mostrarValoresEnvido = mostrarValoresEnvido;
	}

	public TipoCanto getTipoCanto() {
		return tipoCanto;
	}

	public void setTipoCanto(TipoCanto tipoCanto) {
		this.tipoCanto = tipoCanto;
	}

	public boolean isRespuesta() {
		return respuesta;
	}

	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}

	public boolean isMostrarValoresEnvido() {
		return mostrarValoresEnvido;
	}

	public void setMostrarValoresEnvido(boolean mostrarValoresEnvido) {
		this.mostrarValoresEnvido = mostrarValoresEnvido;
	}
}
