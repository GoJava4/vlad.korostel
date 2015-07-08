<%--@elvariable id="qt" type="com.morkva.entities.Quote"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-default">
    <div class="container-fluid">
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <c:set var="css" value="active"/>
                <c:choose>
                    <c:when test="${param.activeLink == \"categories\"}">
                        <c:set var="cssLink1" value="${css}"/>
                    </c:when>
                    <c:when test="${param.activeLink == \"contacts\"}">
                        <c:set var="cssLink2" value="${css}"/>
                    </c:when>
                </c:choose>
                <li class="${cssLink1}"><a href="<c:url value="/categories"/>">Home</a></li>
                <li class="dropdown" >
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">Categories<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <c:forEach var="category" items="${applicationScope.categories}">
                            <c:url value="/category/${category.id}" var="categoryURL"/>
                            <li>
                                    <a href="${categoryURL}">${category.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="${cssLink2}"><a href="<c:url value="/contacts"/>">Contacts</a></li>
                <li><a href="#">Link 3</a></li>
            </ul>
        </div>
    </div>
</div>