<%@ tag trimDirectiveWhitespaces="true" display-name="Pring Action's Trace" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags" %>

<%@ attribute name="trace" type="java.lang.Object" required="true"%>

<c:if test="${!empty trace}">
	<c:set var="traceType" value="${trace.simpleName}"/>
	<c:choose>
		<c:when test="${traceType eq 'Trace'}">
			<div class="trace-elem">
				<span class="trace">${trace.owner.name}</span>
				(total time: <span class="work-time">${trace.duration}ms</span>)
			</div>
		</c:when>
		<c:when test="${traceType eq 'TraceLevel'}">
			<div class="trace-elem">
				<span class="trace-level">sub invoke</span>
				(total time: <span class="work-time">${trace.duration}ms</span>)
			</div>
		</c:when>
		<c:otherwise>
			<div class="trace-elem trace-level-item">
				${trace.owner}
			</div>
		</c:otherwise>
	</c:choose>
	<c:if test="${fn:length(trace.children)>0}">
		<div class="trace-children">
			<c:forEach items="${trace.children}" var="child">
				<util:formatTrace trace="${child}"/>
			</c:forEach>
		</div>
	</c:if>
</c:if>
