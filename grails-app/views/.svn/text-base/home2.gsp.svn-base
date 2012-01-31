<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>K4iros - Sistema de Contactos</title>
<script language="JavaScript" type="text/javascript" src="js/xpath.js"></script>
<script language="JavaScript" type="text/javascript" src="js/SpryData.js"></script>
<script language="JavaScript" type="text/javascript" src="js/SpryJSONDataSet_mini.js"></script>
<script language="JavaScript" type="text/javascript" src="js/SpryNestedJSONDataSet.js"></script>
<script type="text/javascript" src="js/SpryValidationTextField_mini.js"></script>
<script type="text/javascript" src="js/SpryPagedView_mini.js"></script>
<script language="javascript" type="text/javascript" src="js/SpryTooltip.js"></script>
<script src="js/SpryValidationCheckbox.js" type="text/javascript"></script>

<script type="text/javascript">
//var dsItems1 = new Spry.Data.XMLDataSet("data/donuts.xml", "/items/item");
//var dsToppings = new Spry.Data.NestedXMLDataSet(dsItems1, "topping");

//var dsLineas = new Spry.Data.JSONDataSet("data/lineas.js", { path: "items.item", subPaths: [ "batters.batter", "topping" ] });
var personaId;
//var rootPath = "http://78.46.83.66:8080/k4iros/";
var rootPath = "http://localhost:8080/k4iros/";
var listarPersonaService = "persona/listarPersonas";
var crearPersonaService = "persona/crearPersona";
var listarEtiquetaService = "persona/listarEtiquetas";
var listarGruposService = "persona/listarGrupos";
var obtenerPersonaService = "persona/obtenerPersona";
var obtenerEtiquetasPersonaService = "persona/obtenerEtiquetasPersona";
var obtenerHistorialPorPersonaService = "persona/obtenerHistorialPorPersona";
var agregarNotaService = "persona/agregarNota";



var dsTags = new Spry.Data.JSONDataSet(rootPath+listarEtiquetaService+"?filialId=1",{subPaths:"data"});
var dsPersons = new Spry.Data.JSONDataSet(rootPath+listarPersonaService+"?filialId=1",{subPaths:"data"});
//var dsPersons = new Spry.Data.JSONDataSet("data/persons2.js",{subPaths:"data"});
var dsGrupos = new Spry.Data.JSONDataSet(rootPath+listarGruposService+"?filialId=1",{subPaths:"data"});
var dsPersonsDetail = new Spry.Data.JSONDataSet(rootPath+obtenerPersonaService,{subPaths:"data"});
dsPersonsDetail.addObserver(dsPersonDetailsObs);

var dsNotes =  new Spry.Data.JSONDataSet(rootPath+obtenerHistorialPorPersonaService,{subPaths:"data"});
var dsTagsPerson = new Spry.Data.JSONDataSet(rootPath+obtenerEtiquetasPersonaService,{subPaths:"data"});


//var dsUserData =new Spry.Data.JSONDataSet("p_json.php/clientes.Clientes.getClienteBySession",{subPaths:"msg", useCache: false});

function getAllPersons(){
	dsPersons.setURL(rootPath+listarPersonaService+"?filialId=1");
	dsPersons.loadData();
	
	//dsPersons.setURL("data/persons.js");
	//dsPersons.loadData();
	//dsPersons.setURL(rootPath+listarPersona, {method: "POST", postData: "filialId=1",headers: {  "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8" }});
//dsPersons.setURL("http://78.46.83.66:8080/k4iros/persona/listarPersonas", { method: "POST", postData: "filialId=1", headers: {  "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8" });
}	


function getPersonsByTag(rowId){
	var row = dsTags.getRowByID(rowId);
	dsPersons.setURL(rootPath+listarPersonaService+"?filialId=1&etiqueta="+row.data[rowId].nombre);
	dsPersons.loadData();
}

function getPersonsByGroup(group){
	dsPersons.setURL(rootPath+listarPersonaService+"?filialId=1&grupo="+group);
	dsPersons.loadData();
}

function getPersonDetails(id){
	personaId=id;
	dsPersonsDetail.setURL(rootPath+obtenerPersonaService+"?personaId="+id);
	dsPersonsDetail.loadData();
	
	dsTagsPerson.setURL(rootPath+obtenerEtiquetasPersonaService+"?personaId="+id);
	dsTagsPerson.loadData();
	
	dsNotes.setURL(rootPath+obtenerHistorialPorPersonaService+"?personaId="+id);
	dsNotes.loadData();
}

function dsPersonDetailsObs(nType, notifier, data){
	if(nType=="onPostLoad")	{
		var rows = notifier.getData(true);	
		Spry.$('txtName').value = rows[0]["data.nombre"];
		Spry.$('txtPhone').value = rows[0]["data.telefonos"];
		Spry.$('txtAge').value = rows[0]["data.edad"];
		Spry.$('txtMail').value = rows[0]["data.correo"];
		Spry.$('txtFacebook').value = rows[0]["data.facebook"];
		Spry.$('txtTwitter').value = rows[0]["data.twitter"];
		
	}
}

