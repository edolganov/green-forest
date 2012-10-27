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
    
    <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery.caret.js"></script>
    <script type="text/javascript" src="js/app.js"></script>
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h1 class="center">Green-Forest & Servlet/JSP & JDBC</h1>
		</div>
		
		<div id="content">
			<h2 class="center">Items from database</h2>
			<c:choose>
				<c:when test="${fn:length(page.list)>0}">
				
					<div class="items">
						<c:forEach var="item" items="${page.list}">
						
							<c:set var="renamedKey" value="doc.renamed.${item.id}"/>
							<c:set var="errorKey" value="doc.error.${item.id}"/>
							<c:set var="isRenamed" value="${!empty requestScope[renamedKey]}"/>
							<c:set var="hasError" value="${!empty requestScope[errorKey]}"/>
							
				     		<div class="item">
				     			<div class="item-text">${item.name}</div>
				     			<div class="item-form" style="display: none;">
				     				<form action="" method="post">
				     					<input type="text" name="name" class="item-input" />
				     					<input type="hidden" name="id" value="${item.id}"/>
				     					<input type="hidden" name="pageIndex" value="${page.index}"/>
				     					<input type="hidden" name="limit" value="${page.limit}"/>
				     					<input type="submit" style="display: none;"/>
				     				</form>
				     			</div>
				     			<div class="item-actions">
				     				<a href="javascript:" class="button-edit btn btn-info btn-mini" title="Edit"><i class="icon-white icon-pencil"></i></a>
				     				<a href="javascript:" class="button-confirm btn btn-success btn-mini" title="Confirm" style="display: none;"><i class="icon-white icon-ok"></i></a>
				     				<a href="javascript:" class="button-cancel btn btn-warning btn-mini" title="Cancel" style="display: none;"><i class="icon-white icon-remove"></i></a>
				     			</div>
				     			<div class="clear"></div>
				     		</div>
				     	</c:forEach>
			     	</div>
	
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
					<p class="center">Nothing was found <b>:(</b></p>
				</c:otherwise>
			</c:choose>
	     
		</div>

	</div>
	
</body>

</html>