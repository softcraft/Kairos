package kairos

class TableroController {
   def personaService
   def index={ 
	   redirect(action:tablero)
   }
   def tablero= {
	   		def reporte = personaService.obtenerReporteFiliales()
			   return [ reporte : reporte ]
	   }
}
