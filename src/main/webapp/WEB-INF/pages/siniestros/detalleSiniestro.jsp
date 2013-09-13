<%@ include file="/WEB-INF/pages/include.jsp"%>


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="siniestro" method="POST" action="action/siniestros/guardar">
	<fieldset>
		<legend>
			Comunidad: ${siniestro.portal.comunidad.teNombre} 
			<br />
			Portal nº:	<c:if test='${siniestro.portal.teNombre!=null}'> ${siniestro.portal.teNombre}</c:if>
			<br />
			<c:if test='${siniestro.cnSiniestro==null}'>Nuevo Siniestro</c:if>
			<c:if test='${siniestro.cnSiniestro!=null}'>Datos Siniestro: ${siniestro.teNombre}
			</c:if>
		</legend>
		
		<!-- Muestra los mensajes de validación -->
		<jsp:include page="../include_messages.jsp"/>
		
		<form:hidden path="cnSiniestro" />
		<form:hidden path="portal.cnPortal" />

		<div class="row">

    
    	<t:select  id="tipoEmpresa" search="true"  gridClass="col-lg-4" itemLabel="teTipoEmpresa" itemValue="cnTipoEmpresa" items="${tiposEmpresaCombo}" path="cnTipoSiniestro" required="true" label="Tipo siniestro" emptyOption="true" tabindex="1" onchange="cargarEmpresasComunidad(${siniestro.portal.comunidad.cnComunidad },this.value)"/>
			
		<t:select  id="empresaComunidad" search="false" gridClass="col-lg-6" itemLabel="value" itemValue="id" items="${empresasComunidadCombo}" path="empresaComunidad.cnEmpresaComunidad" required="true" label="Empresa" emptyOption="true" tabindex="2"/>

		</div>

		<div class="row">
	
		<t:input gridClass="col-lg-12" path="teNombre" label="Descripción" required="true" maxlength="100" tabindex="3"/>
		</div>
		

<div class="row">
			<t:area gridClass="col-lg-12" path="teObservaciones" label="Observaciones" cols="500" tabindex="9"/>
		</div>
	</fieldset>
	
	
<div>&nbsp;</div>

	
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
				<a href="action/portales/editar?id=${siniestro.portal.cnPortal}" class="btn btn-default">Cancelar</a>
			
			</div>
		</div>
	
<div>&nbsp;</div>

	<c:if test="${not empty siniestro.cnSiniestro }">	
	
	
	<fieldset>
		<legend>
			Listado de actuaciones
		</legend>
		
			
<div>&nbsp;</div>
		
<c:if test="${siniestro.actuaciones != null && empty siniestro.actuaciones}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty siniestro.actuaciones}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Fecha Inicio</th>
				<th>Fecha Vencimiento</th>
				<th>Fecha Cierre</th>
				<th>Estado </th>
				<th>Descripción</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${siniestro.actuaciones}" var="actuacion" varStatus="status">
				<tr>
				
					<td><fmt:formatDate value="${actuacion.feInicio}" pattern="dd/MM/yyyy"/></td>			
					<td><fmt:formatDate value="${actuacion.feInicio}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${actuacion.feCierre}" pattern="dd/MM/yyyy"/></td>
					<td>
						${actuacion.estado.teEstado}				
					</td>
					<td>${actuacion.teDescripcion}</td>
					<td>
						<a href="action/actuaciones/editar?id=${actuacion.cnActuacion }">
							<i class="glyphicon glyphicon-edit"></i></a> &nbsp;
						<a data-toggle="modal" href="#modalDelete" class="delete_row" data-id="action/actuaciones/eliminar?id=${actuacion.cnActuacion }">
							<i class="glyphicon glyphicon-remove"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
<div>&nbsp;</div>

<div>
	<a href="action/actuaciones/nuevo?codSiniestro=${siniestro.cnSiniestro}" class="btn btn-primary"> <i class="glyphicon glyphicon-plus glyphicon-white"></i> <span>Nueva actuación</span></a>
</div>
		
		</fieldset>
	</c:if>
	
</form:form>



<script type="text/javascript">

function cargarEmpresasComunidad(codComunidad, idTipoEmpresa) {
	jQuery.ajax({
		type : "POST",
		url : "/algonz/action/siniestros/cargarEmpresasComunidad",
		data : {"codComunidad":codComunidad, "idTipoEmpresa":idTipoEmpresa},
		success : function(response) {
			while (document.getElementById("empresaComunidad").options.length > 0) {
				document.getElementById("empresaComunidad").options.remove(0);
			}

			$("#empresaComunidad").select2("val", "Seleccionar..."); 

			document.getElementById("empresaComunidad").options.add(new Option("Seleccionar...",
					""))

			for ( var i = 0; i < response.length; i++) {
				var d = response[i];
				document.getElementById("empresaComunidad").options.add(new Option(d.value, d.id))
			}

		}
	});
}

</script>




