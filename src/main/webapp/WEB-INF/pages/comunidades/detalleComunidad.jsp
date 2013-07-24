<%@ include file="/WEB-INF/pages/include.jsp"%>


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="comunidad" method="POST" action="action/comunidades/guardar">
	<fieldset>
		<legend>
			<c:if test='${comunidad.cnComunidad==null}'>Nueva Comunidad</c:if>
			<c:if test='${comunidad.cnComunidad!=null}'>Datos de la comunidad: ${comunidad.teNombre}
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
		
<div>&nbsp;</div>
		


<c:if test="${comunidad.portals != null && empty comunidad.portals}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty comunidad.portals}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Calle</th>
				<th>Número</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${comunidad.portals}" var="portal" varStatus="status">
				<tr>
					<td>${portal.teCalle}</td>
					<td>${portal.teNombre}</td>
					<td>
						<a href="action/portales/editar?id=${portal.cnPortal }">
							<i class="icon-edit"></i></a> &nbsp;
						<a href="action/portales/eliminar?id=${portal.cnPortal }">
							<i class="icon-remove"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>


<div>
	<a href="action/portales/nuevoPortal?codComunidad=${comunidad.cnComunidad }" class="btn btn-primary">Nuevo portal</a>
</div>

<div>&nbsp;</div>

		
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



