<%@ page import="kairos.Persona" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'persona.label', default: 'Persona')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <h1>Kairos : Cumpleaños</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        	<th>Nombre</th>
                        	<th>Grupo</th>
                        	<th>Fecha de Nacimiento</th>
                        </tr>                       
                    </thead>
                    <tbody>
                    <g:each in="${personaInstanceList}" status="i" var="personaInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${fieldValue(bean: personaInstance, field: "nombre")}</td>
                            <td>${fieldValue(bean: personaInstance, field: "grupo")}</td>       
                            <td><g:link action="edit" id="${personaInstance.id}">
	                            	<g:if test="${personaInstance.fechaNacimiento}">
									     <g:formatDate format="MMM-dd" date="${personaInstance.fechaNacimiento}"/>
									</g:if>
									<g:else>
									     Editar
									</g:else>
	                            	
                            	</g:link></td>             
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
