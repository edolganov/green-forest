<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/xml; charset=utf-8"/>
    <title>Green-Forest & Servlet/JSP & JDBC</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/app.css" rel="stylesheet">
    <link href="css/ext.css" rel="stylesheet">
    
    <script type="text/javascript" src="js/jquery-1.4.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h1>Green-Forest & Servlet/JSP & JDBC</h1>
		</div>
		
		<div id="content">
		
		     <c:if test="${fn:length(page.list)>0}">
		     
		     
		     	<c:forEach var="item" items="${page.list}">
		     		<div>${item.name}</div>
		     	</c:forEach>
		     	
				<ul class="pager">
				  <li class="disabled"><a href="#">Previous</a></li>
				  <li><a href="#">Next</a></li>
				</ul>
				
		     </c:if>
	     
		</div>

	</div>
	
</body>

</html>