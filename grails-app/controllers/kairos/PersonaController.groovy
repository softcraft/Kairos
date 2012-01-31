package kairos

import grails.converters.*

class PersonaController {

	def personaService
	def springSecurityService
	
	/**
	* Se obtienen datos de usuario 
	* 
	* @return [{"status":"true","code":201,"message":"Usuario autenticado","data":{"idUsuario":3,"idFilial":2,"nombreFilial":"Aguascalientes","usuarioNombre":"Nombre del usuario"}}]
	**/
	def autenticar = {
		
		def usuario = Susuario.get(springSecurityService.principal.id)
		def datos = [idUsuario:usuario.id,idFilial:usuario.filial.id,nombreFilial:usuario.filial.nombre,usuarioNombre:usuario.nombre]
		render(contentType:'text/json'){
			array {
				info status:'true',code:201,message:'Usuario autenticado',
				data: datos
			}
		}		
	}
	/**
	* Se agrega una Nota a una Persona	
	* 
	* @param	*personaId	Id de la persona a la cual se estableci&oacute; la Nota
	* @param	*usuarioId	Id de el usuario que escribe la Nota 
	* @param	*nota		Cadena con la Nota a establecer 
	* 
	* @throws paramsFailError(402) 		Si no se establece alguno de los parametros personaId,usuarioId o nota
	* @throws validateFailError(400) 	Si la persona o el usuario no existe
	* 
	* @return [{"status":"true","code":201,"message":"Se establecio la nota","data":[{"id":1,"texto":"Notas ...","fecha":"2011-03-12T14:59:41Z","usuario":"","persona":1}]
	**/
	def agregarNota = {
		def personaId = params.personaId
		def usuarioId = params.usuarioId
		def nota = params.nota
		def persona = null
		def usuario = null
		if(!(personaId && usuarioId && nota)){
			redirect(controller:'error',action:'paramsFail')
		}else{			
			if(!((personaService.personaExiste(personaId.toLong()))&&(personaService.usuarioExiste(usuarioId.toLong())))){
				redirect(controller:'error',action:'validateFail')
			}else{
				persona = Persona.get(personaId.toLong())
				usuario = Usuario.get(usuarioId.toLong())
				long notaId = personaService.agregarNota(persona,usuario,nota)
				def n = personaService.obtenerNota(notaId)
				render(contentType:'text/json'){
					array {
						info status:'true',code:201,message:'Se establecio la nota',
						data: array {
								note id: n.id, texto: n.texto, fecha: n.fecha, usuario: n.usuario?.nombre
									persona: n.persona
								} 
					}
				}
			}
		}
	}
	
	/**
	* Se obtiene el Historial de Notas de una persona
	* 
	* @param 	*personaId					Id de la persona de la cual se obtendra su Historial de Notas
	* 
	* @throws 	paramsFailError(412)		Si no se establece el parametro de personaId
	* @throws 	validateFailError(400)		Si la persona no existe
	* 
	* @return [{"status":"true","code":201,"message":"Se obtuvo el historial","data":[{"class":"kairos.Nota","id":1,"texto":"Notas ...","fecha":"2011-03-12T15:15:43Z","usuario":null,"persona":{"class":"Persona","id":1}}]}]
	**/
	def obtenerHistorialPorPersona = {
		def personaId = params.personaId
		def persona = null
		if(!(personaId)){
			redirect(controller:'error',action:'paramsFail')
		}else{
		if(!((personaService.personaExiste(personaId.toLong())))){
			redirect(controller:'error',action:'validateFail')
		}else{
			persona = Persona.get(personaId.toLong())
			def historial = personaService.listarHistorialPorPersona(persona)
			render(contentType:'text/json'){
				array {
					info status:'true',code:201,message:'Se obtuvo el historial',
					data: array {
						for(n in historial) {
							note id: n.id, texto: n.texto, fecha: new java.text.SimpleDateFormat("dd-MM-yyyy").format(n.fecha), usuario: n.usuario?.nombre
								persona: n.persona
							}
						}
				}
			}
		}
		
		}
	}
	
