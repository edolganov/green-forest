<%@ tag display-name="Base layout" pageEncoding="UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="title" fragment="true" required="false" %>
<%@ attribute name="body" fragment="true" required="false" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/xml; charset=utf-8"/>
    <title>
        <c:if test="${!empty title}">
            <jsp:invoke fragment="title"/>
        </c:if>
    </title>
</head>
<body>

 <div id="content" class="cleared">
     <c:if test="${!empty body}">
         <jsp:invoke fragment="body"/>
     </c:if>
 </div>
</body>

</html>