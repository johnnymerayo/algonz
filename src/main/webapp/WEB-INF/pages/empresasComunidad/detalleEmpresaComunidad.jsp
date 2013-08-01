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
		<form:hidden path="cnEmpresaComunidad" />
		<form:hidden path="comunidad.cnComunidad" />

		<div style="width: 50%; float: left">
		
		
		<t:select itemLabel="teTipoEmpresa" itemValue="cnTipoEmpresa" items="${tiposEmpresaCombo}" path="empresa.tipoEmpresa.cnTipoEmpresa" required="true" label="Tipo Empresa" tabindex="1"/>
			
		<t:select itemLabel="teNombre" itemValue="cnEmpresa" items="${empresasCombo}" path="empresa.cnEmpresa" required="true" label="Empresa" tabindex="2"/>
			
    		
			<t:input path="feInicio" label="Fecha inicio" required="true" tabindex="3" date="true"/>
			<t:input path="feFin" label="Fecha fin" required="true" tabindex="4" date="true"/>
			<t:area path="teObservaciones" label="Observaciones" cols="500" tabindex="9"/>
		</div>
	</fieldset>
	
	
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
				<th>Fecha Inicio</th>
				<th>Fecha Vencimiento</th>
				<th>Fecha Cierre</th>
				<th>Estado </th>
				<th>Descripción</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${empresaComunidad.avisosEmpresa}" var="aviso" varStatus="status">
				<tr>
				
					<td><fmt:formatDate value="${aviso.feInicio}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${aviso.feVencimiento}" pattern="dd/MM/yyyy"/></td>
					<td><fmt:formatDate value="${aviso.feCierre}" pattern="dd/MM/yyyy"/></td>
					<td>
						${aviso.estado.teEstado}				
					</td>
					<td>${aviso.teDescripcion}</td>
					<td>
						<a href="action/avisosEmpresa/editar?id=${aviso.cnAvisoEmpresa }">
							<i class="icon-edit"></i></a> &nbsp;
						<a href="action/avisosEmpresa/eliminar?id=${aviso.cnAvisoEmpresa }">
							<i class="icon-remove"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
<div>&nbsp;</div>

<div>
	<a href="action/avisosEmpresa/nuevo?codEmpresaComunidad=${empresaComunidad.cnEmpresaComunidad}" class="btn btn-primary">Nuevo aviso</a>
</div>
		
		</fieldset>
	
	
<div>&nbsp;</div>

	
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
				<!-- <button type="button" class="btn"
					onclick="changeAction('mainForm','action/empresaComunidads/listado')">Cancelar</button> -->
					
				<a href="action/comunidades/editar?id=${empresaComunidad.comunidad.cnComunidad}" class="btn">Cancelar</a>
			</div>
		</div>
</form:form>



