<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>K4iros - Sistema de Contactos</title>
<script language="JavaScript" type="text/javascript" src="js/xpath.js"></script>
<script language="JavaScript" type="text/javascript" src="js/SpryData.js"></script>
<script language="JavaScript" type="text/javascript" src="js/SpryJSONDataSet_mini.js"></script>
<script type="text/javascript" src="js/SpryValidationTextField_mini.js"></script>
<script type="text/javascript" src="js/SpryPagedView_mini.js"></script>
<script language="JavaScript" type="text/javascript" src="js/SpryAutosuggest.js"></script>

<link rel="stylesheet" type="text/css" href="css/SpryValidationTextField.css"/>
<link rel="stylesheet" type="text/css" href="css/SpryAutosuggest.css"/>
<link href="css/styles.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
var personaId = 0;
var usuarioId=0;
var filialId=0;
var grupo="";
var secc="all";
var bUpdate=false;
//var rootPath = "http://78.46.83.66:8080/k4iros/";
var rootPath = "http://78.46.83.66:8080/kairos/";
var autenticarPersonaService = "persona/autenticar";
var listarPersonaService = "persona/listarPersonas";
var crearPersonaService = "persona/crearPersona";
var listarEtiquetaService = "persona/listarEtiquetas";
var listarGruposService = "persona/listarGrupos";
var obtenerPersonaService = "persona/obtenerPersona";
var obtenerEtiquetasPersonaService = "persona/obtenerEtiquetasPersona";
var obtenerHistorialPorPersonaService = "persona/obtenerHistorialPorPersona";
var agregarNotaService = "persona/agregarNota";
var listarUsuariosService = "persona/listarUsuarios";
var eliminarPersonaService = "persona/eliminarPersona";
var actualizarPersonaService = "persona/actualizarPersona";
var establecerEtiquetasService = "persona/establecerEtiquetas";
var establecerUsuarioService = "persona/establecerUsuario";
var obtenerPersonasPorUsuarioService = "persona/obtenerPersonasPorUsuario";

var dsUser = new Spry.Data.JSONDataSet(rootPath+autenticarPersonaService,{subPaths:"data", useCache: false});
dsUser.addObserver(dsUserObs);

var dsTodas = new Spry.Data.JSONDataSet("data/todas.js");
var dsTags = new Spry.Data.JSONDataSet(rootPath+listarEtiquetaService+"?filialId="+filialId+"&grupo="+grupo,{subPaths:"data", useCache: false});
var dsTagsSuggest = new Spry.Data.JSONDataSet(rootPath+listarEtiquetaService+"?filialId="+filialId,{subPaths:"data", useCache: false});

var dsPersons = new Spry.Data.JSONDataSet(rootPath+listarPersonaService+"?filialId="+filialId,{subPaths:"data", useCache: false});
var pvPersons = new Spry.Data.PagedView( dsPersons ,{ pageSize: 100 });
var pvPersonsInfo = pvPersons.getPagingInfo();
dsPersons.addObserver(dsPersonsObs);

var dsGrupos = new Spry.Data.JSONDataSet(rootPath+listarGruposService+"?filialId="+filialId,{subPaths:"data", useCache: false});
var dsGruposCombo = new Spry.Data.JSONDataSet(rootPath+listarGruposService+"?filialId="+filialId,{subPaths:"data", useCache: false});

var dsPersonsDetail = new Spry.Data.JSONDataSet(rootPath+obtenerPersonaService,{subPaths:"data", useCache: false});
dsPersonsDetail.addObserver(dsPersonDetailsObs);

var dsUsers = new Spry.Data.JSONDataSet(rootPath+listarUsuariosService+"?filialId="+filialId,{subPaths:"data", useCache: false});
var dsUsersCombo = new Spry.Data.JSONDataSet(rootPath+listarUsuariosService+"?filialId="+filialId,{subPaths:"data", useCache: false});

var dsNotes =  new Spry.Data.JSONDataSet(rootPath+obtenerHistorialPorPersonaService,{subPaths:"data", useCache: false});
var pvNotes = new Spry.Data.PagedView( dsNotes ,{ pageSize: 5 });
var pvNotesInfo = pvNotes.getPagingInfo();

