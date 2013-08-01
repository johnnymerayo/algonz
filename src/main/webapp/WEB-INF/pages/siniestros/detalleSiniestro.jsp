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
		<form:hidden path="cnSiniestro" />
		<form:hidden path="portal.cnPortal" />

		<div style="width: 50%; float: left">

    
    	<t:select itemLabel="teTipoEmpresa" itemValue="cnTipoEmpresa" items="${tiposEmpresaCombo}" path="cnTipoSiniestro" required="true" label="Tipo siniestro" tabindex="1"/>
			
		<t:select itemLabel="teNombre" itemValue="cnEmpresa" items="${empresasCombo}" path="empresaComunidad.empresa.cnEmpresa" required="true" label="Empresa" tabindex="2"/>
				
			<t:input path="teNombre" label="Descripción" required="true" tabindex="3"/>
		
			<t:area path="teObservaciones" label="Observaciones" cols="500" tabindex="9"/>
		</div>
	</fieldset>
	
	
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
							<i class="icon-edit"></i></a> &nbsp;
						<a href="action/actuaciones/eliminar?id=${actuacion.cnActuacion }">
							<i class="icon-remove"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
<div>&nbsp;</div>

<div>
	<a href="action/actuaciones/nuevo?codSiniestro=${siniestro.cnSiniestro}" class="btn btn-primary">Nueva actuación</a>
</div>
		
		</fieldset>
	
	
<div>&nbsp;</div>

	
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
				<button type="button" class="btn"
					onclick="changeAction('mainForm','action/siniestros/listado')">Cancelar</button>
			</div>
		</div>
</form:form>



