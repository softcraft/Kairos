import kairos.*

class BootStrap {

	 def springSecurityService
	 
     def init = { servletContext ->
		 
		 def userRole = Rol.findByAuthority('ROLE_USER') ?: new Rol(authority: 'ROLE_USER').save(failOnError: true)
		 def adminRole = Rol.findByAuthority('ROLE_ADMIN') ?: new Rol(authority: 'ROLE_ADMIN').save(failOnError: true)
		 		 
		 environments {
			production{
				if (!Filial.count()) {
					
					def Aguascalientes = new Filial(nombre:"Aguascalientes").save()
					def Cuernavaca = new Filial(nombre:"Cuernavaca").save()
					def Coyoacan = new Filial(nombre:"Coyoacan").save()
					def Lindavista = new Filial(nombre:"Lindavista").save()
					def Polanco = new Filial(nombre:"Polanco").save()
					def Roma = new Filial(nombre:"Roma").save()
					def AmadoNervo = new Filial(nombre:"Amado Nervo").save()
					def Ecatepec = new Filial(nombre:"Ecatepec").save()
					def Satelite = new Filial(nombre:"Satelite").save()
					def Texcoco = new Filial(nombre:"Texcoco").save()
					def Toluca = new Filial(nombre:"Toluca").save()
					def Minerva = new Filial(nombre:"Minerva").save()
					def SedeCentral = new Filial(nombre:"Sede Central").save()
					def Leon = new Filial(nombre:"Leon").save()
					def Morelia = new Filial(nombre:"Morelia").save()
					def OaxacaCentro = new Filial(nombre:"Oaxaca Centro").save()
					def OaxacaUniversidad = new Filial(nombre:"Oaxaca Universidad").save()
					def Puebla = new Filial(nombre:"Puebla").save()
					def Queretaro = new Filial(nombre:"Queretaro").save()
					def Prueba = new Filial(nombre:"Prueba").save()
					
					
					def u1 = new Usuario(nombre:"Aguascalientes",username:"Aguascalientes",password:springSecurityService.encodePassword('Aguascalientes'),enabled:true,email:"aguascalientes@acropolis.org.mx", filial:Aguascalientes)
					def u2 = new Usuario(nombre:"Cuernavaca",username:"Cuernavaca",password:springSecurityService.encodePassword('Cuernavaca'),enabled:true,email:"cuernavaca@acropolis.org.mx", filial:Cuernavaca)
					def u3 = new Usuario(nombre:"Coyoacan",username:"Coyoacan",password:springSecurityService.encodePassword('Coyoacan'),enabled:true,email:"Coyoacan@acropolis.org.mx", filial:Coyoacan)
					def u4 = new Usuario(nombre:"Lindavista",username:"Lindavista",password:springSecurityService.encodePassword('Lindavista'),enabled:true,email:"Lindavista@acropolis.org.mx", filial:Lindavista)
					def u5 = new Usuario(nombre:"Polanco",username:"Polanco",password:springSecurityService.encodePassword('Polanco'),enabled:true,email:"Polanco@acropolis.org.mx", filial:Polanco)
					def u6 = new Usuario(nombre:"Roma",username:"Roma",password:springSecurityService.encodePassword('Roma'),enabled:true,email:"Roma@acropolis.org.mx", filial:Roma)
					def u7 = new Usuario(nombre:"AmadoNervo",username:"AmadoNervo",password:springSecurityService.encodePassword('AmadoNervo'),enabled:true,email:"AmadoNervo@acropolis.org.mx", filial:AmadoNervo)
					def u8 = new Usuario(nombre:"Ecatepec",username:"Ecatepec",password:springSecurityService.encodePassword('Ecatepec'),enabled:true,email:"Ecatepec@acropolis.org.mx", filial:Ecatepec)
					def u9 = new Usuario(nombre:"Satelite",username:"Satelite",password:springSecurityService.encodePassword('Satelite'),enabled:true,email:"Satelite@acropolis.org.mx", filial:Satelite)
					def u10 = new Usuario(nombre:"Texcoco",username:"Texcoco",password:springSecurityService.encodePassword('Texcoco'),enabled:true,email:"Texcoco@acropolis.org.mx", filial:Texcoco)
					def u11 = new Usuario(nombre:"Toluca",username:"Toluca",password:springSecurityService.encodePassword('Toluca'),enabled:true,email:"Toluca@acropolis.org.mx", filial:Toluca)
					def u12 = new Usuario(nombre:"Minerva",username:"Minerva",password:springSecurityService.encodePassword('Minerva'),enabled:true,email:"Minerva@acropolis.org.mx", filial:Minerva)
					def u13 = new Usuario(nombre:"SedeCentral",username:"SedeCentral",password:springSecurityService.encodePassword('SedeCentral'),enabled:true,email:"SedeCentral@acropolis.org.mx", filial:SedeCentral)
					def u14 = new Usuario(nombre:"Leon",username:"Leon",password:springSecurityService.encodePassword('Leon'),enabled:true,email:"Leon@acropolis.org.mx", filial:Leon)
					def u15 = new Usuario(nombre:"Morelia",username:"Morelia",password:springSecurityService.encodePassword('Morelia'),enabled:true,email:"Morelia@acropolis.org.mx", filial:Morelia)
					def u16 = new Usuario(nombre:"OaxacaCentro",username:"OaxacaCentro",password:springSecurityService.encodePassword('OaxacaCentro'),enabled:true,email:"OaxacaCentro@acropolis.org.mx", filial:OaxacaCentro)
					def u17 = new Usuario(nombre:"OaxacaUniversidad",username:"OaxacaUniversidad",password:springSecurityService.encodePassword('OaxacaUniversidad'),enabled:true,email:"OaxacaUniversidad@acropolis.org.mx", filial:OaxacaUniversidad)
					def u18 = new Usuario(nombre:"Puebla",username:"Puebla",password:springSecurityService.encodePassword('Puebla'),enabled:true,email:"Puebla@acropolis.org.mx", filial:Puebla)
					def u19 = new Usuario(nombre:"Queretaro",username:"Queretaro",password:springSecurityService.encodePassword('Queretaro'),enabled:true,email:"Queretaro@acropolis.org.mx", filial:Queretaro)
					def u20 = new Usuario(nombre:"Prueba",username:"Prueba",password:springSecurityService.encodePassword('Prueba'),enabled:true,email:"Prueba@acropolis.org.mx", filial:Prueba)
					def uAdmin = new Usuario(nombre:"Administrador",username:"admin",password:springSecurityService.encodePassword('nimda'),enabled:true,email:"mercurio@acropolis.org.mx", filial:SedeCentral)
					
					u1.save(flush:true)
					u2.save(flush:true)
					u3.save(flush:true)
					u4.save(flush:true)
					u5.save(flush:true)
					u6.save(flush:true)
					u7.save(flush:true)
					u8.save(flush:true)
					u9.save(flush:true)
					u10.save(flush:true)
					u11.save(flush:true)
					u12.save(flush:true)
					u13.save(flush:true)
					u14.save(flush:true)
					u15.save(flush:true)
					u16.save(flush:true)
					u17.save(flush:true)
					u18.save(flush:true)
					u19.save(flush:true)
					u20.save(flush:true)
					uAdmin.save(flush:true)
					
					if (!u1.authorities.contains(userRole)) {
						SusuarioRol.create u1, userRole
					}
					
					if (!u2.authorities.contains(userRole)) {
						SusuarioRol.create u2, userRole
					}
					
					if (!u3.authorities.contains(userRole)) {
						SusuarioRol.create u3, userRole
					}
					
					if (!u4.authorities.contains(userRole)) {
						SusuarioRol.create u4, userRole
					}
					
					if (!u5.authorities.contains(userRole)) {
						SusuarioRol.create u5, userRole
					}
					
					if (!u6.authorities.contains(userRole)) {
						SusuarioRol.create u6, userRole
					}
					
					if (!u7.authorities.contains(userRole)) {
						SusuarioRol.create u7, userRole
					}
					
					if (!u8.authorities.contains(userRole)) {
						SusuarioRol.create u8, userRole
					}
					
					if (!u9.authorities.contains(userRole)) {
						SusuarioRol.create u9, userRole
					}
					
					if (!u10.authorities.contains(userRole)) {
						SusuarioRol.create u10, userRole
					}
					
					if (!u11.authorities.contains(userRole)) {
						SusuarioRol.create u11, userRole
					}
					
					if (!u12.authorities.contains(userRole)) {
						SusuarioRol.create u12, userRole
					}
					
					if (!u13.authorities.contains(userRole)) {
						SusuarioRol.create u13, userRole
					}
					
					if (!u14.authorities.contains(userRole)) {
						SusuarioRol.create u14, userRole
					}
					
					if (!u15.authorities.contains(userRole)) {
						SusuarioRol.create u15, userRole
					}
					
					if (!u16.authorities.contains(userRole)) {
						SusuarioRol.create u16, userRole
					}
					
					if (!u17.authorities.contains(userRole)) {
						SusuarioRol.create u17, userRole
					}
					
					if (!u18.authorities.contains(userRole)) {
						SusuarioRol.create u18, userRole
					}
					
					if (!u19.authorities.contains(userRole)) {
						SusuarioRol.create u19, userRole
					}
					
					if (!u20.authorities.contains(userRole)) {
						SusuarioRol.create u20, userRole
					}
					
					if (!uAdmin.authorities.contains(adminRole)) {
						SusuarioRol.create uAdmin, adminRole
					}
				}
			}	 
			development {
				if (!Filial.count()) {
					new Filial(nombre:"Sede Central").save()
					
					def filial1 = Filial.get(1)
					
					def uAdmin = new Usuario(nombre:"admin",username:"admin",password:springSecurityService.encodePassword('admin'),enabled:true,email:"mercurio@acropolis.org.mx", filial:filial1)
					uAdmin.save(flush:true)
					
					if (!uAdmin.authorities.contains(adminRole)) {
						SusuarioRol.create uAdmin, adminRole
					}
					
					def javier = new Usuario(nombre:"Javier Cervantes",username:"javier",password:springSecurityService.encodePassword('javier'),enabled:true,email:"1.27201@acropolis.org.mx", filial:filial1)
							.addToPersonas(
								new Persona(nombre:"Jos� Jos�",genero:"CABALLERO",grupo:"PROBACIONISTAS",telefonos:"00000",correo:"a@a",filial:filial1
									).addToHistorial(new Nota(texto:"Notas ...",fecha:new Date())))
							.addToPersonas(
								new Persona(nombre:"Pepe Pepe",genero:"CABALLERO",grupo:"MIEMBROS",telefonos:"00000",correo:"a@a",filial:filial1
									).addToHistorial(new Nota(texto:"Notas ...",fecha:new Date())))
					.save(failOnError: true)
							
					def andres = new Usuario(nombre:"Andres Cervantes",username:"andres",password:springSecurityService.encodePassword("andres"),enabled:true,email:"andrumagu@acropolis.org.mx",filial:filial1)
							.addToPersonas(
								new Persona(nombre:"dfdf sdfdf",genero:"CABALLERO",grupo:"PROSPECTOS",telefonos:"00000",correo:"a@a",filial:filial1
									).addToHistorial(new Nota(texto:"Notas ...",fecha:new Date())))
							.addToPersonas(
								new Persona(nombre:"Otro mas",genero:"CABALLERO",grupo:"MIEMBROS",telefonos:"00000",correo:"a@a",filial:filial1
									).addToHistorial(new Nota(texto:"Notas ...",fecha:new Date())))
					.save(failOnError: true)
				
					new Filial(nombre:"Aguascalientes").save()
					
					def filial2 = Filial.get(2)
					
					def abel = new Usuario(nombre:"Abel Coronado",username:"abel",password:springSecurityService.encodePassword("abel"),enabled:true,email:"abel@acropolis.org.mx",filial:filial2)
							.addToPersonas(
								new Persona(nombre:"Jos� Jos�",genero:"CABALLERO",grupo:"MIEMBROS",telefonos:"00000",correo:"a@a",filial:filial2
							))
							.addToPersonas(
								new Persona(nombre:"Pepe Pepe",genero:"CABALLERO",grupo:"PROBACIONISTAS",telefonos:"00000",correo:"a@a",filial:filial2
							))
					.save(failOnError: true)
					
					def arturo = new Usuario(nombre:"Arturo Dominguez",username:"arturo",password:springSecurityService.encodePassword("arturo"),enabled:true,email:"arturo@acropolis.org.mx",filial:filial2)
							.addToPersonas(
								new Persona(nombre:"Jos� Jos�",genero:"CABALLERO",grupo:"MIEMBROS",telefonos:"00000",correo:"a@a",filial:filial2
							))
							.addToPersonas(new Persona(nombre:"Pepe Pepe",genero:"CABALLERO",grupo:"BAJAS",telefonos:"00000",correo:"a@a",filial:filial2
							))
					.save(failOnError: true)
					
					println Filial.count()
					println Persona.count()
					println Usuario.count()
					
					Persona.get(1).addTag("etiqueta1")
					Persona.get(1).addTag("Probacionista")
					Persona.get(1).addTag("Conferencia")
					Persona.get(2).addTag("etiqueta2")
					Persona.get(3).addTag("etiqueta1")
					
					Persona.get(1).addTag("etiqueta1")
					Persona.get(1).addTag("Probacionista")
					Persona.get(1).addTag("Conferencia")
					Persona.get(2).addTag("etiqueta2")
					
					if (!javier.authorities.contains(userRole)) {
						SusuarioRol.create javier, userRole
					}
					
					if (!andres.authorities.contains(userRole)) {
						SusuarioRol.create andres, userRole
					}
					
					if (!abel.authorities.contains(userRole)) {
						SusuarioRol.create abel, userRole
					}
					
					if (!arturo.authorities.contains(userRole)) {
						SusuarioRol.create arturo, userRole
					}
				}
			}
			test {
				if (!Filial.count()) {
					new Filial(nombre:"Sede Central").save()
					
					def filial1 = Filial.get(1)
					
					def javier = new Usuario(nombre:"Javier Cervantes",username:"javier",password:springSecurityService.encodePassword('javier'),enabled:true,email:"1.27201@acropolis.org.mx", filial:filial1)
							.addToPersonas(
								new Persona(nombre:"Jos� Jos�",genero:"CABALLERO",grupo:"PROBACIONISTAS",telefonos:"00000",correo:"a@a",filial:filial1
									).addToHistorial(new Nota(texto:"Notas ...",fecha:new Date())))
							.addToPersonas(
								new Persona(nombre:"Pepe Pepe",genero:"CABALLERO",grupo:"MIEMBROS",telefonos:"00000",correo:"a@a",filial:filial1
									).addToHistorial(new Nota(texto:"Notas ...",fecha:new Date())))
					.save(failOnError: true)
							
					def andres = new Usuario(nombre:"Andres Cervantes",username:"andres",password:springSecurityService.encodePassword("andres"),enabled:true,email:"andrumagu@acropolis.org.mx",filial:filial1)
							.addToPersonas(
								new Persona(nombre:"dfdf sdfdf",genero:"CABALLERO",grupo:"PROSPECTOS",telefonos:"00000",correo:"a@a",filial:filial1
									).addToHistorial(new Nota(texto:"Notas ...",fecha:new Date())))
							.addToPersonas(
								new Persona(nombre:"Otro mas",genero:"CABALLERO",grupo:"MIEMBROS",telefonos:"00000",correo:"a@a",filial:filial1
									).addToHistorial(new Nota(texto:"Notas ...",fecha:new Date())))
					.save(failOnError: true)
				
					new Filial(nombre:"Aguascalientes").save()
					
					def filial2 = Filial.get(2)
					
					def abel = new Usuario(nombre:"Abel Coronado",username:"abel",password:springSecurityService.encodePassword("abel"),enabled:true,email:"abel@acropolis.org.mx",filial:filial2)
							.addToPersonas(
								new Persona(nombre:"Jos� Jos�",genero:"CABALLERO",grupo:"MIEMBROS",telefonos:"00000",correo:"a@a",filial:filial2
							))
							.addToPersonas(
								new Persona(nombre:"Pepe Pepe",genero:"CABALLERO",grupo:"PROBACIONISTAS",telefonos:"00000",correo:"a@a",filial:filial2
							))
					.save(failOnError: true)
					
					def arturo = new Usuario(nombre:"Arturo Dominguez",username:"arturo",password:springSecurityService.encodePassword("arturo"),enabled:true,email:"arturo@acropolis.org.mx",filial:filial2)
							.addToPersonas(
								new Persona(nombre:"Jos� Jos�",genero:"CABALLERO",grupo:"MIEMBROS",telefonos:"00000",correo:"a@a",filial:filial2
							))
							.addToPersonas(new Persona(nombre:"Pepe Pepe",genero:"CABALLERO",grupo:"BAJAS",telefonos:"00000",correo:"a@a",filial:filial2
							))
					.save(failOnError: true)
					
					println Filial.count()
					println Persona.count()
					println Usuario.count()
					
					Persona.get(1).addTag("etiqueta1")
					Persona.get(1).addTag("Probacionista")
					Persona.get(1).addTag("Conferencia")
					Persona.get(2).addTag("etiqueta2")
					Persona.get(3).addTag("etiqueta1")
					
					Persona.get(1).addTag("etiqueta1")
					Persona.get(1).addTag("Probacionista")
					Persona.get(1).addTag("Conferencia")
					Persona.get(2).addTag("etiqueta2")
					
					if (!javier.authorities.contains(userRole)) {
						SusuarioRol.create javier, userRole
					}
					
					if (!andres.authorities.contains(userRole)) {
						SusuarioRol.create andres, userRole
					}
					
					if (!abel.authorities.contains(userRole)) {
						SusuarioRol.create abel, userRole
					}
					
					if (!arturo.authorities.contains(userRole)) {
						SusuarioRol.create arturo, userRole
					}
					
				}
			}
		 }
     
     
	 }
	 def destroy = {
     }
} 