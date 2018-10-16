package negocio;

public class Canto {

	private boolean querido;
	private TipoCanto tipoCanto;
	public Canto() {
		super();
		querido=false;
	}
	public boolean isQuerido() {
		return querido;
	}
	public void setQuerido(boolean querido) {
		this.querido = querido;
	}
	public TipoCanto getTipoCanto() {
		return tipoCanto;
	}
	public void setTipoCanto(TipoCanto tipoCanto) {
		this.tipoCanto = tipoCanto;
	}
	
	
	
}
