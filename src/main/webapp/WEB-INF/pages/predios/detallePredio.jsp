<%@ include file="/WEB-INF/pages/include.jsp"%>


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="predio" method="POST" action="action/predios/guardar">
	<fieldset>
		<legend>
			Comunidad: ${predio.portal.comunidad.teNombre} 
			<br />
			Portal:	<c:if test='${predio.portal.teNombre!=null}'> ${predio.portal.teNombre} - </c:if>
			<br />
			<c:if test='${predio.cnPredio==null}'>Nuevo Predio</c:if>
			<c:if test='${predio.cnPredio!=null}'>Datos Predio: ${predio.tePredio}
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
		
		
		<form:hidden path="cnPredio" />
		<form:hidden path="portal.cnPortal" />
		<form:hidden path="terceroByCnPropietario.cnTercero" />
		<form:hidden path="terceroByCnConyuge.cnTercero" />
		<form:hidden path="terceroByCnInquilino.cnTercero" />

		<div style="width: 50%; float: left">


			<div class="control-group ${status.error ? 'error' : '' }">
				<label class="control-label" for="tipoPredio.cnTipoPredio">Tipo
					predio</label>
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

			<div class="control-group ${status.error ? 'error' : '' }">
				<label class="control-label" for="planta.cnPlanta">Planta</label>
				<div class="controls">
					<form:select path="planta.cnPlanta" cssClass="input-xlarge"
						id="planta.cnPlanta" tabindex="2">
						<form:option value="1" label="1" />
						<form:option value="2" label="2" />
					</form:select>

					<c:if test="${status.error}">
						<span class="help-inline">${status.errorMessage}</span>
					</c:if>
				</div>
			</div>
			<t:input path="tePredio" label="Predio" required="false" tabindex="3" />
			<t:input path="teTrastero" label="Trastero" required="false" tabindex="4" />
			<t:input path="tePlaza" label="Plaza asociada" required="false" tabindex="5" />
			
			
			<div class="control-group ${status.error ? 'error' : '' }">
				<label class="control-label" for="tipoRepresentante.cnTipoRepresentante">Tipo
					representante</label>
				<div class="controls">
					<form:select path="tipoRepresentante.cnTipoRepresentante" cssClass="input-xlarge"
						id="tipoRepresentante.cnTipoRepresentante" tabindex="6">
						<form:option value="0" label="Seleccionar..." />
						<form:option value="1" label="Presidente" />
						<form:option value="2" label="Vicepresidente" />
					</form:select>

					<c:if test="${status.error}">
						<span class="help-inline">${status.errorMessage}</span>
					</c:if>
				</div>
			</div>
			
			<t:area path="teObservaciones" label="Observaciones" cols="500"
				tabindex="7" />
		</div>
	</fieldset>



<ul id="tabMenu" class="nav nav-tabs">
  <li id="propietario-tab"><a href="#propietario" data-toggle="tab">Datos del propietario</a></li>
  <li id="conyuge-tab"><a href="#conyuge" data-toggle="tab">Datos del cónyuge</a></li>
  <li id="inquilino-tab"><a href="#inquilino" data-toggle="tab">Datos del inquilino</a></li></ul>



 <div id="tabContent" class="tab-content">
    <div id="propietario" class="tab-pane fade in active">

<t:input path="terceroByCnPropietario.caNif" label="NIF" required="false" tabindex="8" />
<t:input path="terceroByCnPropietario.teNombre" label="Nombre" required="true" tabindex="9" />
<t:input path="terceroByCnPropietario.teApellido1" label="Primer apellido" required="true" tabindex="10" />
<t:input path="terceroByCnPropietario.teApellido2" label="Segundo apellido" required="false" tabindex="11" />
<t:input path="terceroByCnPropietario.teTlfFijo" label="Teléfono" required="true" tabindex="12" />
<t:input path="terceroByCnPropietario.teTlfMovil1" label="Móvil" required="false" tabindex="13" />
<t:input path="terceroByCnPropietario.teTlfMovil2" label="Otro" required="false" tabindex="14" />
<t:input path="terceroByCnPropietario.teEmail" label="E-mail" required="false" tabindex="15" />
<t:input path="terceroByCnPropietario.nuCuentaCorriente" label="Cuenta corriente" required="false" tabindex="16" />
<t:area path="terceroByCnPropietario.teDireccion" label="Dirección" required="false" tabindex="17"/>
<t:area path="terceroByCnPropietario.teDireccionSecundaria" label="Dirección secundaria" required="false" tabindex="18"/>

     
    </div>
    <div id="conyuge" class="tab-pane fade">

<t:input path="terceroByCnConyuge.caNif" label="NIF" required="false" tabindex="19" />
<t:input path="terceroByCnConyuge.teNombre" label="Nombre" required="false" tabindex="20" />
<t:input path="terceroByCnConyuge.teApellido1" label="Primer apellido" required="false" tabindex="21" />
<t:input path="terceroByCnConyuge.teApellido2" label="Segundo apellido" required="false" tabindex="22" />
<t:input path="terceroByCnConyuge.teTlfFijo" label="Teléfono" required="false" tabindex="23" />
<t:input path="terceroByCnConyuge.teTlfMovil1" label="Móvil" required="false" tabindex="24" />
<t:input path="terceroByCnConyuge.teTlfMovil2" label="Otro" required="false" tabindex="25" />
<t:input path="terceroByCnConyuge.teEmail" label="E-mail" required="false" tabindex="26" />
<t:input path="terceroByCnConyuge.nuCuentaCorriente" label="Cuenta corriente" required="false" tabindex="27" />
<t:area path="terceroByCnConyuge.teDireccion" label="Dirección" required="false" tabindex="28"/>
<t:area path="terceroByCnConyuge.teDireccionSecundaria" label="Dirección secundaria" required="false" tabindex="29"/>

    </div>
    <div id="inquilino" class="tab-pane fade">

<t:input path="terceroByCnInquilino.caNif" label="NIF" required="false" tabindex="30" />
<t:input path="terceroByCnInquilino.teNombre" label="Nombre" required="false" tabindex="31" />
<t:input path="terceroByCnInquilino.teApellido1" label="Primer apellido" required="false" tabindex="32" />
<t:input path="terceroByCnInquilino.teApellido2" label="Segundo apellido" required="false" tabindex="33" />
<t:input path="terceroByCnInquilino.teTlfFijo" label="Teléfono" required="false" tabindex="34" />
<t:input path="terceroByCnInquilino.teTlfMovil1" label="Móvil" required="false" tabindex="35" />
<t:input path="terceroByCnInquilino.teTlfMovil2" label="Otro" required="false" tabindex="36" />
<t:input path="terceroByCnInquilino.teEmail" label="E-mail" required="false" tabindex="37" />
<t:input path="terceroByCnInquilino.nuCuentaCorriente" label="Cuenta corriente" required="false" tabindex="38" />

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



