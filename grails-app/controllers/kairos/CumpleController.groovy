package kairos

class CumpleController {

	def springSecurityService
	def personaService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
		
        redirect(action: "list", params: params)
    }

    def list = {
		def filial = Susuario.get(springSecurityService.principal.id).filial
		[personaInstanceList: personaService.obtenerPersonasPorFechaNacimiento(filial)]
    }

    def edit = {
        def personaInstance = Persona.get(params.id)
        if (!personaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [personaInstance: personaInstance]
        }
    }

    def update = {
        def personaInstance = Persona.get(params.id)
        if (personaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (personaInstance.version > version) {
                    
                    personaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'persona.label', default: 'Persona')] as Object[], "Another user has updated this Persona while you were editing")
                    render(view: "edit", model: [personaInstance: personaInstance])
                    return
                }
            }
            personaInstance.properties = params
            if (!personaInstance.hasErrors() && personaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'persona.label', default: 'Persona'), personaInstance.id])}"
                redirect(action: "list")
            }
            else {
                render(view: "edit", model: [personaInstance: personaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), params.id])}"
            redirect(action: "list")
        }
    }
}
