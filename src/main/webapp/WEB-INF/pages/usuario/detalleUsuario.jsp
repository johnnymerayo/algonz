<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ include file="/WEB-INF/pages/include.jsp"%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy/MM/dd" var="hoy" />


<h3>Preferencias</h3>




<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="usuario" method="POST" action="action/usuario/guardar">
	
	
	
		<!-- Muestra los mensajes de validación -->
		<jsp:include page="../include_messages.jsp"/>
		
		<form:hidden path="idUsuario" />
		<form:hidden path="pwdSistema" />
		<form:hidden path="enabled" />
	
	
	
<fieldset>
	<legend>Datos de usuario</legend>


<div class="row">
	<t:input path="idSistema" label="Username" required="true" maxlength="10" tabindex="1" gridClass="col-lg-3"/>
</div>

<div class="row">
	<t:input path="teNombre" label="Nombre" required="false" maxlength="100" tabindex="2" gridClass="col-lg-4"/>
	<t:input path="teApellido1" label="Primer apellido" required="false" maxlength="100" tabindex="3" gridClass="col-lg-4"/>
	<t:input path="teApellido2" label="Segundo apellido" required="false" maxlength="100" tabindex="4" gridClass="col-lg-4"/>
</div>



<c:if test="${not empty usuario.idUsuario}">
<div class="panel-group" id="accordion">

  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapsePassword">
          Cambiar password        </a>
      </h4>
    </div>
    
    <div id="collapsePassword" class="panel-collapse collapse ${colapsar?'':'in' }">
      <div class="panel-body">
      
		<div class="row">
			<t:input path="pwdSistemaOld" label="Password actual" required="false" maxlength="50" tabindex="5" gridClass="col-lg-3" type="password"/>
		</div>
		
		<div class="row">
			<t:input path="pwdSistemaNew" label="Nuevo Password" required="false" maxlength="50" tabindex="6" gridClass="col-lg-3" type="password"/>
			<t:input path="pwdSistemaConfirm" label="Confirmar Password" required="false" maxlength="50" tabindex="7" gridClass="col-lg-3" type="password"/>
		</div>
		
		
      </div>
    </div>
  </div>
  
</div>

</c:if>

	<div>&nbsp;</div>

		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>		
			</div>
		</div>
		


</fieldset>


</form:form>