function clearPerson(){
	Spry.$('txtName').value = "";
	Spry.$('txtPhone').value = "";
	Spry.$('txtAge').value = "";
	Spry.$('txtMail').value = "";
	Spry.$('txtFacebook').value = "";
	Spry.$('txtTwitter').value = "";
}



function logout(){
		req = Spry.Utils.loadURL('POST', 'p_json.php/clientes.Clientes.logout/', true, respLogout);
}

function respLogout(req){
		var myObject = eval('(' + req.xhRequest.responseText + ')');
		if(myObject.status){
			window.location="login.html";
		}
}

function crearPersona(){
var formData=
"nombre="+Spry.$('txtName').value+
"&usuarioId=1"+
"&filialId=1"+
"&edad="+Spry.$('txtAge').value+
"&correo="+Spry.$('txtMail').value+
"&telefonos="+Spry.$('txtPhone').value+
"&facebook="+Spry.$('txtFacebook').value+
"&twitter="+Spry.$('txtTwitter').value+
"&genero="+Spry.$('slGender').value+
"&grupo="+Spry.$('slGroup').value;
request = Spry.Utils.loadURL('POST',rootPath+crearPersonaService, true, crearPersonaResponse, {postData: formData, headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}});	
}

function crearPersonaResponse(){
	var myObject = eval(request.xhRequest.responseText);
	//alert(request.xhRequest.responseText);
	clearPerson();
	dsPersons.loadData();
}


function agregarNota(){
	var formData =
		'usuarioId=1'+
		'&nota='+Spry.$('txtNota').value+
		'&personaId=1';
		request = Spry.Utils.loadURL('POST', rootPath+agregarNotaService, true, agregarNotaResponse, {postData: formData, headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}});	
}

function agregarNotaResponse(){
		//var myObject = eval('(' + request.xhRequest.responseText + ')');
		alert(request.xhRequest);
		/*if(myObject.status){
			dsMsjes.loadData();
			Spry.$('msj').value='';
		}
		Spry.$('respuestaMsj').style.display='block';
  		Spry.Utils.setInnerHTML('respuestaMsj', myObject.msg);
		setTimeout("clearResponse('respuestaMsj')",3000);*/
		Spry.$('txtNota').value = "";
}

function clearOptions(radios){
	for(var i = 0; i < radios.length; i++)
		if(radios[i].checked)
			radios[i].checked = false;
}

function getRadioValue(radio){
	for(var i = 0; i < radio.length; i++) {
		if(radio[i].checked) {
			return radio[i].value;
		}
	}
	return "";
}
</script>
<link rel="stylesheet" type="text/css" href="css/SpryValidationTextField.css"/>
<link href="css/styles.css" rel="stylesheet" type="text/css" />


</head>
<body>
<div id="head" style="margin:auto; width:960px; height:60px; text-align:left; background-color:#FFFFFF">
	<img src="imgs/logoAcropolisSmall.jpg" style="margin-top:10px; margin-left:15px;"/>
    <div style="width:230px; height:20px; float:right; margin-right:15px; margin-top:7px; text-align:right">
    	<!--<img src="imgs/avatar.jpg" style="margin-right:10px;float:left;"/>-->
        <span style="color:#333333; font-weight:bold; font-size:18px;">Hola Juan Alberto!</span><br/>
        <span style="color:#333333">Filial Guadalajara</span>
    	<div spry:region="dsUserData">{msg.nombre}</div>
    </div>
</div>
<div style="width:960px; height:25px; background-color:#FF9900; margin:auto; text-align:left">
    <div class="linkMenu" onClick="logout();" style="float:right; margin-right:10px;">Salir</div>
    <span style="clear:both"></span>
