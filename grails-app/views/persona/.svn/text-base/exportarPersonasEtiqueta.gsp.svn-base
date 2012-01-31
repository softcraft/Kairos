<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Nueva Acropolis - Lista de personas</title>     
</head>
<body>
    <h1>${grupo} 
    <g:if test="${etiqueta == '0'}">
	     Sin etiqueta
	</g:if>
	<g:else>
		<g:if test="${etiqueta == null}">
	     Todas las etiquetas
		</g:if>
		<g:else>
	     ${etiqueta}
	    </g:else>
	</g:else>
	
    </h1>
    <table id="export">
        <tr>
            <th class="main">Nombre</th>
            <th>Correo</th>
            <th>Telefonos</th>
            <th>Genero</th>
            <th>Grupo</th>
        </tr>
    <g:each in="${export}" status="i" var="persona">
        <tr>
            <td class="main">${persona.nombre}</td>
            <td>${persona.correo}</td>
            <td>${persona.telefonos}</td>
            <td>${persona.genero}</td>
            <td>${persona.grupo}</td>
        </tr>
    </g:each>
    </table>
</body>
</html>