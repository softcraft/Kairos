package kairos

import java.util.Date;

class Nota {
    Date fecha
    String texto
    static belongsTo = [persona:Persona]
    Usuario usuario

    def beforeInsert = {
       fecha = new Date()
    }
    
    String toString()
    {
        texto
    }
    
    static constraints = {
        usuario (nullable:true)
    }
}