var dsTagsPerson = new Spry.Data.JSONDataSet(rootPath+obtenerEtiquetasPersonaService,{subPaths:"data", useCache: false});
dsTagsPerson.addObserver(dsTagsPersonObs);
var arrTags=[];
//var usuarioToSet;


function getAllPersons(){
	dsPersons.setURL(rootPath+listarPersonaService+"?filialId="+filialId);
	dsPersons.loadData();
	Spry.$('frmContacto').style.display="none";
	secc="all";
}	

function getPersonsByTag(rowId){
	var row = dsTags.getRowByID(rowId);
	var nombre;
	if(rowId==-1){
			nombre="0"
		}else{
			nombre=row.data[rowId].nombre
		}
	
	dsPersons.setURL(rootPath+listarPersonaService+"?filialId="+filialId+"&etiqueta="+nombre+"&grupo="+grupo);
	dsPersons.loadData();
	Spry.$('frmContacto').style.display="none";
	secc="tag";
	filterTag = nombre;
}

function getPersonsByGroup(nombre){
	grupo=nombre;
	dsPersons.setURL(rootPath+listarPersonaService+"?filialId="+filialId+"&grupo="+nombre);
	dsPersons.loadData();
	dsTags.setURL(rootPath+listarEtiquetaService+"?filialId="+filialId+"&grupo="+nombre);
	dsTags.loadData();
	Spry.$('frmContacto').style.display="none";
	secc="group";
	filterGroup = nombre;
}

function getPersonsByUser(rowId){
	var row = dsUsers.getRowByID(rowId);
	dsPersons.setURL(rootPath+obtenerPersonasPorUsuarioService+"?filialId="+filialId+"&usuarioId="+row.data[rowId].id);
	dsPersons.loadData();
	Spry.$('frmContacto').style.display="none";
	secc="user";
	filterUser = row.data[rowId].id;
}

function reloadPersonSeccion(){
	switch(secc){
		case "all": dsPersons.setURL(rootPath+listarPersonaService+"?filialId="+filialId);
					dsPersons.loadData();
					break;

		case "tag": dsPersons.setURL(rootPath+listarPersonaService+"?filialId="+filialId+"&etiqueta="+filterTag);
					dsPersons.loadData();
					break;

		case "user": dsPersons.setURL(rootPath+obtenerPersonasPorUsuarioService+"?filialId="+filialId+"&usuarioId="+filterUser);
					 dsPersons.loadData();
					 break;

		case "group": dsPersons.setURL(rootPath+listarPersonaService+"?filialId="+filialId+"&grupo="+filterGroup);
					  dsPersons.loadData();
					  break;
	}
	if(!bUpdate)
		Spry.$('frmContacto').style.display="none";
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

function dsUserObs(nType, notifier, data){
	if(nType=="onPostLoad")	{
		
		var rows = notifier.getData(true);

		//Spry.$('nombreFilial').value = rows[0]["data.nombreFilial"] + rows[0]["data.idFilial"] + rows[0]["data.idUsuario"];;
		filialId = rows[0]["data.idFilial"];
		usuarioId = rows[0]["data.idUsuario"];

		dsTags.setURL(rootPath+listarEtiquetaService+"?filialId="+filialId+"&grupo="+grupo);
		dsTags.loadData();
		
		dsTagsSuggest.setURL(rootPath+listarEtiquetaService+"?filialId="+filialId);
		dsTagsSuggest.loadData();
		
		dsPersons.setURL(rootPath+listarPersonaService+"?filialId="+filialId);
		dsPersons.loadData();

		dsGrupos.setURL(rootPath+listarGruposService+"?filialId="+filialId);
		dsGrupos.loadData();
		
		dsGruposCombo.setURL(rootPath+listarGruposService+"?filialId="+filialId);
		dsGruposCombo.loadData();
		
		dsUsers.setURL(rootPath+listarUsuariosService+"?filialId="+filialId);
		dsUsers.loadData();
		
		dsUsersCombo.setURL(rootPath+listarUsuariosService+"?filialId="+filialId);
		dsUsersCombo.loadData();	
		
	}
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
		Spry.$('slGender').value = rows[0]["data.genero"];
		Spry.$('slGroup').value = rows[0]["data.grupo"];
	//	Spry.$('slResponsable').value = rows[0]["data.usuario"];
		Spry.$('btCrearPersona').style.display="none";
		Spry.$('frmContacto').style.display="block";
	}
}

