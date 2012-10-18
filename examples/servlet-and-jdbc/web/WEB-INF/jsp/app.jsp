<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/xml; charset=utf-8"/>
    <title>Green-Forest & Servlet/JSP & JDBC</title>
</head>
<body>

	<div id="content">
	     <c:if test="${fn:length(page.list)>0}">
	     	<c:forEach var="item" items="${page.list}">
	     		<div>${item.name}</div>
	     	</c:forEach>
	     </c:if>
	</div>
	
</body>

</html>