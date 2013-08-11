<%@ include file="/WEB-INF/pages/include.jsp"%>

<h3>Siniestros</h3>


		<!-- Muestra los mensajes de validación -->
		<jsp:include page="../include_messages.jsp"/>

<c:if test="${listaSiniestros != null && empty listaSiniestros}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty listaSiniestros}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Comunidad</th>
				<th>Portal</th>
				<th>Tipo</th>
				<th>Empresa</th>
				<th>Descripción</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaSiniestros}" var="siniestro" varStatus="status">
				<tr>
					<td>${siniestro.portal.comunidad.teNombre}</td>
					<td>${siniestro.portal.teNombre}</td>
					<td>${siniestro.empresaComunidad.empresa.tipoEmpresa.teTipoEmpresa}</td>
					<td>${siniestro.empresaComunidad.empresa.teNombre}</td>
					<td>${siniestro.teNombre}</td>
					<td>
						<a href="action/siniestros/editar?id=${siniestro.cnSiniestro }">
							<i class="glyphicon glyphicon-edit"></i></a> &nbsp;
						<a href="action/siniestros/eliminar?id=${siniestro.cnSiniestro }">
							<i class="glyphicon glyphicon-remove"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
<div>&nbsp;</div>
<!-- 
<div>
	<a href="action/siniestros/nuevo" class="btn btn-primary">Nuevo siniestro</a>
</div>
 -->
