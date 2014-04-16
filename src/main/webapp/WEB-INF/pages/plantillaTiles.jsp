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

<style type="text/css" media="print">
    .NonPrintable
    {
      display: none;
    }
  </style>
  
<body>


<div class="navbar-wrapper">
  <div class="container">

    <div class="navbar navbar-inverse navbar-fixed-top">


	    <a class="navbar-toggle" data-toggle="collapse" data-target=".nav-collapse">
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	    </a>
        <a class="navbar-brand" href="#"> ALGONZ </a>
        <div class="nav-collapse collapse">
          <ul class="nav navbar-nav">	
          <li class="active"><a href="#"><i class="glyphicon glyphicon-home glyphicon-white"></i> Inicio</a></li>
            <!-- <li><a href="http://www.bootply.com" target="ext">About</a></li> -->
            <!-- <li><a href="#contact">Contact</a></li> -->
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
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="col col-lg-3">
			<div class="well sidebar-nav-fixed">
				<ul class="nav nav-list">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-header">Administración</li>			
						<!-- <li><a href="action/siniestros/listado">Siniestros</a></li>		
						<li><a href="action/portales/listado">Portales</a></li>					
						<li><a href="action/consignatarios/listado">Consignatarios</a></li> -->				
					</sec:authorize>					
					
						<li><a href="action/alarmas/listado">Alarmas</a></li>	
						<li><a href="action/comunidades/listado">Comunidades</a></li>	
						<li><a href="action/empresas/listado">Empresas</a></li>			
					</ul>
				</div>
			</div>
			<div class="col col-lg-8">
				<tiles:insertAttribute name="content" />
			</div>
		</div>
	</div>




</body>



  <!-- Modal  confirmar eliminar-->
  <div class="modal fade NonPrintable" id="modalDelete">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">Confirmar</h4>
        </div>
        <div class="modal-body">
        	<p>Está a punto de borrar el registro seleccionado. Este proceso es irreversible</p> 
        	<p>¿Desea continuar?</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	  <span id="delete-url">

        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->


<script>
$('.delete_row').click('show.bs.modal', function () {	
    $('#delete-url').html('<a href="' + $(this).attr('data-id') + '" class="btn btn-primary danger">Eliminar</a>');
});
</script>

</html>