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
			<c:choose>
				<c:when test="${fn:length(page.list)>0}">
				
					<c:forEach var="item" items="${page.list}">
			     		<div>${item.name}</div>
			     	</c:forEach>
	
			     	<c:set var="firstPage" value="${page.index == 0}"/>
			     	<c:set var="lastPage" value="${ ((page.index*page.limit)+page.limit) >= page.count}"/>
			     	
					<ul class="pager">
					  <li class="<c:if test='${firstPage}'>disabled</c:if>">
					  	<c:choose>
					  		<c:when test="${!firstPage}">
							  	<a href="?pageIndex=${page.index-1}&limit=${page.limit}">
							  		Previous
							  	</a>
					  		</c:when>
					  		<c:otherwise>
					  			<span>Previous</span>
					  		</c:otherwise>
					  	</c:choose>
	
					  </li>
					  <li class="<c:if test='${lastPage}'>disabled</c:if>">
					  	<c:choose>
					  		<c:when test="${!lastPage}">
							  	<a href="?pageIndex=${page.index+1}&limit=${page.limit}">
							  		Next
							  	</a>
					  		</c:when>
					  		<c:otherwise>
					  			<span>Next</span>
					  		</c:otherwise>
					  	</c:choose>
	
					  </li>
					</ul>
				</c:when>
				<c:otherwise>
					<h2 class="center">Nothing was found <b>:(</b></h2>
				</c:otherwise>
			</c:choose>
	     
		</div>

	</div>
	
</body>

</html>