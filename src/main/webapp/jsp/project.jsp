<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%--@elvariable id="project" type="com.morkva.entities.Project"--%>
<c:set var="current_project" value="${project}"/>
<h1>Project - ${current_project.name}</h1>
<h3>${current_project.shortDescr}</h3>
<h3>${current_project.needMoney}</h3>
<h3>${current_project.currentMoney}</h3>
<h3>${current_project.daysLeft}</h3>
<h3>${current_project.history}</h3>
<h3>${current_project.urlVideo}</h3>
<br/>
<form action="<c:url value="/project"/>" method="post">
    <label>
        Doanate:
        <input type="text" name="donate-count"/>
    </label>

    <input type="submit" value="DONATE!">
</form>
</body>
</html>
