<%@ include file="/WEB-INF/pages/include.jsp"%>


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="empresaComunidad" method="POST" action="action/empresasComunidad/guardar">
	<fieldset>
		<legend>
			Comunidad: ${empresaComunidad.comunidad.teNombre} 
			<br />
			<c:if test='${empresaComunidad.cnEmpresaComunidad==null}'>Nueva EmpresaComunidad</c:if>
			<c:if test='${empresaComunidad.cnEmpresaComunidad!=null}'>Datos EmpresaComunidad
			</c:if>
		</legend>
		
		
		<!-- Muestra los mensajes de validaci�n -->
		<jsp:include page="../include_messages.jsp"/>
		
		<form:hidden path="cnEmpresaComunidad" />
		<form:hidden path="comunidad.cnComunidad" />

		<div class="row">
		
		<t:select  id="tipoEmpresa" search="true"  itemLabel="teTipoEmpresa" itemValue="cnTipoEmpresa" items="${tiposEmpresaCombo}" path="empresa.tipoEmpresa.cnTipoEmpresa" required="true" label="Tipo Empresa" emptyOption="true" tabindex="1" gridClass="col-lg-4 " onchange="cargarEmpresas(this.value)"/>	
		<t:select  id="empresa" search="false" itemLabel="value" itemValue="id" items="${empresasCombo}" path="empresa.cnEmpresa" required="true" label="Empresa" emptyOption="true" tabindex="2" gridClass="col-lg-4"/>
		</div>	
    		
    		<div class="row">
			<t:input path="feInicio" label="Fecha inicio" required="true" tabindex="3" maxlength="12" date="true" gridClass="col-lg-4"/>
			<t:input path="feFin" label="Fecha fin" required="true" tabindex="4" maxlength="12" date="true" gridClass="col-lg-4"/>
			</div>
			
			<div class="row">
			<t:area path="teObservaciones" label="Observaciones" cols="500" tabindex="9" gridClass="col-lg-12"/>
		</div>
	</fieldset>
	
	
<div>&nbsp;</div>

	
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
				<!-- <button type="button" class="btn btn-default"
					onclick="changeAction('mainForm','action/empresaComunidads/listado')">Cancelar</button> -->
					
				<a href="action/comunidades/editar?id=${empresaComunidad.comunidad.cnComunidad}" class="btn btn-default">Cancelar</a>
			</div>
		</div>
		
		
	
<div>&nbsp;</div>	
		
	<c:if test="${not empty empresaComunidad.cnEmpresaComunidad }">	
	
	
	<fieldset>
		<legend>
			Listado de avisos
		</legend>
		
			
<div>&nbsp;</div>
		
<c:if test="${empresaComunidad.avisosEmpresa != null && empty empresaComunidad.avisosEmpresa}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty empresaComunidad.avisosEmpresa}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th class="dateColumn">Fecha Inicio</th>
				<th class="dateColumn">Fecha Notificaci�n</th>
				<th class="dateColumn">Fecha Vencimiento</th>
				<th class="dateColumn">Fecha Cierre</th>
				<th>Estado </th>
				<th>Descripci�n</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${empresaComunidad.avisosEmpresa}" var="aviso" varStatus="status">
				<tr>
				
					<td><fmt:formatDate value="${aviso.feInicio}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${aviso.feLimite}" pattern="dd/MM/yyyy" /></td>
					<td><fmt:formatDate value="${aviso.feVencimiento}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${aviso.feCierre}" pattern="dd/MM/yyyy"/></td>
					<td>
						${aviso.estado.teEstado}				
					</td>
					<td>${aviso.teDescripcion}</td>
					<td>
						<a href="action/avisosEmpresa/editar?id=${aviso.cnAvisoEmpresa }">
							<i class="glyphicon glyphicon-edit"  title="Consultar"></i></a> &nbsp;
						<a data-toggle="modal" href="#modalDelete" class="delete_row" data-id="action/avisosEmpresa/eliminar?id=${aviso.cnAvisoEmpresa }">
							<i class="glyphicon glyphicon-remove"  title="Eliminar"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
<div>&nbsp;</div>

<div>
	<a href="action/avisosEmpresa/nuevo?codEmpresaComunidad=${empresaComunidad.cnEmpresaComunidad}" class="btn btn-primary"> <i class="glyphicon glyphicon-plus glyphicon-white"></i> <span>Nuevo aviso</span></a>
</div>
		
		</fieldset>
	</c:if>
	
</form:form>

<script type="text/javascript">

function cargarEmpresas(idTipoEmpresa) {
	jQuery.ajax({
		type : "POST",
		url : "/algonz/action/empresasComunidad/cargarEmpresas",
		data : "idTipoEmpresa=" + idTipoEmpresa,
		success : function(response) {
			while (document.getElementById("empresa").options.length > 0) {
				document.getElementById("empresa").options.remove(0);
			}


			$("#empresa").select2("val", "Seleccionar..."); 
			document.getElementById("empresa").options.add(new Option("Seleccionar...",
					""))
			for ( var i = 0; i < response.length; i++) {
				var d = response[i];
				document.getElementById("empresa").options.add(new Option(d.value, d.id))
			}

		}
	});
}

</script>



