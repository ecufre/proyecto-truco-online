package negocio;

import dao.CantoDAO;
import dto.CantoDTO;
import enumeraciones.TipoCanto;
import excepciones.ComunicacionException;

public class Canto {
	private int id;
	private boolean querido;
	private TipoCanto tipoCanto;
	private int cantante;
	
	public Canto(int cantante) {
		this.cantante = cantante;
		this.querido=false;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setQuerido(boolean querido) {
		this.querido = querido;
	}

	public void setTipoCanto(TipoCanto tipoCanto) {
		this.tipoCanto = tipoCanto;
	}

	public void setCantante(int cantante) {
		this.cantante = cantante;
	}

	public int getId() {
		return id;
	}

	public boolean isQuerido() {
		return querido;
	}

	public TipoCanto getTipoCanto() {
		return tipoCanto;
	}

	public int getCantante() {
		return cantante;
	}

	public void grabar() {
		CantoDAO.getInstancia().grabar(this);
	}
	
	public void crear() throws ComunicacionException {
		Integer id = CantoDAO.getInstancia().crear(this);
		if (id != null) this.id = id;
		else throw new ComunicacionException("Hubo un error al crear el canto");
	}

	public CantoDTO toDTO() {
		
		return (new CantoDTO(this.getId(),this.isQuerido(),
				this.getTipoCanto().getId(),this.getTipoCanto().getNombre(),this.getTipoCanto().getValor(),
				this.getCantante()));
		
	
	}
}
