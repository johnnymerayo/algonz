<%@ include file="/WEB-INF/pages/include.jsp"%>

<h3>Portales</h3>

<c:if test="${not empty message }">
	<div class="text-success">${message}</div>
	<div>&nbsp;</div>
</c:if>
<c:if test="${not empty error }">
	<div class="text-error"><h5>${error }</h5></div>
	<div>&nbsp;</div>
</c:if>


<c:if test="${listaPortales != null && empty listaPortales}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty listaPortales}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Comunidad</th>
				<th>Calle</th>
				<th>Nombre</th>
				<th>Código postal</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaPortales}" var="portal" varStatus="status">
				<tr>
				
					<td>${portal.comunidad.teNombre}</td>
					<td>${portal.teCalle}</td>
					<td>${portal.teNombre}</td>
					<td>${portal.comunidad.teCp}</td>
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
<div>&nbsp;</div>
<!--
<div>
	<a href="action/portales/nuevo" class="btn btn-primary">Nuevo portal</a>
</div>
-->