	/**
	* Se obtienen las Personas correspondientes a un Usuario
	* 
	* @param	*usuarioId		Id del usuario del cual se obtendran las Personas
	* 
	* @throws	paramsFailError(412)	Si no se establece el parametro de usuarioId
	* @throws	validateFailError(400)	Si el usuario no existe
	* 
	* @return [{"status":"true","code":200,"message":"Listado de personas","data":[{"id":0,"nombre":"","grupo":"","usuario":"","genero":""}]}]
	**/
	def obtenerPersonasPorUsuario= {
		def usuarioId = params.usuarioId
		def usuario = null
		if(!(usuarioId)){
			redirect(controller:'error',action:'paramsFail')
		}else{
		if(!((personaService.usuarioExiste(usuarioId.toLong())))){
			redirect(controller:'error',action:'validateFail')
		}else{
			usuario = Usuario.get(usuarioId.toLong())
			def personas = personaService.listarPersonasPorUsuario(usuario)
			render(contentType:'text/json'){
				array {
					info status:'true',code:201,message:'Se obtuvieron las personas',
					data: array {
									for(p in personas) {
										persona id:p.id, nombre: p.nombre, grupo: p.grupo, 
													usuario: p.usuario?.nombre, genero: p.genero
									}
							}
				}
			}
		}
		
		}
	}
	
	/**
	* Se obtienen las Personas sin Usuario asignado	
	* @return [{"status":"true","code":201,"message":"Se obtuvieron las personas","data":[]}]
	**/
	//TODO Juntar este metodo con obtenerPersonas
	def obtenerPersonasSinUsuario= {
		
		def personas = personaService.listarPersonasPorUsuarioNull()
		
		render(contentType:'text/json'){
			array {
				info status:'true',code:201,message:'Se obtuvieron las personas',
				data: personas
			}
		}	
		
	}
	
	/**
	* Se establecen las etiquetas de una persona
	* 
	* <ul>
	* <li>Para borrar se manda etiquetas vacio
	* <li>El valor de las etiquetas se sobreescribe
	* <li>Si se quiere establecer mas de una etiqueta, se envian separadas por comas ej: (etiqueta1,etiqueta2)
	* </ul>
	* 
	* @param	*personaId	Id de la persona a la cual se establecieron sus etiquetas
	* @param	etiquetas	Cadena con etiqueta o etiqueta a establecer (vac�a para borrar)
	* 
	* @throws	paramsFailError(412)	Si no se establece el parametro de personaId
	* @throws	validateFailError(400)	Si la persona no existe
	* 
	* @return [{"status":"true","code":201,"message":"Se establecieron las etiquetas","data":["etiqueta1","etiqueta2"]}]
	**/
	def establecerEtiquetas = {
		
		def personaId = params.personaId
		def etiquetas = params.etiquetas
		
		if(!personaId){
			redirect(controller:'error',action:'paramsFail')
		}else{
			
			if(!personaService.personaExiste(personaId.toLong())){
				redirect(controller:'error',action:'validateFail')
				}else{
				
					def persona = Persona.get(personaId.toLong())
					personaService.establecerEtiquetas(persona, etiquetas)
					
					def listaEtiquetas = personaService.listarEtiquetasPorPersona(persona)
					
					render(contentType:'text/json'){
						array {
							info status:'true',code:201,message:'Se establecieron las etiquetas',
							data: array {
								for(e in listaEtiquetas) {
									etiqueta nombre:e, numero: Persona.countByTag(e)
								}
							}
						}
					}
				}	
		}
	}
	
	/**
	* Se obtienen las etiquetas de una persona
	* 
	* @param	*personaId	Id de la persona de la cual se obtendran sus etiquetas
	* 
	* @throws 	paramsFailError(402)	Si no se establece el parametro de personaId
	* @throws 	validateFailError(400)	Si la persona no existe
	* 
	* @return [{"status":"true","code":200,"message":"Lista de etiquetas de persona","data":["etiqueta1","etiqueta2"]}]
	**/
	def obtenerEtiquetasPersona = {
		def personaId = params.personaId
		
		if(!personaId){
			redirect(controller:'error',action:'paramsFail')
		}else{
			
			if(!personaService.personaExiste(personaId.toLong())){
					redirect(controller:'error',action:'validateFail')
				}else{
				
					def persona = Persona.get(personaId.toLong())
					
					def listaEtiquetas = personaService.listarEtiquetasPorPersona(persona)
	
					render(contentType:'text/json'){
						array {
							info status:'true',code:200,message:'Lista de etiquetas de persona',
							data: array {
								for(e in listaEtiquetas) {
									etiqueta nombre:e, numero: Persona.countByTag(e)
								}
							}
						}
					}
				}	
		}
	}
	
