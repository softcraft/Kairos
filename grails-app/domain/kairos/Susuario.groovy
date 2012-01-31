package kairos

class Susuario {

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Rol> getAuthorities() {
		SusuarioRol.findAllBySusuario(this).collect { it.rol } as Set
	}
}
