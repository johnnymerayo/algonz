<%@ include file="/WEB-INF/pages/include.jsp"%>


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="comunidad" method="POST" action="action/comunidades/guardar">
	<fieldset>
		<legend>
			<c:if test='${comunidad.cnComunidad==null}'>Nueva Comunidad</c:if>
			<c:if test='${comunidad.cnComunidad!=null}'>Datos de la comunidad: ${comunidad.teNombre}
			</c:if>
		</legend>
		
		<!-- Muestra los mensajes de validación -->
		<jsp:include page="../include_messages.jsp"/>
		
		<form:hidden path="cnComunidad" />



<div class="row">
<t:input path="caCif" label="CIF" required="true" maxlength="10" tabindex="1" gridClass="col-lg-3"/>
<t:input path="teNombre" label="Nombre" required="true" maxlength="100" tabindex="2" gridClass="col-lg-6"/>
<t:input path="teCp" label="Código postal" required="false" maxlength="5" tabindex="3" gridClass="col-lg-3"/>
</div>

<div class="row">
<t:area path="teObservaciones" label="Observaciones" required="false" tabindex="4" gridClass="col-lg-12"/>
</div>

		
	</fieldset>
	
	
	<div>&nbsp;</div>

		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
				<button type="button" class="btn btn-default"
					onclick="changeAction('mainForm','action/comunidades/listado')">Cancelar</button>
			</div>
		</div>
		
		
<div>&nbsp;</div>
		
	<c:if test="${not empty comunidad.cnComunidad }">	
	
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
							<i class="glyphicon glyphicon-edit"></i></a> &nbsp;
						<a data-toggle="modal" href="#modalDelete" class="delete_row" data-id="action/portales/eliminar?id=${portal.cnPortal }">
							<i class="glyphicon glyphicon-remove"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>


<div>
	<a href="action/portales/nuevoPortal?codComunidad=${comunidad.cnComunidad }" class="btn btn-primary"> <i class="glyphicon glyphicon-plus glyphicon-white"></i> <span>Nuevo portal</span></a>
</div>

<div>&nbsp;</div>

		
		</fieldset>
	
		
	<fieldset>
		<legend>
		Representantes
		</legend>
		
<div>&nbsp;</div>
		
<c:if test="${comunidad.representantes != null && empty comunidad.representantes}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty comunidad.representantes}">

	<table id="tablaPaginada2" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Tipo</th>
				<th>Nombre</th>
				<th>Teléfono</th>
				<th>Email</th>
				<th>Calle</th>	
				<th>Portal</th>	
				<th>Planta</th>	
				<th>Piso</th>
				<!-- <th>Acciones</th> -->	
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${comunidad.representantes}" var="predio" varStatus="status">
				<tr>
					<td>${predio.tipoRepresentante.teTipoRepresentante}</td>
					<td>${predio.terceroByCnPropietario.nombreCompleto}</td>
					<td>
						${predio.terceroByCnPropietario.teTlfFijo}
						<c:if test="${not empty predio.terceroByCnPropietario.teTlfFijo and not empty predio.terceroByCnPropietario.teTlfMovil1}">/</c:if>
						${predio.terceroByCnPropietario.teTlfMovil1}
						<c:if test="${not empty predio.terceroByCnPropietario.teTlfMovil1 and not empty predio.terceroByCnPropietario.teTlfMovil2}">/</c:if>						
						${predio.terceroByCnPropietario.teTlfMovil2}
						
					</td>
					<td>${predio.terceroByCnPropietario.teEmail}</td>
					<!-- <td><a href="mailto:${predio.terceroByCnPropietario.teEmail}">${predio.terceroByCnPropietario.teEmail}</a></td> -->
					<td>${predio.portal.teCalle}</td>
					<td>${predio.portal.teNombre}</td>
					<td>${predio.planta.tePlanta}</td>
					<td>${predio.tePredio}</td>
					<!-- <td>
						<a href="action/predios/editar?id=${predio.cnPredio }">
							<i class="glyphicon glyphicon-edit"></i></a>
					</td> -->	
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
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

	<table id="tablaPaginada3" class="table table-striped table-bordered">
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
					<!--<td><a href="mailto:${empresaComunidad.empresa.teEmail}">${empresaComunidad.empresa.teEmail}</a></td>-->
					<td><fmt:formatDate value="${empresaComunidad.feInicio}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${empresaComunidad.feFin}" pattern="dd/MM/yyyy"/></td>
					<td>
						<a href="action/empresasComunidad/editar?id=${empresaComunidad.cnEmpresaComunidad }">
							<i class="glyphicon glyphicon-edit"></i></a> &nbsp;
						<a data-toggle="modal" href="#modalDelete" class="delete_row" data-id="action/empresasComunidad/eliminar?id=${empresaComunidad.cnEmpresaComunidad }">
							<i class="glyphicon glyphicon-remove"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
<div>&nbsp;</div>

		
<div>
	<a href="action/empresasComunidad/nuevaEmpresaComunidad?codComunidad=${comunidad.cnComunidad }" class="btn btn-primary"> <i class="glyphicon glyphicon-plus glyphicon-white"></i> <span>Añadir empresa</span></a>
</div>


<div>&nbsp;</div>
	
		</fieldset>
		
			
	<fieldset>
		<legend>
		Documentos
		</legend>
		
		
<div>&nbsp;</div>



<c:if test="${comunidad.documentos != null && empty comunidad.documentos}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty comunidad.documentos}">

	
	<table id="tablaPaginada4" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Fecha</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${comunidad.documentos}" var="documento" varStatus="status">
				<tr>
				
					<td>${documento.teNombre}</td>
					<td><fmt:formatDate value="${documento.feGuardado}" pattern="dd/MM/yyyy"/></td>
					<td>
						<a href="action/comunidades/downloadDocument?id=${documento.cnDocumento }">
							<i class="glyphicon glyphicon-download"></i></a> &nbsp;
						<a data-toggle="modal" href="#modalDelete" class="delete_row" data-id="action/comunidades/deleteDocument?codComunidad=${comunidad.cnComunidad }&amp;id=${documento.cnDocumento }">
							<i class="glyphicon glyphicon-remove"></i></a>
							
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
<div>&nbsp;</div>

    <div id="progress" class="progress"  style="display:none">
        <div class="progress-bar progress-bar-success"></div>
    </div>
    
<div>&nbsp;</div>


<div>

<span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus glyphicon-white"></i>
                    <span>Añadir documento</span>
                   <input id="fileupload" type="file" name="files[]" data-url="action/comunidades/uploadDocument?codComunidad=${comunidad.cnComunidad }" multiple>
                </span>
	</div>
	
 	
		</fieldset>
</c:if>	

</form:form>

<div>&nbsp;</div>


<div>&nbsp;</div>




