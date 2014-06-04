<%@ include file="/WEB-INF/pages/include.jsp"%>

<h3>Comunidades</h3>

		<!-- Muestra los mensajes de validación -->
		<jsp:include page="../include_messages.jsp"/>
		
		
		
		

	<ul id="tabMenuComunidades" class="nav nav-tabs">
		<li id="mis-comunidades-tab"><a href="#misComunidades" data-toggle="tab">Mis
				Comunidades</a></li>
		<li id="todos-comunidades-tab"><a href="#todasComunidades"
			data-toggle="tab">Todas las Comunidades</a></li>
	</ul>


	<div>&nbsp;</div>

	<div id="tabContent" class="tab-content">
		<div id="misComunidades" class="tab-pane fade in active">

		

<c:if test="${listaComunidadesUsuario != null && empty listaComunidadesUsuario}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty listaComunidadesUsuario}">

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
			<c:forEach items="${listaComunidadesUsuario}" var="comunidad" varStatus="status">
				<tr>
				
					<td>${comunidad.caCif}</td>
					<td>${comunidad.teNombre}</td>
					<td>${comunidad.teCp}</td>
					<td>
						<a href="action/comunidades/editar?id=${comunidad.cnComunidad }">
							<i class="glyphicon glyphicon-edit"  title="Consultar"></i></a> &nbsp;
						<a data-toggle="modal" href="#modalDelete" class="delete_row" data-id="action/comunidades/eliminar?id=${comunidad.cnComunidad }">
							<i class="glyphicon glyphicon-remove"  title="Eliminar"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>


		</div>
		<div id="todasComunidades" class="tab-pane fade">
		
		

<c:if test="${listaComunidades != null && empty listaComunidades}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty listaComunidades}">

	<table id="tablaPaginada2" class="table table-striped table-bordered">
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
							<i class="glyphicon glyphicon-edit"  title="Consultar"></i></a> &nbsp;
						<a data-toggle="modal" href="#modalDelete" class="delete_row" data-id="action/comunidades/eliminar?id=${comunidad.cnComunidad }">
							<i class="glyphicon glyphicon-remove"  title="Eliminar"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>

		</div>

	</div>
		




<div>&nbsp;</div>
<div>
	<a href="action/comunidades/nuevo" class="btn btn-primary">Nueva comunidad</a>
</div>




<script>
	$(function() {
		$('#tabMenuComunidades a:first').tab('show');
	})
</script>