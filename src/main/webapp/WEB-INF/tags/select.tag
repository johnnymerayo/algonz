<%@tag description="Extended textarea tag to allow for sophisticated errors" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="path" required="true" type="java.lang.String"%>
<%@attribute name="id" required="false" type="java.lang.String"%>
<%@attribute name="cssClass" required="false" type="java.lang.String"%>
<%@attribute name="label" required="false" type="java.lang.String"%>
<%@attribute name="disabled" required="false" type="java.lang.Boolean"%>
<%@attribute name="required" required="false" type="java.lang.Boolean"%>
<%@attribute name="tabindex" required="false" type="java.lang.String"%>
<%@attribute name="items" required="true" type="java.util.Collection"%>
<%@attribute name="itemLabel" required="true" type="java.lang.String"%>
<%@attribute name="itemValue" required="true" type="java.lang.String"%>

<c:if test="${empty label}">
    <c:set var="label" value="${fn:toUpperCase(fn:substring(path, 0, 1))}${fn:toLowerCase(fn:substring(path, 1,fn:length(path)))}" />
</c:if>
<spring:bind path="${path}">
    <div class="control-group ${status.error ? 'error' : '' }">
        <label class="control-label" for="${path}">${label}<c:if test="${required}">&nbsp;<span class="required">*</span></c:if></label>
        <div class="controls">        
            <form:select path="${path}" required="${empty required?false:required }" cssClass="${empty cssClass ? 'input-xlarge' : cssClass}" id="${empty id?path:id }" tabindex="${empty tabindex?'':tabindex }" disabled="${empty disabled?false:disabled }">
            <form:options items="${items }" itemLabel="${itemLabel }" itemValue="${itemValue }"/>
            </form:select>
            <c:if test="${status.error}">
                <span class="help-inline">${status.errorMessage}</span>
            </c:if>
        </div>
    </div>     
</spring:bind>