</div>
<div id="main" style="width:940px; min-height:600px; background-color: #ffffff; padding:10px; margin:auto;">
    <div id="menuTags" style="width:150px; float:left; border-left:solid 1px #CCC; border-right:solid 1px #CCC; text-align:left;">
    	
        <div class="tagMenu" onclick="getAllPersons()" style="margin-left:25px;">TODAS</div>
        <div spry:region="dsGrupos" spry:repeat="dsTags" spry:select="selectMenuItemClass" class="tagMenu" onclick="getPersonsByGroup({dsTags::data})"  style="margin-left:25px;">
            	{data}
		</div>
        <div style="margin-left:15px;">ETIQUETAS:</div>
        <div spry:region="dsTags" style="margin-top:10px;">
    		<div spry:repeat="dsTags" spry:select="selectMenuItemClass" class="tagMenu" onclick="getPersonsByTag({ds_RowID})"  style="margin-left:25px;">
            	{data.nombre}({data.numero})
			</div>
		</div>
    </div>
	
   <div style="width:350px; float:left; text-align:left; margin-left:20px; padding-right:5px; border-right:solid 1px #CCC;">
    	<div onclick="clearPerson()" style="margin-bottom:10px; padding-bottom:5px; border-bottom: dotted 1px #CCC; cursor:pointer">
        	<img src="imgs/avatar_m.jpg" style="margin-right:10px;float:left;"/>
            <div style="float:left">
            	<span style="font-weight:bold; font-size:14px;">Crear nueva persona</span>
            </div>
            <div style="clear:both"></div>
        </div>
        <div id="listPersons" spry:region="dsPersons">
       	 <div spry:repeat="dsPersons" spry:select="selectPersonClass" onclick="getPersonDetails({dsPersons::data.id})" 
        	style="margin-bottom:10px; padding-bottom:5px; border-bottom: dotted 1px #CCC; cursor:pointer">
        		<img src="imgs/avatar_m.jpg" spry:if="'{data.genero}' == 'CABALLERO'"  style="margin-right:10px;float:left;"/>
            	<img src="imgs/avatar_f.jpg" spry:if="'{data.genero}' != 'CABALLERO'"style="margin-right:10px;float:left;"/>
            	<div style="float:left">
            		<span style="font-weight:bold; font-size:14px;">{data.nombre}</span><br/>
            		{data.correo}<br/>
                	{data.telefonos}
            	</div>
            	<div style="clear:both"></div>
        	</div>
        </div>
    </div>

    <div id="frmContacto" style="float:left; margin-left:20px; width:380px">
    	<div>
        	<span class="lbFrm">Nombre: </span>
            <input type="text" class="txtFrm" id="txtName" style="border:none; border-bottom:solid 1px #CCC"/>
        </div>
        <div>
        	<span class="lbFrm">Edad: </span>
            <input type="text"  class="txtFrm" id="txtAge" style="border:none; border-bottom:solid 1px #CCC"/>
        </div>
        <div>
        	<span class="lbFrm">G&eacute;nero: </span>
            <select id="slGender" class="txtFrm">
  				<option value="CABALLERO">Caballero</option>
  				<option value="DAMA">Dama</option>
			</select>
        </div>
        <div>
        	<span class="lbFrm">Correo Electr&oacute;nico: </span>
            <input type="text"  class="txtFrm" id="txtMail" style="border:none; border-bottom:solid 1px #CCC"/>
        </div>
        <div>
        	<span class="lbFrm">Telefonos: </span>
            <input type="text" class="txtFrm" id="txtPhone" style="border:none; border-bottom:solid 1px #CCC"/>
        </div>
         <div>
        	<span class="lbFrm">Facebook: </span>
            <input type="text"  class="txtFrm" id="txtFacebook" style="border:none; border-bottom:solid 1px #CCC"/>
        </div>
        <div>
        	<span class="lbFrm">Twitter: </span>
            <input type="text"  class="txtFrm" id="txtTwitter" style="border:none; border-bottom:solid 1px #CCC"/>
        </div>
        <div>
        	<span class="lbFrm">Grupo: </span>
            <select id="slGroup" class="txtFrm">
  				<option value="MIEMBROS">Miembros</option>
  				<option value="PROBACIONISTAS">Probacionistas</option>
  				<option value="PROSPECTOS">Prospectos</option>
  				<option value="BAJAS">Bajas</option>
			</select>
        </div>
        <div>
        	<span class="lbFrm">Responsable: </span>
             <select id="slResponsable" class="txtFrm">
  				<option value=""></option>
			</select>

        </div>
        <div style="text-align:left">
        	<input type="button" class="buttonSmall" value="Agregar persona" onclick="crearPersona()" style="margin-left:10px;"/>
        </div>
        <div style="margin-top:10px;">
        	<span class="lbFrm" style="margin-bottom:0">Etiquetas: </span>
            <span class="txtFrm"></span>
        </div>
        <div id="txtTags" spry:region="dsTagsPerson" style=" margin-left:10px;">
            	<div style="float:left; padding:5px; margin:5px; background-color:#CCC" 
                spry:repeat="dsTagsPerson">
                	{data}
                </div> 
            	<div style="clear:both"></div>
            </div>
        <div style="margin-top:10px;">
        	<span class="lbFrm">Historial: </span>
            <span class="txtFrm"></span>
        </div>
        <div style="text-align:left">
        	<textarea id="txtNota" rows="4" style="width:380px; font:sans-serif; color:#333333"></textarea>
            <br/>
            <input type="button" class="buttonSmall" value="Agregar nota" style="margin-top:5px" onclick="agregarNota()"/>
        </div>
         <div id="listNotes" spry:region="dsNotes" style="width:350px; float:left; text-align:left; margin-left:20px; margin-top:10px; padding-right:5px;">
    	<div spry:repeat="dsNotes" style="margin-bottom:10px; padding-bottom:5px; border-bottom: dotted 1px #CCC;">
        	<img src="imgs/avatar.jpg" style=" width:30px; margin-right:10px;float:left;"/>
            <div style="float:left">
            	<span style="font-weight:bold; font-size:14px;">{data.usuario}</span><br/>
                {data.fecha}
                <br/>
            	{data.texto}
            </div>
            <div style="clear:both"></div>
        </div>
        
    </div>
        
    </div>
    
    <div style="clear:both"></div>
    
</div>
</body>
</html>