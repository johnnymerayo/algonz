<%@ include file="/WEB-INF/pages/include.jsp"%>


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="portal" method="POST" action="action/portales/guardar">
	<fieldset>
		<legend>
		
			Comunidad:  ${portal.comunidad.teNombre}
			<br/>
			<c:if test='${portal.cnPortal==null}'>Nuevo Portal</c:if>
			<c:if test='${portal.cnPortal!=null}'>
			Datos del portal nº: ${portal.teNombre}
			</c:if>
		</legend>
		
<c:if test="${not empty message }">
	<div class="text-success">${message}</div>
	<div>&nbsp;</div>
</c:if>
<c:if test="${not empty error }">
	<div class="text-error"><h5>${error }</h5></div>
	<div>&nbsp;</div>
</c:if>

		<form:hidden path="cnPortal" />
		<form:hidden path="comunidad.cnComunidad" />


		<div style="width: 100%; float: left">
		
		
			<t:input path="teCalle" label="Calle" required="false" tabindex="1"/>
			<t:input path="teNombre" label="Número" required="true" tabindex="2"/>
			<t:area path="teObservaciones" label="Observaciones" required="false" tabindex="3"/>
    
    
		</div>
		
	</fieldset>
	
	
	<fieldset>
		<legend>
		Predios
		</legend>
		
		
<div>&nbsp;</div>
		
<c:if test="${portal.predios != null && empty portal.predios}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty portal.predios}">

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
			<c:forEach items="${portal.predios}" var="predio" varStatus="status">
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

<div>
	<a href="action/predios/nuevoPredio?codPortal=${portal.cnPortal}" class="btn btn-primary">Nuevo predio</a>
</div>

		
		</fieldset>
	
		
	<fieldset>
		<legend>
		Siniestros
		</legend>
		
		<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
		
		</fieldset>
		
	
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
				<!-- <button type="button" class="btn" onclick="changeAction('mainForm','action/comunidades/editar?id=${portal.comunidad.cnComunidad}')">Cancelar</button>  -->
				<a href="action/comunidades/editar?id=${portal.comunidad.cnComunidad}" class="btn">Cancelar</a>
			</div>
		</div>
</form:form>



