<%@ include file="/WEB-INF/pages/include.jsp"%>

<h3>Consignatarios</h3>

<c:if test="${not empty message }">
	<div class="text-success">${message}</div>
	<div>&nbsp;</div>
</c:if>
<c:if test="${not empty error }">
	<div class="text-error"><h5>${error }</h5></div>
	<div>&nbsp;</div>
</c:if>


<c:if test="${listaConsignatarios != null && empty listaConsignatarios}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty listaConsignatarios}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Poblacion</th>
				<th>Pais</th>
				<th>E-Mail</th>
				<th>Teléfono</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaConsignatarios}" var="consignatario" varStatus="status">
				<tr>
					<td>${consignatario.nomFiscal}</td>
					<td>${consignatario.poblacion}</td>
					<td>${consignatario.pais}</td>
					<td>${consignatario.eMail}</td>
					<td>
						${consignatario.teleflin1}
						<c:if test="${not empty consignatario.teleflin1 and not empty consignatario.telefMovil}">/</c:if>
						${consignatario.telefMovil}
					</td>
					<td>
						<a href="action/consignatarios/editar?codEmpresa=${consignatario.codEmpresa }&codigo=${consignatario.codigo}">
							<i class="glyphicon glyphicon-edit"></i></a> &nbsp;
						<a data-toggle="modal" href="#modalDelete" class="delete_row" data-id="action/consignatarios/eliminar?codEmpresa=${consignatario.codEmpresa }&codigo=${consignatario.codigo}">
							<i class="glyphicon glyphicon-remove"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
<div>&nbsp;</div>
<div>
	<a href="action/consignatarios/nuevo" class="btn btn-primary">Nuevo consignatario</a>
</div>