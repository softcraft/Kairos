package kairos

class Filial {
    String nombre
    static hasMany = [usuarios : Usuario, personas: Persona]
    String toString()
    {
        nombre
    }
    static constraints = {
		usuarios(nullable:true)
		personas(nullable:true)
    }
}