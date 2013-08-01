<%@ include file="/WEB-INF/pages/include.jsp"%>


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="avisoEmpresa" method="POST" action="action/avisosEmpresa/guardar">
	<fieldset>
		<legend>
			Comunidad: ${avisoEmpresa.empresaComunidad.comunidad.teNombre} 
			<br />
			<c:if test='${avisoEmpresa.cnAvisoEmpresa==null}'>Nuevo aviso</c:if>
			<c:if test='${avisoEmpresa.cnAvisoEmpresa!=null}'>Datos del aviso
			</c:if>
		</legend>
		
				
<c:if test="${not empty message }">
	<div class="text-success">${message}</div>
	<div>&nbsp;</div>
</c:if>
<c:if test="${not empty error }">
	<div class="text-error"><h5>${error }</h5></div>
	<div>&nbsp;</div>
</c:if>
		
		<form:hidden path="cnAvisoEmpresa" />
		<form:hidden path="empresaComunidad.cnEmpresaComunidad" />



		<div style="width: 100%; float: left">
		
   		
			
<t:select cssClass="col-lg-4" itemLabel="teEstado" itemValue="cnEstado" items="${estadosCombo}" path="estado.cnEstado" required="true" label="Estado" tabindex="1"/>


			<t:input cssClass="col-lg-4" path="feInicio" label="Fecha inicio" required="true" tabindex="2" date="true"/>
			<t:input cssClass="col-lg-4" path="feVencimiento" label="Fecha vencimiento" required="true" tabindex="3" date="true"/>
			<t:input cssClass="col-lg-4" path="feCierre" label="Fecha cierre" required="false" tabindex="4" date="true"/>			
			
   
			<t:input path="teDescripcion" label="Descripci�n" required="true" tabindex="5"/>
			<t:area path="teObservaciones" label="Observaciones" required="false" tabindex="6"/>
    
    
		</div>
		
	</fieldset>
	
	
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
					<a href="action/empresasComunidad/editar?id=${avisoEmpresa.empresaComunidad.cnEmpresaComunidad}" class="btn">Cancelar</a>
			</div>
		</div>
</form:form>



