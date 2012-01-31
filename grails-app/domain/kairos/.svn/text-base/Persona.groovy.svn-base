package kairos
import java.util.Date;

import org.grails.taggable.Taggable;

class Persona implements Taggable {
    String nombre 
	String apellidos
    String genero
    String grupo
    int edad
    String telefonos
    String correo
	String facebook
	String twitter
	Date   fechaNacimiento
	static belongsTo = [usuario:Usuario, filial: Filial]
    static hasMany = [ historial : Nota]
    String toString()
    {
        nombre
    }
    String tagsToString()
    {
        String etiqueta = this.tags.toString()
        if(etiqueta != null)
        {
            if(etiqueta.size()>2)
            {
                etiqueta = etiqueta[1..-2]
            }else
            {
                etiqueta=""
            }
        }
        

        return etiqueta
    }
    static constraints = {
        nombre()
		apellidos(nullable:true)
        edad(nullable:true)
        correo(nullable:true)
        telefonos(nullable:true)  
		facebook(nullable:true)
		twitter(nullable:true) 
		fechaNacimiento(nullable:true)     
        genero(inList:["DAMA","CABALLERO"] )
        grupo(inList:["MIEMBROS","PROBACIONISTAS","INSCRITOS","PROSPECTOS","BAJAS"] )        
        usuario(nullable:true)
        historial(nullable:true)
    }
}