	/**
	* Regresa un listado de las etiquetas y cuantas personas estan clasificadas por dicha
	* etiqueta filtrado por filial, grupo, ambos o ninguno
	* 
	* @param	grupo		Grupo al que pertenece ("MIEMBROS","PROBACIONISTAS","EXTERNOS","BAJAS")
	* @param	filialId	Filial a la que pertence
	* 
	* @throws 	validateFailError(400)	Si la filial no existe
	* 
	* @return [{"status":"true","code":200,"message":"Listado de las etiquetas","data":["",""]}]
	**/
	def listarEtiquetas = {
			def listaEtiquetas
			def grupo = params.grupo
			def filialId = params.filialId
			
			if(filialId){
				if(!personaService.filialExiste(filialId.toLong())){
					redirect(controller:'error',action:'validateFail')
				}else{
					def filial = personaService.obtenerFilial(filialId.toLong())
					
					if(grupo){
						listaEtiquetas = personaService.listarEtiquetasPorFilialYGrupo(filial,grupo)
					}else{
						listaEtiquetas = personaService.listarEtiquetasPorFilial(filial)
					}					
				}
			}else if(grupo){
				listaEtiquetas = personaService.listarEtiquetasPorGrupo(grupo)
			}else{
				listaEtiquetas = personaService.listarEtiquetasTodas()
			}
			
			render(contentType:'text/json'){
				array {
					info status:'true',code:200,message:'Listado de las etiquetas',
					data: array {
							for(e in listaEtiquetas) {
								if(grupo)
								etiqueta nombre:e, numero: Persona.findAllByTag(e).findAll{it.grupo==grupo}.size()
								else
								etiqueta nombre:e, numero: Persona.countByTag(e)
							}
					}
				}
			}
		}
	
	/**
	* Regresa un listado de los usuarios y cuantas personas estan asignadas a dicho usuario
	*
	* @param	*filialId	Filial a la que pertence
	*
	* @throws 	paramsFailError(412)	Si no se recibe parametro de filialId
	* @throws 	validateFailError(400)	Si la filial no existe
	*
	* @return [{"status":"true","code":200,"message":"Listado de usuarios","data":[{"id":0},{"nombre":""},{"numero":0}]}]
	**/
	def listarUsuarios = {
		
			def listaUsuarios
			def filialId = params.filialId
			
			if(!filialId){
				redirect(controller:'error',action:'paramsFail')
			}else{
				if(!personaService.filialExiste(filialId?.toLong())){
					redirect(controller:'error',action:'validateFail')
				}else{
						def filial = personaService.obtenerFilial(filialId.toLong())
						listaUsuarios = personaService.listarUsuarios(filial)
					}
				}
						
			render(contentType:'text/json'){
				array {
					info status:'true',code:200,message:'Listado de los usuarios',
					data: array {
							for(u in listaUsuarios) {
								usuarios id: u.id, nombre:u.nombre, numero: Persona.countByUsuario(u)
							}
					}
				}
			}
		}
	
	/**
	* Regresa un listado de los grupos de personas que existen, filtrado por filial
	* 
	* @throws	ValidationFailError	Si la filial no existe
	* @param filialId	
	* 
	* @return [{"status":"true","code":200,"message":"Listado de los grupos de persona","data":["",""]}]
	**/
	def listarGrupos = {
		
		    def filialId = params.filialId?.toLong()
			def listaGrupos = personaService.listarGrupos()
			def filial
			
			if(filialId){
				if(!personaService.filialExiste(filialId)){
					redirect(controller:'error',action:'validateFail')
				}else{
					filial = Filial.get(filialId)
				}
			}
			
			render(contentType:'text/json'){
				array {
					info status:'true',code:200,message:'Listado de los grupos de persona',
					data: array {
						for(g in listaGrupos) {
							if(!filialId){
									grupo nombre:g, numero: Persona.countByGrupo(g)
								}else{
									grupo nombre:g, numero: Persona.countByGrupoAndFilial(g,filial)
								}
						}
					}
				}
		}
	}
	
