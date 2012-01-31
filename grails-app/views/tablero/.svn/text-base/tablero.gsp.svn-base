<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Nueva Acrópolis - Kairos - Tablero de la Verdad 3.0</title>
    <link href="../css/tablero.css" rel="stylesheet" type="text/css" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">  
        $(document).ready(function(){
            $("#report tr:odd").addClass("odd");
            $("#report tr:not(.odd)").hide();
            $("#report tr:first-child").show();
            
            $("#report tr.odd").click(function(){
                $(this).next("tr").toggle();
                $(this).find(".arrow").toggleClass("up");
            });
        });
    </script>        
</head>
<body>
    <h1>Kairos : Tablero de la Verdad - OINAM </h1>
    <p>Da click en una filial para ver el detalle</a></p>
    <table id="report">
    
        <tr>
            <th class="main">Filial</th>
            <th>Miembros</th>
            <th>Probacionistas</th>
            <th>Prospectos</th>
            <th></th>
        </tr>
    <g:each in="${reporte}" status="i" var="itemReporte">
        <tr>
            <td class="main">${itemReporte.nombre}</td>
            <td>${itemReporte.countMiembros}</td>
            <td>${itemReporte.countProbacionistas}</td>
            <td>${itemReporte.countProspectos}</td>
            <td><div class="arrow"></div></td>
        </tr>
        <tr>
        	<td/>
            <td>
                <ul>
                <g:each in="${itemReporte.miembros}" status="j" var="miembro">
                    <li>${miembro.nombre}</li>
                </g:each>                
                 </ul>   
            </td>
            <td>
                <ul>
                <g:each in="${itemReporte.probacionistas}" status="k" var="probacionista">
                    <li>${probacionista.nombre}</li>
                </g:each>
                 </ul>   
            </td>
            <td>
                <ul>
                <g:each in="${itemReporte.prospectos}" status="l" var="prospecto">
                    <li>${prospecto.nombre}</li>
                </g:each>
                 </ul>   
            </td>
            <td/>
        </tr>
    </g:each>
    </table>
    <em><%=now%></em>
</body>
</html>
