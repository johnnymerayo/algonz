<%@ include file="/WEB-INF/pages/include.jsp"%>


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="comunidad" method="POST" action="action/comunidades/guardar">
	<fieldset>
		<legend>
			<c:if test='${comunidad.cnComunidad==null}'>Nueva Comunidad</c:if>
			<c:if test='${comunidad.cnComunidad!=null}'>Datos de la comunidad: ${comunidad.teNombre}
			</c:if>
		</legend>
		<form:hidden path="cnComunidad" />

		<div style="width: 100%; float: left">
		
		

    
			<t:input path="caCif" label="CIF" required="true" tabindex="1"/>
			<t:input path="teNombre" label="Nombre" required="true" tabindex="2"/>
			<t:input path="teCp" label="Código postal" required="false" tabindex="3"/>
			<t:area path="teObservaciones" label="Observaciones" required="false" tabindex="4"/>
    
    
		</div>
		
	</fieldset>
	
	
	<fieldset>
		<legend>
		Portales
		</legend>
		
		<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
		
		</fieldset>
	
		
	<fieldset>
		<legend>
		Representantes
		</legend>
		
		<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
		
		</fieldset>
		
			
	<fieldset>
		<legend>
		Empresas
		</legend>
		
		<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
		
		</fieldset>
	
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
				<button type="button" class="btn"
					onclick="changeAction('mainForm','action/comunidades/listado')">Cancelar</button>
			</div>
		</div>
</form:form>