function dsPersonsObs(nType, notifier, data){
	if(nType=="onPostLoad")	{
		var rows = notifier.getData(true);
		if(rows[0]["data.id"]==undefined){
			Spry.$('listPersons').style.display="none";
		}else{
			Spry.$('listPersons').style.display="block";
		}
	}	
}


function dsTagsPersonObs(nType, notifier, data){
	if(nType=="onPostLoad")	{
		var rows = notifier.getData(true);
		if(rows[0]["data.nombre"]==undefined){
			Spry.$('txtTags').style.display="none";
		}else{
			Spry.$('txtTags').style.display="block";	
		}
		arrTags=[];
		for (var i = 0; i < rows.length; i++){
			arrTags.push(rows[i]["data.nombre"]);
		}
		
	}
}

function clearPerson(){
	personaId = 0;
	Spry.$('txtName').value = "";
	Spry.$('txtPhone').value = "";
	Spry.$('txtAge').value = "0";
	Spry.$('txtMail').value = "";
	Spry.$('txtFacebook').value = "";
	Spry.$('txtTwitter').value = "";
	Spry.$('btCrearPersona').style.display="block";
	Spry.$('frmContacto').style.display="block";	
	Spry.$('txtTags').style.display="block";
	establecerEtiquetas();
}

function crearPersona(){
//usuarioToSet = Spry.$('slResponsable').value;
var formData=
	"nombre="+Spry.$('txtName').value+
//	"&usuarioId="+Spry.$('slResponsable').value+
	"&filialId="+filialId+
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
	var str = request.xhRequest.responseText.substr(1);
	str = str.substring(0, str.length-1);
	var myObject = eval('(' + str + ')');
	if(myObject.status){
		//clearPerson();
		reloadPersonSeccion();
		dsTags.loadData();
		dsGrupos.loadData();
		dsGruposCombo.loadData();
		dsUsers.loadData();	
		personaId=myObject.data;
		var formData =
			'usuarioId='+usuarioToSet+
			'&personaId='+personaId;
		request = Spry.Utils.loadURL('POST', rootPath+establecerUsuarioService, true, establecerUsuarioResponse, {postData: formData, headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}});	
			
	}else{
		alert(myObject.message);
	}
}

function establecerUsuarioUpdate(){
	if(personaId!=0){
		var formData =
//			'usuarioId='+Spry.$('slResponsable').value+
			'&personaId='+personaId;
		request = Spry.Utils.loadURL('POST', rootPath+establecerUsuarioService, true, establecerUsuarioResponse, {postData: formData, headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}});	
	}
}

function establecerUsuarioResponse(){
	dsUsers.loadData();
	reloadPersonSeccion();
}

function actualizarPersona(){
	if(personaId != 0){
		var formData=
		"personaId="+personaId+
		"&nombre="+Spry.$('txtName').value+
//		"&usuarioId="+Spry.$('slResponsable').value+
		"&filialId="+filialId+
		"&edad="+Spry.$('txtAge').value+
		"&correo="+Spry.$('txtMail').value+
		"&telefonos="+Spry.$('txtPhone').value+
		"&facebook="+Spry.$('txtFacebook').value+
		"&twitter="+Spry.$('txtTwitter').value+
		"&genero="+Spry.$('slGender').value+
		"&grupo="+Spry.$('slGroup').value;
		request = Spry.Utils.loadURL('POST',rootPath+actualizarPersonaService, true, actualizarPersonaResponse, {postData: formData, headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}});	
	}
}

function actualizarPersonaResponse(){
	var myObject = eval(request.xhRequest.responseText);
	//alert(request.xhRequest.responseText);
	bUpdate=true;
	reloadPersonSeccion();
	dsTags.loadData();
	dsGrupos.loadData();
	dsGruposCombo.loadData();
	dsUsers.loadData();
}

