<%@ include file="/WEB-INF/pages/include.jsp"%>

<h3>Predios</h3>

		<!-- Muestra los mensajes de validación -->
		<jsp:include page="../include_messages.jsp"/>

<c:if test="${listaPredios != null && empty listaPredios}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty listaPredios}">

	<table id="tablaPaginada" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Tipo</th>
				<th>Predio</th>
				<th>Propietario</th>
				<th>Teléfono</th>
				<th>E-mail</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaPredios}" var="predio" varStatus="status">
				<tr>
					<td>${predio.tipoPredio.teTipoPredio}</td>
					<td>${predio.planta.tePlanta} - ${predio.tePredio}</td>
					<td>${predio.terceroByCnPropietario.teNombre} ${predio.terceroByCnPropietario.teApellido1} ${predio.terceroByCnPropietario.teApellido2}</td>
					<td>
						${predio.terceroByCnPropietario.teTlfFijo}
						<c:if test="${not empty predio.terceroByCnPropietario.teTlfFijo and not empty predio.terceroByCnPropietario.teTlfMovil1}">/</c:if>
						${predio.terceroByCnPropietario.teTlfMovil1}
						<c:if test="${not empty predio.terceroByCnPropietario.teTlfMovil1 and not empty predio.terceroByCnPropietario.teTlfMovil2}">/</c:if>						
						${predio.terceroByCnPropietario.teTlfMovil2}
						
					</td>
					<td>${predio.terceroByCnPropietario.teEmail}</td>
					<td>
						<a href="action/predios/editar?id=${predio.cnPredio }">
							<i class="icon-edit"></i></a> &nbsp;
						<a href="action/predios/eliminar?id=${predio.cnPredio }">
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
	<a href="action/predios/nuevo" class="btn btn-primary">Nuevo predio</a>
</div>
 -->