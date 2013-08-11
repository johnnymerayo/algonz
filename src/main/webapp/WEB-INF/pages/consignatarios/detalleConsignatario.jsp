<%@ include file="/WEB-INF/pages/include.jsp"%>


<form:form class=".form-horizontal" id="mainForm"
	modelAttribute="consignatario" method="POST"
	action="action/consignatarios/guardar">
	<fieldset>
		<legend>
			<c:if test='${consignatario.codigo==null}'>Nuevo Consignatario</c:if>
			<c:if test='${consignatario.codigo!=null}'>Datos Consignatario ${consignatario.nomFiscal}
			</c:if>
		</legend>
		<form:hidden path="codigo" />
		<form:hidden path="codEmpresa" />

		<div style="width:50%;float:left">
			<t:input path="nomFiscal" label="Nombre" required="true" />
			<t:input path="direccion" label="Direccion" required="false" />
			<t:input path="poblacion" label="Poblacion" required="false" />
			<t:input path="provincia" label="Provincia" required="false" />
			<t:input path="codPostal" label="C.P." required="false" />
			<t:input path="pais" label="Pais" required="false" />
		</div>
		<div style="width:50%;float:left">
			<t:input path="tipoDocumento" label="Tipo Documento" required="false" />
			<t:input path="documento" label="Documento" required="false" />
			<t:input path="eMail" label="e-mail" required="false" />
			<t:input path="teleflin1" label="Teléfono Fijo" required="false" />
			<t:input path="telefMovil" label="Teléfono Movil" required="false" />
		</div>
		<sec:authorize access="hasRole('ROLE_ADMIN')">aaa</sec:authorize>
		<sec:authorize access="hasRole('ROLE_USER')">bbb</sec:authorize>
		<div class="control-group" style="clear:both">
			<div class="controls">
				<button type="submit" class="btn btn-primary">Guardar</button>
				<button type="button" class="btn btn-default"
					onclick="changeAction('mainForm','action/consignatarios/listado')">Cancelar</button>
			</div>
		</div>
	</fieldset>
</form:form>

