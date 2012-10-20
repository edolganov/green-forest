<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/xml; charset=utf-8"/>
    <title>Green-Forest & Servlet/JSP & JDBC</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/ext.css" rel="stylesheet">
    
    <script type="text/javascript" src="js/jquery-1.4.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
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