	/**
	 * Regresa un listado de todas las personas en el sistema de una filial
	 * , filtrados por grupo, etiqueta, ambos o ninguno
	 * <p>
	 * Si en el parametro de etiqueta se envia '0', se regresar&aacute;n las personas sin etiqueta
	 * 
	 * @params grupo		Grupo al que pertenece ("MIEMBROS","PROBACIONISTAS","EXTERNOS","BAJAS")
	 * @params etiqueta		Etiqueta con la que las persona estan clasificadas ( etiqueta / 0 )
	 * @params *filialId	Filial a la que pertencen las personas
	 * 
	 * @throws paramsFail(412) 		Si la filial no se especifica como par�metro
	 * @throws validataFail(400) 	Si la filial no existe
	 * @return [{"status":"true","code":200,"message":"Listado de personas","data":[{"id":0,"nombre":"","grupo":"","usuario":"","genero":""}]}]
	 **/
	def listarPersonas = {
			def listaPersonas
			def grupo = params.grupo
			def etiqueta = params.etiqueta
			def filialId = params.filialId
			
			if(!filialId){
				redirect(controller:'error',action:'paramsFail')
			}else{
				if(!personaService.filialExiste(filialId.toLong())){
					redirect(controller:'error',action:'validateFail')
				}else{
					def filial = personaService.obtenerFilial(filialId.toLong())
					if(grupo && etiqueta){
							listaPersonas = personaService.listarPersonasPorGrupoYEtiqueta(grupo,etiqueta,filial)
						}else if(grupo){
							listaPersonas = personaService.listarPersonasPorGrupo(grupo,filial)
						}else if(etiqueta){
							listaPersonas = personaService.listarPersonasPorEtiqueta(etiqueta,filial)
						}else{
							listaPersonas = personaService.listarPersonas(filial)
						}	
					
					render(contentType:'text/json'){
						array { 
							info status:'true',code:200,message:'Listado de personas',
							data: array {
									for(p in listaPersonas) {
										persona id:p.id, nombre: p.nombre, grupo: p.grupo, 
													usuario: p.usuario?.nombre, genero: p.genero
									}
							}
						}	
					}
				}
			}
		}
	
	/**
	* Regresa el detalle de una persona	
	* 
	* @param 	*personaId 	id de la persona (obligatorio)
	* 
	* @throws	paramsFailError(412)	Si no se establece el parametro de personaId
	* @throws	validateFailError(400)Si la persona no existe
	* 
	* @return [{"status":"true","code":200,"message":"Detalle de la persona","data":[{"id":3,"nombre":"","edad":0,correo":"","telefonos":"","facebook":"","twitter":"",genero":"","grupo":"","usuario":0,fechaNacimiento:""}]}]
	**/
   def obtenerPersona = {
	   
	    def personaId = params.personaId
	   	
		if(!personaId){
			redirect(controller:'error',action:'paramsFail')
		}else{	 
			
			
			if(!personaService.personaExiste(personaId.toLong())){
					redirect(controller:'error',action:'validateFail')
				}else{
				   
				   def p = personaService.obtenerPersona(personaId.toLong())
				
				   render(contentType:'text/json'){
					   array {
						   info status:'true',code:200,message:'Detalle de la persona',
						   data: array { 
							   		persona id:p.id, nombre: p.nombre, edad: p.edad, 
									   			correo: p.correo, telefonos: p.telefonos, 
												facebook: p.facebook, twitter: p.twitter, fechaNacimiento: p.fechaNacimiento,
												genero: p.genero, grupo: p.grupo, usuario: p.usuario?.id
						   		}
					   }
				   }
				}
	   	}
   }
   
