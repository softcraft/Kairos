package kairos

class Usuario extends Susuario {

    String nombre
	String email
      
    static belongsTo = [filial:Filial]
    static hasMany = [ personas : Persona]
	
    String toString()
    {
        nombre
    }
    static constraints = {
           filial (nullable:true)
           nombre (nullable:true)
    }
}