function eliminarPersona(id){
	var formData =
		'personaId='+id;
		request = Spry.Utils.loadURL('POST', rootPath+eliminarPersonaService, true, eliminarPersonaResponse, {postData: formData, headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}});	
}

function eliminarPersonaResponse(){
	reloadPersonSeccion();
	dsTags.loadData();
	dsGrupos.loadData();
	dsGruposCombo.loadData();
	dsUsers.loadData();
	clearPerson();
}

function agregarNota(){
	var formData =
		'usuarioId='+usuarioId+
		'&nota='+Spry.$('txtNota').value+
		'&personaId='+personaId;
		request = Spry.Utils.loadURL('POST', rootPath+agregarNotaService, true, agregarNotaResponse, {postData: formData, headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}});	
}

function agregarNotaResponse(){
		//var myObject = eval('(' + request.xhRequest.responseText + ')');
		//alert(request.xhRequest);
		/*if(myObject.status){
			dsMsjes.loadData();
			Spry.$('msj').value='';
		}
		Spry.$('respuestaMsj').style.display='block';
  		Spry.Utils.setInnerHTML('respuestaMsj', myObject.msg);
		setTimeout("clearResponse('respuestaMsj')",3000);*/
		Spry.$('txtNota').value = "";
		dsNotes.setURL(rootPath+obtenerHistorialPorPersonaService+"?personaId="+personaId);
		dsNotes.loadData();
}

function saveSuggestion(str){
	/*arrTags=[];
	var rows = dsTagsPerson.getData();
	for (var i = 0; i < rows.length; i++){
		arrTags.push(rows[i]["data.nombre"]);
	}
	arrTags.push(str);*/
	//establecerEtiquetas();
}

function establecerEtiquetasResponse(){
	dsTagsSuggest.loadData();
	dsTagsPerson.setURL(rootPath+obtenerEtiquetasPersonaService+"?personaId="+personaId);
	dsTagsPerson.loadData();
	dsTags.setURL(rootPath+listarEtiquetaService+"?filialId="+filialId+"&grupo="+grupo);
	dsTags.loadData();
	Spry.$('text1').value="";		
}

function borrarTag(index){
	arrTags.splice(index,index+1);
	establecerEtiquetas();
}

function establecerEtiquetas(){
	var formData=
		"personaId="+personaId+
		"&etiquetas="+arrTags.toString();
		request = Spry.Utils.loadURL('POST',rootPath+establecerEtiquetasService, true, establecerEtiquetasResponse, {postData: formData, headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}});		
}

function establecerEtiquetasClickButton(){
	arrTags=[];
	var rows = dsTagsPerson.getData();
	for (var i = 0; i < rows.length; i++){
		arrTags.push(rows[i]["data.nombre"]);
	}
	arrTags.push(Spry.$('text1').value);
	establecerEtiquetas();
	
}

function exportPersonasEtiqueta(rowId){
	var row = dsTags.getRowByID(rowId);
	var nombre;
	var params;
	
	if(rowId<0){
			nombre="0"
		}else{
			nombre=row.data[rowId].nombre
		}
	
	params = 'filialId='+filialId
	
	if(rowId==-1){
		params+='&grupo='+grupo
	}else{
		params+='&etiqueta='+nombre
	}
	
	popupWin = window.open("persona/exportarPersonasEtiqueta?"+params,
			 'open_window',
			 'location=1,status=1,scrollbars=1,width=700,height=600');
}

</script>

</head>
<body>

<div id="head" style="margin:auto; width:960px; height:60px; text-align:left; background-color:#FFFFFF">
	<img src="imgs/logoAcropolisSmall.jpg" style="margin-top:10px; margin-left:15px;"/>
    <div style="width:230px; height:20px; float:right; margin-right:15px; margin-top:7px; text-align:right" spry:region="dsUser">
    	<!--<img src="imgs/avatar.jpg" style="margin-right:10px;float:left;"/>-->
        <span style="color:#333333; font-weight:bold; font-size:18px;" >{data.usuarioNombre}</span><br/>
        <span style="color:#333333">{data.nombreFilial}</span>
    </div>
