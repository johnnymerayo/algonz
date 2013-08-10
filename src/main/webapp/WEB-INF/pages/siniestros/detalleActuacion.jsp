<%@ include file="/WEB-INF/pages/include.jsp"%>


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="actuacion" method="POST" action="action/actuaciones/guardar">
	<fieldset>
		<legend>
			Comunidad: ${actuacion.siniestro.portal.comunidad.teNombre} 
			<br />
			Portal nº:	<c:if test='${actuacion.siniestro.portal.teNombre!=null}'> ${actuacion.siniestro.portal.teNombre}</c:if>
			<br />
			Siniestro: ${actuacion.siniestro.teNombre} 
			<br />
			<c:if test='${actuacion.cnActuacion==null}'>Nueva Actuacion</c:if>
			<c:if test='${actuacion.cnActuacion!=null}'>Datos de la actuacion: ${actuacion.teDescripcion}
			</c:if>
		</legend>
		

		<!-- Muestra los mensajes de validación -->
		<jsp:include page="../include_messages.jsp"/>
		
		<form:hidden path="cnActuacion" />
		<form:hidden path="siniestro.cnSiniestro" />



		<div style="width: 100%; float: left">
		
   		
			
<t:select cssClass="col-lg-4" itemLabel="teEstado" itemValue="cnEstado" items="${estadosCombo}" path="estado.cnEstado" required="true" label="Estado" tabindex="1"/>


			<t:input cssClass="col-lg-4" path="feInicio" label="Fecha inicio" required="true" tabindex="2" maxlength="12" date="true"/>
			<t:input cssClass="col-lg-4" path="feVencimiento" label="Fecha vencimiento" required="true"  maxlength="12" tabindex="3" date="true"/>
			<t:input cssClass="col-lg-4" path="feCierre" label="Fecha cierre" required="false" maxlength="12" tabindex="4" date="true"/>			
			
    
			<t:input path="teNumeroExp" label="Nº Expediente" required="false" maxlength="50" tabindex="5"/>
			<t:input path="teDescripcion" label="Descripción" required="true"  maxlength="100" tabindex="5"/>
			<t:area path="teObservaciones" label="Observaciones" required="false" tabindex="6"/>
    
    
		</div>
		
	</fieldset>
	
	
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
					<a href="action/siniestros/editar?id=${actuacion.siniestro.cnSiniestro}" class="btn">Cancelar</a>
			</div>
		</div>
		
	
	<c:if test="${not empty actuacion.cnActuacion }">		
		
					
<fieldset>
		<legend>
		Documentos
		</legend>
		
		
<div>&nbsp;</div>



<c:if test="${actuacion.documentos != null && empty actuacion.documentos}">
	<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
</c:if>


<c:if test="${not empty actuacion.documentos}">

	
	<table id="tablaPaginada4" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Fecha</th>
				<th>Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${actuacion.documentos}" var="documento" varStatus="status">
				<tr>
				
					<td>${documento.teNombre}</td>
					<td><fmt:formatDate value="${documento.feGuardado}" pattern="dd/MM/yyyy"/></td>
					<td>
						<a href="action/actuaciones/downloadDocument?id=${documento.cnDocumento }">
							<i class="icon-download"></i></a> &nbsp;
						<a href="action/actuaciones/deleteDocument?codActuacion=${actuacion.cnActuacion }&amp;id=${documento.cnDocumento }">
							<i class="icon-remove"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>
<div>&nbsp;</div>



<div>
<span class="btn btn-success fileinput-button">
                    <i class="icon-plus icon-white"></i>
                    <span>Añadir documento</span>
                   <input id="fileupload" type="file" name="files[]" data-url="action/actuaciones/uploadDocument?codActuacion=${actuacion.cnActuacion }" multiple>
                </span>
	</div>

		</fieldset>
</c:if>
		
		
</form:form>



