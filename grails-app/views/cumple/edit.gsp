
<%@ page import="kairos.Persona" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'persona.label', default: 'Persona')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="body">
            <h1>Agregar Fecha Nacimiento</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${personaInstance}">
            <div class="errors">
                <g:renderErrors bean="${personaInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${personaInstance?.id}" />
                <g:hiddenField name="version" value="${personaInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                  	    	    <td valign="top" class="name"><g:message code="persona.nombre.label" default="Nombre" /></td>
                    	        <td valign="top" class="value">${fieldValue(bean: personaInstance, field: "nombre")}</td>
 	                       </tr>
                        
                            <tr class="prop">
                            	<td valign="top" class="name"><g:message code="persona.twitter.label" default="Twitter" /></td>
                            	<td valign="top" class="value">${fieldValue(bean: personaInstance, field: "twitter")}</td>
                        </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fechaNacimiento"><g:message code="persona.fechaNacimiento.label" default="Fecha Nacimiento" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personaInstance, field: 'fechaNacimiento', 'errors')}">
                                    <g:datePicker name="fechaNacimiento" precision="day" value="${personaInstance?.fechaNacimiento}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="Guardar" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
