<%@tag description="Extended input tag to allow for sophisticated errors" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="path" required="true" type="java.lang.String"%>
<%@attribute name="id" required="false" type="java.lang.String"%>
<%@attribute name="cssClass" required="false" type="java.lang.String"%>
<%@attribute name="label" required="false" type="java.lang.String"%>
<%@attribute name="maxlength" required="false" type="java.lang.String"%>
<%@attribute name="required" required="false" type="java.lang.Boolean"%>
<%@attribute name="tabindex" required="false" type="java.lang.String"%>
<%@attribute name="readonly" required="false" type="java.lang.Boolean"%>
<%@attribute name="date" required="false" type="java.lang.Boolean"%>

<c:if test="${empty label}">
    <c:set var="label" value="${fn:toUpperCase(fn:substring(path, 0, 1))}${fn:toLowerCase(fn:substring(path, 1,fn:length(path)))}" />
</c:if>
<spring:bind path="${path}">
    <div class="control-group ${status.error ? 'error' : '' }">
        <label class="control-label" for="${path}">${label}<c:if test="${required}">&nbsp;<span class="required">*</span></c:if></label>
        <div class="controls">
        <c:if test="${not empty date and date==true }">
        <div class="input-append date" id="${empty id?path:id }" data-date="${path}" data-date-format="dd-mm-yyyy">
        </c:if>
            <form:input path="${path}" cssClass="${empty cssClass ? 'input-xlarge' : cssClass}" id="${empty id?path:id }" maxlength="${maxlength!=null?maxlength:'' }" tabindex="${empty tabindex?'':tabindex }" readonly="${empty readonly?false:readonly }"/>
            <c:if test="${not empty date and date==true }">
            <span class="add-on"><i class="icon-calendar"></i></span></div>
            </c:if>
            <c:if test="${status.error}">
                <span class="help-inline">${status.errorMessage}</span>
            </c:if>
        </div>
    </div>
     <c:if test="${not empty date and date==true }">
		<script>
		$('#<c:out value="${empty id?path:id }"/>').datepicker({
			format: 'dd/mm/yyyy'
		});
		</script>
	</c:if>
</spring:bind>
