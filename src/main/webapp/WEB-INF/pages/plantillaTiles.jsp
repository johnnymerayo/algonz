<%@ include file="/WEB-INF/pages/include.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>

<head>
<tiles:insertAttribute name="header" />
</head>
<style>
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

.sidebar-nav-fixed {
	position: fixed;
	top: 60px;
	width: 20%;
}

@media ( max-width : 767px) {
	.sidebar-nav-fixed {
		width: auto;
	}
}

@media ( max-width : 979px) {
	.sidebar-nav-fixed {
		position: static;
		width: auto;
	}
}
</style>
<body>



	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="brand" href="#">ALGONZ</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						
						<li class="active">
							<a href="#"><i class="icon-home icon-white"></i> Inicio</a>
						</li>
						
						<!-- <li><a href="#about">Acerca de...</a></li> -->
						
						<!-- <li><a href="#contact">Contacto</a></li>  -->
						
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-user icon-white"></i>	${usuario.idSistema } <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/j_spring_security_logout" />">Salir</a></li>
							</ul>
						</li>
						
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				<div class="well sidebar-nav-fixed">
					<ul class="nav nav-list">
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li class="nav-header">Administración</li>			
						<!-- <li><a href="action/siniestros/listado">Siniestros</a></li>		
						<li><a href="action/portales/listado">Portales</a></li>					
						<li><a href="action/consignatarios/listado">Consignatarios</a></li> -->				
					</sec:authorize>					
					
						<li><a href="action/comunidades/listado">Comunidades</a></li>	
						<li><a href="action/empresas/listado">Empresas</a></li>			
					</ul>
				</div>
			</div>
			<div class="span9">
				<tiles:insertAttribute name="content" />
			</div>
		</div>
	</div>


</body>

</html>