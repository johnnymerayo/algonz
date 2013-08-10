<%@ include file="/WEB-INF/pages/include.jsp"%>


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="predio" method="POST" action="action/predios/guardar">
	<fieldset>
		<legend>
			Comunidad: ${predio.portal.comunidad.teNombre} 
			<br />
			Portal nº:	<c:if test='${predio.portal.teNombre!=null}'> ${predio.portal.teNombre}</c:if>
			<br />
			<c:if test='${predio.cnPredio==null}'>Nuevo Predio</c:if>
			<c:if test='${predio.cnPredio!=null}'>Datos Predio: ${predio.tePredio}
			</c:if>
		</legend>
		
		
		<!-- Muestra los mensajes de validación -->
		<jsp:include page="../include_messages.jsp"/>
		
		<form:hidden path="cnPredio" />
		<form:hidden path="portal.cnPortal" />
		<form:hidden path="terceroByCnPropietario.cnTercero" />
		<form:hidden path="terceroByCnConyuge.cnTercero" />
		<form:hidden path="terceroByCnInquilino.cnTercero" />

		<div style="width: 50%; float: left">


			<div class="control-group ${status.error ? 'error' : '' }">
				<label class="control-label" for="tipoPredio.cnTipoPredio">Tipo
					predio *</label>
				<div class="controls">
					<form:select path="tipoPredio.cnTipoPredio" cssClass="input-xlarge"
						id="tipoPredio.cnTipoPredio" tabindex="1">
						<form:option value="1" label="Piso" />
						<form:option value="2" label="Garaje" />
					</form:select>

					<c:if test="${status.error}">
						<span class="help-inline">${status.errorMessage}</span>
					</c:if>
				</div>
			</div>
			
			<t:select itemLabel="tePlanta" itemValue="cnPlanta" items="${plantasCombo}" path="planta.cnPlanta" required="true" label="Planta" emptyOption="true" tabindex="1"/>
			
			
			<t:input path="tePredio" label="Predio" required="false" maxlength="50" tabindex="3" />
			<t:input path="teTrastero" label="Trastero" required="false" maxlength="50" tabindex="4" />
			<t:input path="tePlaza" label="Plaza asociada" required="false" maxlength="50" tabindex="5" />
				
			<t:select itemLabel="teTipoRepresentante" itemValue="cnTipoRepresentante" items="${tiposRepresentanteCombo}" path="tipoRepresentante.cnTipoRepresentante" required="false" label="Tipo representante" emptyOption="true" tabindex="7"/>
			
			
			<t:area path="teObservaciones" label="Observaciones" cols="500"
				tabindex="9" />
		</div>
	</fieldset>



<ul id="tabMenu" class="nav nav-tabs">
  <li id="propietario-tab"><a href="#propietario" data-toggle="tab">Datos del propietario</a></li>
  <li id="conyuge-tab"><a href="#conyuge" data-toggle="tab">Datos del cónyuge</a></li>
  <li id="inquilino-tab"><a href="#inquilino" data-toggle="tab">Datos del inquilino</a></li></ul>



 <div id="tabContent" class="tab-content">
    <div id="propietario" class="tab-pane fade in active">

<t:input path="terceroByCnPropietario.caNif" label="NIF" required="false" maxlength="10" tabindex="8" />
<t:input path="terceroByCnPropietario.teNombre" label="Nombre" required="true" maxlength="100" tabindex="9" />
<t:input path="terceroByCnPropietario.teApellido1" label="Primer apellido" required="true" maxlength="50" tabindex="10" />
<t:input path="terceroByCnPropietario.teApellido2" label="Segundo apellido" required="false" maxlength="50" tabindex="11" />
<t:input path="terceroByCnPropietario.teTlfFijo" label="Teléfono" required="true" maxlength="50" tabindex="12" />
<t:input path="terceroByCnPropietario.teTlfMovil1" label="Móvil" required="false" maxlength="50" tabindex="13" />
<t:input path="terceroByCnPropietario.teTlfMovil2" label="Otro" required="false" maxlength="50" tabindex="14" />
<t:input path="terceroByCnPropietario.teEmail" label="E-mail" required="false" maxlength="50" tabindex="15" />
<t:input path="terceroByCnPropietario.nuCuentaCorriente" label="Cuenta corriente" maxlength="20" required="false" tabindex="16" />
<t:area path="terceroByCnPropietario.teDireccionSecundaria" label="Dirección secundaria" required="false" tabindex="18"/>

     
    </div>
    <div id="conyuge" class="tab-pane fade">

<t:input path="terceroByCnConyuge.caNif" label="NIF" required="false" maxlength="10" tabindex="19" />
<t:input path="terceroByCnConyuge.teNombre" label="Nombre" required="false" maxlength="100" tabindex="20" />
<t:input path="terceroByCnConyuge.teApellido1" label="Primer apellido" required="false" maxlength="50" tabindex="21" />
<t:input path="terceroByCnConyuge.teApellido2" label="Segundo apellido" required="false" maxlength="50" tabindex="22" />
<t:input path="terceroByCnConyuge.teTlfFijo" label="Teléfono" required="false" maxlength="50" tabindex="23" />
<t:input path="terceroByCnConyuge.teTlfMovil1" label="Móvil" required="false" maxlength="50" tabindex="24" />
<t:input path="terceroByCnConyuge.teTlfMovil2" label="Otro" required="false" maxlength="50" tabindex="25" />
<t:input path="terceroByCnConyuge.teEmail" label="E-mail" required="false" maxlength="50" tabindex="26" />
<t:input path="terceroByCnConyuge.nuCuentaCorriente" label="Cuenta corriente" required="false" maxlength="20" tabindex="27" />
<t:area path="terceroByCnConyuge.teDireccionSecundaria" label="Dirección secundaria" required="false" tabindex="29"/>

    </div>
    <div id="inquilino" class="tab-pane fade">

<t:input path="terceroByCnInquilino.caNif" label="NIF" required="false" maxlength="10" tabindex="30" />
<t:input path="terceroByCnInquilino.teNombre" label="Nombre" required="false" maxlength="100" tabindex="31" />
<t:input path="terceroByCnInquilino.teApellido1" label="Primer apellido" required="false" maxlength="50" tabindex="32" />
<t:input path="terceroByCnInquilino.teApellido2" label="Segundo apellido" required="false" maxlength="50" tabindex="33" />
<t:input path="terceroByCnInquilino.teTlfFijo" label="Teléfono" required="false" maxlength="50" tabindex="34" />
<t:input path="terceroByCnInquilino.teTlfMovil1" label="Móvil" required="false" maxlength="50" tabindex="35" />
<t:input path="terceroByCnInquilino.teTlfMovil2" label="Otro" required="false" maxlength="50" tabindex="36" />
<t:input path="terceroByCnInquilino.teEmail" label="E-mail" required="false" maxlength="50" tabindex="37" />
<t:input path="terceroByCnInquilino.nuCuentaCorriente" label="Cuenta corriente" maxlength="20" required="false" tabindex="38" />

    </div>
   
  </div> 

	
	<script>
  $(function () {
    $('#tabMenu a:first').tab('show');
  })
</script>




	<fieldset>
		<legend> Incidencias abiertas </legend>

		<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>

	</fieldset>


	<div class="control-group" style="clear: both">
		<div class="controls">
			<button type="submit" class="btn btn-primary">Guardar</button>
			<!--  <button type="button" class="btn"
				onclick="changeAction('mainForm','action/predios/listado')">Cancelar</button>  -->
				<a href="action/portales/editar?id=${predio.portal.cnPortal}" class="btn">Cancelar</a>
		</div>
	</div>
</form:form>



