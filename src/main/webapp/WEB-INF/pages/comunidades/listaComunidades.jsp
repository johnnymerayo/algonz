<%@ include file="/WEB-INF/pages/include.jsp"%>

<h3>Comunidades</h3>

		<!-- Muestra los mensajes de validación -->
		<jsp:include page="../include_messages.jsp"/>

<c:if test="${listaComunidades != null && empty listaComunidades}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty listaComunidades}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>CIF</th>
				<th>Nombre</th>
				<th>Código postal</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaComunidades}" var="comunidad" varStatus="status">
				<tr>
				
					<td>${comunidad.caCif}</td>
					<td>${comunidad.teNombre}</td>
					<td>${comunidad.teCp}</td>
					<td>
						<a href="action/comunidades/editar?id=${comunidad.cnComunidad }">
							<i class="icon-edit"></i></a> &nbsp;
						<a href="action/comunidades/eliminar?id=${comunidad.cnComunidad }">
							<i class="icon-remove"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
<div>&nbsp;</div>
<div>
	<a href="action/comunidades/nuevo" class="btn btn-primary">Nueva comunidad</a>
</div>