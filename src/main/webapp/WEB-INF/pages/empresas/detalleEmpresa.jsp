<%@ include file="/WEB-INF/pages/include.jsp"%>


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="empresa" method="POST" action="action/empresas/guardar">
	<fieldset>
		<legend>
			<c:if test='${empresa.cnEmpresa==null}'>Nuevo Empresa</c:if>
			<c:if test='${empresa.cnEmpresa!=null}'>Datos Empresa: ${empresa.teNombre}
			</c:if>
		</legend>
		<form:hidden path="cnEmpresa" />

		<div style="width: 50%; float: left">
		
		
		 <div class="control-group ${status.error ? 'error' : '' }">
        <label class="control-label" for="tipoEmpresa.cnTipoEmpresa">Tipo empresa</label>
        <div class="controls">        
          <form:select path="tipoEmpresa.cnTipoEmpresa" cssClass="input-xlarge" id="tipoEmpresa.cnTipoEmpresa" tabindex="1" >
				<form:option value="0" label="Seleccionar..." />
				<form:option value="1" label="Ascensores" />
				<form:option value="2" label="Iluminarias" />
			</form:select>
          
          
            <c:if test="${status.error}">
                <span class="help-inline">${status.errorMessage}</span>
            </c:if>
        </div>
    </div>     
    
			<t:input path="teNombre" label="Nombre" required="true" tabindex="3"/>
			<t:input path="teTlfMovil1" label="Teléfono Móvil 1" required="false" tabindex="5"/>
			<t:area path="teDireccion" label="Dirección" required="false" tabindex="7"/>
    
    
		</div>
		<div style="width: 50%; float: left">
			<t:input path="caCif" label="CIF" required="true" tabindex="2"/>
			<t:input path="teTlfFijo" label="Teléfono Fijo" required="false" tabindex="4"/>
			<t:input path="teTlfMovil2" label="Teléfono Movil 2" required="false" tabindex="6"/>
			<t:input path="teEmail" label="E-Mail" required="false" tabindex="8"/>
		</div>

		<div style="width: 100%; float: none">
			<t:area path="teObservaciones" label="Observaciones" cols="500" tabindex="9"/>
		</div>
	</fieldset>
	
	
	<fieldset>
		<legend>
			Incidencias abiertas
		</legend>
		
		<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
		
		</fieldset>
	
	
		<div class="control-group" style="clear: both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
				<button type="button" class="btn"
					onclick="changeAction('mainForm','action/empresas/listado')">Cancelar</button>
			</div>
		</div>
</form:form>



