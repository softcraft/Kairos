package kairos

class PersonaService {

    static transactional = true
	
	def agregarNota(Persona persona, Usuario usuario, String nota){
		Nota objNota = new Nota(fecha:new Date(),texto:nota, usuario:usuario)
		objNota.save()
		persona.addToHistorial(objNota)
		persona.save()
		return Nota.count()
	}
	
	def obtenerNota(long notaId){
		Nota.get(notaId)
	}
	
	def listarHistorialPorPersona(Persona persona){		
		Nota.findAllByPersona(persona)
	}
	
	def listarPersonasPorUsuario(Usuario usuario){
		Persona.findAllByUsuario(usuario,[sort:"nombre"])
	}
	
	def listarPersonasPorUsuarioNull(){
		Persona.findAllByUsuarioIsNull([sort:"nombre"])		
	}
	
	boolean usuarioExisteByLoginPassword(String login, String password){
		def usuario = Usuario.findByLoginAndPassword(login,password.encodeAsSHA1())
		usuario != null
	}
	
	def usuarioByLoginPassword(String login, String password){
		Usuario.findByLoginAndPassword(login,password.encodeAsSHA1())		
	}
	
	boolean usuarioExiste(long usuarioId){
		Usuario.exists(usuarioId)
	}
	
	boolean personaExiste(long personaId){
		Persona.exists(personaId)
	}
	
	boolean filialExiste(long filialId){
		Filial.exists(filialId)
	}
	
	def establecerEtiquetas(Persona persona, String etiquetas){
			persona.setTags([])
			if(etiquetas){
				persona.parseTags(etiquetas)
			}
		}
	
	def obtenerUsuario(long usuarioId){
		Usuario.get(usuarioId)
	}
	
	def obtenerFilial(long filialId){
		Filial.get(filialId)
	}
	
	boolean establecerUsuario(Persona persona, Usuario usuario){
			usuario.addToPersonas(persona)
			usuario.save(flush:true)
		}

	def listarEtiquetasTodas(){
		Persona.allTags.sort()
	}
	
	def listarEtiquetasPorPersona(Persona persona){
			persona.tags.sort()
		}

	def listarEtiquetasPorGrupo(String grupo){
		def etiquetas = []
		def personas = Persona.findAllByGrupo(grupo,[sort:"nombre"])
		personas.each{p->
			etiquetas.addAll(p.tags)
		}
		etiquetas.unique().sort()
	}
	
	def listarEtiquetasPorFilial(Filial filial){
		def etiquetas = []
		def personas = Persona.findAllByFilial(filial,[sort:"nombre"])
		personas.each{p->
			etiquetas.addAll(p.tags)
		}
		etiquetas.unique().sort()
	}
	
	def listarEtiquetasPorFilialYGrupo(Filial filial, String grupo){
		def etiquetas = []
		def personas = Persona.findAllByFilialAndGrupo(filial,grupo,[sort:"nombre"])
		personas.each{p->
			etiquetas.addAll(p.tags)
		}
		etiquetas.unique().sort()
	}
	
	def listarGrupos(){
			Persona.constraints.grupo.inList
		}
	
	def listarUsuarios(Filial filial){
			Usuario.findAllByFilial(filial,[sort:"nombre"])		
		}
	
    def listarPersonas(Filial filial){
			Persona.findAllByFilial(filial,[sort:"nombre"])
		}
	
	def listarPersonasPorGrupo(String grupo, Filial filial){
			Persona.findAllByGrupoAndFilial(grupo,filial,[sort:"nombre"])
		}
	
	def listarPersonasPorGrupoYEtiqueta(String grupo, String etiqueta, Filial filial){
			if(etiqueta == '0'){
					Persona.findAllByGrupoAndFilial(grupoId,filial,[sort:"nombre"]).findAll{it.tags==[]}
				}else{
					Persona.findAllByTag(etiqueta,[sort:"nombre"]).findAll{it.filial == filial}.findAll{it.grupo==grupo}
				}
		}
	
	def listarPersonasPorEtiqueta(String etiqueta,Filial filial){
			if(etiqueta == '0'){
				(Persona.findAllByFilial(filial).findAll{it.tags==[]}).sort{it.nombre}
			}else{
				Persona.findAllByTag(etiqueta,[sort:"nombre"]).findAll{it.filial == filial}
			}	
		}
	
	def obtenerPersona(long personaId){
			Persona.get(personaId)
		}
	
	boolean guardarPersona(Persona personaInstance){
			if(!personaInstance.hasErrors() && personaInstance.save(flush:true)){
					true
				}else{
					false
				}
			
		}
	def obtenerReporteFiliales(){
	
		def reporte = []
		def itemReporte = null
		Filial.list().each 
		{filial->
			 itemReporte = new Expando()
			 itemReporte.nombre=filial.nombre
			 itemReporte.countProbacionistas=Persona.countByFilialAndGrupo(filial,"PROBACIONISTAS")
			 itemReporte.countMiembros=Persona.countByFilialAndGrupo(filial,"MIEMBROS")
			 itemReporte.countProspectos=Persona.countByFilialAndGrupo(filial,"PROSPECTOS")			 
			 itemReporte.probacionistas=Persona.findAllByFilialAndGrupo(filial,"PROBACIONISTAS")
			 itemReporte.miembros=Persona.findAllByFilialAndGrupo(filial,"MIEMBROS")
			 itemReporte.prospectos=Persona.findAllByFilialAndGrupo(filial,"PROSPECTOS")
			 reporte.add itemReporte
		}
		return reporte
	}
	
	def obtenerPersonasPorFechaNacimiento(Filial filial){
		Persona.findAll("from Persona as p where p.filial=:filial and p.grupo in (:grupos)",[filial:filial,grupos:["PROBACIONISTAS","MIEMBROS","INSCRITOS"]]).sort{it.fechaNacimiento?.date ?: 0}.sort{it.fechaNacimiento?.month ?: 0}
	}
}
