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
		
		
<div>&nbsp;</div>
		
<c:if test="${comunidad.empresasComunidad != null && empty comunidad.empresasComunidad}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty comunidad.empresasComunidad}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Tipo</th>
				<th>CIF</th>
				<th>Nombre</th>
				<th>Teléfonos</th>
				<th>E-mail</th>
				<th>Fecha inicio</th>
				<th>Fecha fin</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${comunidad.empresasComunidad}" var="empresaComunidad" varStatus="status">
				<tr>
				
					<td>${empresaComunidad.empresa.tipoEmpresa.teTipoEmpresa}</td>
					<td>${empresaComunidad.empresa.caCif}</td>
					<td>${empresaComunidad.empresa.teNombre}</td>
					<td>
						${empresaComunidad.empresa.teTlfFijo}
						<c:if test="${not empty empresaComunidad.empresa.teTlfFijo and not empty empresaComunidad.empresa.teTlfMovil1}">/</c:if>
						${empresaComunidad.empresa.teTlfMovil1}
						<c:if test="${not empty empresaComunidad.empresa.teTlfMovil1 and not empty empresaComunidad.empresa.teTlfMovil2}">/</c:if>						
						${empresaComunidad.empresa.teTlfMovil2}
						
					</td>
					<td>${empresaComunidad.empresa.teEmail}</td>
					<td><fmt:formatDate value="${empresaComunidad.feInicio}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${empresaComunidad.feFin}" pattern="dd/MM/yyyy"/></td>
					<td>
						<a href="action/empresasComunidad/editar?id=${empresaComunidad.cnEmpresaComunidad }">
							<i class="icon-edit"></i></a> &nbsp;
						<a href="action/empresasComunidad/eliminar?id=${empresaComunidad.cnEmpresaComunidad }">
							<i class="icon-remove"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
<div>&nbsp;</div>

		
<div>
	<a href="action/empresasComunidad/nuevaEmpresaComunidad?codComunidad=${comunidad.cnComunidad }" class="btn btn-primary">Añadir empresa</a>
</div>

<div>&nbsp;</div>

		</fieldset>
	
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
				<button type="button" class="btn"
					onclick="changeAction('mainForm','action/comunidades/listado')">Cancelar</button>
			</div>
		</div>
</form:form>



