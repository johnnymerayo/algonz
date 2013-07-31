<%@ include file="/WEB-INF/pages/include.jsp"%>


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="actuacion" method="POST" action="action/actuaciones/guardar">
	<fieldset>
		<legend>
			Comunidad: ${actuacion.siniestro.portal.comunidad.teNombre} 
			<br />
			Portal nº:	<c:if test='${actuacion.siniestro.portal.teNombre!=null}'> ${actuacion.siniestro.portal.teNombre}</c:if>
			<br />
			Siniestro: ${actuacion.siniestro.teNombre} 
			<br />
			<c:if test='${actuacion.cnActuacion==null}'>Nueva Actuacion</c:if>
			<c:if test='${actuacion.cnActuacion!=null}'>Datos de la actuacion: ${actuacion.teDescripcion}
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
		
		<form:hidden path="cnActuacion" />
		<form:hidden path="siniestro.cnSiniestro" />

<div class="row">

<t:select cssClass="col-lg-4" itemLabel="teEstado" itemValue="cnEstado" items="${estadosCombo}" path="estado.cnEstado" required="true" label="Estado" tabindex="1"/>
				
</div>



<div class="row">

			<t:input cssClass="col-lg-4" path="feInicio" label="Fecha inicio" required="true" tabindex="2" date="true"/>
			<t:input cssClass="col-lg-4" path="feVencimiento" label="Fecha vencimiento" required="true" tabindex="3" date="true"/>
			<t:input cssClass="col-lg-4" path="feCierre" label="Fecha cierre" required="false" tabindex="4" date="true"/>			
</div>


		<div style="width: 100%; float: left">
		
   		
			
    
			<t:input path="teDescripcion" label="Descripción" required="true" tabindex="5"/>
			<t:area path="teObservaciones" label="Observaciones" required="false" tabindex="6"/>
    
    
		</div>
		
	</fieldset>
	
	
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
					<a href="action/siniestros/editar?id=${actuacion.siniestro.cnSiniestro}" class="btn">Cancelar</a>
			</div>
		</div>
</form:form>



