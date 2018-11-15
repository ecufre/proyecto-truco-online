package enumeraciones;

public enum TipoCanto {
	Envido (1,"Envido", 2,null),
	EnvidoEnvido (2,"Envido  envido", 2,1),
	RealEnvido (3,"Real envido", 3,null),
	FaltaEnvido(4,"Falta envido", 30,null),
	Truco(5,"Truco", 1,null), 
	ReTruco(6,"Re truco", 1,5), 
	ValeCuatro(7,"Vale cuatro", 1,6); 
	
	private final int id;
	private final String nombre;
	private final int valor;
	private final Integer predecesor;

	private TipoCanto(int id, String nombre, int valor, Integer predecesor) {
		this.id = id;
		this.nombre = nombre;
		this.valor = valor;
		this.predecesor = predecesor;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getValor() {
		return valor;
	}

	public Integer getPredecesor() {
		return predecesor;
	}
}