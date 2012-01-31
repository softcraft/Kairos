package kairos

class ErrorController {

	def errorService
	
    def generateError = {
			errorService.generateError()
		}
	
	def paramsFail = {
			render(contentType:'text/json'){
				array {
					info status:'false',code:412,message:'No se especifico uno o mas parametros requeridos',data: ''
				}
			}
		}
	
	def validateFail = {
		render(contentType:'text/json'){
			array {
				info status:'false',code:400,message:'Error al validar los parametros',data: ''
			}
		}
	}
	
	def updateFail = {
		render(contentType:'text/json'){
			array {
				info status:'false',code:304,message:'Error al actualizar datos',data: ''
			}
		}
	}
	
	def unknownFail = {
		render(contentType:'text/json'){
			array {
				info status:'false',code:500,message:'Ups! Error desconocido',data: ''
			}
		}
	}
	def deleteDataIntegrityError = {
		render(contentType:'text/json'){
			array {
				info status:'true',code:407,message:'Error de integridad al eliminar persona',data: ''
			}
		}
	}
}
