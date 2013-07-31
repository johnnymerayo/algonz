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
		
		
		 <div class="control-group ${status.error ? 'error' : '' }">
        <label class="control-label" for="tipoSiniestro.cnTipoSiniestro">Tipo siniestro</label>
        <div class="controls">        
          <form:select path="cnTipoSiniestro" cssClass="input-xlarge" id="tipoSiniestro.cnTipoSiniestro" tabindex="1" >
				<form:option value="0" label="Seleccionar..." />
				<form:option value="1" label="Ascensores" />
				<form:option value="2" label="Iluminarias" />
			</form:select>
          
          
            <c:if test="${status.error}">
                <span class="help-inline">${status.errorMessage}</span>
            </c:if>
        </div>
    </div>     
    
    		
		 <div class="control-group ${status.error ? 'error' : '' }">
        <label class="control-label" for="empresaComunidad.empresa.cnEmpresa">Tipo siniestro</label>
        <div class="controls">        
          <form:select path="empresaComunidad.cnEmpresaComunidad" cssClass="input-xlarge" id="empresaComunidad.empresa.cnEmpresa" tabindex="1" >
				<form:option value="0" label="Seleccionar..." />
				<form:option value="1" label="ThyssenGroup" />
				<form:option value="2" label="Luces Juan" />
			</form:select>
          
          
            <c:if test="${status.error}">
                <span class="help-inline">${status.errorMessage}</span>
            </c:if>
        </div>
    </div>     
    
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
					<td>${actuacion.feInicio}</td>
					<td>${actuacion.feVencimiento}</td>
					<td>${actuacion.feCierre}</td>
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



