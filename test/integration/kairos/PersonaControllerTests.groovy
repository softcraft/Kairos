package kairos

import grails.test.*

class PersonaControllerTests extends GrailsUnitTestCase {
	
	def personaService
	def controller
	
    protected void setUp() {
        super.setUp()
		controller = new PersonaController()
		controller.personaService = personaService
    }

    protected void tearDown() {
        super.tearDown()
    }
	
	void testPersonaControllerObtenerPersonasPorUsuarioFailNoParamsUsuarioId()
	{		
		controller.params.usuarioId = null		
		controller.obtenerPersonasPorUsuario()
		println controller.response.redirectedUrl
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerObtenerPersonasPorUsuarioFailNoUsuarioIdValid()
	{
		controller.params.usuarioId = '9999'
		controller.obtenerPersonasPorUsuario()
		println controller.response.redirectedUrl
		assertEquals "/error/validateFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerObtenerPersonasPorUsuarioSuccess()
	{
		controller.params.usuarioId = '1'
		controller.obtenerPersonasPorUsuario()
		def result =  controller.response.contentAsString
		println result
		def expected = '"status":"true","code":201'
		println expected
		assertTrue(result.contains(expected))
	}
	
	void testPersonaControllerObtenerPersonasSinUsuarioSuccess()
	{		
		controller.obtenerPersonasSinUsuario()
		def result =  controller.response.contentAsString
		println result
		def expected = '"status":"true","code":201'
		println expected
		assertTrue(result.contains(expected))
	}
	
	void testPersonaControllerEstablecerNotaFailNoParams()
	{
		controller.agregarNota()		
		println controller.response.redirectedUrl		
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerEstablecerNotaFailNoParamsPersonaId()
	{
		controller.params.personaId = null
		controller.params.usuarioId = '1'
		controller.params.nota = 'Nota de Prueba :P'

		controller.agregarNota()
		println controller.response.redirectedUrl
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	void testPersonaControllerEstablecerNotaFailNoParamsUsuarioId()
	{
		controller.params.personaId = '1'
		controller.params.usuarioId = null
		controller.params.nota = 'Nota de Prueba :P'

		controller.agregarNota()
		println controller.response.redirectedUrl
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	void testPersonaControllerEstablecerNotaFailNoParamsNota()
	{
		controller.params.personaId = '1'
		controller.params.usuarioId = '1'
		controller.params.nota = null

		controller.agregarNota()
		println controller.response.redirectedUrl
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	void testPersonaControllerEstablecerNotaFailNoPersonIdValid()
	{
		controller.params.personaId = '9999'
		controller.params.usuarioId = '1'
		controller.params.nota = 'Nota de Prueba :P'

		controller.agregarNota()
		println controller.response.redirectedUrl
		assertEquals "/error/validateFail" , controller.response.redirectedUrl
	}

	void testPersonaControllerEstablecerNotaSuccess()
	{
		controller.params.personaId = '1'
		controller.params.usuarioId = '1'
		controller.params.nota = 'Nota de Prueba :P'
		controller.agregarNota()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":201'
		def expected1 = 'Nota de Prueba :P'
		println expected
		println expected1
		assertTrue(result.contains(expected))
		assertTrue(result.contains(expected1))
	}
	
	void testPersonaControllerHistorialPorPersonaFailNoParams()
	{
		controller.params.personaId = null
		controller.obtenerHistorialPorPersona()
		println controller.response.redirectedUrl
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerHistorialPorPersonaFailNoPersonIdValid()
	{
		controller.params.personaId = '9999'
		controller.obtenerHistorialPorPersona()
		println controller.response.redirectedUrl
		assertEquals "/error/validateFail" , controller.response.redirectedUrl
	}	
	
	void testPersonaControllerObtenerHistorialPorPersonaSuccess()
	{
		controller.params.personaId = '1'				
		controller.obtenerHistorialPorPersona()
		def result =  controller.response.contentAsString
		println result
		def expected = '"status":"true","code":201'
		println expected
		assertTrue(result.contains(expected))
	}
	
	void testPersonaControllerEstablecerEtiquetasFailNoParams()
	{
		controller.establecerEtiquetas()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerEstablecerEtiquetasFailNoParamPersonaId()
	{
		controller.params.etiquetas = 'etiqueta_prueba'
		controller.establecerEtiquetas()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	
	
	void testPersonaControllerEstablecerEtiquetasFailPersonaNoExiste()
	{
		controller.params.etiquetas = 'etiqueta_prueba'
		controller.params.personaId = '10000'
		
		controller.establecerEtiquetas()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/validateFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerEstablecerEtiquetaVaciaSuccess()
	{
		controller.params.personaId = '1'
		controller.establecerEtiquetas()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":201'
		println expected
		assertTrue(result.contains(expected))
	}
	
	void testPersonaControllerEstablecerEtiquetaSuccess()
	{
		controller.params.personaId = '1'
		controller.params.etiquetas = 'etiqueta_prueba'
		controller.establecerEtiquetas()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":201'
		def expected1 = 'etiqueta_prueba'
		println expected
		println expected1
		assertTrue(result.contains(expected))
		assertTrue(result.contains(expected1))
	}
	
	void testPersonaControllerEstablecerEtiquetasSuccess()
	{
		controller.params.personaId = '1'
		controller.params.etiquetas = 'etiqueta_prueba,etiqueta_prueba2'
		controller.establecerEtiquetas()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":201'
		def expected1 = 'etiqueta_prueba'
		def expected2 = 'etiqueta_prueba2'
		
		println expected
		println expected1
		println expected2
		assertTrue(result.contains(expected))
		assertTrue(result.contains(expected1))
		assertTrue(result.contains(expected2))
	}
	
	void testPersonaControllerObtenerEtiquetasPersonaFailNoParamPersonaId()
	{
		controller.obtenerEtiquetasPersona()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerObtenerEtiquetasPersonaFailPersonaNoExiste()
	{
		controller.params.personaId = '1000'
		
		controller.obtenerEtiquetasPersona()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/validateFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerObtenerEtiquetasPersonaSuccess()
	{
		controller.params.personaId = '1'
		
		controller.obtenerEtiquetasPersona()
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":200'
		def expected1 = '[{"nombre":"conferencia","numero":1},{"nombre":"etiqueta1","numero":2},{"nombre":"probacionista","numero":1}]'
		println expected
		println expected1
		assertTrue(result.contains(expected))
		assertTrue(result.contains(expected1))
	}
	
	void testPersonaControllerListarGruposSuccess()
	{
		controller.listarGrupos()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":200'
		def expected1 = '[{"nombre":"MIEMBROS","numero":4},{"nombre":"PROBACIONISTAS","numero":2},{"nombre":"INSCRITOS","numero":0},{"nombre":"PROSPECTOS","numero":1},{"nombre":"BAJAS","numero":1}]'
		println expected
		println expected1
		assertTrue(result.contains(expected))
		assertTrue(result.contains(expected1))
	}
	
	void testPersonaControllerListarGruposFilialSuccess()
	{
		controller.params.filialId=1
		controller.listarGrupos()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":200'
		def expected1 = '[{"nombre":"MIEMBROS","numero":2},{"nombre":"PROBACIONISTAS","numero":1},{"nombre":"INSCRITOS","numero":0},{"nombre":"PROSPECTOS","numero":1},{"nombre":"BAJAS","numero":0}]'
		println expected
		println expected1
		assertTrue(result.contains(expected))
		assertTrue(result.contains(expected1))
	}
	
	void testPersonaControllerListarEtiquetasSuccess()
	{
		controller.listarEtiquetas()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":200'
		def expected1 = '"data":[{"nombre":"conferencia","numero":1},{"nombre":"etiqueta1","numero":2},{"nombre":"etiqueta2","numero":1},{"nombre":"probacionista","numero":1}]'
		println expected
		println expected1
		assertTrue(result.contains(expected))
		assertTrue(result.contains(expected1))
	}
	
	void testPersonaControllerListarUsuariosSuccess()
	{
		controller.params.filialId = 1
		controller.listarUsuarios()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":200'
		println expected
		assertTrue(result.contains(expected))
	}
	
	void testPersonaControllerListarEtiquetasPorFilialSuccess()
	{
		controller.params.filialId = 1
		controller.listarEtiquetas()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":200'
		println expected
		
		assertTrue(result.contains(expected))
	}
	
	void testPersonaControllerListarEtiquetasPorFilialYGrupoSuccess()
	{
		controller.params.grupo = 'MIEMBROS'
		controller.params.filialId = 1
		controller.listarEtiquetas()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":200'
		println expected
		
		assertTrue(result.contains(expected))
	}
	
	void testPersonaControllerListarEtiquetasPorGrupoSuccess()
	{
		controller.params.grupo = 'MIEMBROS'
		controller.listarEtiquetas()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":200'
		println expected
		
		assertTrue(result.contains(expected))
	}
	
	void testPersonaControllerListarEtiquetasFailFilialNoExiste()
	{
		controller.params.filialId = 1000
		
		controller.listarEtiquetas()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/validateFail" , controller.response.redirectedUrl
	}
	
    void testPersonaControllerListarPersonasSuccess()
	{	
		controller.params.filialId=1
		controller.listarPersonas()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":200'
		
		println expected
		assertTrue(result.contains(expected)) 
	}
	
	void testPersonaControllerListarPersonasFailFilialNoExiste()
	{
		controller.params.filialId = 1000
		
		controller.listarPersonas()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/validateFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerListarPersonasFailNoFilial()
	{

		controller.listarPersonas()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerListarPersonasPorEtiquetaSuccess()
	{
		controller.params.filialId=1
		controller.params.etiqueta='etiqueta1'
		controller.listarPersonas()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":200'
		
		println expected
		assertTrue(result.contains(expected))
	}
	
	void testPersonaControllerListarPersonasPorGrupoYEtiquetaSuccess()
	{
		controller.params.grupo='PROBACIONISTAS'
		controller.params.filialId=1
		controller.params.etiqueta='etiqueta1'
		controller.listarPersonas()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":200'
		
		println expected
		assertTrue(result.contains(expected))
	}
	
	void testPersonaControllerListarPersonasPorGrupoSuccess()
	{
		controller.params.filialId = 1
		controller.params.grupo = 'MIEMBROS'
		controller.listarPersonas()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":200'
		
		println expected
		assertTrue(result.contains(expected))
	}
	
	void testPersonaControllerObtenerPersonaSuccess()
	{
		controller.params.personaId = '1'
		controller.obtenerPersona()
		
		def result =  controller.response.contentAsString
		println result
		
		def expected = '"status":"true","code":200'
		
		println expected
		assertTrue(result.contains(expected))
	}
	
	void testPersonaControllerObtenerPersonaFailNoParam()
	{	
		controller.obtenerPersona()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	void testPersonaControllerCrearPersonaFailNoParam()
	{
		controller.crearPersona()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerCrearPersona()
	{
		
		controller.params.putAll([nombre:"Antonio",edad :"38",correo :"antonio@jemeil",telefonos :"12345",facebook :"eltono",twitter :"@eltono",genero :"CABALLERO",grupo:"PROSPECTOS",filialId:1])
		controller.crearPersona()
		def result = controller.response.contentAsString
		println result 
		def expected = '[{"status":"true","code":201,"message":"Se creo persona","data":9}]'

		println expected
		assertEquals (expected, result)			
	}
	
	void testPersonaControllerActualizarPersonaFailNoParam()
	{
		controller.actualizarPersona()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerEstablecerUsuario()
	{		
		controller.params.usuarioId = 3
		controller.params.personaId = 8
		controller.establecerUsuario()
		
		def result = controller.response.contentAsString
		println result
		def expected = '"usuario":3'
		println expected
		assertTrue (result.contains(expected))
	}
	
	void testPersonaControllerEstablecerUsuarioFailNoParamUsuario()
	{
		controller.params.personaId = 8
		controller.establecerUsuario()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerEstablecerUsuarioFailNoParamPersona()
	{
		controller.params.usuarioId = 3
		controller.establecerUsuario()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerEstablecerUsuarioFailNoParams()
	{
		controller.establecerUsuario()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerEstablecerUsuarioFailPersonaNoExiste()
	{		
		controller.params.usuarioId = 3
		controller.params.personaId = 800
		controller.establecerUsuario()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/validateFail" , controller.response.redirectedUrl
	}
	
	void testPersonaControllerEstablecerUsuarioFailUsuarioNoExiste()
	{		
		controller.params.usuarioId = 300
		controller.params.personaId = 8
		controller.establecerUsuario()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/validateFail" , controller.response.redirectedUrl
	}
		
	void testPersonaControllerActualizarPersona()
	{
		controller.params.putAll([personaId:"8",nombre:"Josefo",edad :"38",correo :"antonio@jemeil",telefonos :"12345",facebook :"eltono",twitter :"@eltono",genero :"CABALLERO",grupo:"PROSPECTOS"])
		controller.actualizarPersona()
		def result = controller.response.contentAsString
		println result
		def expected = '[{"status":"true","code":202,"message":"Se actualizo persona","data":[{"id":8,"nombre":"Josefo","edad":38,"correo":"antonio@jemeil","telefonos":"12345","facebook":"eltono","twitter":"@eltono","genero":"CABALLERO","grupo":"PROSPECTOS","usuario":4}]}]'
		println expected
		assertEquals (expected, result)
	}
	
	void testPersonaControllerEliminarPersonaFailNoParam()
	{
		controller.eliminarPersona()
		
		println controller.response.redirectedUrl
		
		assertEquals "/error/paramsFail" , controller.response.redirectedUrl
	}
	void testPersonaControllerEliminarPersona()
	{
		controller.params.putAll([personaId:"8"])
		controller.eliminarPersona()
		def result = controller.response.contentAsString
		println result
		def expected = '[{"status":"true","code":203,"message":"Se elimino persona","data":""}]'
		println expected
		assertEquals (expected, result)					
	}
}
