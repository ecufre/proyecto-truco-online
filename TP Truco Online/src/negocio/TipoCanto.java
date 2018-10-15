package negocio;


public enum TipoCanto {

    ENVIDO (1,"ENVIDO", 2,null),
   
    ENVIDOENVIDO (2,"ENVIDOENVIDO", 4,null),

    REALENVIDO (3,"REALENVIDO", 5,null),

    FALTAENVIDO(4,"FALTAENVIDO", 15,null),
    TRUCO(5,"TRUCO", 1,null), 
    RETRUCO(6,"RETRUCO", 1,5), 
    VALE4(7,"VALE4", 1,6); 
    
   public Integer getPredecesor() {
		return predecesor;
	}




private final int id;
   private final String nombre;
   private final int valor;
   private final Integer predecesor;
  

    

   TipoCanto (int id, String nombre,int valor,Integer pre) { 
	   this.id =id;
	   this.nombre=nombre;
	  
	   this.valor=valor;
	   this.predecesor=pre;
    

    } //Cierre del constructor




public int getId() {
	return id;
}




public String getNombre() {
	return nombre;
}









public int getValor() {
	return valor;
}






 

    //Métodos de la clase tipo Enum

 

} //Cierre del enum
