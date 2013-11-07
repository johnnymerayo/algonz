<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://efsavage.com/twitter-bootstrap" prefix="bs"%>
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}
</style>
<body onload='document.f.j_username.focus();'>




	<form name='f' action="<c:url value='/j_spring_security_check' />"
		method='POST' class="form-signin">
		<h2 class="form-signin-heading text-center">Plataforma ALGONZ</h2>
		<p class="text-center">
			<em>v1.0</em>
		</p>
		<c:if test="${not empty error}">
			<div class="errorblock">
				<p class="text-error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
			</div>
		</c:if>
		<div class="form-group">
			<label class="control-label" for="j_username">Usuario</label>
			<div class="controls">
				<input type='text' name='j_username' value='' id="j_username"
					placeholder="Usuario" class="form-control input-block-level">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label" for="j_password">Password</label>
			<div class="controls">
				<input type="password" name="j_password" id="j_password"
					placeholder="Password" class="form-control input-block-level">
			</div>
		</div>
		<div class="form-group">
			<div class="controls">

				<bs:button type="submit" value="Entrar" text="Entrar"
					cssClass="btn btn-primary" />
				<bs:button type="reset" value="Limpiar" text="Limpiar" />
			</div>
		</div>
	</form>	
	

</body>
