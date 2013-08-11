<%@ include file="/WEB-INF/pages/include.jsp"%>

<h3>Empresas</h3>


		<!-- Muestra los mensajes de validación -->
		<jsp:include page="../include_messages.jsp"/>


<c:if test="${listaEmpresas != null && empty listaEmpresas}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty listaEmpresas}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Tipo</th>
				<th>CIF</th>
				<th>Nombre</th>
				<th>Teléfono</th>
				<th>E-mail</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaEmpresas}" var="empresa" varStatus="status">
				<tr>
					<td>${empresa.tipoEmpresa.teTipoEmpresa}</td>
					<td>${empresa.caCif}</td>
					<td>${empresa.teNombre}</td>
					<td>
						${empresa.teTlfFijo}
						<c:if test="${not empty empresa.teTlfFijo and not empty empresa.teTlfMovil1}">/</c:if>
						${empresa.teTlfMovil1}
						<c:if test="${not empty empresa.teTlfMovil1 and not empty empresa.teTlfMovil2}">/</c:if>						
						${empresa.teTlfMovil2}
						
					</td>
					<td>${empresa.teEmail}</td>
					<td>
						<a href="action/empresas/editar?id=${empresa.cnEmpresa }">
							<i class="glyphicon glyphicon-edit"></i></a> &nbsp;
						<a href="action/empresas/eliminar?id=${empresa.cnEmpresa }">
							<i class="glyphicon glyphicon-remove"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
<div>&nbsp;</div>
<div>
	<a href="action/empresas/nuevo" class="btn btn-primary">Nueva empresa</a>
</div>