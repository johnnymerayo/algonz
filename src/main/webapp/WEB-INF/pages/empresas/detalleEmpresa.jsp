<%@ include file="/WEB-INF/pages/include.jsp"%>


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="empresa" method="POST" action="action/empresas/guardar">
	<fieldset>
		<legend>
			<c:if test='${empresa.cnEmpresa==null}'>Nueva Empresa</c:if>
			<c:if test='${empresa.cnEmpresa!=null}'>Datos Empresa: ${empresa.teNombre}
			</c:if>
		</legend>
		<form:hidden path="cnEmpresa" />


<div class="row">
<t:select id="empresa" search="true" itemLabel="teTipoEmpresa" itemValue="cnTipoEmpresa" items="${tiposEmpresaCombo}" path="tipoEmpresa.cnTipoEmpresa" required="true" label="Tipo Empresa" emptyOption="true" tabindex="1" gridClass="col-lg-4"/>
</div>



<div class="row">
<t:input path="caCif" label="CIF" required="false" tabindex="2" maxlength="10" gridClass="col-lg-4"/>
<t:input path="teNombre" label="Nombre" required="true" tabindex="3" maxlength="100" gridClass="col-lg-4"/>
</div>


<div class="row">
<t:input path="teTlfFijo" label="Tel�fono Fijo" required="true" tabindex="4" maxlength="50" gridClass="col-lg-4"/>
<t:input path="teTlfMovil1" label="Tel�fono M�vil 1" required="false" tabindex="5" maxlength="50" gridClass="col-lg-4"/>
<t:input path="teTlfMovil2" label="Tel�fono Movil 2" required="false" tabindex="6" maxlength="50" gridClass="col-lg-4"/>
</div>


<div class="row">
<t:input path="teEmail" label="E-Mail" required="false" tabindex="7" maxlength="50" gridClass="col-lg-4"/>
<t:area path="teDireccion" label="Direcci�n" required="false" tabindex="8"  gridClass="col-lg-4"/>
</div>


<div class="row">
<t:area path="teObservaciones" label="Observaciones" cols="500" tabindex="9" gridClass="col-lg-12"/>
</div>


	</fieldset>
	
	
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
				<button type="button" class="btn btn-default"
					onclick="changeAction('mainForm','action/empresas/listado')">Cancelar</button>
			</div>
		</div>
	
	
<div>&nbsp;</div>
	
	<c:if test="${not empty empresa.cnEmpresa }">	
	
	
<fieldset>
	<legend> Incidencias abiertas </legend>
	
	
<div>&nbsp;</div>

	<c:if test="${listaActuaciones != null && empty listaActuaciones}">
		<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
	</c:if>


	<c:if test="${not empty listaActuaciones}">

		<table id="tablaPaginada_print" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Comunidad</th>
					<th>Portal</th>
					<th class="dateColumn">Fecha Inicio</th>
					<th class="dateColumn">Fecha Vencimiento</th>
					<th>Estado</th>
					<th>Descripci�n</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaActuaciones}" var="actuacion"
					varStatus="status">				
					<tr>
						<td>${actuacion.siniestro.portal.comunidad.teNombre}</td>
						<td>${actuacion.siniestro.portal.teNombre}</td>
						<td><fmt:formatDate value="${actuacion.feInicio}"
								pattern="dd/MM/yyyy" /></td>
						<td><fmt:formatDate value="${actuacion.feVencimiento}"
								pattern="dd/MM/yyyy" /></td>
						<td>${actuacion.estado.teEstado}</td>
						<td>${actuacion.teDescripcion}</td>
						<td><a
							href="action/actuaciones/editar?id=${actuacion.cnActuacion }">
								<i class="glyphicon glyphicon-edit"></i>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</c:if>

</fieldset>
<div>&nbsp;</div>




<fieldset>
	<legend> Listado de avisos </legend>


	<div>&nbsp;</div>

	<c:if
		test="${listaAvisosEmpresa != null && empty listaAvisosEmpresa}">
		<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
	</c:if>


	<c:if test="${not empty listaAvisosEmpresa}">

		<table id="tablaPaginada2_print" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Comunidad</th>
					<th class="dateColumn">Fecha Inicio</th>
					<th class="dateColumn">Fecha Vencimiento</th>
					<th>Estado</th>
					<th>Descripci�n</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaAvisosEmpresa}" var="aviso"
					varStatus="status">
										
					<tr>
						<td>${aviso.empresaComunidad.comunidad.teNombre}</td>
						<td><fmt:formatDate value="${aviso.feInicio}"
								pattern="dd/MM/yyyy" /></td>
						<td><fmt:formatDate value="${aviso.feVencimiento}"
								pattern="dd/MM/yyyy" /></td>
						<td>${aviso.estado.teEstado}</td>
						<td>${aviso.teDescripcion}</td>
						<td><a
							href="action/avisosEmpresa/editar?id=${aviso.cnAvisoEmpresa }">
								<i class="glyphicon glyphicon-edit"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</c:if>
	<div>&nbsp;</div>


</fieldset>
</c:if>
</form:form>



