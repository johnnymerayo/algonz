<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ include file="/WEB-INF/pages/include.jsp"%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy/MM/dd" var="hoy" />


<h3>Alarmas</h3>

<fieldset>
	<legend> Incidencias abiertas </legend>


<div>&nbsp;</div>

	<c:if test="${listaActuaciones != null && empty listaActuaciones}">
		<p class="text-info">NO SE HAN ENCONTRADO RESULTADOS</p>
	</c:if>


	<c:if test="${not empty listaActuaciones}">

		<table id="tablaAlarmas" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Comunidad</th>
					<th>Portal</th>
					<th>Empresa</th>
					<th class="dateColumn">Fecha Inicio</th>
					<th class="dateColumn">Fecha Vencimiento</th>
					<th>Estado</th>
					<th>Descripción</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaActuaciones}" var="actuacion"
					varStatus="status">
				
				
<fmt:formatDate value="${actuacion.feVencimiento}" pattern="yyyy/MM/dd" var="vencimiento" />
				
				
<%
String vencimiento = (String) pageContext.getAttribute("vencimiento");
Calendar c = Calendar.getInstance();
c.setTime(new Date (vencimiento)); 
c.add(Calendar.DATE, -5);  
pageContext.setAttribute("date", c.getTime());%>
<c:set var="date" value="${date}" />

<fmt:formatDate value="${date}" pattern="yyyy/MM/dd" var="before" />
				
				
										
					<tr class="				
<c:if test="${vencimiento lt hoy}">
 danger
</c:if>	
<c:if test="${vencimiento eq hoy}">
warning
</c:if>
<c:if test="${vencimiento gt hoy && hoy gt before }">
success
</c:if>
">
						<td>${actuacion.siniestro.portal.comunidad.teNombre}</td>
						<td>${actuacion.siniestro.portal.teNombre}</td>
						<td>${actuacion.siniestro.empresaComunidad.empresa.teNombre}</td>
						<td><fmt:formatDate value="${actuacion.feInicio}"
								pattern="dd/MM/yyyy" /></td>
						<td><fmt:formatDate value="${actuacion.feVencimiento}"
								pattern="dd/MM/yyyy" /></td>
						<td>${actuacion.estado.teEstado}</td>
						<td>${actuacion.teDescripcion}</td>
						<td><a
							href="action/actuaciones/editar?id=${actuacion.cnActuacion }">
								<i class="glyphicon glyphicon-edit"  title="Consultar"></i>
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

		<table id="tablaAlarmas2" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Comunidad</th>
					<th>Empresa</th>
					<th class="dateColumn">Fecha Inicio</th>
					<th class="dateColumn">Fecha Notificación</th>
					<th class="dateColumn">Fecha Vencimiento</th>
					<th>Estado</th>
					<th>Descripción</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listaAvisosEmpresa}" var="aviso"
					varStatus="status">
					
<fmt:formatDate value="${aviso.feLimite}" pattern="yyyy/MM/dd" var="vencimiento" />
								
												
<%
String vencimiento = (String) pageContext.getAttribute("vencimiento");
Calendar c = Calendar.getInstance();
c.setTime(new Date (vencimiento)); 
c.add(Calendar.DATE, -5);  
pageContext.setAttribute("date", c.getTime());%>
<c:set var="date" value="${date}" />

<fmt:formatDate value="${date}" pattern="yyyy/MM/dd" var="before" />
				
				
				
								
								
										
					<tr class="				
<c:if test="${vencimiento lt hoy}">
 danger
</c:if>	
<c:if test="${vencimiento eq hoy}">
warning
</c:if>
<c:if test="${vencimiento gt hoy && hoy gt before }">
success
</c:if>
">
						<td>${aviso.empresaComunidad.comunidad.teNombre}</td>
						<td>${aviso.empresaComunidad.empresa.teNombre}</td>
						<td><fmt:formatDate value="${aviso.feInicio}"
								pattern="dd/MM/yyyy" /></td>
						<td><fmt:formatDate value="${aviso.feLimite}"
								pattern="dd/MM/yyyy" /></td>
						<td><fmt:formatDate value="${aviso.feVencimiento}"
								pattern="dd/MM/yyyy" /></td>
						<td>${aviso.estado.teEstado}</td>
						<td>${aviso.teDescripcion}</td>
						<td><a
							href="action/avisosEmpresa/editar?id=${aviso.cnAvisoEmpresa }">
								<i class="glyphicon glyphicon-edit"  title="Consultar"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</c:if>
	<div>&nbsp;</div>


</fieldset>




