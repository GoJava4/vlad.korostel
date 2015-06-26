<%--
  Created by IntelliJ IDEA.
  User: koros
  Date: 20.06.2015
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TITLE</title>
</head>
<body>
<%--@elvariable id="list" type="java.util.List<com.morkva.entities.Category>"--%>
<%--@elvariable id="quote" type="com.morkva.entities.Quote"--%>
<c:set var="qt" value="${quote}"/>
<h1>"<c:out value="${qt.value}"/>" - <c:out value="${qt.author}"/></h1>
<h1>Categories</h1>
<ul>
    <c:forEach var="category" items="${list}">
        <c:url value="/category" var="categoryURL">
            <c:param name="category-id" value="${category.id}"/>
        </c:url>
        <li>
            <h2>
                <a href="${categoryURL}">${category.name}</a>
            </h2>
        </li>
    </c:forEach>
</ul>
</body>
</html>
