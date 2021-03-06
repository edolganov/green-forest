<%@ page contentType="text/html;charset=UTF-8" session="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/xml; charset=utf-8"/>
    <title>${label.title}</title>
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
			<h1 class="center">${label.title}</h1>
		</div>
		
		<div id="content">
			<h2 class="center">Items from database</h2>
			<c:choose>
				<c:when test="${fn:length(page.list)>0}">
				
					<div class="items">
						<c:forEach var="item" items="${page.list}">
						
							<c:set var="renamedKey" value="doc.renamed.${item.id}"/>
							<c:set var="errorKey" value="doc.error-key.${item.id}"/>
							<c:set var="errorObjKey" value="doc.error-obj.${item.id}"/>
							<c:set var="newNameKey" value="doc.newName.${item.id}"/>
							
							<c:set var="isRenamed" value="${!empty requestScope[renamedKey]}"/>
							<c:set var="hasError" value="${!empty requestScope[errorKey]}"/>
							<c:set var="exception" value="${requestScope[errorObjKey]}"/>
							
							<div class="msg-wrap <c:if test='${hasError}'>error-wrap</c:if> <c:if test='${isRenamed}'>updated-wrap</c:if>">
					     		<div class="item">
					     			<div class="item-text" <c:if test='${hasError}'>style="display: none;"</c:if>>${item.name}</div>
					     			<div class="item-form" <c:if test='${!hasError}'>style="display: none;"</c:if>>
					     				<form action="" method="post">
					     					<input type="text" name="name" class="item-input" <c:if test='${hasError}'>value="${requestScope[newNameKey]}"</c:if> />
					     					<input type="hidden" name="id" value="${item.id}"/>
					     					<input type="hidden" name="pageIndex" value="${page.index}"/>
					     					<input type="hidden" name="limit" value="${page.limit}"/>
					     					<input type="submit" class="submit" style="display: none;"/>
					     				</form>
					     			</div>
					     			<div class="item-actions">
					     				<a href="javascript:" class="button-edit btn btn-info btn-mini" title="Edit" 
					     					<c:if test='${hasError}'>style="display: none;"</c:if>><i class="icon-white icon-pencil"></i></a>
					     				<a href="javascript:" class="button-confirm btn btn-success btn-mini" title="Confirm" 
					     					<c:if test='${!hasError}'>style="display: none;"</c:if>><i class="icon-white icon-ok"></i></a>
					     				<a href="javascript:" class="button-cancel btn btn-warning btn-mini" title="Cancel" 
					     					<c:if test='${!hasError}'>style="display: none;"</c:if>><i class="icon-white icon-remove"></i></a>
					     			</div>
					     			<div class="clear"></div>
					     		</div>
					     		<c:if test="${hasError}">
					     			<div class="msg-label">
					     				<c:choose>
					     					<c:when test="${requestScope[errorKey] eq 'DocEmptyNameException'}">
					     						must not be empty
					     					</c:when>
					     					<c:when test="${requestScope[errorKey] eq 'DocToLongNameException'}">
					     						max lenght is ${exception.maxSize}
					     					</c:when>
					     					<c:otherwise>
					     						unknown error
					     					</c:otherwise>
					     				</c:choose>
					     			</div>
					     		</c:if>
					     		<c:if test="${isRenamed}">
					     			<div class="msg-label">edited</div>
					     		</c:if>
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
		
		<div id="debug-block">
			<h2 class="center">Debug information</h2>
			<c:if test="${!empty updateTrace}">
				<div class="trace-title">Update item trace</div>
				<util:formatTrace trace="${updateTrace}"/>
			</c:if>
			<c:if test="${!empty selectTrace}">
				<div class="trace-title">Select items trace</div>
				<util:formatTrace trace="${selectTrace}"/>
			</c:if>
		</div>
		
		<div class="clear"></div>
		

	</div>
	
</body>

</html>