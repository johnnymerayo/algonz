<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty message }">
	<div class="alert alert-success">${message}</div>
	<div>&nbsp;</div>
</c:if>
<c:if test="${not empty error }">
	<div class="alert alert-danger">${error }</div>
	<div>&nbsp;</div>
</c:if>