   /**
   * Crear una nueva persona
   * 
   * @param 	*nombre		Nombre (obligatorio)
   * @param		*filialId	Id de la filial (obligatorio)
   * @param 	edad		Edad (opcional)
   * @param 	correo		Correo (opcional)
   * @param 	telefonos	Telefonos (opcional)
   * @param 	facebook	Facebook (opcional)
   * @param 	twitter		Twitter (opcional)
   * @param 	fechaNacimiento FechaNacimiento (opcional)
   * @param 	*genero		Genero ("DAMA","CABALLERO") (obligatorio)
   * @param 	*grupo		Grupo al que pertenece ("MIEMBROS","PROBACIONISTAS","EXTERNOS","BAJAS")  (obligatorio)
   * 
   * @throws	paramsFailError(412)	Si no se establece el parametro de filialId,nombre, genero o grupo
   * @throws	validateFailError(400)	Si los parametros especificados nos son validos para una persona o filial
   * @throws	updateFailError(304)	Si existe un error al crear la persona
   * 
   * @return [{"status":"true","code":201,"message":"Se creo persona","data":0}]
   **/
   def crearPersona = {
	   
	   def filialId = params.filialId
	   if(!(params.nombre && params.genero && params.grupo && filialId)){
		   redirect(controller:'error',action:'paramsFail')
	   }else{   
		   
	   	   def p = new Persona()
		   p.properties = params
		   
		   if(!personaService.filialExiste(filialId.toLong())){
			   		redirect(controller:'error',action:'validateFail')
			   }else{
				   p.filial = personaService.obtenerFilial(filialId.toLong())
				   
				   if(!p.validate()){
					   redirect(controller:'error',action:'validateFail')
				   }else{
				    	if(personaService.guardarPersona(p)){
							render(contentType:'text/json'){
								   array {
									   info status:'true',code:201,message:'Se creo persona',
									   data: p.id
								   }
							}
						}else{
							redirect(controller:'error', action:'updateFail')
						}	
				  }
			   }
	   }
   }

   /**
   * Actualizar una persona
   * 
   * @param     *personaId  Identificador de la persona
   * @param 	nombre		Nombre 
   * @param 	edad		Edad 
   * @param 	correo		Correo 
   * @param 	telefonos	Telefonos 
   * @param 	facebook	Facebook 
   * @param 	twitter		Twitter 
   * @param		fechaNacimiento FechaNacimiento
   * @param 	genero		Genero ("DAMA","CABALLERO")
   * @param 	grupo		Grupo al que pertenece ("MIEMBROS","PROBACIONISTAS","EXTERNOS","BAJAS")
   * 
   * @return [{"status":"true","code":202,"message":"Se actualizo persona","data":[{"id":3,"nombre":"","edad":0,correo":"","telefonos":"","facebook":"","twitter":"",genero":"","grupo":"","usuario":0}]}]
   **/
   //TODO:Validar que la persona existe, antes de intentar actualizar.
   def actualizarPersona = {
	   def personaId = params.personaId
	   if(personaId){
		   
		   def p = personaService.obtenerPersona(personaId.toLong())
		   if(p) {
			   p.properties = params
			   if(!p.validate()){
				   
				   redirect(controller:'error',action:'validateFail')
			   }else
			   {
				
		    	if(personaService.guardarPersona(p)){
				
					render(contentType:'text/json'){
						   array {
							   info status:'true',code:202,message:'Se actualizo persona',
							   data: array { 
							   		persona id:p.id, nombre: p.nombre, edad: p.edad, 
									   			correo: p.correo, telefonos: p.telefonos, 
												facebook: p.facebook, twitter: p.twitter, p.fechaNacimiento,
												genero: p.genero, grupo: p.grupo, usuario: p.usuario?.id
						   		}
						   }
					}
				}else{				
					redirect(controller:'error', action:'updateFail')				
				}			 			
			   }			
		   }
		   else {			   
			   redirect(controller:'error',action:'paramsFail') 
		   }
	   }else
   {	   
	   redirect(controller:'error',action:'paramsFail')
   }
	   
	   	   
   }
   
