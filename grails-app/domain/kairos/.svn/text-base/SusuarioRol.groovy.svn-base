package kairos

import org.apache.commons.lang.builder.HashCodeBuilder

class SusuarioRol implements Serializable {

	Susuario susuario
	Rol rol

	boolean equals(other) {
		if (!(other instanceof SusuarioRol)) {
			return false
		}

		other.susuario?.id == susuario?.id &&
			other.rol?.id == rol?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (susuario) builder.append(susuario.id)
		if (rol) builder.append(rol.id)
		builder.toHashCode()
	}

	static SusuarioRol get(long susuarioId, long rolId) {
		find 'from SusuarioRol where susuario.id=:susuarioId and rol.id=:rolId',
			[susuarioId: susuarioId, rolId: rolId]
	}

	static SusuarioRol create(Susuario susuario, Rol rol, boolean flush = false) {
		new SusuarioRol(susuario: susuario, rol: rol).save(flush: flush, insert: true)
	}

	static boolean remove(Susuario susuario, Rol rol, boolean flush = false) {
		SusuarioRol instance = SusuarioRol.findBySusuarioAndRol(susuario, rol)
		instance ? instance.delete(flush: flush) : false
	}

	static void removeAll(Susuario susuario) {
		executeUpdate 'DELETE FROM SusuarioRol WHERE susuario=:susuario', [susuario: susuario]
	}

	static void removeAll(Rol rol) {
		executeUpdate 'DELETE FROM SusuarioRol WHERE rol=:rol', [rol: rol]
	}

	static mapping = {
		id composite: ['rol', 'susuario']
		version false
	}
}
