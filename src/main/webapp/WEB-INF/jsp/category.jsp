<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CATEGORY ${category_id}</title>
</head>
<body>
<div class="container">
    <h1>CATEGORY</h1>
    <jsp:include page="menu.jsp"/>
    <h2>Category Name - ${category_name}</h2>

    <h2>Projects:</h2>
    <ul>
        <%--@elvariable id="projects" type="java.util.List<com.morkva.entities.Project>"--%>
        <c:forEach var="prject" items="${projects}">
            <c:url value="/project" var="projectURL">
                <c:param name="projectId" value="${prject.id}"/>
            </c:url>
            <li>
                <h2>
                    <a href="${projectURL}">${prject.name}</a>
                </h2>
                <br/>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