   /**
   * Establecer el usuario de una persona
   * 
   * @param     *personaId   Identificador de la persona 
   * @param		*usuarioId	 Identificador del usuario 
   * 
   * @throws	paramsFailError(412)	Si no se establece el parametro de usuarioId o personaId
   * @throws	validateFailError(400)	Si no existe el usuario o la persona
   * @throws	updateFailError(304)	Si existe un error al establecer la relacion
   * 
   * @return [{"status":"true","code":202,"message":"Se establecio usuario a persona","data":[{"id":3,"nombre":"","edad":0,correo":"","telefonos":"","facebook":"","twitter":"",genero":"","grupo":"","usuario":0}]}]
   **/
   def establecerUsuario = {
	   
	   def personaId = params.personaId
	   def usuarioId = params.usuarioId
	   
	   if(!(personaId && usuarioId)){
		   		redirect(controller:'error',action:'paramsFail')
	   		}else{
	   
		   if(!(personaService.personaExiste(personaId.toLong()) && personaService.usuarioExiste(usuarioId.toLong()))){
			   		redirect(controller:'error',action:'validateFail')
			   }else{
			   
				   def p = personaService.obtenerPersona(personaId.toLong())
				   def usuario = personaService.obtenerUsuario(usuarioId.toLong())
						
					if(!personaService.establecerUsuario(p,usuario)){
						redirect(controller:'error', action:'updateFail')
					}else{
					
						render(contentType:'text/json'){
							   array {
								   info status:'true',code:202,message:'Se establecio usuario a persona',
								   data: array {
											   persona id:p.id, nombre: p.nombre, edad: p.edad,
														   correo: p.correo, telefonos: p.telefonos,
														facebook: p.facebook, twitter: p.twitter,
														genero: p.genero, grupo: p.grupo, usuario: p.usuario?.id
										   }
							   }
						}
					}	
			   }	
	   		}	  
   }
   
   /**
   * Eliminar una persona
   * 
   * @param     *personaId   Id (Int)   
   * 
   * @return [{"status":"true","code":203,"message":"Se elimino persona","data":''}]
   **/
   def eliminarPersona= {
	   def personaId = params.personaId
	   
	   if(!personaId){
		   redirect(controller:'error',action:'paramsFail')
	   }else
   	   {	   	   		  
		   def persona = personaService.obtenerPersona(personaId.toLong())
		   if(persona) {
			   try {
				   persona.delete(flush:true)
				   render(contentType:'text/json'){
						   array {
							   info status:'true',code:203,message:'Se elimino persona',
							   data:''
						   }
					}
			   }
			   catch(org.springframework.dao.DataIntegrityViolationException e) {				   
				    redirect(controller:'error',action:'deleteDataIntegrityError')
			   }
		   }
		   else {
			   redirect(controller:'error',action:'paramsFail') 
		   }	
   	   }   
	  }
   
   /**
   * Regresa un listado de todas las personas en el sistema de una filial
   * , filtrados por grupo, etiqueta, ambos o ninguno
   * <p>
   * Si en el parametro de etiqueta se envia '0', se regresar&aacute;n las personas sin etiqueta
   *
   * @params etiqueta		Etiqueta con la que las persona estan clasificadas ( etiqueta / 0 )
   * @params grupo		Grupo al que pertenece ("MIEMBROS","PROBACIONISTAS","EXTERNOS","BAJAS")
   * @params *filialId	Filial a la que pertencen las personas
   *
   * @throws paramsFail(412) 		Si la filial no se especifica como par�metro
   * @throws validataFail(400) 	Si la filial no existe
   * @return [{"status":"true","code":200,"message":"Listado de personas","data":[{"id":0,"nombre":"","telefonos":"","correo":""}]}]
   **/
  def exportarPersonasEtiqueta = {
		  def listaPersonas
		  def etiqueta = params.etiqueta
		  def filialId = params.filialId
		  def grupo = params.grupo
		  
		  if(!filialId){
			  redirect(controller:'error',action:'paramsFail')
		  }else{
			  if(!personaService.filialExiste(filialId.toLong())){
				  redirect(controller:'error',action:'validateFail')
			  }else{
				  def filial = personaService.obtenerFilial(filialId.toLong())
				  if(grupo && etiqueta){
							listaPersonas = personaService.listarPersonasPorGrupoYEtiqueta(grupo,etiqueta,filial)
						}else if(grupo){
							listaPersonas = personaService.listarPersonasPorGrupo(grupo,filial)
						}else if(etiqueta){
							listaPersonas = personaService.listarPersonasPorEtiqueta(etiqueta,filial)
						}else{
							listaPersonas = personaService.listarPersonas(filial)
						}

					  def export = []
					  for(p in listaPersonas) {
						  def item = new Expando()
						   item.nombre = p.nombre
						   item.telefonos = p.telefonos
						   item.correo = p.correo
						   item.genero = p.genero
						   item.grupo = p.grupo
						   export.add item
					  }
					  
					  [export:export,etiqueta:etiqueta,grupo:grupo]
				  }
			  }
		  }
}
