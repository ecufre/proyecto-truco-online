package enumeraciones;

public enum TipoCategoria {
	Novato (1,"Novato"),
	Calificado (2,"Calificado"),
	Experto (3,"Experto"),
	Master (4,"Master");
	
	private int id;
	private String nombre;
	
	private TipoCategoria(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
