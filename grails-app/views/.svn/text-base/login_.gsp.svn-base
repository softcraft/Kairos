<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="favicon_0.png" type="image/x-icon" />
<title>K4iros - Sistema de Contactos</title>
<script type="text/javascript" src="js/xpath_mini.js"></script>
<script type="text/javascript" src="js/SpryData.js"></script>
<script type="text/javascript" src="js/SpryJSONDataSet_mini.js"></script>
<script type="text/javascript" src="js/SpryUtils_mini.js"></script>
<script type="text/javascript" src="js/SpryValidationTextField_mini.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script>

	function updateResponseLogin(req) {
		var myObject = eval('(' + req.xhRequest.responseText + ')');
		if(myObject.status){
			window.location = "home.gsp";
			/*switch(myObject.msg){
				case "A":		window.location = "inmueble.php";
										break;
				
				case "R":		window.location = "inmueble.php";
										break;
										
				case "S":		window.location = "supervision.php";
										break;
										
				case "D":		window.location = "ver_inmueble.php";
										break;
						
			}*/
				
		}else{
			Spry.$('respuestaLogin').style.display='block';
			Spry.Utils.setInnerHTML('respuestaLogin', myObject.msg);
			setTimeout("clearResponse('respuestaLogin')",3000);
		}
		Spry.$('frmLogin').reset();
	}
	
	function updateResponseRecovery(req) {
		var myObject = eval('(' + req.xhRequest.responseText + ')');
		Spry.$('respuestaLogin').style.display='block';
		Spry.Utils.setInnerHTML('respuestaLogin', myObject.msg);
		setTimeout("clearResponse('respuestaLogin')",3000);
		Spry.$('frmRecuperar').reset();
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

</script>

<link rel="stylesheet" type="text/css" href="css/SpryValidationTextField.css"/>
<link rel="stylesheet" type="text/css" href="css/styles.css"/>
</head>

<body style="background-color:#004f25; margin:auto;">
  
	<div id="head" style="background:url(images/pestanalogin.png) no-repeat; margin:auto; margin-top:40px; width:704px; height:97px; text-align:left;">
		
	</div>
	<div class="main">
  	<div class="windowLog" style="width:610px; height:100%; background-color: #ffffff;margin:auto;">
    	<img src="imgs/logoAcropolis.jpg" style="margin-top:25px;"/>
		<div id="respuestaLogin" style="padding-bottom:10px;"></div>
		<!--<form  id="frmLogin" action="p_json.php/clientes.Clientes.login/" method="post" onsubmit="return validateSubmit(this, updateResponseLogin)" style="padding:30px; margin:auto; width:550px;">-->
        <form  id="frmLogin" method="post" onsubmit="return validateSubmit(this, updateResponseLogin)" style="padding:30px; margin:auto; width:550px;">
        
    	<fieldset class="fld">
  		<table width="100%" border="0" cellspacing="5" cellpadding="10" id="formregion">
          <tr>
            <td>&nbsp;</td>
            <td colspan="3" align="left">&nbsp;</td>
          </tr>
          <tr id="theEmail">
    		<td><label for="email" class="lb">Usuario:</label></td>
      		<td colspan="3" align="left">
            	<input type="type" name="email" id="email" style="width:350px;" class="input" value="test@mail.com" />
              	<br/>
	  		    <div class="textfieldRequiredMsg">* Escribe tu email.</div>
              	<div class="textfieldInvalidFormatMsg">* Formato inv&aacute;lido.</div>            
            </td>      
	      </tr>
          <tr id="theContrasena">
    		 <td><label for="passwd" class="lb">Contrase&ntilde;a:</label></td>
      		 <td colspan="3" align="left">
		       <input type="password" name="passwd" id="passwd" style="width:350px;" class="input" value="12345678" />
               <br/>
	  		   <div class="textfieldRequiredMsg">* Escribe tu contrase&ntilde;a.</div>            
             </td>      
	  	   </tr>
  		   <tr>
           	 <td>&nbsp;</td>
		     <td colspan="3" align="left"><input type="button"  name="xbtnAgregar" id="xbtnAgregar" value="Ingresar" class="button" onclick="window.location = 'home.gsp'"/></td>
	  	   </tr>
         </table>
         </fieldset>
       </form>
       <form style="display:none;" id="frmRecuperar" action="p_json.php/usuario.recovery/" method="post" onsubmit="return validateSubmit(this, updateResponseRecovery)">
    	<fieldset>
      	<legend>Reestablecer Contrase&ntilde;a</legend>
         <table width="100%" border="0" cellspacing="5" cellpadding="0" id="formregion">
           <tr>
		     <td colspan="2"><a href="#" onclick="showRecuperar()">Recuperar contraseña</a></td>
	  	   </tr>
           <tr id="theEmail2">
		      <td><label for="email" class="lb">E-mail</label></td>
      		  <td colspan="2" align="left">
            	<input type="type" name="email" id="email" style="width:350px;" class="input" />
              	<br/>
	  		    <div class="textfieldRequiredMsg">* Escribe tu email.</div>
              	<div class="textfieldInvalidFormatMsg">* Formato inv&aacute;lido.</div>            
             </td> 
             <td>
             	<input type="submit"  name="btRecuperar" id="btRecuperar" value="Reestablecer" class="button" />
             </td> 
	  	   </tr>
		 </table>
         </fieldset>
		</form>
   	</div>
   	<div class="clearer"></div>
  </div>
  <script type="text/javascript">
		var theEmail = new Spry.Widget.ValidationTextField("theEmail", "email", {validateOn:["blur"]});
		var theContrasena = new Spry.Widget.ValidationTextField("theContrasena", "none", {validateOn:["blur"]});
		var theEmail2 = new Spry.Widget.ValidationTextField("theEmail2", "email", {validateOn:["blur"]});
  </script>
</body>
</html>