</div>
<div style="width:960px; height:25px; background-color:#FF9900; margin:auto; text-align:left">
    <div class="linkMenu" style="float:right; margin-right:10px;"><a href="logout">Salir</a></div>
    <span style="clear:both"></span>
</div>

<div id="main" style="width:940px; min-height:1050px; background-color: #ffffff; padding:10px; margin:auto;">
    <div id="menuTags" style="width:150px; float:left; border-left:solid 1px #CCC; border-right:solid 1px #CCC; text-align:left;">
    	<div style="margin-left:15px;">GRUPO:</div>
	    	<select id="slGroupFilter" class="txtFrmShort" spry:region="dsGruposCombo" onchange="getPersonsByGroup(this.value)" style="margin-left:5px;">
	    			<option value="">Cualquiera</option>
	  				<option spry:repeat="dsGruposCombo" value="{data.nombre}" >{data.nombre} ({data.numero})</option>
		    </select>

        <div style="margin-left:5px;">ETIQUETAS:</div>
        
        <div spry:region="dsTodas" style="margin-left:5px;">
    		<img src="images/print.png" style="margin-right:10px;float:left;" onclick="exportPersonasEtiqueta(-1)" /><div spry:repeat="dsTodas"class="tagMenu" spry:select="selectMenuItemClass" onclick="getAllPersons()" style="margin-left:15px;">todas las etiquetas</div>
    	</div>
        <div spry:region="dsTags" style="margin-left:5px;">
    		<div spry:repeat="dsTags" spry:select="selectMenuItemClass" class="tagMenu" onclick="getPersonsByTag({ds_RowID})">
            	<img src="images/print.png" style="margin-right:10px;float:left;" onclick="exportPersonasEtiqueta({ds_RowID})" />{data.nombre} ({data.numero})
			</div>
		</div>
		<div spry:region="dsTodas" style="margin-left:5px;">
		<img src="images/print.png" style="margin-right:10px;float:left;" onclick="exportPersonasEtiqueta(-2)" /><div spry:repeat="dsTodas" class="tagMenu" spry:select="selectMenuItemClass" onclick="getPersonsByTag(-1)" style="margin-left:15px;">sin etiqueta</div>
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
        <div id="listPersons" spry:region="pvPersons">
       	 <div spry:repeat="pvPersons" spry:select="selectPersonClass"
        	style="margin-bottom:10px; padding:5px; border-bottom: dotted 1px #CCC; cursor:pointer">
        		<img src="imgs/avatar_m.jpg" spry:if="'{data.genero}' == 'CABALLERO'"  style="margin-right:10px;float:left;" 
        		onclick="getPersonDetails({pvPersons::data.id})"/>
            	<img src="imgs/avatar_f.jpg" spry:if="'{data.genero}' != 'CABALLERO'"style="margin-right:10px;float:left;"
            	onclick="getPersonDetails({pvPersons::data.id})"/>
            	<div style="float:left;width:85%" onclick="getPersonDetails({pvPersons::data.id})">
            		<span style="font-weight:bold; font-size:14px;">{data.nombre}</span><br/>
            		{data.usuario}<br/>
                	{data.grupo}
            	</div>
            	<div style="float:left" onclick="eliminarPersona({pvPersons::data.id})">x</div>
            	<div style="clear:both"></div>
          </div>
        </div>
        <div spry:region="pvPersons dsPersons">
      		<div spry:if="'{ds_PageNumber}' != 1" onclick="pvPersons.previousPage();" style="width:15px;float:left;">&laquo; &nbsp;</div>
        	<div spry:if="'{ds_PageNumber}' == 1" style="width:15px;float:left;">&nbsp;</div>
      	</div>
      	<div spry:region="pvPersonsInfo" spry:repeatchildren="pvPersonsInfo" id="regionPersons" style="float:left; text-align:center;">
      		<a spry:if="{ds_CurrentRowNumber} != {ds_RowNumber}" href="#" onclick="pvPersons.goToPage('{ds_PageNumber}'); return false;">{ds_PageNumber}</a>
        	<span spry:if="{ds_RowCount} > 1">
        		<span spry:if="{ds_CurrentRowNumber} == {ds_RowNumber}" style="font-size:14px; font-weight:bold">{ds_PageNumber}</span>
      		</span>
      	</div>
    	<div spry:region="pvPersons dsPersons">
      		<div spry:if="'{ds_PageNumber}' != '{ds_PageCount}'" onclick="pvPersons.nextPage();" class="anchor" style="float:left;">&nbsp; &raquo;</div>
        	<div spry:if="'{ds_PageNumber}' == '{ds_PageCount}'" style="float:left;"></div>
      	</div>
      	<div style="clear:both"></div>
    </div>

    <div id="frmContacto" style="float:left; margin-left:20px; width:380px;display:none">
    	<div id="theName">
        	<span class="lbFrm">Nombre: </span>
            <input type="text" class="txtFrm" id="txtName" onchange="actualizarPersona()"
            style="border:none; border-bottom:solid 1px #CCC"/>
            <br/>
	  		<div class="textfieldRequiredMsg" style="float:right; margin-right:10px">* Escribe el nombre de la persona.</div>  
        	<div style="clear:both"></div>
        </div>
        <div id="theAge">
        	<span class="lbFrm">Edad: </span>
            <input type="text"  class="txtFrm" id="txtAge" onchange="actualizarPersona()"
            style="border:none; border-bottom:solid 1px #CCC"/>
            <br/>
	  		<div class="textfieldInvalidFormatMsg" style="float:right; margin-right:10px">* Escribe un n&uacute;mero.</div>  
        	<div style="clear:both"></div>
        </div>
        <div>
        	<span class="lbFrm">G&eacute;nero: </span>
            <select id="slGender" class="txtFrm" onchange="actualizarPersona()">
  				<option value="CABALLERO">Caballero</option>
  				<option value="DAMA">Dama</option>
			</select>
        </div>
        <div id="theEmail">
        	<span class="lbFrm">Correo Electr&oacute;nico: </span>
            <input type="text"  class="txtFrm" id="txtMail" onchange="actualizarPersona()"
            style="border:none; border-bottom:solid 1px #CCC"/>
            <br/>
	  		<div class="textfieldInvalidFormatMsg" style="float:right; margin-right:10px">* Formato de correo inv&aacute;lido.</div>  
        	<div style="clear:both"></div>
        </div>
        <div>
        	<span class="lbFrm">Telefonos: </span>
            <input type="text" class="txtFrm" id="txtPhone" onchange="actualizarPersona()"
            style="border:none; border-bottom:solid 1px #CCC"/>
         <div>
        	<span class="lbFrm">Facebook: </span>
            <input type="text"  class="txtFrm" id="txtFacebook" onchange="actualizarPersona()"
            style="border:none; border-bottom:solid 1px #CCC"/>
        </div>
        <div>
        	<span class="lbFrm">Twitter: </span>
            <input type="text"  class="txtFrm" id="txtTwitter" onchange="actualizarPersona()"
            style="border:none; border-bottom:solid 1px #CCC"/>
        </div>
        <div>
        	<span class="lbFrm">Grupo: </span>
            <select id="slGroup" class="txtFrm" spry:region="dsGruposCombo" onchange="actualizarPersona()">
  				<option spry:repeat="dsGruposCombo" value="{data.nombre}">{data.nombre}</option>
			</select>
        </div>
        <!-- 
        <div>
        	<span class="lbFrm">Responsable: </span>
             <select id="slResponsable" class="txtFrm" spry:region="dsUsersCombo" onchange="establecerUsuarioUpdate()">
  				<option spry:repeat="dsUsersCombo" value="{data.id}">{data.nombre}</option>
			</select>
		-->
        </div>
        <div style="text-align:left">
        	<input type="button" id="btCrearPersona" class="buttonSmall" value="Agregar persona" onclick="crearPersona()" style="margin-left:10px;float:right"/>
       	    <div style="clear:both"></div>
        </div>
        <div style="margin-top:10px;">
        	<span class="lbFrm" style="margin-bottom:0">Etiquetas: </span>
            <span class="txtFrm">
          	  <div id="spryautosuggest1">
  				<input type="text" name="text1" id="text1" class="txtFrm" 
  				style="border:none; border-bottom:solid 1px #CCC;margin-bottom:0"/>
				<div id="tagsSuggest" spry:region="dsTagsSuggest">
					<div spry:repeat="dsTagsSuggest" spry:suggest="{data.nombre}">
						<div>{data.nombre}</div>
					</div>
				</div>
			  </div>
            </span><br/>
            <input type="button" class="buttonSmall" value="Agregar etiqueta" style="float:right" onclick="establecerEtiquetasClickButton()"/>
        	<div style="clear:both"></div>
        </div>
        <div id="txtTags" spry:region="dsTagsPerson" style=" margin-left:10px;">
            	<div style="float:left; padding:5px; margin:5px; background-color:#CCC" 
                spry:repeat="dsTagsPerson">
                	{data.nombre}<span onclick="borrarTag({ds_RowID})" style="cursor:pointer;">&nbsp; x</span>
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
            <input type="button" class="buttonSmall" value="Agregar nota" style="margin-top:5px;float:right" onclick="agregarNota()"/>
        	<div style="clear:both"></div>
        </div>
         <div id="listNotes" spry:region="pvNotes" style="width:350px; float:left; text-align:left; margin-left:20px; margin-top:10px; padding-right:5px;">
    		<div spry:repeat="pvNotes" style="margin-bottom:10px;padding-bottom:5px; border-bottom: dotted 1px #CCC;">
        		<!--<img src="imgs/avatar.jpg" style=" width:30px; margin-right:10px;float:left;"/>
            	-->
            	<div style="float:left">
            		<span style="font-weight:bold; font-size:14px;">{data.texto}</span><br/>
             	   {data.usuario}
            	    <br/>
            	   {data.fecha}
            	</div>
            	<div style="clear:both"></div>
        	</div>
    	</div>
        <div spry:region="pvNotes dsNotes">
      		<div spry:if="'{ds_PageNumber}' != 1" onclick="pvNotes.previousPage();" style="width:15px;float:left;">&laquo; &nbsp;</div>
        	<div spry:if="'{ds_PageNumber}' == 1" style="width:15px;float:left;">&nbsp;</div>
      	</div>
      	<div spry:region="pvNotesInfo" spry:repeatchildren="pvNotesInfo" id="regionNotes" style="float:left; text-align:center;">
      		<a spry:if="{ds_CurrentRowNumber} != {ds_RowNumber}" href="#" onclick="pvNotes.goToPage('{ds_PageNumber}'); return false;">{ds_PageNumber}</a>
       		<span spry:if="{ds_RowCount} > 1">
       			<span spry:if="{ds_CurrentRowNumber} == {ds_RowNumber}" style="font-size:14px; font-weight:bold">{ds_PageNumber}</span>
      		</span>
      	</div>
    	<div spry:region="pvNotes dsNotes">
      		<div spry:if="'{ds_PageNumber}' != '{ds_PageCount}'" onclick="pvNotes.nextPage();" class="anchor" style="float:left;">&nbsp; &raquo;</div>
       		<div spry:if="'{ds_PageNumber}' == '{ds_PageCount}'" style="float:left;"></div>
      	</div>
      	<div style="clear:both"></div>
    </div>
    
    <div style="clear:both"></div>   
</div>
<script type="text/javascript">
<!--
var sprysuggest1 = new Spry.Widget.AutoSuggest("spryautosuggest1", "tagsSuggest", "dsTagsSuggest", "data.nombre");
var theName = new Spry.Widget.ValidationTextField("theName", "none", {validateOn:["blur"]});
var theAge = new Spry.Widget.ValidationTextField("theAge", "integer", {validateOn:["blur"],isRequired:false});
var theEmail = new Spry.Widget.ValidationTextField("theEmail", "email", {validateOn:["blur"],isRequired:false});
//-->
</script>

</body>
</html>