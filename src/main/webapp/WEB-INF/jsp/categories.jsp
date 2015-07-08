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
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
</head>
<body>
<%--@elvariable id="list" type="java.util.List<com.morkva.entities.Category>"--%>
<%--@elvariable id="quote" type="com.morkva.entities.Quote"--%>
<c:set var="qt" value="${quote}"/>
<div class="container">
    <jsp:include page="menu.jsp"/>
    <c:import url="menu.jsp"
    <div class="panel panel-default">
        <h1>"<c:out value="${qt.value}"/>" - <c:out value="${qt.author}"/></h1>

        <h1>Categories</h1>
        <ul>
            <c:forEach var="category" items="${list}">
                <c:url value="/category/${category.id}" var="categoryURL"/>
                <li>
                    <h2>
                        <a href="${categoryURL}">${category.name}</a>
                    </h2>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
